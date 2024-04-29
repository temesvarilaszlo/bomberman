package view;

import javax.swing.JFrame;
import static view.HelperMethods.*;

/**
 *
 * @author tlasz
 */
public class GameWindow extends JFrame{
    private final GamePanel gamePanel;

    public GameWindow() {
        super();
        gamePanel = new GamePanel();
        
        // add panel to frame
        add(gamePanel);
        
        // GUI settings
        setLookandFeel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
