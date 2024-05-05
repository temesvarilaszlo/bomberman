package model;

public enum Speed {
    SLOW(1), NORMAL(2), FAST(3);
    
    public float speed;

    Speed(float speed) {
        this.speed = speed;
    }

}
