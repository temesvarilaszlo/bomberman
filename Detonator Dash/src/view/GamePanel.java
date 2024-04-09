package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.Direction;
import model.GameEngine;
import model.KeyHandler;
import model.Player;
import static view.MainMenuWindow.is2PlayerGame;

/**
 *
 * @author tlasz
 */
public class GamePanel extends JPanel {

    public static final int BLOCK_PIXEL_SIZE = 50;
    public static final int PLAYER_PIXEL_SIZE = 35;
    public static final int MAP_SIZE = 15;
    public static final int WIDTH = BLOCK_PIXEL_SIZE * MAP_SIZE;
    public static final int HEIGHT = BLOCK_PIXEL_SIZE * MAP_SIZE;

    private final GameEngine engine;
    private final Timer timer;
    private final KeyHandler keyH;

    public GamePanel() {
        super();

        // GUI stuff
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setFocusable(true);

        // initialize engine related stuff
        engine = new GameEngine();
        keyH = new KeyHandler(engine);
        this.addKeyListener(keyH);

        timer = new Timer(10, new TimerListener());
        timer.start();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics gr) {
        // cast to graphics2d
        Graphics2D g = (Graphics2D) gr;
        super.paintComponent(g);

        // the things that need to be drawn
        engine.drawSprites(g);
    }

    /**
     * Sets the players direction and calls move()
     */
    private void updatePos() {
        for (int i = 0; i < engine.getPlayers().size(); i++) {
            Player player = engine.getPlayers().get(i);

            if (i == 0 && (keyH.upPressed1 || keyH.downPressed1 || keyH.rightPressed1 || keyH.leftPressed1)) {
                if (keyH.upPressed1) {
                    player.setDirection(Direction.UP);
                } else if (keyH.downPressed1) {
                    player.setDirection(Direction.DOWN);
                } else if (keyH.rightPressed1) {
                    player.setDirection(Direction.RIGHT);
                } else if (keyH.leftPressed1) {
                    player.setDirection(Direction.LEFT);
                }
                player.move();
            } else if (i == 1 && (keyH.upPressed2 || keyH.downPressed2 || keyH.rightPressed2 || keyH.leftPressed2)) {
                if (keyH.upPressed2) {
                    player.setDirection(Direction.UP);
                } else if (keyH.downPressed2) {
                    player.setDirection(Direction.DOWN);
                } else if (keyH.rightPressed2) {
                    player.setDirection(Direction.RIGHT);
                } else if (keyH.leftPressed2) {
                    player.setDirection(Direction.LEFT);
                }
                player.move();
            } else if (!is2PlayerGame && i == 2 && (keyH.upPressed3 || keyH.downPressed3 || keyH.rightPressed3 || keyH.leftPressed3)) {
                if (keyH.upPressed3) {
                    player.setDirection(Direction.UP);
                } else if (keyH.downPressed3) {
                    player.setDirection(Direction.DOWN);
                } else if (keyH.rightPressed3) {
                    player.setDirection(Direction.RIGHT);
                } else if (keyH.leftPressed3) {
                    player.setDirection(Direction.LEFT);
                }
                player.move();
            } else {
                player.setDirection(Direction.STOPPED);
            }
        }
    }

    /**
     * Placing bombs
     */
    private void placeBombs() {
        for (int i = 0; i < engine.getPlayers().size(); i++) {
            if (i == 0 && keyH.placeBomb1) {
                engine.getPlayers().get(i).placeBomb();
                keyH.placeBomb1 = false;
            } else if (i == 1 && keyH.placeBomb2) {
                engine.getPlayers().get(i).placeBomb();
                keyH.placeBomb2 = false;
            } else if (!is2PlayerGame && i == 2 && keyH.placeBomb3) {
                engine.getPlayers().get(i).placeBomb();
                keyH.placeBomb3 = false;
            }
        }
    }

    class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            placeBombs();
            engine.explodeBombs();
            engine.explosionEffects();
            updatePos();
            engine.moveMonsters();
            engine.clearDeadMonsters();
            repaint();
            
            if (engine.isGameOver()){
                timer.stop();
                removeKeyListener(keyH);
            }
        }

    }
}
