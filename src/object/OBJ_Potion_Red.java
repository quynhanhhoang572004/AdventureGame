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
    }

    public void use(Entity entity){
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You used the " + name + " \nand restore your life by " + value + ".";
        entity.life += value;
        gp.playSE(12);        
    }    
}
