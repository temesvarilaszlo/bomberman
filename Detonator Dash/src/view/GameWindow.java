package view;

import java.io.IOException;
import javax.swing.JFrame;

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
        getContentPane().add(gamePanel);
        
        // GUI settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
