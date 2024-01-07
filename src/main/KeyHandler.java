package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//The "listener" interface for receiving keyboard events (keystrokes)
public class KeyHandler  implements KeyListener, MouseListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, FPressed, shotKeyPressed;
	public boolean leftMouse;    
    public static boolean showDebugText = false;
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
	    
		//OPTIONS STATE
		else if(gp.gameState == gp.optionsState){
			optionsState(code);
		}    
		//GAMEOVER STATE
		else if(gp.gameState == gp.gameOverState){
			gameOverState(code);
		}    
		//TRADE STATE
		else if(gp.gameState == gp.tradeState){
			tradeState(code);
		}    
	}
    
    public void tradeState(int code) {
    	if (code == KeyEvent.VK_ENTER) {
    		enterPressed = true;
    	}
    	if (gp.ui.subState == 0) {
    		if (code == KeyEvent.VK_W) {
    			gp.ui.commandNum--;
    			if (gp.ui.commandNum < 0) {
    				gp.ui.commandNum = 2;
    			}
    			gp.playSE(9);
    		}
        	if (code == KeyEvent.VK_S) {
        			gp.ui.commandNum++;
        			if (gp.ui.commandNum > 2) {
        				gp.ui.commandNum = 0;
        			}
        			gp.playSE(9);
        		}
        	}         	
        if (gp.ui.subState == 1) {
        	npcInventory(code);
        	if (code == KeyEvent.VK_ESCAPE) {
        		gp.ui.subState = 0;
        	}        		
        }
        if (gp.ui.subState == 2) {
        	playerInventory(code);
        	if (code == KeyEvent.VK_ESCAPE) {
        		gp.ui.subState = 0;
        	}        		
        }
    }    
    
    public void gameOverState(int code) {
    	if (code == KeyEvent.VK_W) {
    		gp.ui.commandNum--;
    		if (gp.ui.commandNum < 0) {
    			gp.ui.commandNum = 1;
    		}
    		gp.playSE(9);
    	} 
    	if (code == KeyEvent.VK_S) {
    		gp.ui.commandNum++;
    		if (gp.ui.commandNum > 1) {
    			gp.ui.commandNum = 0;
    		}
    		gp.playSE(9);
    	}
    	if (code == KeyEvent.VK_ENTER) {
    		if (gp.ui.commandNum == 0) {
    			gp.gameState = gp.playState;
    			gp.retry();
    			gp.playMusic(0);
    		} else if (gp.ui.commandNum == 1) {
    			gp.gameState = gp.titleState;
    			gp.restart();
    		}
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
		       		gp.gameState = gp.playState;
		          	gp.ui.titleScreenState = 1;
		          	gp.playMusic(0);
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
	
	// PLAY STATE
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
		if(code == KeyEvent.VK_G){
			gp.gameState = gp.characterState;
			gp.playSE(10);
		}
	    if(code == KeyEvent.VK_ENTER) {
            enterPressed = true;
	    }
		if(code == KeyEvent.VK_F){
			FPressed = true;
		}
		if(code == KeyEvent.VK_E){
			shotKeyPressed = true;
		}
		if(code == KeyEvent.VK_ESCAPE){
			gp.gameState = gp.optionsState;
		}
	            
	    // DEBUG
	    if(code  ==  KeyEvent.VK_T){
	        if (showDebugText  ==  false) {
	        	showDebugText = true;
	        }
	        else if (showDebugText  ==  true) {
	            showDebugText = false;
	        }           
	    }
		if(code  ==  KeyEvent.VK_N){
			switch (gp.currentMap) { 
			case 0: gp.tileM.loadMap("maps/worldV2.txt", 0); break;
			case 1: gp.tileM.loadMap("maps/interior01.txt", 1); break;
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
		if(code == KeyEvent.VK_G){
			gp.gameState = gp.playState;
		}
		if(code == KeyEvent.VK_ENTER){
			gp.player.selectItem();
			gp.playSE(11);
		}
		playerInventory(code);
	}
	
	public void optionsState(int code) {
		if(code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
		}
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		
		int maxCommandNum = 0;
		switch(gp.ui.subState) {
		case 0: maxCommandNum = 5; break;
		case 3: maxCommandNum = 1; break;
		}
		
		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			gp.playSE(9);
			if (gp.ui.commandNum < 0) {
				gp.ui.commandNum = maxCommandNum;
			}
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			gp.playSE(9);
			if (gp.ui.commandNum > maxCommandNum) {
				gp.ui.commandNum = 0;
			}
		}
		if(code == KeyEvent.VK_A) {
		    if(gp.ui.subState == 0) {
		        if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
		            gp.music.volumeScale--;
		            gp.music.checkVolume();
		            gp.playSE(9);
		        }
		        if(gp.ui.commandNum == 2 && gp.se.volumeScale > 0) {
		            gp.se.volumeScale--;		
		            gp.playSE(9);
		        }
		    }
		}
		if(code == KeyEvent.VK_D) {
		    if(gp.ui.subState == 0) {
		        if(gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
		            gp.music.volumeScale++;
		            gp.music.checkVolume();
		            gp.playSE(9);
		        }
		        if(gp.ui.commandNum == 2 && gp.se.volumeScale < 5) { 
		            gp.se.volumeScale++;		
		            gp.playSE(9);
		        }
		    }
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
		if(code == KeyEvent.VK_E){
			shotKeyPressed = false;
		}		
    }
    
    public void playerInventory (int code) {
		if(code == KeyEvent.VK_W){
			if(gp.ui.playerSlotRow != 0){
				gp.ui.playerSlotRow--;
				gp.playSE(9);
			}			
		}
		if(code == KeyEvent.VK_A){
			if(gp.ui.playerSlotCol != 0){
				gp.ui.playerSlotCol--;
				gp.playSE(9);
			}			
		}
		if(code == KeyEvent.VK_S){
			if(gp.ui.playerSlotRow != 3){
				gp.ui.playerSlotRow++;
				gp.playSE(9);
			}	
		}
		if(code == KeyEvent.VK_D){
			if(gp.ui.playerSlotCol != 4){
				gp.ui.playerSlotCol++;
				gp.playSE(9);
			}
		}
    }
    
    public void npcInventory (int code) {
		if(code == KeyEvent.VK_W){
			if(gp.ui.npcSlotRow != 0){
				gp.ui.npcSlotRow--;
				gp.playSE(9);
			}			
		}
		if(code == KeyEvent.VK_A){
			if(gp.ui.npcSlotCol != 0){
				gp.ui.npcSlotCol--;
				gp.playSE(9);
			}			
		}
		if(code == KeyEvent.VK_S){
			if(gp.ui.npcSlotRow != 3){
				gp.ui.npcSlotRow++;
				gp.playSE(9);
			}	
		}
		if(code == KeyEvent.VK_D){
			if(gp.ui.npcSlotCol != 4){
				gp.ui.npcSlotCol++;
				gp.playSE(9);
			}
		}
    }
}


