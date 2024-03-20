package model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
    public boolean upPressed, downPressed, leftPressed, rightPressed, placeBomb;
    private boolean bombReady;
    
    public KeyHandler(){
        super();
        upPressed = false;
        downPressed = false;
        leftPressed = false;
        rightPressed = false;
        placeBomb = false;
        bombReady = true;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_SHIFT && e.getKeyLocation() == KeyEvent.KEY_LOCATION_LEFT && bombReady){
            placeBomb = true;
            bombReady = false;
        }
        
        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        else if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        else if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        else if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_SHIFT && e.getKeyLocation() == KeyEvent.KEY_LOCATION_LEFT){
            placeBomb = false;
            bombReady = true;
        }
        
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        else if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        else if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        else if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
    
}
