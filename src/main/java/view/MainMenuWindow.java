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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import static view.HelperMethods.*;
import static view.HelperMethods.setProperties;

public class MainMenuWindow extends JFrame {

    private static final JComboBox<String> maps = new JComboBox<>(new String[]{"map1", "map2", "map3"});
    public static boolean is2PlayerGame = true;
    private int numberToWin = 1;

    public MainMenuWindow() {
        init(this, "Detonator Dash", 800, 750);
        setLookandFeel();

        //Data
        JPanel middlePanel = new JPanel();
        middlePanel.setPreferredSize(new Dimension(750, 690));

        JLabel title = new JLabel("Main menu");
        JLabel selectMapLabel = new JLabel("Select Map:");
        JLabel firstToWinLabel = new JLabel("First to win:");
        JButton twoPlayerButton = new JButton("2 players");
        JButton threePlayerButton = new JButton("3 players");
        JButton settingsButton = new JButton("Settings");
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));


        //Set properties of labels, buttons etc.
        setProperties(title, 50, 210, 60, 330, 45);
        setProperties(twoPlayerButton, 30, 260, 150, 230, 40);
        setProperties(threePlayerButton, 30, 260, 230, 230, 40);
        setProperties(settingsButton, 30, 260, 310, 230, 40);
        setProperties(selectMapLabel, 30, 265, 380, 215, 40);
        setProperties(maps, 15, 325 , 430, 100, 35);
        setProperties(firstToWinLabel,30, 250, 500, 250, 40);
        setProperties(spinner, 15, 335, 550, 80, 35);

        //Adding actionsListeners to buttons
        twoPlayerButton.addActionListener((ActionEvent e) -> {
            is2PlayerGame = true;
            new GameWindow(numberToWin);
            this.dispose();
        });
        threePlayerButton.addActionListener((ActionEvent e) -> {
            is2PlayerGame = false;
            new GameWindow(numberToWin);
            this.dispose();
        });
        settingsButton.addActionListener((ActionEvent e) -> {
            new SettingsWindow();
            this.dispose();
        });
        spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                numberToWin = (int) spinner.getValue();
                // Do something with the new value
                System.out.println("New value: " + numberToWin);
            }
        });

        //Adding everything to the JPanel
        middlePanel.add(title);
        middlePanel.add(twoPlayerButton);
        middlePanel.add(threePlayerButton);
        middlePanel.add(settingsButton);
        middlePanel.add(selectMapLabel);
        middlePanel.add(maps);
        middlePanel.add(firstToWinLabel);
        middlePanel.add(spinner);
        middlePanel.setLayout(null);
        setLayout(new GridBagLayout());
        add(middlePanel, new GridBagConstraints());

        setVisible(true);
    }

    public static String GetMap() {
        return (String) maps.getSelectedItem();
    }

    public static boolean IsTwoPlayerGame(){
        return is2PlayerGame;
    }
}
