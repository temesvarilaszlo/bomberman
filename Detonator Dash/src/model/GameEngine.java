package model;

import static assets.AssetLoader.*;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import view.GamePanel;
import view.MainMenuWindow;

/**
 *
 * @author tlasz
 */
public class GameEngine {
    public static String[][] mapString;
    private final Sprite[][] gameMap;
    private final ArrayList<Player> players;
    private final ArrayList<Monster> monsters;

    public GameEngine() throws IOException {
        players = new ArrayList<>();
        monsters = new ArrayList<>();
        players.add(new Player(60, 60, GamePanel.PLAYER_PIXEL_SIZE, loadImage("assets/white.png")));
        monsters.add(new Monster(60, 260, GamePanel.PLAYER_PIXEL_SIZE, loadImage("assets/white.png")));
        monsters.add(new Monster(60, 310, GamePanel.PLAYER_PIXEL_SIZE, loadImage("assets/white.png")));
        
        gameMap = new Sprite[GamePanel.MAP_SIZE][GamePanel.MAP_SIZE];
        mapString = loadMap();  
        initMap();  
    }
    
    private String[][] loadMap(){
        String mapName = MainMenuWindow.getMap();
        InputStream is = loadTxt("assets/" + mapName + ".txt");
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
    
    private void initMap() throws IOException{
        for (int i = 0; i < mapString.length; i++) {
            for (int j = 0; j < mapString[i].length; j++) {
                switch (mapString[i][j]) {
                    case "W":
                        gameMap[i][j] = new Block(i*GamePanel.BLOCK_PIXEL_SIZE, j*GamePanel.BLOCK_PIXEL_SIZE, GamePanel.BLOCK_PIXEL_SIZE,
                                loadImage("assets/wall.png"));
                        break;
                    case "P":
                        gameMap[i][j] = new Path(i*GamePanel.BLOCK_PIXEL_SIZE, j*GamePanel.BLOCK_PIXEL_SIZE, GamePanel.BLOCK_PIXEL_SIZE,
                                loadImage("assets/path.png"));
                        break;
                    case "B":
                        gameMap[i][j] = new Box(i*GamePanel.BLOCK_PIXEL_SIZE, j*GamePanel.BLOCK_PIXEL_SIZE, GamePanel.BLOCK_PIXEL_SIZE,
                                loadImage("assets/Box.png"));
                        break;
                    default: break;
                }
            }
        }
    }
    
    public void drawPlayers(Graphics2D g){
        for (Player p : players){
            p.draw(g);
        }
    }
    
    public void drawMonsters(Graphics2D g){
        for (Monster m : monsters){
            m.draw(g);
        }
    }
    
    public void moveMonsters(){
        for (Monster m : monsters){
            m.move();
        }
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
    
    public ArrayList<Monster> getMonsters(){
        return monsters;
    }
}
