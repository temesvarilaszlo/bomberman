package utilz;

import java.awt.Image;
import static utilz.AssetLoader.*;
import java.io.IOException;

/**
 *
 * @author tlasz
 */
public class Images {
    // sprites
    public static Image bombImg;
    public static Image boxImg;
    public static Image fireImg;
    public static Image monsterImg;
    public static Image pathImg;
    public static Image player1Img;
    public static Image player2Img;
    public static Image player3Img;
    public static Image wallImg;
    public static Image whiteImg;
    public static Image ghost1Img;
    public static Image ghost2Img;
    public static Image ghost3Img;
    public static Image player1Inv;
    public static Image player2Inv;
    public static Image player3Inv;

    // drops
    public static Image detonatorImg;
    public static Image ghostImg;
    public static Image invincibilityImg;
    public static Image obstacleImg;
    public static Image plusBombImg;
    public static Image plusBombRangeImg;
    public static Image skateImg;
    
    
    public Images() {
        // sprites
        bombImg = loadImage("assets/bomb.png");
        boxImg = loadImage("assets/box.png");
        fireImg = loadImage("assets/fire.png");
        monsterImg = loadImage("assets/monster.png");
        pathImg = loadImage("assets/path.png");
        player1Img = loadImage("assets/player1.png");
        player2Img = loadImage("assets/player2.png");
        player3Img = loadImage("assets/player3.png");
        wallImg = loadImage("assets/wall.png");
        whiteImg = loadImage("assets/white.png");
        ghost1Img = loadImage("assets/ghost1.png");
        ghost2Img = loadImage("assets/ghost2.png");
        ghost3Img = loadImage("assets/ghost3.png");
        player1Inv = loadImage("assets/player1Inv.png");
        player2Inv = loadImage("assets/player2Inv.png");
        player3Inv = loadImage("assets/player3Inv.png");

        // drops
        detonatorImg = loadImage("assets/detonator.png");
        ghostImg = loadImage("assets/ghost.png");
        invincibilityImg = loadImage("assets/invincibility.png");
        obstacleImg = loadImage("assets/obstacle.png");
        plusBombImg = loadImage("assets/plusbomb.png");
        plusBombRangeImg = loadImage("assets/plusbombrange.png");
        skateImg = loadImage("assets/rollerskate.png");
    }
}
