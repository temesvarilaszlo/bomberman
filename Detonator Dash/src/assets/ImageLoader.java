/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
        return ImageIO.read(url);
    }
}
