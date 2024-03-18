package model;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;

/**
 *
 * @author tlasz
 */
public class Monster extends Character{

    Timer timer;
    private static int passedTime = 0;
    
    public Monster(int x, int y, int size, Image img) {
        super(x, y, size, img);
        direction = getRandomDirection();
        timer = new Timer(10, new TimerListener());
        timer.start();
    }
    
   
    
    
    private static Direction getRandomDirection(){
        Random random = new Random();
        int randomNumber = random.nextInt(4);
        switch (randomNumber){
            case 0:
                return Direction.DOWN;
            case 1:
                return Direction.LEFT;
            case 2:
                return Direction.RIGHT;
            case 3:
                return Direction.UP;
        }
        return null;
    }
    
    class TimerListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            passedTime++;
            if(!move()){
                direction = getRandomDirection();
            }
            else if(passedTime == 500){
                direction = getRandomDirection();
                passedTime = 0;
            }
            else{
                move();
            }
        }
            
    }
    
}
