package model;

import static assets.AssetLoader.*;
import assets.Images;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.Timer;
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
    private final ArrayList<Bomb> explodedBombs;

    public GameEngine()  {
        players = new ArrayList<>();
        monsters = new ArrayList<>();
        explodedBombs = new ArrayList<>();
        players.add(new Player(60, 60, GamePanel.PLAYER_PIXEL_SIZE, Images.whiteImg));
        monsters.add(new Monster(660, 60, GamePanel.PLAYER_PIXEL_SIZE, Images.whiteImg));
        monsters.add(new Monster(560, 60, GamePanel.PLAYER_PIXEL_SIZE, Images.whiteImg));
        
        gameMap = new Sprite[GamePanel.MAP_SIZE][GamePanel.MAP_SIZE];
        mapString = loadMap();  
        initMap();  
//        generateFires(new Bomb(350, 350, GamePanel.PLAYER_PIXEL_SIZE, Images.bombImg));
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
            System.out.println("Error while reading map from txt!");
        }
        
        return matrix;
    }
    
    private void initMap() {
        for (int i = 0; i < mapString.length; i++) {
            for (int j = 0; j < mapString[i].length; j++) {
                switch (mapString[i][j]) {
                    case "W":
                        gameMap[i][j] = new Block(j*GamePanel.BLOCK_PIXEL_SIZE, i*GamePanel.BLOCK_PIXEL_SIZE, GamePanel.BLOCK_PIXEL_SIZE,
                                Images.wallImg);
                        break;
                    case "P":
                        gameMap[i][j] = new Path(j*GamePanel.BLOCK_PIXEL_SIZE, i*GamePanel.BLOCK_PIXEL_SIZE, GamePanel.BLOCK_PIXEL_SIZE,
                                Images.pathImg);
                        break;
                    case "B":
                        gameMap[i][j] = new Box(j*GamePanel.BLOCK_PIXEL_SIZE, i*GamePanel.BLOCK_PIXEL_SIZE, GamePanel.BLOCK_PIXEL_SIZE,
                                Images.boxImg);
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
    
    public void drawBombsAndFires(Graphics2D g){
        for (Player p : players){
            for (Bomb b : p.getPlacedBombs()){
                b.draw(g);
            }
        }
        
        for (Bomb b : explodedBombs){
            for (Fire f : b.getFires()){
                if (f.isActive){
                    f.draw(g);
                }
            }
        }
    }
    
    public void explodeBombs(){
        for (Player p : players){
            ArrayList<Bomb> bombsCopy = new ArrayList<>(p.getPlacedBombs());
            for (Bomb b : bombsCopy){
                if (b.isReadyToExplode()){
                    // remove bomb from map after explosion
                    System.out.println("Bomba robban " + b.x + " " + b.y);
                    mapString[b.currentMatrixPosition().x][b.currentMatrixPosition().y] = "P";
                    p.getPlacedBombs().remove(b);
                    
                    // explosion
                    b.setFires(generateFires(b));
                    explodedBombs.add(b);
                    b.explosion();
                }
            }
        }
    }
    
    private ArrayList<Fire> generateFires(Bomb bomb){
        ArrayList<Fire> fires = new ArrayList<>();
        
        int bombRow = bomb.currentMatrixPosition().x;
        int bombCol = bomb.currentMatrixPosition().y;
        System.out.println((bombRow) + " " + (bombCol));
        System.out.println(mapString[bombRow][bombCol]);
        
        fires.add(new Fire(bombCol * GamePanel.BLOCK_PIXEL_SIZE, bombRow * GamePanel.BLOCK_PIXEL_SIZE,
                        GamePanel.BLOCK_PIXEL_SIZE, 0));
        
        for (Direction d : Direction.values()){
            if (d != Direction.STOPPED){
                for (int wave = 1; wave <= bomb.getRange(); wave++) {
                    int xInd = bombRow + (d.x*wave);
                    int yInd = bombCol + (d.y*wave);
                    
                    if (xInd >= mapString.length || yInd >= mapString[0].length || mapString[xInd][yInd].equals("W")){
                        break;
                    }
                    else {
                        fires.add(new Fire(yInd * GamePanel.BLOCK_PIXEL_SIZE, xInd * GamePanel.BLOCK_PIXEL_SIZE,
                        GamePanel.BLOCK_PIXEL_SIZE, wave));
                        if (mapString[xInd][yInd].equals("B")){
                            break; 
                        }
                    }
                    
//                    System.out.println((bombRow + (d.x*wave)) + " " + (bombCol + (d.y*wave)));
//                    System.out.println(mapString[bombRow + (d.x*wave)][bombRow + (d.y*wave)]);
                }
            }
        }
        
        return fires;
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
