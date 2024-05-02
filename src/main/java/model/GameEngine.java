package model;

import assets.Images;
import java.awt.Graphics2D;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import view.GamePanel;
import view.MainMenuWindow;
import static assets.AssetLoader.*;
import static assets.Controls.controls;
import static view.MainMenuWindow.is2PlayerGame;

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
    private final ArrayList<Drop> drops;

    public static int player1Wins = 0;
    public static int player2Wins = 0;
    public static int player3Wins = 0;

    public GameEngine() {
        players = new ArrayList<>();
        monsters = new ArrayList<>();
        explodedBombs = new ArrayList<>();
        drops = new ArrayList<>();
        
        if (is2PlayerGame)
            add2Players();
        else
            add3Players();
        addMonsters();

        gameMap = new Sprite[GamePanel.MAP_SIZE][GamePanel.MAP_SIZE];
        mapString = loadMap();
        initMap();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public static void setPlayerWinsToZero(){
        GameEngine.player1Wins = 0;
        GameEngine.player2Wins = 0;
        GameEngine.player3Wins = 0;
    }

    /**
     * Loads the map from the txt using loadTxt() from AssetLoader class
     *
     * @return String[][]
     */
    private String[][] loadMap() {
        String mapName = MainMenuWindow.getMap();
        InputStream is = loadTxt("assets/" + mapName + ".txt");
        String[][] matrix = new String[GamePanel.MAP_SIZE][GamePanel.MAP_SIZE];

        try (Scanner sc = new Scanner(is)) {
            int i = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] stringArray = line.split("");
                matrix[i] = stringArray;
                i++;
            }
        } catch (Exception e) {
            System.out.println("Error while reading map from txt!");
        }

        return matrix;
    }

    /**
     * Creating the gameMap Sprite matrix
     */
    private void initMap() {
        for (int i = 0; i < mapString.length; i++) {
            for (int j = 0; j < mapString[i].length; j++) {
                switch (mapString[i][j]) {
                    case "W":
                        gameMap[i][j] = new Block(j * GamePanel.BLOCK_PIXEL_SIZE, i * GamePanel.BLOCK_PIXEL_SIZE, GamePanel.BLOCK_PIXEL_SIZE,
                                Images.wallImg);
                        break;
                    case "P":
                        gameMap[i][j] = new Path(j * GamePanel.BLOCK_PIXEL_SIZE, i * GamePanel.BLOCK_PIXEL_SIZE, GamePanel.BLOCK_PIXEL_SIZE,
                                Images.pathImg);
                        break;
                    case "B":
                        gameMap[i][j] = new Box(j * GamePanel.BLOCK_PIXEL_SIZE, i * GamePanel.BLOCK_PIXEL_SIZE, GamePanel.BLOCK_PIXEL_SIZE,
                                Images.boxImg);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * Adding 2 players
     */
    private void add2Players() {
        players.add(new Player(60, 60, GamePanel.PLAYER_PIXEL_SIZE, Images.player1Img, this, controls[0]));
        players.add(new Player(60, 360, GamePanel.PLAYER_PIXEL_SIZE, Images.player2Img, this, controls[1]));
    }

    /**
     * Adding 3 players
     */
    private void add3Players() {
        add2Players();
        players.add(new Player(260, 360, GamePanel.PLAYER_PIXEL_SIZE, Images.player3Img, this, controls[2]));
    }
    
     /**
     * Adding monsters
     */
    private void addMonsters() {
        monsters.add(new Monster(650, 60, GamePanel.PLAYER_PIXEL_SIZE, Images.monsterImg, this));
        monsters.add(new Monster(650, 120, GamePanel.PLAYER_PIXEL_SIZE, Images.monsterImg, this));
    }

    /**
     * Checks if monster catches player
     *
     * @param monster monster
     */
    private void checkCollisionsWithPlayers(Monster monster) {
        for (Player player : players) {
            if (monster.collidesWith(player) && !player.getPowerups().contains("I")) {
                player.isAlive = false;
            }
        }
    }

    /**
     * Monster-monster collision
     *
     * @param monster
     */
    private void checkCollisionsWithMonsters(Monster monster) {
        for (Monster m : monsters) {
            if (m != monster && monster.collidesWith(m)) {
                m.direction = Direction.oppositeDirection(m.direction);
                monster.direction = Direction.oppositeDirection(monster.direction);
            }
        }
    }
    
    /**
     * Move monsters
     */
    public void moveMonsters() {
        for (Monster m : monsters) {
            m.move();
            //két metódus
            checkCollisionsWithPlayers(m);
            //checkCollisionsWithMonsters(m);
        }
    }

    /**
     * Draws players
     * @param g
     */
    public void drawPlayers(Graphics2D g) {
        for (Player p : players) {
            if (p.isAlive){
                p.draw(g);
            }
        }
    }

    /**
     * Draws monsters
     * @param g
     */
    public void drawMonsters(Graphics2D g) {
        for (Monster m : monsters) {
            m.draw(g);
        }
    }
    
    /**
     * Draws the drops
     * @param g 
     */    
    public void drawDrops(Graphics2D g){
        for (Drop d : drops){
            d.draw(g);
        }
    }
    
    /**
     * Draws the map
     * @param g
     */
    public void drawMap(Graphics2D g) {
        for (Sprite[] row : gameMap) {
            for (Sprite sprite : row) {
                sprite.draw(g);
            }
        }
    }
    
    /**
     * Draws bombs and fires
     * @param g 
     */
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
    
    /**
     * Drawing all the sprites
     * @param g 
     */
    public void drawSprites(Graphics2D g){
        drawMap(g);
        drawDrops(g);
        drawBombsAndFires(g);
        drawPlayers(g);
        drawMonsters(g);
    }
    
    /**
     * This functions makes the bombs explode
     */
    public void explodeBombs(){
        for (Player p : players){
            ArrayList<Bomb> bombsCopy = new ArrayList<>(p.getPlacedBombs());
            for (Bomb b : bombsCopy){
                if (b.isReadyToExplode()){
                    // remove bomb from map after explosion
                    mapString[b.currentMatrixPosition().x][b.currentMatrixPosition().y] = "P";
                    p.getPlacedBombs().remove(b);
                    
                    // explosion
                    b.setFires(generateFires(b));
                    explodedBombs.add(b);
                    b.explosion();
                }
            }
        }
        
        // delete exploded bombs
        ArrayList<Bomb> explodedBombsCopy = new ArrayList<>(explodedBombs);
        for (Bomb b : explodedBombsCopy){
            if (b.isExplosionOver()){
                explodedBombs.remove(b);
            }
        } 
    }
    
    /**
     * Generates fires for the bomb
     * @param bomb
     * @return 
     */
    private ArrayList<Fire> generateFires(Bomb bomb){
        ArrayList<Fire> fires = new ArrayList<>();
        
        int bombRow = bomb.currentMatrixPosition().x;
        int bombCol = bomb.currentMatrixPosition().y;
        
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
                        if (mapString[xInd][yInd].equals("B") || mapString[xInd][yInd].equals("Bomb")){
                            break; 
                        }
                    }
                }
            }
        }
        
        return fires;
    }
    
    /**
     * Does the explosion effect
     */
    public void explosionEffects(){
        for (Bomb b : explodedBombs){
            for (Fire f : b.getFires()){
                if (f.isActive){
                    int row = f.currentMatrixPosition().x;
                    int col = f.currentMatrixPosition().y;
                    if (mapString[row][col].equals("B")){
                        Box explodedBox = (Box)gameMap[row][col];
                        if (explodedBox.containsDrop()){
                            drops.add(new Drop(col * GamePanel.BLOCK_PIXEL_SIZE, row * GamePanel.BLOCK_PIXEL_SIZE));
                        }
                        mapString[row][col] = "P";
                        gameMap[row][col] = new Path(col * GamePanel.BLOCK_PIXEL_SIZE, row * GamePanel.BLOCK_PIXEL_SIZE, 
                                GamePanel.BLOCK_PIXEL_SIZE, Images.pathImg);
                    }
                    else if (mapString[row][col].equals("Bomb")){
                        for (Player p : players){
                            for (Bomb bomb : p.getPlacedBombs()){
                                if (bomb.currentMatrixPosition().x == row && bomb.currentMatrixPosition().y == col){
                                    bomb.explode();
                                }
                            }
                        }
                    }
                    else {
                        for (Player p : players){
                            if (f.collidesWith(p) && !p.getPowerups().contains("I")){
                                p.isAlive = false;
                            }
                        }
                        for (Monster m : monsters){
                            if (f.collidesWith(m)){
                                m.isAlive = false;
                            }
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Clears dead monsters from the map so they're not visible
     */
    public void clearDeadMonsters(){
        ArrayList<Monster> monstersCopy = new ArrayList<>(monsters);
        for (Monster m : monstersCopy){
            if (!m.isAlive){
                monsters.remove(m);
            }
        } 
    }
    
    /**
     * Checks the number of players alive, checks if at least one bomb is placed or an explosion
     * is in progress.
     * @return if the game is over
     */
    public boolean isGameOver(){
        int alivePlayers = 0;
        boolean areBombsPlaced = false;
        
        for (Player p : players){
            if (p.isAlive){
                alivePlayers++;
            }
            if (!p.getPlacedBombs().isEmpty()){
                areBombsPlaced = true;
            }
        }
        
        return alivePlayers <= 1 && explodedBombs.isEmpty() && !areBombsPlaced;
    }
    
    /**
     * Player picks up drop
     */
    public void pickupDrops(){
        ArrayList<Drop> dropsCopy = new ArrayList<>(drops);
        for (Drop d : dropsCopy){
            for (Player p : players){
                if (d.collidesWith(p)){
                    p.powerupChooser(d.getType());
                    drops.remove(d);
                }
            }
        }
    }
}
