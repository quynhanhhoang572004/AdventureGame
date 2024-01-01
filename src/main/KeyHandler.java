package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//The "listener" interface for receiving keyboard events (keystrokes)
public class KeyHandler  implements KeyListener, MouseListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, FPressed;
	public boolean leftMouse;    
    public static boolean checkDrawTime = false;
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

	//STATUS: MOUSEPRESSED
	@Override
	public void mousePressed(MouseEvent e){
		int code = e.getButton();
		if (gp.gameState == gp.playState) {
			if(code == MouseEvent.BUTTON1){
				leftMouse = true;
			}
		}
		//else if (gp.gameState == gp.dialogueState) {
	        //if(code == MouseEvent.BUTTON1) {
	        	//gp.gameState = gp.playState;
	        //}
	    //}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int code = e.getButton();
		if (code == MouseEvent.BUTTON1) { 
            leftMouse = false;
        }
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

    // STATUS: KEYTYPED
    @Override
    public void keyTyped(KeyEvent e) {
    }   
 
    
    // STATUS: KEYPRESSED
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();	// Returns the integer keyCode associated with the key in this event
			
		// TITLE STATE
	    if(gp.gameState == gp.titleState){
			titleState(code);
		}

		// PLAY STATE
		else if(gp.gameState == gp.playState){
			playState(code);
		}
	        
	        
	    // PAUSE STATE
	    else if (gp.gameState == gp.pauseState) {
			pauseState(code);
	    }

	    // DIALOGUE STATE
	    else if (gp.gameState == gp.dialogueState) {
	        dialogueState(code);
	    }

		//CHARACTER STATE
		else if(gp.gameState == gp.characterState){
			characterState(code);
		}        
	}
	public void titleState(int code){      
	    if(gp.ui.titleScreenState  ==  0){
	        if(code  ==  KeyEvent.VK_W){
	            gp.ui.commandNum--;		// move upward
	            if(gp.ui.commandNum < 0){
	                gp.ui.commandNum = 2;	// when you move the cursor more than new game it return to quit
	            }
	        }
	        if(code  ==  KeyEvent.VK_S){
	           	gp.ui.commandNum++;		// move downward
	           	if(gp.ui.commandNum > 2){
	            	gp.ui.commandNum = 0;		// when you move the cursor lower than quit it return to new game
	           	}
	        }
		    if(code  ==  KeyEvent.VK_ENTER){
		       	if(gp.ui.commandNum  ==  0){
		          	gp.ui.titleScreenState = 1;
		       	}
		       	if(gp.ui.commandNum == 1){
		        // add later 
		       	}
		       	if(gp.ui.commandNum == 2){
		          	System.exit(0);
				}
			}
		}	        	                   
		else if(gp.ui.titleScreenState == 1){
		    if(code  ==  KeyEvent.VK_W){
		        gp.ui.commandNum--;			// move upward
		        if(gp.ui.commandNum < 0){
		            gp.ui.commandNum = 3;	// when you move the cursor more than new game it return to quit
		        }
		    }
		    if(code  ==  KeyEvent.VK_S){
		        gp.ui.commandNum++;		// move downward
		        if(gp.ui.commandNum > 3){
		        	gp.ui.commandNum = 0;	// when you move the cursor lower than quit it return to new game
		        }
		    }
		    if(code  ==  KeyEvent.VK_ENTER){
		        if(gp.ui.commandNum  ==  0){
		            System.out.println("Do some fighter specific stuff");
		            gp.gameState = gp.playState;
		            gp.playMusic(0);
		        }
		        if(gp.ui.commandNum == 1){
		            System.out.println("Do some thief specific stuff");
		            gp.gameState = gp.playState;
		            gp.playMusic(0);
		        }
		        if(gp.ui.commandNum == 2){
		            System.out.println("Do some sorcerer specific stuff");
		            gp.gameState = gp.playState;
		            gp.playMusic(0);
		        }		
		        if(gp.ui.commandNum == 3){
		            gp.ui.titleScreenState = 0;
		        }
		    }
		}
	}
	public void playState(int code){
	    if(code  ==  KeyEvent.VK_W){
	        upPressed = true;
	    }
	    if(code  ==  KeyEvent.VK_S){
	        downPressed = true;
	    }
	    if(code  ==  KeyEvent.VK_A){
	        leftPressed = true;
	    }
	    if(code  ==  KeyEvent.VK_D){
	        rightPressed = true;
	    }
	    if(code == KeyEvent.VK_P) {
            gp.gameState = gp.pauseState;
	    }
		if(code == KeyEvent.VK_C){
			gp.gameState = gp.characterState;
		}
	    if(code == KeyEvent.VK_ENTER) {
            enterPressed = true;
	    }
		if(code == KeyEvent.VK_F){
			FPressed = true;
		}
	            
	    // Debug
	    if(code  ==  KeyEvent.VK_T){
	        if (checkDrawTime  ==  false) {
	        	checkDrawTime = true;
	        }
	        else if (checkDrawTime  ==  true) {
	            checkDrawTime = false;
	        }           
	    }
	}
	public void pauseState(int code){
		if(code == KeyEvent.VK_P){
            gp.gameState = gp.playState;
	    }
	}
	public void dialogueState(int code){
		if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_F) {
	        gp.gameState = gp.playState;
	    }
	}
	public void characterState(int code){
		if(code == KeyEvent.VK_C){
			gp.gameState = gp.playState;
		}
	}
    
    // STATUS: KEYRELEASED
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code  ==  KeyEvent.VK_W){
            upPressed = false;
        }
        if(code  ==  KeyEvent.VK_S){
            downPressed = false;
        }
        if(code  ==  KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code  ==  KeyEvent.VK_D){
            rightPressed = false;
        }
		if(code == KeyEvent.VK_F){
			FPressed = false;
		}
		
    }

	
}


