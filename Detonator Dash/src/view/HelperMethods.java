package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class HelperMethods {

    /**
     * Sets the window its starting properties
     * @param frame
     * @param title
     * @param width
     * @param height
     */
    public static void init(JFrame frame, String title, int width, int height) {
        frame.setTitle(title);
        Dimension dim = new Dimension(width, height);
        frame.setPreferredSize(dim);
        frame.setMaximumSize(dim);
        frame.setSize(dim);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        createMenuBar(frame);
    }

    /**
     * Sets windows look and feel for the window
     */
    public static void setLookandFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        }
    }
    
    /**
     * Creates the menu bar
     * @param frame 
     */
    public static void createMenuBar(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuGame = new JMenu("Game");
        JMenuItem mainMenu = new JMenuItem("Main Menu");
        mainMenu.addActionListener((e) -> {
            new MainMenuWindow();
            frame.dispose();
        });
        JMenuItem settingsMenu = new JMenuItem("Main Menu");
        settingsMenu.addActionListener((e) -> {
            new SettingsWindow();
            frame.dispose();
        });
        JMenuItem menuExit = new JMenuItem(new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuGame.add(mainMenu);
        menuGame.add(settingsMenu);
        menuGame.addSeparator();
        menuGame.add(menuExit);
        menuBar.add(menuGame);
        frame.setJMenuBar(menuBar);
    }
}
