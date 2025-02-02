package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class PowerupImagePanel extends JPanel {
    private final HashMap<String, Image> powerupImages;
    private HashSet<String> powerups;
    private final int imgSize;
    private final int imgPadding;

    public PowerupImagePanel(HashMap<String, Image> powerupImages) {
        super();
        imgSize = 30;
        imgPadding = 6;
        setPreferredSize(new Dimension(5 * imgSize + 5 * imgPadding, 100));

        this.powerupImages = powerupImages;
    }

    @Override
    protected void paintComponent(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        super.paintComponent(g);

        int i = 0;
        for (String powerup : powerups) {
            Image img = powerupImages.get(powerup);
            g.drawImage(img, i * imgSize + imgPadding, 0, imgSize, imgSize, null);
            i++;
        }
    }

    public void drawPowerupImages(ArrayList<String> powerups) {
        this.powerups = new HashSet<>(powerups);
        repaint();
    }
}
