package model;

import assets.Images;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;
import view.GamePanel;
import static model.HelpMethods.canMoveHere;

/**
 *
 * @author tlasz
 */
public class Player extends Character {

    private int bombRange;
    private int bombCapacity;
    private final GameEngine gameEngine;
    
    private final ArrayList<Bomb> placedBombs;
    private final ArrayList<String> powerups;
    private final ArrayList<Integer> controls;

    public Player(int x, int y, int size, Image img, GameEngine game, int[] ctrls) {
        super(x, y, size, img);
        bombRange = 2;
        bombCapacity = 3;
        gameEngine = game;
        
        placedBombs = new ArrayList<>();
        powerups = new ArrayList<>();
        controls = new ArrayList<>();
        for (int i = 0; i < ctrls.length; i++) {
            controls.add(ctrls[i]);
        }
    }

    public ArrayList<Bomb> getPlacedBombs() {
        return placedBombs;
    }

    public ArrayList<Integer> getControls() {
        return controls;
    }

    @Override
    public boolean move() {
        if (direction == Direction.STOPPED || !isAlive) {
            return false;
        }

        float xSpeed = direction.x * speed;
        float ySpeed = direction.y * speed;

        // if the player is on their own placed bomb, don't check collision for bomb
        if(!powerups.contains("G")){
            if (isOnPlacedBlock(getLastPlacedBomb())) {
                if (canMoveHere(x + xSpeed, y + ySpeed, size, size, GameEngine.mapString, getLastPlacedBomb())) {
                    x += xSpeed;
                    y += ySpeed;
                    return true;
                }
                return false;
            }

            for (Player p : gameEngine.getPlayers()) {
                if (p != this && this.collidesWith(p)) {
                    return false;
                }
            }

            if (canMoveHere(x + xSpeed, y + ySpeed, size, size, GameEngine.mapString)) {
                x += xSpeed;
                y += ySpeed;
                return true;
            }
            return false;
        }
        else{
            if (x + xSpeed >= 0 && x + xSpeed <= (GamePanel.BLOCK_PIXEL_SIZE * GamePanel.MAP_SIZE) - size && y + ySpeed >= 0 && y + ySpeed <= (GamePanel.BLOCK_PIXEL_SIZE * GamePanel.MAP_SIZE) - size) {
                x += xSpeed;
                y += ySpeed;
                return true;  
            }
            return false;
        }
        
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
        if (placedBombs.isEmpty()) {
            return null;
        }
        return placedBombs.get(placedBombs.size() - 1);
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
     * Removes the power up given in the parameter from the powerups arraylist
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
                        !canMoveHere(x, y - 1, size, size, GameEngine.mapString)) {
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
                this.powerups.add(powerup);
            }
            case "G" -> {
                ghostPowerup();
                this.powerups.add(powerup);
            }
            case "I" -> {
                
            }
            case "O" -> {
                
            }
            case "PB" -> {
                increaseBombCapacity();
            }
            case "PR" -> {
                increaseBombRange();
            }
            case "S" -> {
                increaseSpeed();
            }
            default -> throw new AssertionError();
        }
    }
}
