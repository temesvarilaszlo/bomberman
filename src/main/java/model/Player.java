package model;

import assets.Images;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.Timer;
import view.GamePanel;
import static model.HelpMethods.canMoveHere;

public class Player extends Character {

    private int bombRange;
    private int bombCapacity;
    private final GameEngine gameEngine;
    
    private final ArrayList<Bomb> placedBombs;
    private final ArrayList<Box> placedBoxes;
    private final ArrayList<String> powerups;
    private final ArrayList<Integer> controls;

    public Player(int x, int y, int size, Image img, GameEngine game, int[] ctrls) {
        super(x, y, size, img);
        bombRange = 2;
        bombCapacity = 1;
        gameEngine = game;
        
        placedBombs = new ArrayList<>();
        placedBoxes = new ArrayList<>();
        powerups = new ArrayList<>();
        controls = new ArrayList<>();
        for (int i = 0; i < ctrls.length; i++) {
            controls.add(ctrls[i]);
        }
    }
    
    
    //GETTERS -------------------------------
    public ArrayList<Bomb> getPlacedBombs() {
        return placedBombs;
    }

    public ArrayList<Box> getPlacedBoxes() {
        return placedBoxes;
    }

    public ArrayList<String> getPowerups() {
        return powerups;
    }

    public ArrayList<Integer> getControls() {
        return controls;
    }

    public int getBombRange() {
        return bombRange;
    }

    public int getBombCapacity() {
        return bombCapacity;
    }

    @Override
    public boolean move() {
        if (direction == Direction.STOPPED || !isAlive)
            return false;

        float xSpeed = direction.x * speed;
        float ySpeed = direction.y * speed;

        if(!powerups.contains("G")){
            for (Player p : gameEngine.getPlayers()) {
                if (p != this && this.collidesWith(p)) {
                    return false;
                }
            }
            // if the player is on their own placed bomb, don't check collision for bomb
            if ((isOnPlacedBlock(getLastPlacedBomb()) && canMoveHere(x + xSpeed, y + ySpeed, size, size, GameEngine.mapString, getLastPlacedBomb()))
                || (isOnPlacedBlock(getLastPlacedBox()) && canMoveHere(x + xSpeed, y + ySpeed, size, size, GameEngine.mapString, getLastPlacedBox()))
                || canMoveHere(x + xSpeed, y + ySpeed, size, size, GameEngine.mapString)
            ) {
                x += xSpeed;
                y += ySpeed;
                return true;
            }
        }
        else{
            if (x + xSpeed >= 0 && x + xSpeed <= (GamePanel.BLOCK_PIXEL_SIZE * GamePanel.MAP_SIZE) - size && y + ySpeed >= 0 && y + ySpeed <= (GamePanel.BLOCK_PIXEL_SIZE * GamePanel.MAP_SIZE) - size) {
                x += xSpeed;
                y += ySpeed;
                return true;
            }
        }
        return false;
    }

    /**
     * Player places bombs
     */
    public void placeBomb() {
        if (isOnPlacedBlock(getLastPlacedBomb())) {
            return;
        }
        boolean hasDetonator = powerups.contains("D");
        if (!isAlive || placedBombs.size() == bombCapacity){
            if (hasDetonator){
                for (Bomb b : placedBombs){
                    b.explode();
                }
            }
            return;
        }
        
        if (GameEngine.mapString[currentMatrixPosition().x][currentMatrixPosition().y].equals("P")){
            // assign bomb to player
            placedBombs.add(new Bomb(currentMatrixPosition().y * GamePanel.BLOCK_PIXEL_SIZE, currentMatrixPosition().x * GamePanel.BLOCK_PIXEL_SIZE,
                    GamePanel.BLOCK_PIXEL_SIZE, Images.bombImg, bombRange, !hasDetonator));

            // add to mapString for collision checking
            GameEngine.mapString[currentMatrixPosition().x][currentMatrixPosition().y] = "Bomb";
        }
    }

    public void placeBox() {
        if (isOnPlacedBlock(getLastPlacedBox())) {
            return;
        }

        if (powerups.contains("O") && GameEngine.mapString[currentMatrixPosition().x][currentMatrixPosition().y].equals("P")){
            // assign bomb to player
            placedBoxes.add(new Box(currentMatrixPosition().y * GamePanel.BLOCK_PIXEL_SIZE, currentMatrixPosition().x * GamePanel.BLOCK_PIXEL_SIZE,
                    GamePanel.BLOCK_PIXEL_SIZE, Images.boxImg, false));

            // add to mapString for collision checking
            GameEngine.mapString[currentMatrixPosition().x][currentMatrixPosition().y] = "B";
            gameEngine.getGameMap()[currentMatrixPosition().x][currentMatrixPosition().y] = new Box(currentMatrixPosition().y * GamePanel.BLOCK_PIXEL_SIZE, currentMatrixPosition().x * GamePanel.BLOCK_PIXEL_SIZE,
                    GamePanel.BLOCK_PIXEL_SIZE, Images.boxImg, false);
            removeFromPowerups("O");
        }

    }

    /**
     * Decides if a player is on a placed bomb by them or not
     *
     * @param placedBlock
     * @return
     */
    public boolean isOnPlacedBlock(Block placedBlock) {
        if (placedBlock == null) {
            return false;
        }
        //Bomb lastBomb = placedBombs.get(placedBombs.size()-1);
        Rectangle player = new Rectangle(x, y, size, size);
        Rectangle placedBlockHitbox = new Rectangle(placedBlock.x, placedBlock.y, placedBlock.size, placedBlock.size);

        return placedBlockHitbox.intersects(player);
    }

    /**
     * Returns the last placed bomb by a player
     *
     * @return
     */
    public Bomb getLastPlacedBomb() {
        return placedBombs.isEmpty() ? null : placedBombs.get(placedBombs.size() - 1);
    }

    public Box getLastPlacedBox() {
        if (placedBoxes.isEmpty()) {
            return null;
        }
        return placedBoxes.get(placedBoxes.size() - 1);
    }

    /**
     * Increases player's bomb's range
     */
    private void increaseBombRange(){
        bombRange++;
    }
    
    /**
     * Increases player's bomb capacity
     */
    private void increaseBombCapacity(){
        bombCapacity++;
    }
    
    /**
     * Ghost power up
     */
    public void ghostPowerup(){
        Timer timer = new Timer(3000, new ActionListener() {
            int elapsedTime = 0;
            int timerDelay = 500;
            int max = 5000;
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTime += timerDelay;
                
                if (elapsedTime >= max) {
                    Timer s = (Timer)e.getSource();
                    removeFromPowerups("G");
                    s.stop();
                    if (!powerups.contains("G") && !canMoveHere(x + 1, y, size, size, GameEngine.mapString) &&
                        !canMoveHere(x - 1, y, size, size, GameEngine.mapString) &&
                        !canMoveHere(x, y + 1, size, size, GameEngine.mapString) &&
                        !canMoveHere(x, y - 1, size, size, GameEngine.mapString) && !powerups.contains("I")) {
                            isAlive = false;
                        }
                }     
            }
        });
        timer.start();     
    }
    
    /**
     * Increases player's speed if not increased before
     */
    private void increaseSpeed(){
        if(this.speed == Speed.FAST.speed) return;
        this.speed = Speed.FAST.speed;
    }
    
    /**
     * Chooses a power up for the player
     * @param powerup 
     */
    public void powerupChooser(String powerup){
        switch (powerup) {
            case "D" -> {
                if (!powerups.contains(powerup)){
                    this.powerups.add(powerup);
                }
            }
            case "G" -> {
                ghostPowerup();
                this.powerups.add(powerup);
            }
            case "I" -> {
                this.powerups.add(powerup);
                ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
                executor.schedule(() -> {
                    removeFromPowerups("I");
                    executor.shutdown();
                }, 10, TimeUnit.SECONDS);
            }
            case "O" -> {
                for (int i = 0; i < 3; i++) {
                    this.powerups.add(powerup);
                }
            }
            case "PB" -> {
                increaseBombCapacity();
            }
            case "PR" -> {
                increaseBombRange();
            }
            case "S" -> {
                if (!powerups.contains(powerup)){
                    this.powerups.add(powerup);
                }
                increaseSpeed();
            }
            default -> throw new AssertionError();
        }
    }
    
       
    /**
     * Removes the power up given in the parameter from the "powerups" arraylist
     * @param name 
     */
    public void removeFromPowerups(String name){
        for(int i = 0; i < powerups.size();i++){
               if (powerups.get(i).equals(name)) {
                   powerups.remove(i);
                   break;
               }
           } 
    }
}
