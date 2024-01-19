package entity;

import main.GamePanel;
import object.OBJ_Axe;
import object.OBJ_Key;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Diamond;
import object.OBJ_Shield_Metal;
import object.OBJ_Splash_Poison;
import object.OBJ_Sword_LV2;
import object.OBJ_Sword_Normal;
import object.OBJ_Tent;

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
    	dialogues[0][0] = "Oh hey. Is that the Frost King? \nFinally you have found me.\nI have some good stuff.\nDo you want to trade?";	
        dialogues[1][0] = "Come again, meow meow!";
        dialogues[2][0] = "You need more coins to buy that!";
        dialogues[3][0] = "You cannot sell an equipped item!";
    }
    
    public void setItems() {
    	inventory.add(new OBJ_Potion_Red(gp));
    	inventory.add(new OBJ_Shield_Diamond(gp));
    	inventory.add(new OBJ_Sword_Normal(gp));
    	inventory.add(new OBJ_Axe(gp));   
        inventory.add(new OBJ_Shield_Metal(gp));
        inventory.add(new OBJ_Sword_LV2(gp));
        inventory.add(new OBJ_Tent(gp));	
        inventory.add(new OBJ_Key(gp));
    }
    
    public void speak() {
    	facePlayer();
        startDialogue(this,dialogueSet);
    	gp.gameState = gp.tradeState;
    	gp.ui.npc = this;
    }
}
