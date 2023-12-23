package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
//import java.awt.image.BufferedImage;
//import object.OBJ_Key;

public class UI {
	GamePanel gp;
	Font arial_40, arial_80B;
	//BufferedImage keyImage;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	private Graphics2D g2;
	
	public UI (GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
		//OBJ_Key key = new OBJ_Key(gp);
		//keyImage = key.image;
	}
	
	public void showMessage (String text) {
		message = text;
		messageOn = true;
	}
	
	public void draw (Graphics2D g2) {
		this.g2 = g2;

		g2.setFont(arial_40);
		g2.setColor(Color.yellow);
		// TITLE STATE 
		if(gp.gameState == gp.titleState){
			drawTitleScreen();
		}

		if(gp.gameState == gp.playState) {
			//play state
		}
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
	}
	public void drawPauseScreen() {
		String text = "PAUSE";
		int x = getXforCenteredObject(text);
		int y = gp.screenHeight/2;


		g2.drawString(text, x, y);
	}
	// THIS LINE IS FOR DIALOUGE STATE
	public void drawTitleScreen() {
		g2.setColor(new Color (0,0,0));
		g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
		//TITLE NAME
		//why the text is not in center 
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,55F));//NOT FINISH YET SINCE WE ARE NOT IMPORT FONT YET
		String text ="Frost King Adventure"; // GAME TITLE
		int x=getXforCenteredObject(text);
		int y= gp.tileSize*3;
		// SHADOW
		g2.setColor(Color.gray);
		g2.drawString(text,x+2,y+2);
		//MAIN COLOR
		g2.setColor(Color.white);
		g2.drawString(text,x,y);
	}
	public int getXforCenteredObject(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenHeight/2 - length/2;
		return x;
	}

	
		
}
