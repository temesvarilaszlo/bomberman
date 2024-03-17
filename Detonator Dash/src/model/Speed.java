package model;

/**
 *
 * @author tlasz
 */
public enum Speed {
    NORMAL(1), FAST(2);
    
    public float speed;

    Speed(float speed) {
        this.speed = speed;
    }
    
    
}
