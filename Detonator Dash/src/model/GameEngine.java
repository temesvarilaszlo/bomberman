package model;

import static assets.AssetLoader.*;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import view.GamePanel;

/**
 *
 * @author tlasz
 */
public class GameEngine {
    private String[][] mapString;
    private Sprite[][] gameMap;
    private ArrayList<Player> players;
    private ArrayList<Monster> monsters;

    public GameEngine() throws IOException {
        players = new ArrayList<>();
        players.add(new Player(100, 100, GamePanel.PLAYER_PIXEL_SIZE, loadImage("assets/white.png")));
        players.get(0).setDirection(Direction.RIGHT);
        
        gameMap = new Sprite[GamePanel.MAP_SIZE][GamePanel.MAP_SIZE];
        mapString = loadMap();
        
        // initialize map
        for (int i = 0; i < gameMap.length; i++) {
            for (int j = 0; j < gameMap[i].length; j++) {
                if(mapString[i][j].equals("W")){
                    gameMap[i][j] = new Block(i*GamePanel.BLOCK_PIXEL_SIZE, j*GamePanel.BLOCK_PIXEL_SIZE, GamePanel.BLOCK_PIXEL_SIZE, 
                        loadImage("assets/wall.png"));
                }
                else if(mapString[i][j].equals("P")){
                    gameMap[i][j] = new Path(i*GamePanel.BLOCK_PIXEL_SIZE, j*GamePanel.BLOCK_PIXEL_SIZE, GamePanel.BLOCK_PIXEL_SIZE, 
                        loadImage("assets/path.png"));
                }
                else if(mapString[i][j].equals("B")){
                    gameMap[i][j] = new Box(i*GamePanel.BLOCK_PIXEL_SIZE, j*GamePanel.BLOCK_PIXEL_SIZE, GamePanel.BLOCK_PIXEL_SIZE, 
                        loadImage("assets/Box.png"));
                }
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
    
    public String[][] loadMap(){
        InputStream is = loadTxt("assets/map1.txt");
        String[][] matrix = new String[GamePanel.MAP_SIZE][GamePanel.MAP_SIZE];
        
        try (Scanner sc = new Scanner(is)){
            int i = 0;
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                String[] stringArray = line.split("");
                matrix[i] = stringArray;
                i++;
            }
        } catch (Exception e){
            System.out.println("Ajaj");
        }
        
        return matrix;
    }
    
    public void drawMap(Graphics2D g){
        for (Sprite[] row : gameMap) {
            for (Sprite sprite : row) {
                sprite.draw(g);
            }
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
    
}
