package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Metal extends Entity {
    public OBJ_Shield_Metal(GamePanel gp){
        super(gp);

        type = type_shield;
        name = "Metal Shield";
        down1 = setup("res/objects/shield/shield_metal_test", gp.tileSize, gp.tileSize);
        defenseValue = 2;
        description = "[" + name + "]\nA Metal Shield with \nbetter Defense";
        
        price = 10;
    }
    
}
