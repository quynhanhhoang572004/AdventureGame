package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lantern extends Entity {

    public OBJ_Lantern(GamePanel gp){
        super(gp);

       type=type_light;
       name = "Lantern";
       down1 = setup("res/objects/items/lantern", gp.tileSize, gp.tileSize);
       description="[Lantern]\nIlluminates your \nsurroundings.";
       price= 100;
       lightRadius=250;
    }

}
