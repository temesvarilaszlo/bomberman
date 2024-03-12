/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Image;
import java.awt.Point;
import view.GamePanel;

/**
 *
 * @author tlasz
 */
public class Character extends Sprite{
    protected Speed speed;
    protected Direction direction;
    protected boolean isAlive;

    public Character(int x, int y, int size, Image img) {
        super(x, y, size, img);
        isAlive = true;
        speed = Speed.NORMAL;
        direction = Direction.STOPPED;
    }
    
    
    public void move(){
        x += direction.x;
        y += direction.y;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    public Point currentMatrixPosition(){
        return new Point((x + size / 2) / GamePanel.BLOCK_PIXEL_SIZE, (y + size / 2) / GamePanel.BLOCK_PIXEL_SIZE);
    }
}
