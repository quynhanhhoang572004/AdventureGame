package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import entity.Entity;
import object.OBJ_Heart;

public class UI {
	GamePanel gp;
	Font maruMonica, purisaB;
	BufferedImage heart_full, heart_half, heart_blank;
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
		try {
			// getClass().getResourceAsStream will cause errors due to its old syntaxes
		    InputStream is = new FileInputStream("res/font/x12y16pxMaruMonica.ttf");
		    maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
		    is = new FileInputStream("res/font/Purisa Bold.ttf");
		    purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException | IOException e) {
		    e.printStackTrace();
		}
		
		// CREATE OBJECT HUD
		Entity heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
		
	}

	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}

	public void draw(Graphics2D g2) {
		this.g2 = g2;
		g2.setFont(maruMonica);
	 // g2.setFont(purisaB);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.yellow);
		// TITLE STATE
		if (gp.gameState  ==  gp.titleState) {
			drawTitleScreen();
		}
		// PLAY STATE
		if (gp.gameState  ==  gp.playState) {
			drawPlayerLife();
		}
		// PAUSE STATE
		if (gp.gameState  ==  gp.pauseState) {
			drawPlayerLife();
			drawPauseScreen();
		}
		// DIALOGUE STATE
		if (gp.gameState == gp.dialogueState) {
			drawPlayerLife();
			drawDialogueScreen();
		}
		//CHARACTER STATE
		if(gp.gameState == gp.characterState){
			drawCharacterScreen();

		}
	}
	
	public void drawPlayerLife () {
//		gp.player.life = 3;
		int x = gp.tileSize / 2;
		int y = gp.tileSize / 2;
		int i = 0;
		// DRAW MAX LIFE
		while (i < gp.player.maxLife / 2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x += gp.tileSize;
		}
		
		// RESET 
		x = gp.tileSize / 2;
		y = gp.tileSize / 2;
		i = 0;
		
		// DRAW CURRENT LIFE
		while(i < gp.player.life) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			if (i < gp.player.life) {
				g2.drawImage(heart_full, x, y, null);
			}
			i++;
			x += gp.tileSize;
		}
	}

	public void drawPauseScreen() {
		String text = "PAUSED";
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

	public void drawCharacterScreen(){
		//CREATE A FRAME
		final int frameX = gp.tileSize - 35;
		final int frameY = gp.tileSize;
		final int frameWidth = gp.tileSize*5;
		final int frameHeight = gp.tileSize*10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);

		//TEXT
		g2.setColor(Color.WHITE);
		g2.setFont(g2.getFont().deriveFont(32F));

		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 32;

		//NAMES
		g2.drawString("Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Life", textX, textY);
		textY += lineHeight;
		g2.drawString("Strength", textX, textY);
		textY += lineHeight;
		g2.drawString("Dexterity", textX, textY);
		textY += lineHeight;
		g2.drawString("Attack", textX, textY);
		textY += lineHeight;
		g2.drawString("Defense", textX, textY);
		textY += lineHeight;
		g2.drawString("Exp", textX, textY);
		textY += lineHeight;
		g2.drawString("Next Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Coin", textX, textY);
		textY += lineHeight + 20;
		g2.drawString("Weapon", textX, textY);
		textY += lineHeight + 15;
		g2.drawString("Shield", textX, textY);
		textY += lineHeight;

		//VALUES
		int tailX = (frameX + frameWidth) - 30;
		//Reset textY
		textY = frameY + gp.tileSize;
		String value;

		value = String.valueOf(gp.player.level);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.strength);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.dexterity);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.attack);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.defense);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.exp);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.nextLevelExp);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.coin);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 14, null);
		textY += gp.tileSize;
		g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 14, null);
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
	public int getXforCenteredObject(String text) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = (gp.screenWidth - length) / 2 ;
		return x;
	}

	public int getXforAlignToRightText(String text, int tailX) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
	}

}
