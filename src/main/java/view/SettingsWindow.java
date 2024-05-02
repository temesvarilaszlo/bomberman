package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import static view.HelperMethods.*;
import static assets.Controls.*;

public class SettingsWindow extends JFrame {

    private final JComboBox<String> playersCombo = new JComboBox<>(new String[]{"Player 1", "Player 2", "Player 3"});
    private final JButton upButton = new JButton();
    private final JButton downButton = new JButton();
    private final JButton leftButton = new JButton();
    private final JButton rightButton = new JButton();
    private final JButton bombButton = new JButton();
    private final JButton obstacleButton = new JButton();
    private final JButton backToMenuButton = new JButton("Back to Menu");
    private final ButtonClickListener buttonClickListener;

    private static final ArrayList<JButton> buttonsList = new ArrayList<>();

    public SettingsWindow() {
        init(this, "Settings", 800, 750);
        setLookandFeel();

        //Handlers, listeners
        SettingsKeyHandler keyH = new SettingsKeyHandler(this);
        addKeyListener(keyH);
        buttonClickListener = new ButtonClickListener(this);

        addButtonsToList();
        renderControls(); //calling it so it sets the default for player 1

        //Data
        JPanel middlePanel = new JPanel();
        middlePanel.setPreferredSize(new Dimension(750, 690));

        JLabel title = new JLabel("Settings");
        JLabel upLabel = new JLabel("Up");
        JLabel downLabel = new JLabel("Down");
        JLabel leftLabel = new JLabel("Left");
        JLabel rightLabel = new JLabel("Right");
        JLabel placeBombLabel = new JLabel("Place Bomb");
        JLabel placeObstacleLabel = new JLabel("Place Box");

        //Set properties of labels, buttons etc.
        setProperties(title, 50, 240, 60, 270, 45);
        setProperties(playersCombo, 20, 305, 170, 140, 40);
        setProperties(upLabel, 25, 200, 260, 50, 40);
        setProperties(upButton, 20, 400, 260, 200, 35);
        setProperties(downLabel, 25, 200, 320, 90, 40);
        setProperties(downButton, 20, 400, 320, 200, 35);
        setProperties(leftLabel, 25, 200, 380, 80, 40);
        setProperties(leftButton, 20, 400, 380, 200, 35);
        setProperties(rightLabel, 25, 200, 440, 90, 40);
        setProperties(rightButton, 20, 400, 440, 200, 35);
        setProperties(placeBombLabel, 25, 200, 500, 180, 40);
        setProperties(bombButton, 20, 400, 500, 200, 35);
        setProperties(placeObstacleLabel, 25, 200, 560, 180, 40);
        setProperties(obstacleButton, 20, 400, 560, 200, 35);
        setProperties(backToMenuButton, 30, 235, 620, 280, 40);

        //Adding actionsListeners
        backToMenuButton.addActionListener((ActionEvent e) -> {
            new MainMenuWindow();
            this.dispose();
        });
        playersCombo.addActionListener((ActionEvent e) -> {
            renderControls();
        });

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

        setVisible(true);
    }

    public ArrayList<JButton> getButtonsList() {
        return buttonsList;
    }

    /**
     * Adds all buttons to a list
     */
    public void addButtonsToList() {
        buttonsList.add(upButton);
        buttonsList.add(rightButton);
        buttonsList.add(downButton);
        buttonsList.add(leftButton);
        buttonsList.add(bombButton);
        buttonsList.add(obstacleButton);
        buttonsList.add(backToMenuButton);

        for(JButton b : buttonsList){
            if(b != backToMenuButton)
                b.addActionListener(buttonClickListener);
        }
    }

    /**
     * Enables all buttons and playerCombo
     */
    public void enableButtons(){
        for(JButton button : buttonsList){
            button.setEnabled(true);
        }
        playersCombo.setEnabled(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Disables all buttons and playersCombo except the clickedButton
     * @param clickedButton clicked button
     */
    public void disableButtons(JButton clickedButton){
        for(JButton button : buttonsList){
            if(button != clickedButton)
                button.setEnabled(false);
        }
        playersCombo.setEnabled(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    /**
     * Updates the controls matrix with the new keybindings
     * @param clickedButton clicked button
     * @param keyCode key code of the letter on the button
     */
    public void updateControlsMatrix(JButton clickedButton, int keyCode){
        HashMap<String, int[]> playerControlsMap = new HashMap<>();
        playerControlsMap.put("Player 1", controls[0]);
        playerControlsMap.put("Player 2", controls[1]);
        playerControlsMap.put("Player 3", controls[2]);

        String selectedPlayer = (String) playersCombo.getSelectedItem();
        int[] selectedControls = playerControlsMap.getOrDefault(selectedPlayer, null);
        if (selectedControls != null) {
            int index = -1;
            if (clickedButton == upButton) index = 0;
            else if (clickedButton == rightButton) index = 1;
            else if (clickedButton == downButton) index = 2;
            else if (clickedButton == leftButton) index = 3;
            else if (clickedButton == bombButton) index = 4;
            else if (clickedButton == obstacleButton) index = 5;

            if (index != -1) {
                selectedControls[index] = keyCode;
            }
        } else {
            throw new AssertionError();
        }
    }

    /**
     * Updates control combo boxes based on which player is selected
     */
    private void renderControls() {
        String selectedPlayer = (String) playersCombo.getSelectedItem();
        switch (Objects.requireNonNull(selectedPlayer)) {
            case "Player 1" -> { setButtonsText(buttonsList, 0); }
            case "Player 2" -> { setButtonsText(buttonsList, 1); }
            case "Player 3" -> { setButtonsText(buttonsList, 2); }
            default -> throw new AssertionError();
        }
    }
}