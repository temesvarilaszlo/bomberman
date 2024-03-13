package model;

import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author tlasz
 */
public class Player extends Character{
    private ArrayList<Bomb> bombs;
    
    public Player(int x, int y, int size, Image img) {
        super(x, y, size, img);
        bombs = new ArrayList<>();
    }
    
}
