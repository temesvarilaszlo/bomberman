package model;

/**
 *
 * @author tlasz
 */
public enum Speed {
    NORMAL(2), FAST(3);
    
    public float speed;

    Speed(float speed) {
        this.speed = speed;
    }
    
    
}
