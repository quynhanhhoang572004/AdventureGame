package object;


import entity.Entity;
import main.GamePanel;


public class OBJ_Door extends Entity{
    
    public OBJ_Door (GamePanel gp) {
        super(gp);
    
        name = "Door";
        down1 = setup("res/objects/door");
        collision = true;   // The player cannot go though this object

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}

    

