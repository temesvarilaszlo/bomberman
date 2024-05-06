package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.LineBorder;

import static utilz.AssetLoader.CUSTOM_FONT;
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
        JMenuItem settingsMenu = new JMenuItem("Settings");
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

    /**
     * Sets the properties of the given combo box
     */
    public static void setProperties(JComboBox comboBox, int fontSize, int x, int y, int width, int height) {
        comboBox.setBorder(new LineBorder(Color.BLACK));
        comboBox.setFont(CUSTOM_FONT.deriveFont(Font.PLAIN, fontSize));
        comboBox.setBounds(x, y, width, height);
        comboBox.setFocusable(false);
    }

    /**
     * Sets the properties of the given label
     */
    public static void setProperties(JLabel label, int fontSize, int x, int y, int width, int height) {
        label.setFont(CUSTOM_FONT.deriveFont(Font.PLAIN, fontSize));
        label.setBounds(x, y, width, height);
    }

    /**
     * Sets the properties of the given button
     */
    public static void setProperties(JButton button, int fontSize, int x, int y, int width, int height) {
        button.setBorder(new LineBorder(Color.BLACK));
        button.setFont(CUSTOM_FONT.deriveFont(Font.PLAIN, fontSize));
        button.setBounds(x, y, width, height);
        button.setFocusable(false);
    }

    /**
     * Sets the properties of the given spinner
     */
    public static void setProperties(JSpinner spinner, int fontSize, int x, int y, int width, int height) {
        ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setEditable(false); //so players cant edit with keyboard
        spinner.setBorder(new LineBorder(Color.BLACK));
        spinner.setFont(CUSTOM_FONT);
        spinner.setBounds(x, y, width, height);
        spinner.setFocusable(false);
    }
}
