package model;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import static model.HelpMethods.canMoveHere;

/**
 *
 * @author tlasz
 */
public class Monster extends Character{

    Timer timer;
    TimerListener listener;
    private static int passedTime = 0;
    
    public Monster(int x, int y, int size, Image img) {
        super(x, y, size, img);
        speed = Speed.SLOW.speed;
        direction = getRandomDirection();
        listener = new TimerListener();
        timer = new Timer(new Random().nextInt(2000, 10000), listener);
        timer.start();
    }
    
   
    
    
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
        return null;
    }
    
    @Override
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
            /*do {
                direction = getRandomDirection();
            } while (!canMoveHere(x+direction.x, y+direction.y, size, size, GameEngine.mapString));
            */
            direction = getRandomDirection();
            timer.stop();
            timer = new Timer(new Random().nextInt(2000, 10000), listener);
            timer.start();
            //passedTime++;
//            if(!move()){
//                direction = getRandomDirection();
//            }
//            else if(passedTime == 500){
//                direction = getRandomDirection();
//                passedTime = 0;
//            }
//            else{
//                move();
//            }
        }
            
    }
    
}
