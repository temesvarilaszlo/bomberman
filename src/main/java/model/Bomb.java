package model;

import java.awt.Image;
import java.util.ArrayList;
import javax.swing.Timer;

public class Bomb extends Block{
    private Timer timer;
    private final int explosionDelay;
    private boolean readyToExplode;
    private boolean isExplosionOver;
    private final int range;
    private ArrayList<Fire> fires;
    private int currWave;
    
    public Bomb(int x, int y, int size, Image img, int range, boolean isDetonated) {
        super(x, y, size, img);
        explosionDelay = 3000;
        readyToExplode = false;
        isExplosionOver = false;
        this.range = range;
        currWave = 1;
        
        if (isDetonated){
            startTimer();
        }
    }

    private void startTimer() {
        timer = new Timer(explosionDelay, (e) -> explode());
        timer.start();
    }

    public void explode(){
        readyToExplode = true;
        if (timer != null){
            timer.stop();
        }
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

    /**
     * Makes the bomb's explosion
     */
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
