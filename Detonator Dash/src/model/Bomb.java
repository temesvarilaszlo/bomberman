package model;

import java.awt.Image;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author tlasz
 */
public class Bomb extends Block{
    private Timer timer;
    private final int explosionDelay;
    private boolean readyToExplode;
    private boolean isExplosionOver;
    private int range;
    private ArrayList<Fire> fires;
    private int currWave;
    
    public Bomb(int x, int y, int size, Image img) {
        super(x, y, size, img);
        explosionDelay = 3000;
        readyToExplode = false;
        isExplosionOver = false;
        range = 2;
        currWave = 1;
        
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

    public boolean isExplosionOver() {
        return isExplosionOver;
    }

    public void setReadyToExplode(boolean readyToExplode) {
        this.readyToExplode = readyToExplode;
    }

    public int getRange() {
        return range;
    }

    public ArrayList<Fire> getFires() {
        return fires;
    }

    public void setFires(ArrayList<Fire> fires) {
        this.fires = fires;
    }
    
    public void explosion(){
        fires.get(0).isActive = true;
        timer = new Timer(200, (e) -> {
            for (Fire f : fires){
                f.isActive = f.wave <= currWave && currWave <= range;
            }
            if (currWave > range){
                timer.stop();
                isExplosionOver = true;
            }
            currWave++;
        });
        timer.start();
    }
}
