package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity {
    GamePanel gp; 
    
    public OBJ_Potion_Red(GamePanel gp){
        super(gp);        
        this.gp = gp;        
        type = type_consumable;
        name = "Life Potion";
        value = 5;
        down1 = setup("res/objects/potion/potion_red", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nHeal your Life ++ " + value;
        
        price = 10;
        stackable=true;

        setDialogue();
    }
    public void setDialogue(){
        dialogues[0][0] = "You used the " + name + " \nand restore your life by " + value + ".";
    }
    public boolean use(Entity entity){

        startDialogue(this,0);
        entity.life += value;
        gp.playSE(12); 
        return true;       
    }    
}
