package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.Direction;
import model.GameEngine;
import model.KeyHandler;

/**
 *
 * @author tlasz
 */
public class GamePanel extends JPanel{
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
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setFocusable(true);
        
        // initialize engine related stuff
        engine = new GameEngine();
        keyH = new KeyHandler();
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
        engine.drawMap(g);
        engine.drawBombs(g);
        engine.drawPlayers(g);
        engine.drawMonsters(g);
    }
    
    private void updatePos(){
        if(keyH.upPressed){
            engine.getPlayers().get(0).setDirection(Direction.UP);
            engine.getPlayers().get(0).move();
        }
        else if(keyH.downPressed){
            engine.getPlayers().get(0).setDirection(Direction.DOWN);
            engine.getPlayers().get(0).move();
        }
        else if(keyH.rightPressed){
            engine.getPlayers().get(0).setDirection(Direction.RIGHT);
            engine.getPlayers().get(0).move();
        }
        else if(keyH.leftPressed){
            engine.getPlayers().get(0).setDirection(Direction.LEFT);
            engine.getPlayers().get(0).move();
        }
        else{
            engine.getPlayers().get(0).setDirection(Direction.STOPPED);
        }
    }
    
    private void placeBombs(){
        if (keyH.placeBomb){
            engine.getPlayers().get(0).placeBomb();
            keyH.placeBomb = false;
        }
    }
    
    class TimerListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            //engine.movePlayers();
            placeBombs();
            engine.explodeBombs();
            updatePos();
            engine.moveMonsters();
            repaint();
        }
            
    }
}
