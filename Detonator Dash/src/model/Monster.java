package model;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.TimerTask;
import javax.swing.Timer;
import static model.HelpMethods.canMoveHere;
import static model.GameEngine.mapString;
import view.GamePanel;

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
            move();
        }
            
    }
    
}
