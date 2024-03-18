package model;

import java.awt.Image;
import static model.HelpMethods.canMoveHere;

public class Character extends Sprite{
    protected float speed;
    protected Direction direction;
    protected boolean isAlive;

    public Character(int x, int y, int size, Image img) {
        super(x, y, size, img);
        isAlive = true;
        speed = Speed.FAST.speed;
        direction = Direction.STOPPED;
    }
    
    public boolean move(){
        if(direction == Direction.STOPPED)
            return false;
        
        //temp variables to store y and x speed
        float xSpeed = 0, ySpeed = 0;
        
        if(direction == Direction.UP)
            ySpeed = -speed;
        else if(direction == Direction.DOWN)
            ySpeed = speed;
        else if(direction == Direction.LEFT)
            xSpeed = -speed;
        else if(direction == Direction.RIGHT)
            xSpeed = speed;
        
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
}
