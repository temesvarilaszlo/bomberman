package model;

import assets.Images;
import java.awt.Image;
import java.util.ArrayList;
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
        bombCapacity = 2;
    }
    
    public void placeBomb(){
        if (placedBombs.size() == bombCapacity){
            return;
        }
        
        if (!GameEngine.mapString[currentMatrixPosition().x][currentMatrixPosition().y].equals("Bomb")){
            // assign bomb to player
            placedBombs.add(new Bomb(currentMatrixPosition().x * GamePanel.BLOCK_PIXEL_SIZE, currentMatrixPosition().y * GamePanel.BLOCK_PIXEL_SIZE,
                    GamePanel.BLOCK_PIXEL_SIZE,Images.bombImg));

            // add to mapString for collision checking
            GameEngine.mapString[currentMatrixPosition().x][currentMatrixPosition().y] = "Bomb";
            System.out.println("Bomba lerak " + (bombCapacity - placedBombs.size()));
        }
    }

    public ArrayList<Bomb> getPlacedBombs() {
        return placedBombs;
    }
    
    
}
