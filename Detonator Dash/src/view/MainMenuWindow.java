package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.LineBorder;
import static assets.AssetLoader.CUSTOM_FONT;

public class MainMenuWindow extends JFrame {
    public MainMenuWindow(){
        init();
        setLookandFeel();
        
        JPanel middlePanel = new JPanel();
        middlePanel.setPreferredSize(new Dimension(750, 700));

        JLabel title = new JLabel("Main menu");
        title.setFont(CUSTOM_FONT.deriveFont(Font.PLAIN, 50));
        title.setBounds(210, 60, 330, 45);
        
        JButton twoPlayers = new JButton("2 players");
        twoPlayers.addActionListener(twoPlayers());
        twoPlayers.setFont(CUSTOM_FONT.deriveFont(Font.PLAIN, 30));
        twoPlayers.setBorder(new LineBorder(Color.BLACK));
        twoPlayers.setBounds(260, 150, 230, 40);
        twoPlayers.setFocusable(false);
        
        JButton threePlayers = new JButton("3 players");
        threePlayers.addActionListener(threePlayers());
        threePlayers.setFont(CUSTOM_FONT.deriveFont(Font.PLAIN, 30));
        threePlayers.setBorder(new LineBorder(Color.BLACK));
        threePlayers.setBounds(260, 230, 230, 40);
        threePlayers.setFocusable(false);
        
        JButton settings = new JButton("Settings");
        settings.addActionListener(settings());
        settings.setFont(CUSTOM_FONT.deriveFont(Font.PLAIN, 30));
        settings.setBorder(new LineBorder(Color.BLACK));
        settings.setBounds(260, 310, 230, 40);
        settings.setFocusable(false);
        
        JLabel selectMapLabel = new JLabel("Select Map:");
        selectMapLabel.setFont(CUSTOM_FONT.deriveFont(Font.PLAIN, 30));
        selectMapLabel.setBounds(265, 380, 215, 40);
        
        JComboBox maps = new JComboBox<>(new String[] { "Map 1", "Map 2", "Map 3" });
        maps.setBorder(new LineBorder(Color.BLACK));
        maps.setFont(CUSTOM_FONT);
        maps.setBounds(325, 430, 100, 35);
        maps.setFocusable(false);

        JLabel firstToWinLabel = new JLabel("First to win:");
        firstToWinLabel.setFont(CUSTOM_FONT.deriveFont(Font.PLAIN, 30));
        firstToWinLabel.setBounds(250, 500, 250, 40);
        
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        ((JSpinner.DefaultEditor)spinner.getEditor()).getTextField().setEditable(false); //so players cant edit with keyboard
        spinner.setBorder(new LineBorder(Color.BLACK));
        spinner.setFont(CUSTOM_FONT);
        spinner.setBounds(335, 550, 80, 35);
        spinner.setFocusable(false);
        
        middlePanel.add(title);
        middlePanel.add(twoPlayers);
        middlePanel.add(threePlayers);
        middlePanel.add(settings);
        middlePanel.add(selectMapLabel);
        middlePanel.add(maps);
        middlePanel.add(firstToWinLabel);
        middlePanel.add(spinner);
        middlePanel.setLayout(null);
        setLayout(new GridBagLayout());
        add(middlePanel, new GridBagConstraints());
        

        setVisible(true);
    }
    
    
    /**
     * Sets the window its starting properties
     */
    private void init(){
        setTitle("Detonator Dash");
        Dimension dim = new Dimension(800, 750);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setSize(dim);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * Sets windows look and feel for the window
     */
    private void setLookandFeel(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        }
    }
    
    /**
     * Creates a game with two players
     * @return 
     */
    private ActionListener twoPlayers() {
        return (ActionEvent e) -> {
            try {
                new GameWindow();
            } catch (IOException ex) {System.out.println("GameWindow couldn't open!");}            
            setVisible(false);
        };
    }
    
    /**
     * Creates a game with three players
     * @return 
     */
    private ActionListener threePlayers() {
        return (ActionEvent e) -> {
            try {
                new GameWindow();
            } catch (IOException ex) {System.out.println("GameWindow couldn't open!");}            
            setVisible(false);
        };
    }
    
    /**
     * Opens settings
     * @return 
     */
    private ActionListener settings() {
        return (ActionEvent e) -> {
            new SettingsWindow(this); //parameter so we can reopen the menu
            setVisible(false);
        };
    }
}
