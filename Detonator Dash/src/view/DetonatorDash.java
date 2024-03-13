package view;

import java.io.IOException;

/**
 *
 * @author tlasz
 */
public class DetonatorDash {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            new GameWindow();
        }
        catch(IOException e){
            System.err.println("File not found");
        }
    }
    
}
