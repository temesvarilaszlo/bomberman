package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import view.GamePanel;

public class Sprite {
    protected int x;
    protected int y;
    protected int size;
    protected Image img;

    protected Sprite(int x, int y, int size, Image img) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.img = img;
    }
    
    /**
     * Draws the Sprite
     * @param g Graphics2D
     */
    public void draw(Graphics2D g){
        g.drawImage(img, x, y, size, size, null);
    }
    
    /**
     * Return a Sprite's current positions in the matrix
     * @return Point
     */
    public Point currentMatrixPosition(){
        return new Point((y + size / 2) / GamePanel.BLOCK_PIXEL_SIZE, (x + size / 2) / GamePanel.BLOCK_PIXEL_SIZE);
    }
    
    /**
     * Checking collision with another character
     * @param c Character
     * @return boolean
     */
    public boolean collidesWith(Character c){
        if (!c.isAlive) return false;
        Rectangle sprite = new Rectangle(x, y, size, size);
        
        Rectangle otherCharacter = new Rectangle(c.x,c.y,c.size,c.size);
        
        return sprite.intersects(otherCharacter);
    }
}
