/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import assets.Images;
import java.awt.Image;

/**
 *
 * @author tlasz
 */
public class Fire extends Block{
    public int wave;
    public boolean isActive;
    
    public Fire(int x, int y, int size, int wave) {
        super(x, y, size, Images.fireImg);
        this.wave = wave;
        isActive = false;
    }
    
}
