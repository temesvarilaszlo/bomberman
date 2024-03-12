/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Graphics2D;
import java.awt.Image;

/**
 *
 * @author tlasz
 */
public class Sprite {
    protected int x;
    protected int y;
    protected int size;
    protected Image img;

    protected Sprite(int x, int y, int size, Image img) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.img = img;
    }
    
    public void draw(Graphics2D g){
        g.drawImage(img, x, y, size, size, null);
    }
}
