package model;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import static model.HelpMethods.canMoveHere;

public class Monster extends Character{

    private Timer timer;
    private TimerListener listener;
    private GameEngine gameEngine;
    
    public Monster(int x, int y, int size, Image img, GameEngine game) {
        super(x, y, size, img);
        speed = Speed.SLOW.speed;
        direction = getRandomDirection();
        listener = new TimerListener();
        timer = new Timer(new Random().nextInt(2000, 10000), listener);
        timer.start();
        gameEngine = game;
        
    }
    
    /**
     * Chooses a random direction for the monster
     * @return 
     */
    private static Direction getRandomDirection(){
        Random random = new Random();
        int randomNumber = random.nextInt(4);
        switch (randomNumber){
            case 0 -> {
                return Direction.DOWN;
            }
            case 1 -> {
                return Direction.LEFT;
            }
            case 2 -> {
                return Direction.RIGHT;
            }
            case 3 -> {
                return Direction.UP;
            }
        }
        return Direction.STOPPED;
    }
    
    @Override
    public boolean move(){
        if(direction == Direction.STOPPED || !this.isAlive)
            return false;
        
        for(Monster m : gameEngine.getMonsters()){
            if (this != m && collidesWith(m)) {
                m.direction = Direction.oppositeDirection(m.direction);
                this.direction = Direction.oppositeDirection(this.direction);
            }
        }
        
        if(canMoveHere(x+direction.x, y+direction.y, size, size, GameEngine.mapString)){
            x += direction.x;
            y += direction.y;
            return true;
        }
        else{
            do {
                direction = getRandomDirection();
            } while (!canMoveHere(x+direction.x, y+direction.y, size, size, GameEngine.mapString));
        }
        return false;
    }
    
    class TimerListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            direction = getRandomDirection();
            timer.stop();
            timer = new Timer(new Random().nextInt(4000, 12000), listener);
            timer.start();
        }
            
    }
    
}
