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
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.event.KeyListener;
import static assets.AssetLoader.CUSTOM_FONT;
import static view.HelperMethods.*;
import static assets.Controls.*;

public class SettingsWindow extends JFrame implements KeyListener {

    private final JPanel middlePanel = new JPanel();
    private final JComboBox playersCombo = new JComboBox<>(new String[]{"Player 1", "Player 2", "Player 3"});
    private final JButton upButton = new JButton();
    private final JButton downButton = new JButton();
    private final JButton leftButton = new JButton();
    private final JButton rightButton = new JButton();
    private final JButton bombButton = new JButton();
    private final JButton obstacleButton = new JButton();
    private final JButton backToMenuButton = new JButton("Back to Menu");
    private final ArrayList<JButton> buttonList = new ArrayList<>();

    public SettingsWindow() {
        init(this, "Settings", 800, 750);
        setLookandFeel();
        middlePanel.setPreferredSize(new Dimension(750, 690));
        addButtonsToList();
        renderControls(); //calling it so it sets the default for player 1

        //Title
        JLabel title = new JLabel("Settings");
        setProperties(title, 50, 240, 60, 270, 45);

        //Players
        setProperties(playersCombo, 20, 305, 170, 140, 40);
        playersCombo.addActionListener((ActionEvent e) -> {
            renderControls();
        });

        //Up
        JLabel upLabel = new JLabel("Up");
        setProperties(upLabel, 25, 200, 260, 50, 40);
        setProperties(upButton, 20, 400, 260, 200, 35);

        //Down
        JLabel downLabel = new JLabel("Down");
        setProperties(downLabel, 25, 200, 320, 90, 40);
        setProperties(downButton, 20, 400, 320, 200, 35);

        //Left
        JLabel leftLabel = new JLabel("Left");
        setProperties(leftLabel, 25, 200, 380, 80, 40);
        setProperties(leftButton, 20, 400, 380, 200, 35);

        //Right
        JLabel rightLabel = new JLabel("Right");
        setProperties(rightLabel, 25, 200, 440, 90, 40);
        setProperties(rightButton, 20, 400, 440, 200, 35);

        //Place Bomb
        JLabel placeBombLabel = new JLabel("Place Bomb");
        setProperties(placeBombLabel, 25, 200, 500, 180, 40);
        setProperties(bombButton, 20, 400, 500, 200, 35);
        
        //Place Bomb
        JLabel placeObstacleLabel = new JLabel("Place Box");
        setProperties(placeObstacleLabel, 25, 200, 560, 180, 40);
        setProperties(obstacleButton, 20, 400, 560, 200, 35);

        //Back to menu button
        backToMenuButton.addActionListener(backToMenu());
        setProperties(backToMenuButton, 30, 235, 620, 280, 40);

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
        middlePanel.add(placeObstacleLabel);
        middlePanel.add(obstacleButton);
        middlePanel.add(backToMenuButton);
        middlePanel.setLayout(null);
        setLayout(new GridBagLayout());
        add(middlePanel, new GridBagConstraints());
        
        addKeyListener(this);
        
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
        buttonList.add(obstacleButton);
        buttonList.add(backToMenuButton);
        
        for(JButton b : buttonList){
            if(b != backToMenuButton)
                b.addActionListener(new ButtonClickListener());
        }
    }
    
    /**
     * Disables all buttons and playersCombo except the clickedButton
     * @param exception
     */
    private void disableButtons(JButton exception){
        for(JButton button : buttonList){
            if(button != exception)
                button.setEnabled(false);
        }
        playersCombo.setEnabled(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
    
    /**
     * Enables all buttons and playerCombo
     */
    private void enableButtons(){
        for(JButton button : buttonList){
            button.setEnabled(true);
        }
        playersCombo.setEnabled(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(isMatchingKey(keyCode))
            return;
        
        //setting text for button
        JButton clickedButton = null;
        for (JButton button : buttonList) {
            if (button.isEnabled()) {
                clickedButton = button;
                clickedButton.setForeground(Color.BLACK);
                clickedButton.setText(KeyEvent.getKeyText(keyCode));
            }
        }
        enableButtons();
        
        updateControlsMatrix(clickedButton, keyCode);
        updateControlsFile();
    }
    
    @Override
    public void keyReleased(KeyEvent e) {}
    
    /**
     * Updates the controls matrix with the new keybindings
     * @param clickedButton
     * @param keyCode 
     */
    private void updateControlsMatrix(JButton clickedButton, int keyCode){
        String selectedPlayer = (String) playersCombo.getSelectedItem();
        switch (selectedPlayer) {
            case "Player 1" -> {
                if(clickedButton == upButton)
                    controls[0][0] = keyCode;
                else if(clickedButton == rightButton)
                    controls[0][1] = keyCode;
                else if(clickedButton == downButton)
                    controls[0][2] = keyCode;
                else if(clickedButton == leftButton)
                    controls[0][3] = keyCode;
                else if(clickedButton == bombButton)
                    controls[0][4] = keyCode;
                else if(clickedButton == obstacleButton)
                    controls[0][5] = keyCode;
            }
            case "Player 2" -> {
                if(clickedButton == upButton)
                    controls[1][0] = keyCode;
                else if(clickedButton == rightButton)
                    controls[1][1] = keyCode;
                else if(clickedButton == downButton)
                    controls[1][2] = keyCode;
                else if(clickedButton == leftButton)
                    controls[1][3] = keyCode;
                else if(clickedButton == bombButton)
                    controls[1][4] = keyCode;
                else if(clickedButton == obstacleButton)
                    controls[1][5] = keyCode;
            }
            case "Player 3" -> {
                if(clickedButton == upButton)
                    controls[2][0] = keyCode;
                else if(clickedButton == rightButton)
                    controls[2][1] = keyCode;
                else if(clickedButton == downButton)
                    controls[2][2] = keyCode;
                else if(clickedButton == leftButton)
                    controls[2][3] = keyCode;
                else if(clickedButton == bombButton)
                    controls[2][4] = keyCode;
                else if(clickedButton == obstacleButton)
                    controls[2][5] = keyCode;
            }
            default -> throw new AssertionError();
        }
    }
    
    /**
     * Updates control combo boxes based on which player is selected
     */
    private void renderControls() {
        String selectedPlayer = (String) playersCombo.getSelectedItem();
        switch (selectedPlayer) {
            case "Player 1" -> { setButtonsText(buttonList, 0); }
            case "Player 2" -> { setButtonsText(buttonList, 1); }
            case "Player 3" -> { setButtonsText(buttonList, 2); }
            default -> throw new AssertionError();
        }
    }

    private class ButtonClickListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();
            disableButtons(clickedButton);
            clickedButton.setText("input...");
            clickedButton.setForeground(Color.RED);
            clickedButton.requestFocus();
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