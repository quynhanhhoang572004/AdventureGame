package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class UI {
	GamePanel gp;
	Font arial_40, arial_80B;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	private Graphics2D g2;
	public int commandNum = 0;
	public int titleScreenState = 0;
	// 0: is the first screen 
	// 1: is the second screen
	public String currentDialogue = "";

	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
	}

	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}

	public void draw(Graphics2D g2) {
		this.g2 = g2;
		g2.setFont(arial_40);
		g2.setColor(Color.yellow);
		// TITLE STATE
		if (gp.gameState  ==  gp.titleState) {
			drawTitleScreen();
		}
		// PLAY STATE
		if (gp.gameState  ==  gp.playState) {
			// play state stuff later
		}
		// PAUSE STATE
		if (gp.gameState  ==  gp.pauseState) {
			drawPauseScreen();
		}
		// DIALOGUE STATE
		if (gp.gameState == gp.dialogueState) {
			drawDialogueScreen();
		}
	}

	public void drawPauseScreen() {
		String text = "PAUSE";
		int x = getXforCenteredObject(text);
		int y = gp.screenHeight / 2;
		g2.drawString(text, x, y);
	}

	public void drawDialogueScreen() {
	    // WINDOW
	    int x = gp.tileSize * 2;
	    int y = gp.tileSize / 2;
	    int width = gp.screenWidth - (gp.tileSize * 4);
	    int height = gp.tileSize * 4;
	    drawSubWindow(x, y, width, height);
	    // SETTING FOR THE DIALOGUE  
	    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
	    x += gp.tileSize;
	    y += gp.tileSize;
	    FontMetrics fontMetrics = g2.getFontMetrics();
	    String[] words = currentDialogue.split("\\s+");
	    StringBuilder line = new StringBuilder();
	    for (String word : words) {
	        // Check if adding the word exceeds the width of the dialogue box
	        if (fontMetrics.stringWidth(line.toString() + word) <= width - gp.tileSize) {
	            // Add the word to the current line
	            line.append(word).append(" ");
	        } else {
	            g2.drawString(line.toString().trim(), x, y);
	            y += 40;  // Vertical spacing
	            line = new StringBuilder(word + " ");
	        }
	    }
	    // Draw the last line
	    g2.drawString(line.toString().trim(), x, y);
	}

	public void drawSubWindow(int x, int y, int width, int height) {
		Color c = new Color(0, 0, 0, 210);	// RGB number for black
											// 210: opacity level (this number represents the transparency degree of the dialogue box)
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);	// The dimensions of the dialogue box
		c = new Color(255, 255, 255);	// RGB number for white
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);	// Set the dimensions for the border of the dialogue box
	}
	
	public void drawTitleScreen() {
		if(titleScreenState  ==  0){
			g2.setColor(new Color(0, 0, 0));
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

			// TITLE NAME
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 55F));	// NOT FINISH YET SINCE WE ARE NOT IMPORT FONT YET
			String text = "Frost King Adventure"; 					// GAME TITLE
			int x = getXforCenteredObject(text);
			int y = gp.tileSize * 3;
	
			// SHADOW
			g2.setColor(Color.gray);
			g2.drawString(text, x + 2, y + 2);
	
			// MAIN COLOR
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
	
			// FROGI IMAGE
			x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
			y  +=  gp.tileSize * 2;
			g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
	
			//MENU
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,35F));		// NOT FINISH YET SINCE WE ARE NOT IMPORT FONT YET
	
			text = "NEW GAME";
			x = getXforCenteredObject(text);
			y  +=  gp.tileSize*3.5;
			g2.drawString(text, x, y);

			if(commandNum  ==  0){
				g2.drawString(">",x-gp.tileSize,y); 	// we can use drawImage instead of draw String if we want
			}

		
			text = "LOAD GAME";
			x = getXforCenteredObject(text);
			y  +=  gp.tileSize;
			g2.drawString(text, x, y);
			
			if(commandNum  ==  1){
				g2.drawString(">",x - gp.tileSize,y); 	// we can use drawImage instead of draw String if we want
			}
			text = "QUIT";
			x = getXforCenteredObject(text);
			y  +=  gp.tileSize;
			g2.drawString(text, x, y);
			
			if(commandNum  ==  2){
				g2.drawString(">",x - gp.tileSize,y); // we can use drawImage instead of draw String if we want
			}
		}
		
		// CLASS SELECTION SCREEN
		else if(titleScreenState  ==  1){
			// CLASS SELECTION SCREEN
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(30F));

			String text = "Select your class";
			int x = getXforCenteredObject(text);
			int y = gp.tileSize * 3;
			g2.drawString(text,x,y);

			text = "Fighter";
			x = getXforCenteredObject(text);
			y += gp.tileSize;
			g2.drawString(text,x,y);
			if(commandNum == 0){
				g2.drawString(">", x-gp.tileSize, y);
			}

				text = "Thief";
			x = getXforCenteredObject(text);
			y  +=  gp.tileSize;
			g2.drawString(text,x,y);
			if(commandNum  ==  1){
				g2.drawString(">", x - gp.tileSize, y);
			}


				text = "Sorcerer";
			x = getXforCenteredObject(text);
			y += gp.tileSize;
			g2.drawString(text,x,y);
			if(commandNum == 2){
				g2.drawString(">", x - gp.tileSize, y);
			}

				text = "Back";
			x = getXforCenteredObject(text);
			y += gp.tileSize*2;
			g2.drawString(text,x,y);
			if(commandNum == 3){
				g2.drawString(">", x - gp.tileSize, y);
			}
		}
	}

	public int getXforCenteredObject(String text) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = (gp.screenWidth - length) / 2 ;
		return x;
	}

}
