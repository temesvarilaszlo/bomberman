package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import static assets.AssetLoader.CUSTOM_FONT;
import static view.HelperMethods.*;

public class SettingsWindow extends JFrame {

    private final JPanel middlePanel = new JPanel();
    private final JComboBox playersCombo = new JComboBox<>(new String[]{"Player 1", "Player 2", "Player 3"});
    private final JComboBox upCombo = new JComboBox();
    private final JComboBox downCombo = new JComboBox();
    private final JComboBox leftCombo = new JComboBox();
    private final JComboBox rightCombo = new JComboBox();
    private final JComboBox bombCombo = new JComboBox();

    public SettingsWindow() {
        init(this, "Settings", 800, 750);
        setLookandFeel();
        middlePanel.setPreferredSize(new Dimension(750, 690));
        updateControlComboBoxes(); //calling it so it sets the default for player 1

        //Title
        JLabel title = new JLabel("Settings");
        setProperties(title, 50, 240, 60, 270, 45);

        //Players
        setProperties(playersCombo, 20, 305, 170, 140, 40);
        playersCombo.addActionListener((ActionEvent e) -> {
            updateControlComboBoxes();
        });

        //Up
        JLabel upLabel = new JLabel("Up");
        setProperties(upLabel, 25, 200, 260, 50, 40);
        setProperties(upCombo, 20, 400, 260, 200, 35);

        //Down
        JLabel downLabel = new JLabel("Down");
        setProperties(downLabel, 25, 200, 320, 90, 40);
        setProperties(downCombo, 20, 400, 320, 200, 35);

        //Left
        JLabel leftLabel = new JLabel("Left");
        setProperties(leftLabel, 25, 200, 380, 80, 40);
        setProperties(leftCombo, 20, 400, 380, 200, 35);

        //Right
        JLabel rightLabel = new JLabel("Right");
        setProperties(rightLabel, 25, 200, 440, 90, 40);
        setProperties(rightCombo, 20, 400, 440, 200, 35);

        //Place Bomb
        JLabel placeBombLabel = new JLabel("Place Bomb");
        setProperties(placeBombLabel, 25, 200, 500, 180, 40);
        setProperties(bombCombo, 20, 400, 500, 200, 35);

        //Back to menu button
        JButton backToMenu = new JButton("Back to Menu");
        backToMenu.addActionListener(backToMenu());
        backToMenu.setFont(CUSTOM_FONT.deriveFont(Font.PLAIN, 30));
        backToMenu.setBorder(new LineBorder(Color.BLACK));
        backToMenu.setBounds(235, 580, 280, 40);
        backToMenu.setFocusable(false);

        //Adding everything to the JPanel
        middlePanel.add(title);
        middlePanel.add(playersCombo);
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
     * Sets the properties of the given combo box
     */
    private void setProperties(JComboBox comboBox, int fontSize, int x, int y, int width, int height) {
        comboBox.setBorder(new LineBorder(Color.BLACK));
        comboBox.setFont(CUSTOM_FONT.deriveFont(Font.PLAIN, fontSize));
        comboBox.setBounds(x, y, width, height);
        comboBox.setFocusable(false);
    }

    /**
     * Sets the properties of the given label
     */
    private void setProperties(JLabel label, int fontSize, int x, int y, int width, int height) {
        label.setFont(CUSTOM_FONT.deriveFont(Font.PLAIN, fontSize));
        label.setBounds(x, y, width, height);
    }

    /**
     * Updates control combo boxes based on which player is selected
     */
    private void updateControlComboBoxes() {
        String selectedPlayer = (String) playersCombo.getSelectedItem();
        clearControlComboBoxes();
        switch (selectedPlayer) {
            case "Player 1" -> {
                upCombo.setModel(new DefaultComboBoxModel<>(new String[]{"W"}));
                downCombo.setModel(new DefaultComboBoxModel<>(new String[]{"S"}));
                leftCombo.setModel(new DefaultComboBoxModel<>(new String[]{"A"}));
                rightCombo.setModel(new DefaultComboBoxModel<>(new String[]{"D"}));
                bombCombo.setModel(new DefaultComboBoxModel<>(new String[]{"L Shift"}));
            }
            case "Player 2" -> {
                upCombo.setModel(new DefaultComboBoxModel<>(new String[]{"UP arrow"}));
                downCombo.setModel(new DefaultComboBoxModel<>(new String[]{"DOWN arrow"}));
                leftCombo.setModel(new DefaultComboBoxModel<>(new String[]{"LEFT arrow"}));
                rightCombo.setModel(new DefaultComboBoxModel<>(new String[]{"RIGHT arrow"}));
                bombCombo.setModel(new DefaultComboBoxModel<>(new String[]{"Space"}));
            }
            case "Player 3" -> {
                upCombo.setModel(new DefaultComboBoxModel<>(new String[]{"NUM8"}));
                downCombo.setModel(new DefaultComboBoxModel<>(new String[]{"NUM2"}));
                leftCombo.setModel(new DefaultComboBoxModel<>(new String[]{"NUM4"}));
                rightCombo.setModel(new DefaultComboBoxModel<>(new String[]{"NUM6"}));
                bombCombo.setModel(new DefaultComboBoxModel<>(new String[]{"NUM5"}));
            }
        }
    }

    /**
     * Clears comboBoxes
     */
    private void clearControlComboBoxes() {
        upCombo.removeAllItems();
        downCombo.removeAllItems();
        leftCombo.removeAllItems();
        rightCombo.removeAllItems();
        bombCombo.removeAllItems();
    }

    /**
     * Goes back to the main menu
     *
     * @return
     */
    private ActionListener backToMenu() {
        return (ActionEvent e) -> {
            new MainMenuWindow();
            this.dispose();
        };
    }
}
