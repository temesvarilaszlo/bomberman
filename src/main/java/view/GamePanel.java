package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import model.Direction;
import model.GameEngine;
import model.KeyHandler;
import model.Player;
import static view.MainMenuWindow.Is2PlayerGame;

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
    private final int numberToWin;

    private final GameEngine engine;
    private final GameWindow gameWindow;
    private final KeyHandler keyH;
    private final Timer timer;

    public GamePanel(GameWindow GameWindow, int numberToWin, GameEngine engine) {
        super();

        // GUI stuff
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setFocusable(true);

        // initialize engine related stuff
        this.engine = engine;
        this.gameWindow = GameWindow;
        this.numberToWin = numberToWin;
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
                } else {
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
                } else {
                    player.setDirection(Direction.LEFT);
                }
                player.move();
            } else if (!Is2PlayerGame() && i == 2 && (keyH.upPressed3 || keyH.downPressed3 || keyH.rightPressed3 || keyH.leftPressed3)) {
                if (keyH.upPressed3) {
                    player.setDirection(Direction.UP);
                } else if (keyH.downPressed3) {
                    player.setDirection(Direction.DOWN);
                } else if (keyH.rightPressed3) {
                    player.setDirection(Direction.RIGHT);
                } else {
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
    private void placeBomb() {
        for (int i = 0; i < engine.getPlayers().size(); i++) {
            if (i == 0 && keyH.placeBomb1) {
                engine.getPlayers().get(i).placeBomb();
                keyH.placeBomb1 = false;
            } else if (i == 1 && keyH.placeBomb2) {
                engine.getPlayers().get(i).placeBomb();
                keyH.placeBomb2 = false;
            } else if (!Is2PlayerGame() && i == 2 && keyH.placeBomb3) {
                engine.getPlayers().get(i).placeBomb();
                keyH.placeBomb3 = false;
            }
        }
    }

    /**
     * Placing boxes
     */
    private void placeBox() {
        for (int i = 0; i < engine.getPlayers().size(); i++) {
            if (i == 0 && keyH.placeBox1) {
                engine.getPlayers().get(i).placeBox();
                keyH.placeBox1 = false;
            } else if (i == 1 && keyH.placeBox2) {
                engine.getPlayers().get(i).placeBox();
                keyH.placeBox1 = false;
            } else if (!Is2PlayerGame() && i == 2 && keyH.placeBox3) {
                engine.getPlayers().get(i).placeBox();
                keyH.placeBox1 = false;
            }
        }

    }

    /**
     * Return the winning player's index or -1 if draw
     * @return int
     */
    private int getWinner(){
        for (int i = 0; i < engine.getPlayers().size(); i++) {
            if(engine.getPlayers().get(i).getIsAlive()){
                return i + 1;
            }
        }
        return -1;
    }

    private boolean checkWinner(int winnerIndex){
        if(winnerIndex == -1)
            return false;
        switch (winnerIndex){
            case 1:
                if(GameEngine.player1Wins == numberToWin){
                    JOptionPane.showMessageDialog(gameWindow, "Player" + winnerIndex + " won the game! \nThanks for playing!"  , "Game over", JOptionPane.INFORMATION_MESSAGE);
                    gameWindow.dispose();
                    new MainMenuWindow();
                    GameEngine.setPlayerWinsToZero();
                    return true;
                }
            break;
            case 2:
                if(GameEngine.player2Wins == numberToWin){
                    JOptionPane.showMessageDialog(gameWindow, "Player" + winnerIndex + " won the game! \nThanks for playing!"  , "Game over", JOptionPane.INFORMATION_MESSAGE);
                    gameWindow.dispose();
                    new MainMenuWindow();
                    GameEngine.setPlayerWinsToZero();
                    return true;
                }
            break;
            case 3:
                if(GameEngine.player3Wins == numberToWin){
                    JOptionPane.showMessageDialog(gameWindow, "Player" + winnerIndex + " won the game! \nThanks for playing!"  , "Game over", JOptionPane.INFORMATION_MESSAGE);
                    gameWindow.dispose();
                    new MainMenuWindow();
                    GameEngine.setPlayerWinsToZero();
                    return true;
                }
            break;
        }
        return false;
    }

    class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            placeBox();
            placeBomb();
            engine.explodeBombs();
            engine.explosionEffects();
            updatePos();
            engine.moveMonsters();
            engine.clearDeadMonsters();
            engine.pickupDrops();
            repaint();
            gameWindow.getPowerupPanel().updateLabels();

            if (engine.isGameOver()){
                timer.stop();
                int winnerIndex = getWinner();
                //increasing the win of the player who won, if not draw
                if(winnerIndex != -1){
                    switch (winnerIndex){
                        case 1: GameEngine.player1Wins++; break;
                        case 2: GameEngine.player2Wins++; break;
                        case 3: GameEngine.player3Wins++; break;
                    }
                }
                //checking final winner
                if (checkWinner(winnerIndex)){
                    timer.stop();
                    removeKeyListener(keyH);
                    return;
                }
                JOptionPane.showMessageDialog(gameWindow, winnerIndex == - 1 ? "Draw! Onto the next one!"  : "Player" + winnerIndex + " won this round! Onto the next one!"  , "Next Game", JOptionPane.INFORMATION_MESSAGE);
                gameWindow.dispose();
                new GameWindow(numberToWin);
            }
        }

    }
}
