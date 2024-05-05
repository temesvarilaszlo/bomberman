package model;

import assets.Images;

public class Fire extends Block{
    public int wave;
    public boolean isActive;
    
    public Fire(int x, int y, int size, int wave) {
        super(x, y, size, Images.fireImg);
        this.wave = wave;
        isActive = false;
    }
    
}
