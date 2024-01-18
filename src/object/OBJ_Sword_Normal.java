package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity{
    public OBJ_Sword_Normal(GamePanel gp){
        super(gp);

        type = type_sword;
        name = "Normal Sword";
        down1 = setup("res/objects/sword/sword_normal", gp.tileSize , gp.tileSize );
        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nA Normal Sword";
        
        price = 30;
        knockBackPower=2;
    }
}
