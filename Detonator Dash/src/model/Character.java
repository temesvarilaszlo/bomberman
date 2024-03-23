package model;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import static model.HelpMethods.canMoveHere;
import view.GamePanel;

public class Character extends Sprite{
    protected float speed;
    protected Direction direction;
    protected boolean isAlive;

    public Character(int x, int y, int size, Image img) {
        super(x, y, size, img);
        isAlive = true;
        speed = Speed.NORMAL.speed;
        direction = Direction.STOPPED;
    }
    
    public boolean move(){
        if(direction == Direction.STOPPED)
            return false;
        
        float xSpeed = direction.x * speed;
        float ySpeed = direction.y * speed;
        
        if(canMoveHere(x+xSpeed, y+ySpeed, size, size, GameEngine.mapString)){
            x += xSpeed;
            y += ySpeed;
            return true;
        }
        return false;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    public boolean collidesWith(Character c){
        Rectangle character = new Rectangle(x, y,size,size);
        if (this.getClass().equals(c.getClass())) {
            character = new Rectangle(x + direction.x,y + direction.y,size,size);
        }
        Rectangle otherCharacter = new Rectangle(c.x,c.y,c.size,c.size);
        
        return character.intersects(otherCharacter);
    }
}
