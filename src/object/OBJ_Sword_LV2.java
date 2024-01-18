package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_LV2 extends Entity{
    public OBJ_Sword_LV2(GamePanel gp){
        super(gp);

        type = type_sword;
        name = "Big Sword";
        down1 = setup("res/objects/sword/sword_lv2", gp.tileSize , gp.tileSize );
        attackValue = 6;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nA Big Sword";
        
        price = 250;
        knockBackPower = 4;
    }
}
