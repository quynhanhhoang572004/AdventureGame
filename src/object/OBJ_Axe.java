package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity{
    public OBJ_Axe (GamePanel gp){
        super(gp);

        type = type_axe;
        name = "Wooden Axe";
        down1 = setup("res/objects/axe/axe", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 25;
        attackArea.height = 25;
        description = "[" + name + "]\nAn Axe to cut tree";
        
        price = 20;
        knockBackPower=2;
    }
}
