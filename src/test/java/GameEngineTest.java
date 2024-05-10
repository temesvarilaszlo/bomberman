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
import view.GamePanel;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
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
        System.out.println("explodeBombs");
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
     * Test of clearDeadMonsters method, of class GameEngine.
     */
    @Test
    public void testClearDeadMonsters() {
        System.out.println("clearDeadMonsters");
        GameEngine instance = new GameEngine();

        Monster monster = new Monster(50, 50, 50, Images.monsterImg, instance);
        instance.getMonsters().add(monster);

        assertEquals(true, instance.getMonsters().contains(monster));

        monster.setIsAlive(false);
        instance.clearDeadMonsters();

        assertEquals(false, instance.getMonsters().contains(monster));
    }


    /**
     * Test of isGameOver method, of class GameEngine.
     */
    @Test
    public void testIsGameOver() {
        System.out.println("isGameOver");
        GameEngine instance = new GameEngine();

        assertEquals(false,instance.isGameOver());
        instance.getPlayers().get(0).setIsAlive(false);
        assertEquals(false,instance.isGameOver());
        instance.getPlayers().get(2).setIsAlive(false);
        assertEquals(true,instance.isGameOver());
    }

    /**
     * Test of pickupDrops method, of class GameEngine.
     */
    @Test
    public void testPickupDrops() {
        System.out.println("pickupDrops");
        GameEngine instance = new GameEngine();

        Drop drop = new Drop(50, 50, "S");
        instance.getDrops().add(drop);

        assertEquals(true, instance.getDrops().contains(drop));
        instance.pickupDrops();
        assertEquals(false, instance.getDrops().contains(drop));

        boolean atLeastOnePlayerHasDrop = false;
        for (Player player : instance.getPlayers()) {
            if (player.getPowerups().contains(drop.getType())) {
                atLeastOnePlayerHasDrop = true;
                break;
            }

        }
        assertEquals(true, atLeastOnePlayerHasDrop);
    }
}
