package entity;

import main.GamePanel;
import object.*;

public class NPC_Merchant extends Entity {
	public NPC_Merchant(GamePanel gp) {
		super(gp);
		
		direction = "down" ;
		speed = 1;
		solidArea.x = 8;
        solidArea.y = 10;
        solidArea.width = 32;
        solidArea.height = 38;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;		
		getImage();
		setDialogue();
		setItems(); 
	}	
	
	// Get the sprite of the NPC
    public void getImage() {
        up1 = setup("res/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("res/npc/merchant_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("res/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("res/npc/merchant_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("res/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("res/npc/merchant_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("res/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("res/npc/merchant_down_2", gp.tileSize, gp.tileSize);
    }
    
    public void setDialogue() {
    	// Input the dialogue between the quotes
    	dialogues[0] = "Oh hey. Is that the Frost King? \nFinally you have found me.\nI have some good stuff.\nDo you want to trade?";	
    }
    
    public void setItems() {
    	inventory.add(new OBJ_Potion_Red(gp));
    	inventory.add(new OBJ_Shield_Diamond(gp));
    	inventory.add(new OBJ_Sword_Normal(gp));
    	inventory.add(new OBJ_Axe(gp));    	
    }
    
    public void speak() {
    	super.speak();
    	gp.gameState = gp.tradeState;
    	gp.ui.npc = this;
    }
}
