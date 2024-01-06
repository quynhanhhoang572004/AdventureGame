package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Diamond extends Entity {
    public OBJ_Shield_Diamond(GamePanel gp){
        super(gp);

        type = type_shield;
        name = "Diamond Shield";
        down1 = setup("res/objects/shield/shield_diamond_test", gp.tileSize, gp.tileSize);
        defenseValue = 3;
        description = "[" + name + "]\nA Diamond Shield with \nbetter Defense";
        
        price = 10;
    }
    
}
