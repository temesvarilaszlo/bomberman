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
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import static view.HelperMethods.*;
import static assets.Controls.*;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class SettingsWindow extends JFrame implements KeyListener {

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
        buttonList.add(backToMenuButton);
        
        for(JButton b : buttonList){
            b.addActionListener(new ButtonClickListener());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        //if the pressed key matches any other key
        for(int i = 0; i < controls.length; i++){
            for(int j = 0; j < controls[i].length; j++){
                if(controls[i][j] == e.getKeyCode())
                    return;
            }
        }
        
        JButton clickedButton = null;
        for (JButton button : buttonList) {
            if (button.isEnabled()) {
                clickedButton = button;
                button.setText(KeyEvent.getKeyText(e.getKeyCode()));
            }
            button.setEnabled(true);
        }
        
        try {
            writeControlsToFile(clickedButton, e.getKeyCode());
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {}
    
    private void writeControlsToFile(JButton clickedButton, int keyCode) throws URISyntaxException{
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
            }
            default -> throw new AssertionError();
        }
        
        
        URL url = SettingsWindow.class.getClassLoader().getResource("assets/controls.txt");
        File controlFile = Paths.get(url.toURI()).toFile();        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(controlFile))) {
            for (int[] row : controls) {
                StringBuilder rowString = new StringBuilder();
                for (int col : row) {
                    rowString.append(col).append(",");
                }
                // Remove the last comma and write the row to the file
                writer.write(rowString.substring(0, rowString.length() - 1));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing matrix to file: " + e.getMessage());
        }
    }


    private class ButtonClickListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();
            for(JButton button : buttonList){
                if(button != clickedButton)
                    button.setEnabled(false);
            }
            clickedButton.requestFocus();
        }
        
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