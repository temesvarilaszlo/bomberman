package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ButtonClickListener implements ActionListener {

    private final SettingsWindow settings;

    public ButtonClickListener(SettingsWindow settings){
        this.settings = settings;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        settings.disableButtons(clickedButton);
        clickedButton.setText("input...");
        clickedButton.setForeground(Color.RED);
        clickedButton.requestFocus();
    }

}