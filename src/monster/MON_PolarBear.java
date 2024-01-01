package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class MON_PolarBear extends Entity{
    GamePanel gp;
    
    public MON_PolarBear(GamePanel gp){
        super(gp);

        this.gp = gp;

        type = 2;
        name = "Polar Bear";
        getImage();
        speed = 1;
        maxLife = 12;
        life = maxLife;
        
        
        solidArea.x = 30;
        solidArea.y = 30;
        solidArea.width = 30*2;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }

    public void getImage(){
       up1 = setup("res/monster/Bear_up1", gp.tileSize, gp.tileSize);
       up2 = setup("res/monster/Bear_up2", gp.tileSize, gp.tileSize);
       down1 = setup("res/monster/Bear_down2", gp.tileSize, gp.tileSize);
       down2 = setup("res/monster/Bear_down3", gp.tileSize, gp.tileSize);
       left1 = setup("res/monster/Bear_left2", gp.tileSize, gp.tileSize);
       left2 = setup("res/monster/Bear_left3", gp.tileSize, gp.tileSize);
       right1 = setup("res/monster/Bear_right1", gp.tileSize, gp.tileSize);
       right2 = setup("res/monster/Bear_right2", gp.tileSize, gp.tileSize);
       
    }

    public void setAction(){
        actionLockCounter++;
    	if (actionLockCounter == 120) {
	    	Random random = new Random();
	    	int i = random.nextInt(100)+1;
	    	// Pick up a number from 1 to 100
	    	if (i <= 25) {
	    		direction = "up";
	    	}
	    	if (i > 25 && i <= 50) {
	    		direction = "down";
	    	}
	    	if (i > 50 && i <= 75) {
	    		direction = "left";
	    	}
	    	if (i > 75 && i <= 100) {
	    		direction = "right";
	    	}
	    	actionLockCounter = 0;
	    }
    }

    //when bear gets dmg, it runs away
    public void damageReaction(){
        actionLockCounter = 0;
        direction = gp.player.direction;
    }
}
