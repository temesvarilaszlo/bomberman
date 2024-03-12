/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author tlasz
 */
public class GameWindow extends JFrame{
    private GamePanel gamePanel;

    public GameWindow() throws IOException {
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
