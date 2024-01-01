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
        
        
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }

    public void getImage(){
       up1 = setup("res/monster/Bear_up1", gp.tileSize*2, gp.tileSize*2);
       up2 = setup("res/monster/Bear_up2", gp.tileSize*2, gp.tileSize*2);
       down1 = setup("res/monster/Bear_down2", gp.tileSize*2, gp.tileSize*2);
       down2 = setup("res/monster/Bear_down3", gp.tileSize*2, gp.tileSize*2);
       left1 = setup("res/monster/Bear_left2", gp.tileSize*2, gp.tileSize*2);
       left2 = setup("res/monster/Bear_left3", gp.tileSize*2, gp.tileSize*2);
       right1 = setup("res/monster/Bear_right1", gp.tileSize*2, gp.tileSize*2);
       right2 = setup("res/monster/Bear_right2", gp.tileSize*2, gp.tileSize*2);
       
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
}
