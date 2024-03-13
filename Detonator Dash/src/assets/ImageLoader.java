package assets;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author tlasz
 */
public class ImageLoader {
    public static Image loadImage(String resName) throws IOException{
        URL url = ImageLoader.class.getClassLoader().getResource(resName);
        //asd
        return ImageIO.read(url);
    }
}
