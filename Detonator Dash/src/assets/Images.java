package assets;

import java.awt.Image;
import static assets.AssetLoader.*;
import java.io.IOException;

/**
 *
 * @author tlasz
 */
public class Images {
    public static Image bombImg;
    public static Image boxImg;
    public static Image fireImg;
    public static Image pathImg;
    public static Image wallImg;
    public static Image whiteImg;
    
    public Images() throws IOException{
        bombImg = loadImage("assets/bomb.png");
        boxImg = loadImage("assets/box.png");
        fireImg = loadImage("assets/fire.png");
        pathImg = loadImage("assets/path.png");
        wallImg = loadImage("assets/wall.png");
        whiteImg = loadImage("assets/white.png");
    }
}
