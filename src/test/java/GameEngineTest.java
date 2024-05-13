import model.*;
import utilz.Controls;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utilz.Images;
import view.GamePanel;
import view.MainMenuWindow;

import java.awt.*;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static utilz.AssetLoader.loadFont;
import static utilz.AssetLoader.loadTxt;
import static utilz.Controls.controls;

/**
 *
 * @author tlasz
 */
public class GameEngineTest {
    
    public GameEngineTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        new Controls();
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of explodeBombs method, of class GameEngine.
     */
    @Test
    public void testExplodeBombs() {
        GameEngine instance = new GameEngine();
        instance.explodeBombs();
    }

    /**
     * Test of explosionEffects method, of class GameEngine.
     */
    @Test
    public void testExplosionEffects() {
        System.out.println("explosionEffects");
        GameEngine instance = new GameEngine();
        instance.explosionEffects();
    }

    /**
     * Test if images load correctly
     */
    @Test
    public void testImages(){
        new Images();
        Image bomb = Images.bombImg;
        Image player1 = Images.player1Img;
        Image ghost = Images.ghostImg;
        Image detonator = Images.detonatorImg;

        assertNotNull(bomb);
        assertNotNull(player1);
        assertNotNull(ghost);
        assertNotNull(detonator);
    }

    /**
     * Test if maps load correctly
     */
    @Test
    public void testMaps(){
        InputStream map1 = loadTxt("assets/map1.txt");
        InputStream map2 = loadTxt("assets/map2.txt");
        InputStream map3 = loadTxt("assets/map3.txt");

        assertNotNull(map1);
        assertNotNull(map2);
        assertNotNull(map3);
    }

    /**
     * Test if font load correctly
     */
    @Test
    public void testFont(){
        Font customFont = loadFont();
        assertNotNull(customFont);
    }

    /**
     * Test of clearDeadMonsters method, of class GameEngine.
     */
    @Test
    public void testClearDeadMonsters() {
        GameEngine instance = new GameEngine();

        Monster monster = new Monster(50, 50, 50, Images.monsterImg, instance);
        instance.getMonsters().add(monster);

        assertTrue(instance.getMonsters().contains(monster));

        monster.setIsAlive(false);
        instance.clearDeadMonsters();

        assertFalse(instance.getMonsters().contains(monster));
    }


    /**
     * Test of isGameOver method, of class GameEngine.
     */
    @Test
    public void testIsGameOver() {
        GameEngine instance = new GameEngine();

        assertFalse(instance.isGameOver());
        instance.getPlayers().get(0).setIsAlive(false);
        assertFalse(instance.isGameOver());
        instance.getPlayers().get(2).setIsAlive(false);
        assertTrue(instance.isGameOver());
    }

    /**
     * Test of player wins start with 0
     */
    @Test
    public void testWinner() {
        assertEquals(GameEngine.player1Wins, 0);
        assertEquals(GameEngine.player2Wins, 0);
        assertEquals(GameEngine.player3Wins, 0);
    }

    /**
     * Test rolleskate power up
     */
    @Test
    public void testSpeedPowerUp(){
        GameEngine instance = new GameEngine();
        Player player = new Player(258, 358, GamePanel.PLAYER_PIXEL_SIZE, Images.player3Img, instance, controls[0]);
        player.powerupChooser("S");

        assertEquals(3, player.getSpeed());
    }

    /**
     * Test bombcapacity power up
     */
    @Test
    public void testBombCapacityPowerUp(){
        GameEngine instance = new GameEngine();
        Player player = new Player(258, 358, GamePanel.PLAYER_PIXEL_SIZE, Images.player3Img, instance, controls[0]);
        int initialBombCapacity = player.getBombCapacity();
        player.powerupChooser("PB");

        assertEquals(initialBombCapacity + 1, player.getBombCapacity());
    }

    /**
     * Test bombrange power up
     */
    @Test
    public void testBombRangePowerUp(){
        GameEngine instance = new GameEngine();
        Player player = new Player(258, 358, GamePanel.PLAYER_PIXEL_SIZE, Images.player3Img, instance, controls[0]);
        int initialBombRange = player.getBombRange();
        player.powerupChooser("PR");

        assertEquals(initialBombRange + 1, player.getBombRange());
    }

    /**
     * Test box place power up
     */
    @Test
    public void testBoxPlacePowerUp(){
        GameEngine instance = new GameEngine();
        Player player = new Player(258, 358, GamePanel.PLAYER_PIXEL_SIZE, Images.player3Img, instance, controls[0]);
        player.powerupChooser("O");

        int sum_O = 0;
        for (int i = 0; i < player.getPowerups().size(); i++) {
            if(player.getPowerups().get(i).equals("O")){
                sum_O++;
            }
        }

        assertEquals(3, sum_O);
    }

    /**
     * Test invincibility place power up
     */
    @Test
    public void testInvincibilityPowerUp(){
        GameEngine instance = new GameEngine();
        Player player = new Player(258, 358, GamePanel.PLAYER_PIXEL_SIZE, Images.player3Img, instance, controls[0]);
        player.powerupChooser("I");
        Monster monster = new Monster(278, 358, GamePanel.PLAYER_PIXEL_SIZE, Images.player3Img, instance);

        if (monster.collidesWith(player) && !player.getPowerups().contains("I")) {
            player.setIsAlive(false);
        }

        assertTrue(player.getIsAlive());
    }

    /**
     * Test of pickupDrops method, of class GameEngine.
     */
    @Test
    public void testPickupDrops() {
        GameEngine instance = new GameEngine();

        Drop drop = new Drop(50, 50, "S");
        instance.getDrops().add(drop);

        assertTrue(instance.getDrops().contains(drop));
        instance.pickupDrops();
        assertFalse(instance.getDrops().contains(drop));

        boolean atLeastOnePlayerHasDrop = false;
        for (Player player : instance.getPlayers()) {
            if (player.getPowerups().contains(drop.getType())) {
                atLeastOnePlayerHasDrop = true;
                break;
            }

        }
        assertTrue(atLeastOnePlayerHasDrop);
    }
}
