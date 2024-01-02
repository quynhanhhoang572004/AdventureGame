package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Wood extends Entity{
    public OBJ_Shield_Wood(GamePanel gp){
        super(gp);

        type = type_shield;
        name = "Wooden Shield";
        down1 = setup("res/objects/Shield/Shield", gp.tileSize , gp.tileSize );
        defenseValue = 1;
        description = "[" + name + "]\nA Wood shield";
    }
}
