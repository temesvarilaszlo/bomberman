package view;

import java.io.IOException;
import javax.swing.JFrame;
import static view.HelperMethods.*;

/**
 *
 * @author tlasz
 */
public class GameWindow extends JFrame{
    private GamePanel gamePanel;

    public GameWindow() throws IOException{
        super();
        gamePanel = new GamePanel();
        
        // add panel to frame
        add(gamePanel);
        
        // GUI settings
        createMenuBar(this);
        setLookandFeel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
