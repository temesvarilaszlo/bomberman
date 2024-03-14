package view;

import assets.FontLoader;
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

public class SettingsWindow extends JFrame {
    private static final Font CUSTOM_FONT = FontLoader.loadFont(15);
    
    public SettingsWindow(MainMenuWindow mainMenu){
        init();
        setLookandFeel();
        
        JPanel middlePanel = new JPanel();
        middlePanel.setPreferredSize(new Dimension(750, 700));

        JLabel title = new JLabel("Settings");
        title.setFont(CUSTOM_FONT.deriveFont(Font.PLAIN, 50));
        title.setBounds(240, 60, 270, 45);
        
        JComboBox players = new JComboBox();
        players.addItem("Player 1");
        players.addItem("Player 2");
        players.addItem("Player 3");
        players.setBorder(new LineBorder(Color.BLACK));
        players.setFont(CUSTOM_FONT.deriveFont(Font.PLAIN, 20));
        players.setBounds(305, 170, 140, 40);
        players.setFocusable(false);
        
        JLabel upLabel = new JLabel("Up");
        upLabel.setFont(CUSTOM_FONT.deriveFont(Font.PLAIN, 25));
        upLabel.setBounds(200, 260, 50, 40);
        JComboBox upCombo = new JComboBox();
        upCombo.addItem("W");
        upCombo.setBorder(new LineBorder(Color.BLACK));
        upCombo.setFont(CUSTOM_FONT.deriveFont(Font.PLAIN, 20));
        upCombo.setBounds(400, 260, 120, 35);
        upCombo.setFocusable(false);
        
        JLabel downLabel = new JLabel("Down");
        downLabel.setFont(CUSTOM_FONT.deriveFont(Font.PLAIN, 25));
        downLabel.setBounds(200, 320, 90, 40);
        JComboBox downCombo = new JComboBox();
        downCombo.addItem("S");
        downCombo.setBorder(new LineBorder(Color.BLACK));
        downCombo.setFont(CUSTOM_FONT.deriveFont(Font.PLAIN, 20));
        downCombo.setBounds(400, 320, 120, 35);
        downCombo.setFocusable(false);
        
        JLabel leftLabel = new JLabel("Left");
        leftLabel.setFont(CUSTOM_FONT.deriveFont(Font.PLAIN, 25));
        leftLabel.setBounds(200, 380, 80, 40);
        JComboBox leftCombo = new JComboBox();
        leftCombo.addItem("A");
        leftCombo.setBorder(new LineBorder(Color.BLACK));
        leftCombo.setFont(CUSTOM_FONT.deriveFont(Font.PLAIN, 20));
        leftCombo.setBounds(400, 380, 120, 35);
        leftCombo.setFocusable(false);
        
        JLabel rightLabel = new JLabel("Right");
        rightLabel.setFont(CUSTOM_FONT.deriveFont(Font.PLAIN, 25));
        rightLabel.setBounds(200, 440, 90, 40);
        JComboBox rightCombo = new JComboBox();
        rightCombo.addItem("D");
        rightCombo.setBorder(new LineBorder(Color.BLACK));
        rightCombo.setFont(CUSTOM_FONT.deriveFont(Font.PLAIN, 20));
        rightCombo.setBounds(400, 440, 120, 35);
        rightCombo.setFocusable(false);
        
        JLabel placeBombLabel = new JLabel("Place Bomb");
        placeBombLabel.setFont(CUSTOM_FONT.deriveFont(Font.PLAIN, 25));
        placeBombLabel.setBounds(200, 500, 180, 40);
        JComboBox bombCombo = new JComboBox();
        bombCombo.addItem("Space");
        bombCombo.setBorder(new LineBorder(Color.BLACK));
        bombCombo.setFont(CUSTOM_FONT.deriveFont(Font.PLAIN, 20));
        bombCombo.setBounds(400, 500, 120, 35);
        bombCombo.setFocusable(false);
        
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
    
    private ActionListener backToMenu(MainMenuWindow mainMenu) {
        return (ActionEvent e) -> {
            mainMenu.setVisible(true);
            this.dispose();
        };
    }
}
