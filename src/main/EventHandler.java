package main;


public class EventHandler {
	GamePanel gp;
	EventRect eventRect[][];

	int previousEventX, previousEventY;
	boolean canTouchEvent = true;



//using this type of [][] makes the code better to design?:D
	public EventHandler (GamePanel gp) {
		this.gp = gp;
		eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
		int col = 0;
		int row = 0;
		while(col < gp.maxWorldCol && row < gp.maxWorldRow){
			eventRect[col][row] = new EventRect();
			eventRect[col][row].x = 23;
			eventRect[col][row].y = 23;
			eventRect[col][row].width = 2;
			eventRect[col][row].height = 2;
			eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
			eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

			col++;
			if(col == gp.maxWorldCol){
				col = 0;
				row++;
			}
		}
		
	}
	
	
	public void checkEvent () {		// This function uses data from the worldV2.txt, and is a sample, can modify later 
		//checl whether the player character is more than 1 tile away from the last event
		int xDistance = Math.abs(gp.player.worldX - previousEventX);
		int yDistance = Math.abs(gp.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		if(distance > gp.tileSize){
			canTouchEvent = true;
		}

		if(canTouchEvent == true){//CONCERNING: REMEMBER TO REDUCE THE COORDINATE BY 1 DUE TO THE FACT THAT THIS IS JAVA :V
			if (hit(28,21,"any") == true) {
				damagePit(28, 21, gp.dialogueState);
			}
			 // (x,y): Coordinate corresponds to the coordinate in worldV2.txt
			if (hit(36 ,15,"any") == true) {
				healingPool(36, 15, gp.dialogueState);
			}
			if(hit(29, 22, "any") == true){
				teleport(29, 12, gp.dialogueState);
			}
		}

		
				
	}
	
	public boolean hit(int col, int row, String reqDirection) {
		boolean hit = false;
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
		eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;
		
		if(gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false) {
			if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
				hit = true;

				previousEventX = gp.player.worldX;
				previousEventY = gp.player.worldY;
			}
		}
		
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
		eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

		return hit;		
	}

	public void teleport(int col, int row, int gameState){
		gp.gameState = gameState;
		gp.ui.currentDialogue = "Teleport piu piu piu";
		gp.player.worldX = gp.tileSize*37;
		gp.player.worldY = gp.tileSize*30;
	}
	
	public void damagePit(int col, int row, int gameState) {
		gp.gameState = gameState;
		gp.ui.currentDialogue = "You fall into a pit!";
		gp.player.life -= 1;
		//eventRect[col][row].eventDone = true; //we do this in order to make the event happen once only
		canTouchEvent = false;
	}
	
	public void healingPool(int col, int row, int gameState) {
		if(gp.keyH.enterPressed == true) {
			gp.gameState = gameState;
			gp.ui.currentDialogue = "You drink the water. \n Your life has been recovered.";
			if(gp.player.life < gp.player.maxLife){
				gp.player.life +=1 ;
			}
			gp.aSetter.setMonster();
		}
	}
}
