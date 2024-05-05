package view;

import assets.AssetLoader;
import assets.Images;
import model.GameEngine;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PowerupPanel extends JPanel {
    private final GameEngine engine;
    private final ArrayList<Player> players;
    private final HashMap<String, JComponent>[] playerLabels;
    private final HashMap<String, Image> powerupImages;

    public PowerupPanel(GameEngine engine) {
        super();
        this.engine = engine;
        this.players = new ArrayList<>(engine.getPlayers());
        playerLabels = new HashMap[engine.getPlayers().size()];
        // powerup image map
        powerupImages = new HashMap<>();
        powerupImages.put("D", Images.detonatorImg);
        powerupImages.put("G", Images.ghostImg);
        powerupImages.put("I", Images.invincibilityImg);
        powerupImages.put("O", Images.obstacleImg);
        powerupImages.put("S", Images.skateImg);
        powerupImages.replaceAll((k, v) -> v.getScaledInstance(30, 30, Image.SCALE_SMOOTH));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        for (int i = 0; i < playerLabels.length; i++) {
            playerLabels[i] = new HashMap<>();
            playerLabels[i].put("bombs", new JLabel());
            playerLabels[i].put("bombrange", new JLabel());
            playerLabels[i].put("obstacles", new JLabel());
            playerLabels[i].put("powerups", new PowerupImagePanel(powerupImages));

            JLabel playerLabel = new JLabel("Player " + (i + 1));
            playerLabel.setFont(AssetLoader.CUSTOM_FONT);
            add(playerLabel);
            for (JComponent component: playerLabels[i].values()) {
                add(component);
            }
        }

        updateLabels();
    }


    public void updateLabels() {
        for (int i = 0; i < playerLabels.length; i++) {
            JLabel bombLabel = (JLabel) playerLabels[i].get("bombs");
            JLabel bombrangeLabel = (JLabel) playerLabels[i].get("bombrange");
            JLabel obstacleLabel = (JLabel) playerLabels[i].get("obstacles");
            PowerupImagePanel powerupImagePanel = (PowerupImagePanel) playerLabels[i].get("powerups");

            bombLabel.setText("Bombs: " +
                    (players.get(i).getBombCapacity() - players.get(i).getPlacedBombs().size()) + "/"
                    + players.get(i).getBombCapacity());
            bombrangeLabel.setText("Bomb range: " + players.get(i).getBombRange());
            int obstacleCounter = 0;
            for (String s : players.get(i).getPowerups()){
                if (s.equals("O")){
                    obstacleCounter++;
                }
            }
            obstacleLabel.setText("Obstacles: " + obstacleCounter);
            powerupImagePanel.drawPowerupImages(players.get(i).getPowerups());
        }
    }
}
