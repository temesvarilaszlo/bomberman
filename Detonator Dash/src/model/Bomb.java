package model;

import java.awt.Image;
import javax.swing.Timer;

/**
 *
 * @author tlasz
 */
public class Bomb extends Block{
    private Timer timer;
    private final int explosionDelay;
    private boolean readyToExplode;
    
    public Bomb(int x, int y, int size, Image img) {
        super(x, y, size, img);
        explosionDelay = 3000;
        readyToExplode = false;
        System.out.println(explosionDelay);
        
        timer = new Timer(explosionDelay, (e) -> {
            readyToExplode = true;
            timer.stop();
        });
        timer.start();
    }
    
    public void explode(){
        readyToExplode = false;
    }

    public boolean isReadyToExplode() {
        return readyToExplode;
    }
    
    
}
