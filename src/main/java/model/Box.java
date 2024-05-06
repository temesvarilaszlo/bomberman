package model;

import java.awt.Image;
import java.util.Random;

public class Box extends Block{
    private boolean containsDrop;
    
    public Box(int x, int y, int size, Image img, boolean mayContainDrop) {
        super(x, y, size, img);
        
        if (mayContainDrop){
            Random r = new Random();
            containsDrop = true;
        }
    }

    public Box(int x, int y, int size, Image img) {
        this(x, y, size, img, true);
    }

    public boolean containsDrop() {
        return containsDrop;
    }
    
}
