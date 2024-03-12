/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import assets.ImageLoader;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.Timer;
import view.GamePanel;

/**
 *
 * @author tlasz
 */
public class GameEngine {
    private Block[][] gameMap;
    private ArrayList<Player> players;
    private ArrayList<Monster> monsters;

    public GameEngine() throws IOException {
        players = new ArrayList<>();
        players.add(new Player(100, 100, GamePanel.PLAYER_PIXEL_SIZE, ImageLoader.loadImage("assets/white.png")));
        players.get(0).setDirection(Direction.RIGHT);
        
        gameMap = new Block[GamePanel.MAP_SIZE][GamePanel.MAP_SIZE];
        
        // initialize map
        for (int i = 0; i < gameMap.length; i++) {
            for (int j = 0; j < gameMap[i].length; j++) {
                gameMap[i][j] = new Path(i*GamePanel.BLOCK_PIXEL_SIZE, j*GamePanel.BLOCK_PIXEL_SIZE, GamePanel.BLOCK_PIXEL_SIZE, 
                        ImageLoader.loadImage("assets/white_frame.png"));
            }
        }
        
    }
    
    public void drawPlayers(Graphics2D g){
        for (Player p : players){
            p.draw(g);
        }
    }
    
    public void movePlayers(){
        for (Player p : players){
            p.move();
        }
    }
    
    public void drawMap(Graphics2D g){
        for (Block[] row : gameMap) {
            for (Block block : row) {
                block.draw(g);
            }
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
    
}
