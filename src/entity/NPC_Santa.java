package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_Santa extends Entity {
	public NPC_Santa(GamePanel gp) {
		super(gp);
		
		direction = "down" ;
		speed = 2;
		solidArea.x = 8;
        solidArea.y = 10;
        solidArea.width = 32;
        solidArea.height = 38;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;		
		getImage();
		setDialogue();
	}
		
	// Get the sprite of the NPC
    public void getImage() {
        up1 = setup("res/npc/santa_up1", gp.tileSize, gp.tileSize);
        up2 = setup("res/npc/santa_up2", gp.tileSize, gp.tileSize);
        down1 = setup("res/npc/santa_down1", gp.tileSize, gp.tileSize);
        down2 = setup("res/npc/santa_down2", gp.tileSize, gp.tileSize);
        left1 = setup("res/npc/santa_left1", gp.tileSize, gp.tileSize);
        left2 = setup("res/npc/santa_left2", gp.tileSize, gp.tileSize);
        right1 = setup("res/npc/santa_right1", gp.tileSize, gp.tileSize);
        right2 = setup("res/npc/santa_right2", gp.tileSize, gp.tileSize);
    }
    
    public void setDialogue() {
    	// Input the dialogue between the quotes
    	dialogues[0] = "Hello.";	
    	dialogues[1] = "So you've come to this snowy land to find the treasure.";
    	dialogues[2] = "I am Great Santa, who will guide you and give you quests in order to achieve the treasure.";
    	dialogues[3] = "Good luck to you, that's the end for now.";    	
    }
    
    public void setAction () {
    	if (onPath == true) {
//    		int goalCol = 30;	// Default location (col) for the NPC to find the way back after interacting with the character
//    		int goalRow = 10;	// Default location (row) for the NPC to find the way back after interacting with the character
    		// When we want the NPC follows the character:
    		int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
    		int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;
    		searchPath(goalCol, goalRow);    		    		
    	} else {
        	actionLockCounter++;
        	if (actionLockCounter == 120) {
    	    	Random random = new Random();
    	    	int i = random.nextInt(100)+1;
    	    	// Pick up a number from 1 to 100
    	    	if (i <= 25) {
    	    		direction = "up";
    	    	}
    	    	if (i > 25 && i <= 50) {
    	    		direction = "down";
    	    	}
    	    	if (i > 50 && i <= 75) {
    	    		direction = "left";
    	    	}
    	    	if (i > 75 && i <= 100) {
    	    		direction = "right";
    	    	}
    	    	actionLockCounter = 0;
    	    }	
    	}    	    	    	
	}
    
    public void speak () {
    	// Specific stuff
    	super.speak();
    	onPath = true;
    }
}