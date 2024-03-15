package view;

import assets.FontLoader;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.LineBorder;

public class SettingsWindow extends JFrame {
    private static final Font CUSTOM_FONT = FontLoader.loadFont(15);
    private final JPanel middlePanel;
    private final JComboBox players;
    
    public SettingsWindow(MainMenuWindow mainMenu){
        middlePanel = new JPanel();
        players = new JComboBox();
        init();
        setLookandFeel();

        JLabel title = new JLabel("Settings");
        setProperties(title, 50, 240, 60, 270, 45);
        
        players.addItem("Player 1");
        players.addItem("Player 2");
        players.addItem("Player 3");
        setProperties(players, 20, 305, 170, 140, 40);
        
        
        JLabel upLabel = new JLabel("Up");
        setProperties(upLabel, 25, 200, 260, 50, 40);       
        JComboBox upCombo = new JComboBox();
        upCombo.addItem("W");
        setProperties(upCombo, 20, 400, 260, 120, 35);
        
        
        JLabel downLabel = new JLabel("Down");
        setProperties(downLabel, 25, 200, 320, 90, 40);
        JComboBox downCombo = new JComboBox();
        downCombo.addItem("S");
        setProperties(downCombo, 20, 400, 320, 120, 35);
        
        
        JLabel leftLabel = new JLabel("Left");
        setProperties(leftLabel, 25, 200, 380, 80, 40);
        JComboBox leftCombo = new JComboBox();
        leftCombo.addItem("A");
        setProperties(leftCombo, 20, 400, 380, 120, 35);
        
        
        JLabel rightLabel = new JLabel("Right");
        setProperties(rightLabel, 25, 200, 440, 90, 40);
        JComboBox rightCombo = new JComboBox();
        rightCombo.addItem("D");
        setProperties(rightCombo, 20, 400, 440, 120, 35);
        
        
        JLabel placeBombLabel = new JLabel("Place Bomb");
        setProperties(placeBombLabel, 25, 200, 500, 180, 40);
        JComboBox bombCombo = new JComboBox();
        bombCombo.addItem("Space");
        setProperties(bombCombo, 20, 400, 500, 120, 35);
        
        
        JButton backToMenu = new JButton("Back to Menu");
        backToMenu.addActionListener(backToMenu(mainMenu));
        backToMenu.setFont(CUSTOM_FONT.deriveFont(Font.PLAIN, 30));
        backToMenu.setBorder(new LineBorder(Color.BLACK));
        backToMenu.setBounds(235, 580, 280, 40);
        backToMenu.setFocusable(false);
        
        middlePanel.add(title);
        middlePanel.add(players);
        middlePanel.add(upLabel);
        middlePanel.add(upCombo);
        middlePanel.add(downLabel);
        middlePanel.add(downCombo);
        middlePanel.add(leftLabel);
        middlePanel.add(leftCombo);
        middlePanel.add(rightLabel);
        middlePanel.add(rightCombo);
        middlePanel.add(placeBombLabel);
        middlePanel.add(bombCombo);
        middlePanel.add(backToMenu);
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
        middlePanel.setPreferredSize(new Dimension(750, 700));
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
     * Sets the properties of the given combo box
     */
    private void setProperties(JComboBox comboBox, int fontSize, int x, int y, int width, int height){
        comboBox.setBorder(new LineBorder(Color.BLACK));
        comboBox.setFont(CUSTOM_FONT.deriveFont(Font.PLAIN, fontSize));
        comboBox.setBounds(x, y, width, height);
        comboBox.setFocusable(false);
    }
    
    /**
     * Sets the properties of the given label
     */
    private void setProperties(JLabel label, int fontSize, int x, int y, int width, int height){
        label.setFont(CUSTOM_FONT.deriveFont(Font.PLAIN, fontSize));
        label.setBounds(x, y, width, height);
    }
    
    private ActionListener backToMenu(MainMenuWindow mainMenu) {
        return (ActionEvent e) -> {
            mainMenu.setVisible(true);
            this.dispose();
        };
    }
}
