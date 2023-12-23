package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler  implements KeyListener {// the listener interface for receiving keyboard events (keystokes)
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    //DEBUG
    public static boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();// Returns the integer keyCode associated with the key in this event

        //TITLE STATE
        if(gp.gameState == gp.titleState){
             if(code == KeyEvent.VK_W){
                gp.ui.commandNum--;// move upward
                if(gp.ui.commandNum<0){
                    gp.ui.commandNum=2;// when you move the cursor more than new game it return to quit
                }
        }
        if(code == KeyEvent.VK_S){
           gp.ui.commandNum++;// move downward
           if(gp.ui.commandNum>2){
            gp.ui.commandNum=0;// when you move the cursor lower than quit it return to new game
           }
        }
        }
        // PLAY STATE
        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        //DEBUG
        if(code == KeyEvent.VK_T){
            if (checkDrawTime == false) {
                checkDrawTime = true;
            }
            else if (checkDrawTime == true) {
                checkDrawTime = false;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        //Press P to pause
        if(code == KeyEvent.VK_P){
            if (gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
            }
            else if (gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
            }
        }
    }
}
