package view;

import java.io.IOException;
import assets.Images;

public class DetonatorDash {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // load images
        try{
            new Images();
        }
        catch (IOException e){
            System.err.println("Error while loading images!");
        }
        new MainMenuWindow();
    }
    
}
