package main;

import entity.*;

public class EventHandler {
	GamePanel gp;
	EventRect eventRect[][][];
	int previousEventX, previousEventY;
	boolean canTouchEvent = true;
	int tempMap, tempCol, tempRow;

	public EventHandler (GamePanel gp) {
		this.gp = gp;
		eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		
		int map = 0;
		int col = 0;
		int row = 0;
		while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
			eventRect[map][col][row] = new EventRect();
			eventRect[map][col][row].x = 23;
			eventRect[map][col][row].y = 23;
			eventRect[map][col][row].width = 2;
			eventRect[map][col][row].height = 2;
			eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
			eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

			col++;
			
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
				if (row == gp.maxWorldRow) {
					row = 0;
					map++;
				}
			}
		}		
	}
		
	public void checkEvent () {		// This function uses data from the worldV2.txt, and is a sample, can modify later 
		//check whether the player character is more than 1 tile away from the last event
		int xDistance = Math.abs(gp.player.worldX - previousEventX);
		int yDistance = Math.abs(gp.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		if(distance > gp.tileSize){
			canTouchEvent = true;
		}

		// 0: the number of the first map (the map that we are using)
		if(canTouchEvent == true){	// REMEMBER TO REDUCE THE COORDINATE BY 1 
			if (hit(0, 28, 21, "any") == true) {
				damagePit(gp.dialogueState);
			}
			 // (x,y): (col,row): Coordinate corresponds to the coordinate in worldV2.txt
			else if (hit(0, 36, 15, "any") == true) {
				healingPool(gp.dialogueState);
			}
//			if(hit(0, 29, 22, "any") == true) {
//				teleport(29, 22, gp.dialogueState);
//			}
			else if (hit(0, 37, 18, "any") == true) {
				teleport(1, 12, 13);
			}
			else if (hit(1, 12, 13, "any") == true) {
				teleport(0, 37, 18);
			}
			else if (hit(1, 12, 9, "up") == true) {
				speak(gp.npc[1][0]);
			}
		}
	}
	
	public boolean hit(int map, int col, int row, String reqDirection) {
		boolean hit = false;
		
		if (map == gp.currentMap) {
			gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
			gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
			eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
			eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;			
			if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
				if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
					hit = true;
					previousEventX = gp.player.worldX;
					previousEventY = gp.player.worldY;
				}
			}			
			gp.player.solidArea.x = gp.player.solidAreaDefaultX;
			gp.player.solidArea.y = gp.player.solidAreaDefaultY;
			eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
			eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
		}
		return hit;		
	}

	public void teleport(int map, int col, int row){
		gp.gameState = gp.transitionState;
		tempMap = map;
		tempCol = col;
		tempRow = row;		
//		gp.currentMap = map;
//		gp.player.worldX = gp.tileSize * col;
//		gp.player.worldY = gp.tileSize * row;
//		previousEventX = gp.player.worldX;
//		previousEventY = gp.player.worldY;
		canTouchEvent = false;
		gp.playSE(17);
	}
	
	public void speak(Entity entity) {
		if (gp.keyH.enterPressed == true) {
			gp.gameState = gp.dialogueState;
			entity.speak();
			gp.player.attacking = false;			
		}
	}
	
	public void damagePit(int gameState) {
		gp.gameState = gameState;
		gp.ui.currentDialogue = "You fall into a pit!";
		gp.player.life -= 1;
		//eventRect[col][row].eventDone = true; // we do this in order to make the event happen once only
		canTouchEvent = false;		
        gp.playSE(6);  
	}
	
	public void healingPool(int gameState) {
		if(gp.keyH.enterPressed == true) {
			gp.gameState = gameState;
			gp.ui.currentDialogue = "You drink the water. \n Your life and mana has been recovered.";
			if(gp.player.life < gp.player.maxLife){
				gp.player.life += 1 ;
			}
			gp.player.mana = gp.player.maxMana;
			gp.aSetter.setMonster();			
	        gp.playSE(12);  
		}
	}
}
