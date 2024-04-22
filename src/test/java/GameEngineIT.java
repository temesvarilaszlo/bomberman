/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
import assets.Controls;
import model.GameEngine;
import static assets.Controls.controls;

import java.awt.Graphics2D;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author tlasz
 */
public class GameEngineIT {
    
    public GameEngineIT() {
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
        instance.clearDeadMonsters();
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of isGameOver method, of class GameEngine.
     */
    @Test
    public void testIsGameOver() {
        System.out.println("isGameOver");
        GameEngine instance = new GameEngine();
        boolean expResult = false;
        boolean result = instance.isGameOver();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of pickupDrops method, of class GameEngine.
     */
    @Test
    public void testPickupDrops() {
        System.out.println("pickupDrops");
        GameEngine instance = new GameEngine();
        instance.pickupDrops();
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
