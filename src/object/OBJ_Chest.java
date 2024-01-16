package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity{
    GamePanel gp;
    public OBJ_Chest (GamePanel gp) {
        super(gp);
        this.gp=gp;

        type=type_obstacle;
        name = "Chest";
        image=setup("res/objects/mapobject/chest", gp.tileSize, gp.tileSize); 
        image2 =setup("res/objects/mapobject/chest_open", gp.tileSize, gp.tileSize); 
        down1= image;
        collision=true;

        solidArea.x = 4;
        solidArea.y=16;
        solidArea.width=40;
        solidArea.height=32;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
    }
    public void setLoot(Entity loot) {
        this.loot = loot;
    }
    public void interact(){
        gp.gameState = gp.dialogueState;

        if(opened == false){
          gp.playSE(3);  

           StringBuilder sb = new StringBuilder();
           sb.append(" You opend the chest find a "+ loot.name + "!");

           if(gp.player.canObtainItem(loot)==false){
                sb.append("\n....But you cannot carry anymore");
           }
           else{
            sb.append("\n You obtain the "+loot.name+"!");
            down1=image2;
            opened=true;
           }
           gp.ui.currentDialogue = sb.toString();
        }
        else{
            gp.ui.currentDialogue=" It is empty";
        }
    }
}
