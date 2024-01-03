package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Splash_Poison extends Projectile{
    GamePanel gp;

    public OBJ_Splash_Poison(GamePanel gp){
        super(gp);
        this.gp =gp;
        name = "Poison Splash potion";
        speed = 5;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }
    
    public void getImage(){
        up1 = setup("res/objects/potion/splash_potion", gp.tileSize, gp.tileSize);
        up2 = setup("res/objects/potion/splash_potion_left1", gp.tileSize, gp.tileSize);
        down1 = setup("res/objects/potion/splash_potion_down1", gp.tileSize, gp.tileSize);
        down2 = setup("res/objects/potion/splash_potion_down2", gp.tileSize, gp.tileSize);
        left1 = setup("res/objects/potion/splash_potion", gp.tileSize, gp.tileSize);
        left2 = setup("res/objects/potion/splash_potion_left1", gp.tileSize, gp.tileSize);
        right1 = setup("res/objects/potion/splash_potion", gp.tileSize, gp.tileSize);
        right2 = setup("res/objects/potion/splash_potion_left1", gp.tileSize, gp.tileSize);      	
        }
        
    public boolean haveResource(Entity user) {
    	boolean haveResource = false;
    	if(user.mana >= useCost) {
    		haveResource = true;   		
    	}
    	return haveResource;
    }
    
    public void substractResource(Entity user) {
    	user.mana -= useCost;    	
    }
}
