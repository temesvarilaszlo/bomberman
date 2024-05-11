package utilz;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

public class AssetLoader {
    public static final Font CUSTOM_FONT = loadFont();
    
    /**
     * Loads the font used in the game
     * @return Font
     */
    public static Font loadFont(){
        Font customFont = null;
        try {
            URL url = AssetLoader.class.getClassLoader().getResource("assets/bm.ttf");
            File fontfile = Paths.get(url.toURI()).toFile();
            //create the font to use. Specify the size!
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontfile).deriveFont(Font.PLAIN, 15);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(customFont);
            
            return customFont;
        } catch (IOException | FontFormatException | URISyntaxException ignored) {}
        
        return customFont;
    }
    
    /**
     * Loads images
     * @param resName path to the image
     * @return Image
     */
    public static Image loadImage(String resName) {
        URL url = AssetLoader.class.getClassLoader().getResource(resName);
        try {
            return ImageIO.read(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Loads text files
     * @param resName path to the text file
     * @return InputStream
     */
    public static InputStream loadTxt(String resName){
        return AssetLoader.class.getClassLoader().getResourceAsStream(resName);
    } 
}
