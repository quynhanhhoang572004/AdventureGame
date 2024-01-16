package object;


import entity.Entity;
import main.GamePanel;


public class OBJ_Key extends Entity {

    public OBJ_Key (GamePanel gp) {
        super(gp);
        type  = type_consumable;
        
        name = "Key";
        down1 = setup("res/objects/items/key", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nA Key";
        price=100;
        stackable = true;

        setDialogue();
    }
    public void setDialogue(){
        dialogues[0][0] = " You use the " + name + " and open the door";
        dialogues[1][0] = " ???:D";

    }
    public boolean use(Entity entity){

        int objIndex=getDetected(entity, gp.obj, "Door");
        
        if(objIndex != 999){
            startDialogue(this,0);
            gp.playSE(3);
            gp.obj[gp.currentMap][objIndex] = null;
            return true;
        }
        else {
            startDialogue(this,1);
            return false;
        }
      
    }
}