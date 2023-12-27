package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_Santa extends Entity {
	public NPC_Santa(GamePanel gp) {
		super(gp);
		
		direction = "down" ;
		speed = 1;		
		getImage();
		setDialogue();
	}
	
	// Get the sprite of the NPC
    public void getImage() {
        up1 = setup("res/npc/santa_up1");
        up2 = setup("res/npc/santa_up2");
        down1 = setup("res/npc/santa_down1");
        down2 = setup("res/npc/santa_down2");
        left1 = setup("res/npc/santa_left1");
        left2 = setup("res/npc/santa_left2");
        right1 = setup("res/npc/santa_right1");
        right2 = setup("res/npc/santa_right2");
    }
    
    public void setDialogue() {
    	// Input the dialogue between the quotes
    	dialogues[0] = "Hello.";	
    	dialogues[1] = "So you've come to this snowy land to find the treasure.";
    	dialogues[2] = "I am Great Santa, who will guide you and give you quests in order to achieve the treasure.";
    	dialogues[3] = "Good luck to you, that's the end for now.";    	
    }
    
    public void setAction () {
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
    
    public void speak () {
    	// Specific stuff
    	super.speak();
    }
}