package assets;

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
    public static final Font CUSTOM_FONT = loadFont(15);
    
    /**
     * Loads the game's font
     * @param size
     * @return 
     */
    private static Font loadFont(int size){
        Font customFont = null;
        try {
            URL url = AssetLoader.class.getClassLoader().getResource("assets/bm.ttf");
            File fontfile = Paths.get(url.toURI()).toFile();
            //create the font to use. Specify the size!
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontfile).deriveFont(Font.PLAIN, size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(customFont);
            
            return customFont;
        } catch (IOException | FontFormatException | URISyntaxException e) {}
        
        return customFont;
    }
    
    public static Image loadImage(String resName) throws IOException{
        URL url = AssetLoader.class.getClassLoader().getResource(resName);
        return ImageIO.read(url);
    }
    
    public static InputStream loadTxt(String resName){
        return AssetLoader.class.getClassLoader().getResourceAsStream(resName);
    } 
}
