package model;

import assets.Images;
import java.awt.Image;
import java.util.Random;
import view.GamePanel;

public class Drop extends Block{
    private String type;
    
    public Drop(int x, int y){
        super(x, y, GamePanel.BLOCK_PIXEL_SIZE, null);
        Image img;
        Random r = new Random();
        int typeNum = r.nextInt(7);
        switch (typeNum) {
            case 0 -> {
                type = "D";
                img = Images.detonatorImg;
            }
            case 1 -> {
                type = "G";
                img = Images.ghostImg;
            }
            case 2 -> {
                type = "I";
                img = Images.invincibilityImg;
            }
            case 3 -> {
                type = "O";
                img = Images.obstacleImg;
            }
            case 4 -> {
                type = "PB";
                img = Images.plusBombImg;
            }
            case 5 -> {
                type = "PR";
                img = Images.plusBombRangeImg;
            }
            case 6 -> {
                type = "S";
                img = Images.skateImg;
            }
            default -> throw new AssertionError();
        }
        this.img = img;
    }

    public String getType() {
        return type;
    }
    
}
