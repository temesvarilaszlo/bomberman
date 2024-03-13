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
import model.GameEngine;

/**
 *
 * @author tlasz
 */
public class GamePanel extends JPanel{
    public static final int BLOCK_PIXEL_SIZE = 50;
    public static final int PLAYER_PIXEL_SIZE = 30;
    public static final int MAP_SIZE = 15;
    public static final int WIDTH = BLOCK_PIXEL_SIZE * MAP_SIZE;
    public static final int HEIGHT = BLOCK_PIXEL_SIZE * MAP_SIZE;
    
    private GameEngine engine;
    private Timer timer;

    public GamePanel() throws IOException {
        super();
        
        // GUI stuff
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        // initialize engine related stuff
        engine = new GameEngine();
        
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
        engine.drawPlayers(g);
    }
    
    class TimerListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            engine.movePlayers();
            System.out.println(engine.getPlayers().get(0).currentMatrixPosition());
            repaint();
        }
            
    }
}
