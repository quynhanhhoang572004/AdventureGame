package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin_Gold extends Entity {
	GamePanel gp;
	
	public OBJ_Coin_Gold (GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_pickupOnly;
		name = "Gold Coin";
		value = 50;
		down1 = setup("res/objects/items/coin_gold", gp.tileSize, gp.tileSize);
	}
	
    public boolean use(Entity entity){
    	gp.playSE(1);
    	gp.ui.addMessage("Coin + " + value);
    	gp.player.coin += value;
		return true;
		// return " true" if you use this item, return false if you failed to use it 
    }     
}
