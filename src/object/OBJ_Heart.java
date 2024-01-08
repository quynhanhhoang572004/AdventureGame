package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity{
    GamePanel gp;
    public OBJ_Heart (GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_pickupOnly;
        value = 2;
        name = "Heart";
        down1 = setup("res/objects/health/heart_full", gp.tileSize, gp.tileSize); 
        image = setup("res/objects/health/heart_full", gp.tileSize, gp.tileSize); 
        image2 = setup("res/objects/health/heart_half", gp.tileSize, gp.tileSize); 
        image3 = setup("res/objects/health/heart_blank", gp.tileSize, gp.tileSize); 
    }
    
    public boolean use (Entity entity) {
    	gp.playSE(2);
    	gp.ui.addMessage("Life + " + value);
    	entity.life += value; 
        return true;   
    }
}
