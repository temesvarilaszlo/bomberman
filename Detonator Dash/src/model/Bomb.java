package model;

import java.awt.Image;
import javax.swing.Timer;

/**
 *
 * @author tlasz
 */
public class Bomb extends Block{
    private final Timer timer;
    private final int explosionDelay;
    private boolean readyToExplode;
    private int range;
    
    public Bomb(int x, int y, int size, Image img) {
        super(x, y, size, img);
        explosionDelay = 3000;
        readyToExplode = false;
        range = 2;
        
        timer = new Timer(explosionDelay, (e) -> {
            explode();
        });
        timer.start();
    }
    
    public void explode(){
        readyToExplode = true;
        timer.stop();
    }

    public boolean isReadyToExplode() {
        return readyToExplode;
    }
    
    
}
