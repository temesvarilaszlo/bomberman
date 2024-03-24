package view;

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
import javax.swing.border.LineBorder;
import static assets.AssetLoader.CUSTOM_FONT;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import static view.HelperMethods.*;
import static assets.Controls.*;

public class SettingsWindow extends JFrame {

    private final JPanel middlePanel = new JPanel();
    private final JComboBox playersCombo = new JComboBox<>(new String[]{"Player 1", "Player 2", "Player 3"});
    private final JButton upButton = new JButton();
    private final JButton downButton = new JButton();
    private final JButton leftButton = new JButton();
    private final JButton rightButton = new JButton();
    private final JButton bombButton = new JButton();
    private final JButton backToMenuButton = new JButton("Back to Menu");
    private final ArrayList<JButton> buttonList = new ArrayList<>();

    public SettingsWindow() {
        init(this, "Settings", 800, 750);
        setLookandFeel();
        middlePanel.setPreferredSize(new Dimension(750, 690));
        addButtonsToList();
        updateControls(); //calling it so it sets the default for player 1

        //Title
        JLabel title = new JLabel("Settings");
        setProperties(title, 50, 240, 60, 270, 45);

        //Players
        setProperties(playersCombo, 20, 305, 170, 140, 40);
        playersCombo.addActionListener((ActionEvent e) -> {
            updateControls();
        });

        //Up
        JLabel upLabel = new JLabel("Up");
        setProperties(upLabel, 25, 200, 260, 50, 40);
        setProperties(upButton, 20, 400, 260, 200, 35);
//        changeKeyBinding(upButton);

        //Down
        JLabel downLabel = new JLabel("Down");
        setProperties(downLabel, 25, 200, 320, 90, 40);
        setProperties(downButton, 20, 400, 320, 200, 35);
//        changeKeyBinding(downButton);

        //Left
        JLabel leftLabel = new JLabel("Left");
        setProperties(leftLabel, 25, 200, 380, 80, 40);
        setProperties(leftButton, 20, 400, 380, 200, 35);
//        changeKeyBinding(leftButton);

        //Right
        JLabel rightLabel = new JLabel("Right");
        setProperties(rightLabel, 25, 200, 440, 90, 40);
        setProperties(rightButton, 20, 400, 440, 200, 35);
//        changeKeyBinding(rightButton);

        //Place Bomb
        JLabel placeBombLabel = new JLabel("Place Bomb");
        setProperties(placeBombLabel, 25, 200, 500, 180, 40);
        setProperties(bombButton, 20, 400, 500, 200, 35);
//        changeKeyBinding(bombButton);

        //Back to menu button
        backToMenuButton.addActionListener(backToMenu());
        setProperties(backToMenuButton, 30, 235, 580, 280, 40);

        //Adding everything to the JPanel
        middlePanel.add(title);
        middlePanel.add(playersCombo);
        middlePanel.add(upLabel);
        middlePanel.add(upButton);
        middlePanel.add(downLabel);
        middlePanel.add(downButton);
        middlePanel.add(leftLabel);
        middlePanel.add(leftButton);
        middlePanel.add(rightLabel);
        middlePanel.add(rightButton);
        middlePanel.add(placeBombLabel);
        middlePanel.add(bombButton);
        middlePanel.add(backToMenuButton);
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
     * Sets the properties of the given button
     */
    private void setProperties(JButton button, int fontSize, int x, int y, int width, int height) {
        button.setBorder(new LineBorder(Color.BLACK));
        button.setFont(CUSTOM_FONT.deriveFont(Font.PLAIN, fontSize));
        button.setBounds(x, y, width, height);
        button.setFocusable(false);
    }

    /**
     * Adds all buttons to a list
     */
    private void addButtonsToList() {
        buttonList.add(upButton);
        buttonList.add(rightButton);
        buttonList.add(downButton);
        buttonList.add(leftButton);
        buttonList.add(bombButton);
        buttonList.add(backToMenuButton);
    }

    /**
     * Changes the keybindings
     *
     * @param button
     */
    private void changeKeyBinding(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button.setFocusable(true);
                for (JButton b : buttonList) {
                    b.setEnabled(false);
                    playersCombo.setEnabled(false);
                }
            }
        });

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!button.isEnabled()) {
                    int keyCode = e.getKeyCode();
                    System.out.println(keyCode);
                    int otherKeyCode = -1;
                    for (JButton b : buttonList) {
                        if (b.getText().equals(KeyEvent.getKeyText(keyCode))) {
                            // If the key is already bound to another button, do nothing
                            //return;
                        }
                    }

                    button.setText(KeyEvent.getKeyText(keyCode));
                    for (JButton b : buttonList) {
                        b.setEnabled(true);
                    }
                    playersCombo.setEnabled(true);
                    button.setFocusable(false);
                }
            }
        });
    }

    /**
     * Updates control combo boxes based on which player is selected
     */
    private void updateControls() {
        String selectedPlayer = (String) playersCombo.getSelectedItem();
        switch (selectedPlayer) {
            case "Player 1":
                for (int i = 0; i < controls[0].length; i++) {
                    buttonList.get(i).setText(KeyEvent.getKeyText(controls[0][i]));
                }
                break;
            case "Player 2":
                for (int i = 0; i < controls[1].length; i++) {
                    buttonList.get(i).setText(KeyEvent.getKeyText(controls[1][i]));
                }
                break;
            case "Player 3":
                for (int i = 0; i < controls[2].length; i++) {
                    buttonList.get(i).setText(KeyEvent.getKeyText(controls[2][i]));
                }
                break;
            default:
                throw new AssertionError();
        }
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
