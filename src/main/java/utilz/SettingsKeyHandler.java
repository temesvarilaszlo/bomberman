package utilz;

import view.SettingsWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utilz.Controls.isMatchingKey;
import static utilz.Controls.updateControlsFile;

public class SettingsKeyHandler implements KeyListener {
    private final SettingsWindow settings;

    public SettingsKeyHandler(SettingsWindow settings){
        super();

        this.settings = settings;
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
        for (JButton button : settings.getButtonsList()) {
            if (button.isEnabled()) {
                clickedButton = button;
                clickedButton.setForeground(Color.BLACK);
                clickedButton.setText(KeyEvent.getKeyText(keyCode));
            }
        }
        settings.enableButtons();

        settings.updateControlsMatrix(clickedButton, keyCode);
        updateControlsFile();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

}
