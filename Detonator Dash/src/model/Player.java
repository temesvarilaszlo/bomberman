package model;

import assets.Images;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import static model.HelpMethods.canMoveHere;
import view.GamePanel;

/**
 *
 * @author tlasz
 */
public class Player extends Character{
    private ArrayList<Bomb> placedBombs;
    private int bombCapacity;
    
    public Player(int x, int y, int size, Image img) {
        super(x, y, size, img);
        placedBombs = new ArrayList<>();
        bombCapacity = 3;
    }
    
    @Override
    public boolean move(){
        if(direction == Direction.STOPPED)
            return false;
        
        float xSpeed = direction.x * speed;
        float ySpeed = direction.y * speed;
        
        // if the player is on their own placed bomb, don't check collision for bomb
        if (isOnPlacedBlock(getLastPlacedBomb())){
            if(canMoveHere(x+xSpeed, y+ySpeed, size, size, GameEngine.mapString, "W", "B")){
                x += xSpeed;
                y += ySpeed;
                return true;
            }
            return false;
        }
        
        if(canMoveHere(x+xSpeed, y+ySpeed, size, size, GameEngine.mapString)){
            x += xSpeed;
            y += ySpeed;
            return true;
        }
        return false;
    }
    
    public void placeBomb(){
        if (placedBombs.size() == bombCapacity || isOnPlacedBlock(getLastPlacedBomb())){
            return;
        }
        
        if (!GameEngine.mapString[currentMatrixPosition().x][currentMatrixPosition().y].equals("Bomb")){
            // assign bomb to player
            placedBombs.add(new Bomb(currentMatrixPosition().y * GamePanel.BLOCK_PIXEL_SIZE, currentMatrixPosition().x * GamePanel.BLOCK_PIXEL_SIZE,
                    GamePanel.BLOCK_PIXEL_SIZE,Images.bombImg));

            // add to mapString for collision checking
            GameEngine.mapString[currentMatrixPosition().x][currentMatrixPosition().y] = "Bomb";
            System.out.println("Bomba lerak " + (bombCapacity - placedBombs.size()));
        }
    }

    public ArrayList<Bomb> getPlacedBombs() {
        return placedBombs;
    }
    
    public boolean isOnPlacedBlock(Block placedBlock){
        if (placedBlock == null) return false;
//        Bomb lastBomb = placedBombs.get(placedBombs.size()-1);
        Rectangle player = new Rectangle(x, y, size, size);
        Rectangle placedBlockHitbox = new Rectangle(placedBlock.x, placedBlock.y, placedBlock.size, placedBlock.size);
        
        return placedBlockHitbox.intersects(player);
    }
    
    public Bomb getLastPlacedBomb(){
        if (placedBombs.size() == 0) return null;
        return placedBombs.get(placedBombs.size() - 1);
    }
}
