package view;

import model.GameEngine;

import javax.swing.*;
import java.awt.*;

import static view.HelperMethods.*;

/**
 *
 * @author tlasz
 */
public class GameWindow extends JFrame{
    private final GamePanel gamePanel;
    private final PowerupPanel powerupPanel;

    public GameWindow(int numberToWin) {
        super();
        GameEngine engine = new GameEngine();
        gamePanel = new GamePanel(this, numberToWin, engine);
        powerupPanel = new PowerupPanel(engine);

        // add panel to frame
        int padding = 10;
        powerupPanel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        add(powerupPanel, BorderLayout.WEST);
        add(gamePanel, BorderLayout.EAST);


        // GUI settings
        setLookandFeel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public PowerupPanel getPowerupPanel() {
        return powerupPanel;
    }
}
