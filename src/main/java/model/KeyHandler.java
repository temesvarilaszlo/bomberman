package model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static view.MainMenuWindow.Is2PlayerGame;

public class KeyHandler implements KeyListener {

    public boolean upPressed1, downPressed1, leftPressed1, rightPressed1, bombReady1, placeBomb1, placeBox1;
    public boolean upPressed2, downPressed2, leftPressed2, rightPressed2, bombReady2, placeBomb2, placeBox2;
    public boolean upPressed3, downPressed3, leftPressed3, rightPressed3, bombReady3, placeBomb3, placeBox3;
    private GameEngine engine;

    public KeyHandler(GameEngine engine) {
        super();
        upPressed1 = false;
        downPressed1 = false;
        leftPressed1 = false;
        rightPressed1 = false;
        placeBomb1 = false;
        bombReady1 = true;
        placeBox1 = false;

        upPressed2 = false;
        downPressed2 = false;
        leftPressed2 = false;
        rightPressed2 = false;
        placeBomb2 = false;
        bombReady2 = true;
        placeBox2 = false;

        upPressed3 = false;
        downPressed3 = false;
        leftPressed3 = false;
        rightPressed3 = false;
        placeBomb3 = false;
        bombReady3 = true;
        placeBox3 = false;

        this.engine = engine;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        Player player1 = engine.getPlayers().get(0);
        if (code == player1.getControls().get(0)) {
            upPressed1 = true;
        } else if (code == player1.getControls().get(1)) {
            rightPressed1 = true;
        } else if (code == player1.getControls().get(2)) {
            downPressed1 = true;
        } else if (code == player1.getControls().get(3)) {
            leftPressed1 = true;
        } else if (code == player1.getControls().get(4) && bombReady1) {
            placeBomb1 = true;
            bombReady1 = false;
        }else if (code == player1.getControls().get(5)) {
            placeBox1 = true;
        }

        Player player2 = engine.getPlayers().get(1);
        if (code == player2.getControls().get(0)) {
            upPressed2 = true;
        } else if (code == player2.getControls().get(1)) {
            rightPressed2 = true;
        } else if (code == player2.getControls().get(2)) {
            downPressed2 = true;
        } else if (code == player2.getControls().get(3)) {
            leftPressed2 = true;
        } else if (code == player2.getControls().get(4) && bombReady2) {
            placeBomb2 = true;
            bombReady2 = false;
        } else if (code == player2.getControls().get(5)) {
            placeBox2 = true;
        }
        

        if (!Is2PlayerGame()) {
            Player player3 = engine.getPlayers().get(2);
            if (code == player3.getControls().get(0)) {
                upPressed3 = true;
            } else if (code == player3.getControls().get(1)) {
                rightPressed3 = true;
            } else if (code == player3.getControls().get(2)) {
                downPressed3 = true;
            } else if (code == player3.getControls().get(3)) {
                leftPressed3 = true;
            } else if (code == player3.getControls().get(4) && bombReady3) {
                placeBomb3 = true;
                bombReady3 = false;
            } else if (code == player3.getControls().get(5)) {
                placeBox3 = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        Player player1 = engine.getPlayers().get(0);
        if (code == player1.getControls().get(0)) {
            upPressed1 = false;
        } else if (code == player1.getControls().get(1)) {
            rightPressed1 = false;
        } else if (code == player1.getControls().get(2)) {
            downPressed1 = false;
        } else if (code == player1.getControls().get(3)) {
            leftPressed1 = false;
        } else if (code == player1.getControls().get(4)) {
            placeBomb1 = false;
            bombReady1 = true;
        } else if (code == player1.getControls().get(5)) {
            placeBox1 = false;
        }

        Player player2 = engine.getPlayers().get(1);
        if (code == player2.getControls().get(0)) {
            upPressed2 = false;
        } else if (code == player2.getControls().get(1)) {
            rightPressed2 = false;
        } else if (code == player2.getControls().get(2)) {
            downPressed2 = false;
        } else if (code == player2.getControls().get(3)) {
            leftPressed2 = false;
        } else if (code == player2.getControls().get(4)) {
            placeBomb2 = false;
            bombReady2 = true;
        } else if (code == player2.getControls().get(5)) {
            placeBox2 = false;
        }

        if (!Is2PlayerGame()) {
            Player player3 = engine.getPlayers().get(2);
            if (code == player3.getControls().get(0)) {
                upPressed3 = false;
            } else if (code == player3.getControls().get(1)) {
                rightPressed3 = false;
            } else if (code == player3.getControls().get(2)) {
                downPressed3 = false;
            } else if (code == player3.getControls().get(3)) {
                leftPressed3 = false;
            } else if (code == player3.getControls().get(4)) {
                placeBomb3 = false;
                bombReady3 = true;
            } else if (code == player3.getControls().get(5)) {
                placeBox3 = false;
            }
        }
    }
}
