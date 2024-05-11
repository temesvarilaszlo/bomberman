import model.Drop;
import model.Monster;
import model.Player;
import utilz.Controls;
import model.GameEngine;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utilz.Images;

import java.awt.*;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static utilz.AssetLoader.loadFont;
import static utilz.AssetLoader.loadTxt;

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
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of explosionEffects method, of class GameEngine.
     */
    @Test
    public void testExplosionEffects() {
        System.out.println("explosionEffects");
        GameEngine instance = new GameEngine();
        instance.explosionEffects();
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
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
