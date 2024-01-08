package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door extends Entity {    
    public OBJ_Door (GamePanel gp) {
        super(gp);
        this.gp=gp;

        type=type_obstacle;
        name = "Door";
        down1 = setup("res/objects/mapobject/door", gp.tileSize, gp.tileSize);
        collision = true;   // The player cannot go though this object

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    public void interact(){
       gp.gameState = gp.dialogueState;
       gp.ui.currentDialogue="You need a key to open this";
    }
}

    

