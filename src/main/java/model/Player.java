package model;

import utilz.Images;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

    public Player(int x, int y, int size, Image img, GameEngine game, int[] controls) {
        super(x, y, size, img);
        bombRange = 2;
        bombCapacity = 1;
        gameEngine = game;
        
        placedBombs = new ArrayList<>();
        placedBoxes = new ArrayList<>();
        powerups = new ArrayList<>();
        this.controls = new ArrayList<>();
        for (int ctrl : controls) {
            this.controls.add(ctrl);
        }
    }
    
    
    //GETTERS -------------------------------
    public ArrayList<Bomb> getPlacedBombs() {
        return placedBombs;
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

    public Bomb getLastPlacedBomb() {
        return placedBombs.isEmpty() ? null : placedBombs.get(placedBombs.size() - 1);
    }

    public Box getLastPlacedBox() {
        return placedBoxes.isEmpty() ? null : placedBoxes.get(placedBoxes.size() - 1);
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
            // if the player is on their own placed bomb or box, don't check collision for bomb
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

    /**
     * Player places boxes
     */
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
     * Decides if a player is on a placed block by them or not
     * @param placedBlock Block
     * @return boolean
     */
    public boolean isOnPlacedBlock(Block placedBlock) {
        if (placedBlock == null) {
            return false;
        }
        //Bomb lastBomb = placedBombs.get(placedBombs.size()-1);
        Rectangle player = new Rectangle(x, y, size, size);
        Rectangle placedBlockHitBox = new Rectangle(placedBlock.x, placedBlock.y, placedBlock.size, placedBlock.size);

        return placedBlockHitBox.intersects(player);
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
    private void ghostPowerup(){
        if(img == Images.player1Img){
            img = Images.ghost1Img;
        }
        else if(img == Images.player2Img){
            img = Images.ghost2Img;
        }
        else if(img == Images.player3Img){
            img = Images.ghost3Img;
        }
        Timer timer = new Timer(500, new ActionListener() {
            int elapsedTime = 0;
            int timerDelay = 200;
            int max = 8000;
            boolean switchImage = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTime += timerDelay;

                if (elapsedTime >= max) {
                    Timer s = (Timer) e.getSource();
                    removeFromPowerups("G");
                    s.stop();
                    if (!powerups.contains("G") && !canMoveHere(x + 1, y, size, size, GameEngine.mapString) &&
                            !canMoveHere(x - 1, y, size, size, GameEngine.mapString) &&
                            !canMoveHere(x, y + 1, size, size, GameEngine.mapString) &&
                            !canMoveHere(x, y - 1, size, size, GameEngine.mapString) && !powerups.contains("I")) {
                        isAlive = false;
                    }
                } else if (elapsedTime >= max - 1800) { // Last
                    switchImage = !switchImage;
                    if(img == Images.ghost1Img || img == Images.player1Img) {
                        if(switchImage){
                            img = Images.player1Img;
                        }
                        else{
                            img = Images.ghost1Img;
                        }
                    }

                    if(img == Images.ghost2Img || img == Images.player2Img) {
                        if(switchImage){
                            img = Images.player2Img;
                        }
                        else{
                            img = Images.ghost2Img;
                        }
                    }

                    if(img == Images.ghost3Img|| img == Images.player3Img) {
                        if(switchImage){
                            img = Images.player3Img;
                        }
                        else{
                            img = Images.ghost3Img;
                        }
                    }
                }
            }
        });
        timer.start();
    }

    /**
     * Invincibility power up
     */
    private void invincibilityPowerup(){
        if(img == Images.player1Img){
            img = Images.player1Inv;
        }
        else if(img == Images.player2Img){
            img = Images.player2Inv;
        }
        else if(img == Images.player3Img){
            img = Images.player3Inv;
        }
        Timer timer = new Timer(500, new ActionListener() {
            int elapsedTime = 0;
            int timerDelay = 200;
            int max = 4000;
            boolean switchImage = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTime += timerDelay;

                if (elapsedTime >= max) {
                    Timer s = (Timer) e.getSource();
                    removeFromPowerups("I");
                    s.stop();
                } else if (elapsedTime >= max - 1400) { // Last
                    switchImage = !switchImage;
                    if(img == Images.player1Inv || img == Images.player1Img) {
                        if(switchImage){
                            img = Images.player1Img;
                        }
                        else{
                            img = Images.player1Inv;
                        }
                    }

                    if(img == Images.player2Inv || img == Images.player2Img) {
                        if(switchImage){
                            img = Images.player2Img;
                        }
                        else{
                            img = Images.player2Inv;
                        }
                    }

                    if(img == Images.player3Inv|| img == Images.player3Img) {
                        if(switchImage){
                            img = Images.player3Img;
                        }
                        else{
                            img = Images.player3Inv;
                        }
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
     * @param powerup String
     */
    public void powerupChooser(String powerup){
        switch (powerup) {
            case "D" -> {
                if (!powerups.contains(powerup)){
                    this.powerups.add(powerup);
                }
            }
            case "G" -> {
                if (!powerups.contains(powerup)){
                    this.powerups.add(powerup);
                }
                ghostPowerup();
            }
            case "I" -> {
                if (!powerups.contains(powerup)){
                    this.powerups.add(powerup);
                }
                invincibilityPowerup();
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
     * @param name String
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
