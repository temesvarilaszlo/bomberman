package model;

import static assets.AssetLoader.*;
import assets.Images;
import java.awt.Graphics2D;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import view.GamePanel;
import view.MainMenuWindow;
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

    public GameEngine() {
        players = new ArrayList<>();
        monsters = new ArrayList<>();
        if (is2PlayerGame) {
            add2Players();
        } else {
            add3Players();
        }
        monsters.add(new Monster(60, 260, GamePanel.PLAYER_PIXEL_SIZE, Images.whiteImg, this));
        monsters.add(new Monster(60, 310, GamePanel.PLAYER_PIXEL_SIZE, Images.whiteImg, this));

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

    /**
     * Loads the map from the txt using loadTxt() from AssetLoader class
     *
     * @return
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
        players.add(new Player(60, 60, GamePanel.PLAYER_PIXEL_SIZE, Images.whiteImg, this, controls[0]));
        players.add(new Player(60, 360, GamePanel.PLAYER_PIXEL_SIZE, Images.whiteImg, this, controls[1]));
    }

    /**
     * Adding 3 players
     */
    private void add3Players() {
        players.add(new Player(60, 60, GamePanel.PLAYER_PIXEL_SIZE, Images.whiteImg, this, controls[0]));
        players.add(new Player(60, 360, GamePanel.PLAYER_PIXEL_SIZE, Images.whiteImg, this, controls[1]));
        players.add(new Player(260, 360, GamePanel.PLAYER_PIXEL_SIZE, Images.whiteImg, this, controls[2]));
    }

    /**
     * Checks if monster catches player
     *
     * @param monster
     */
    private void checkCollisionsWithPlayers(Monster monster) {
        for (Player player : players) {
            if (monster.collidesWith(player)) {
                player.isAlive = false;
            }
        }
    }

    /**
     * Monster-monster collision
     *
     * @param g
     */
    /*private void checkCollisionsWithMonsters(Monster monster) {
        for (Monster m : monsters) {
            if (m != monster && monster.collidesWith(m)) {
                m.direction = Direction.oppositeDirection(m.direction);
                monster.direction = Direction.oppositeDirection(monster.direction);
            }
        }
    }*/
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
     *
     * @param g
     */
    public void drawPlayers(Graphics2D g) {
        for (Player p : players) {
            p.draw(g);
        }
    }

    /**
     * Draws monsters
     *
     * @param g
     */
    public void drawMonsters(Graphics2D g) {
        for (Monster m : monsters) {
            m.draw(g);
        }
    }

    /**
     * Draws bombs
     *
     * @param g
     */
    public void drawBombs(Graphics2D g) {
        for (Player p : players) {
            for (Bomb b : p.getPlacedBombs()) {
                b.draw(g);
            }
        }
    }

    /**
     * Draws the map
     *
     * @param g
     */
    public void drawMap(Graphics2D g) {
        for (Sprite[] row : gameMap) {
            for (Sprite sprite : row) {
                sprite.draw(g);
            }
        }
    }
}
