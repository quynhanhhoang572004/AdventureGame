package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Gold;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

public class MON_PolarBear extends Entity {
    GamePanel gp;

    public MON_PolarBear(GamePanel gp){
        super(gp);
        this.gp = gp;
        type = type_monster;
        name = "Polar Bear";
        getImage();
        speed = 1;
        maxLife = 12;
        life = maxLife;
        attack = 2;
        defense = 0;
        exp = 2;
        projectile = new OBJ_Rock(gp);	
        
        solidArea.x = 6;
        solidArea.y = 6;
        solidArea.width = 6*2;
        solidArea.height = 6;
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

    public void setAction() {
    	if (onPath == true) {
    		int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
    		int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;
    		searchPath(goalCol, goalRow);    		    		
    		int i = new Random().nextInt(200)+1;
    		if (i < 199 && projectile.alive == false && shotAvailableCounter == 30) {
    			projectile.set(worldX, worldY, direction, true, this);
    			gp.projectileList.add(projectile);
    			shotAvailableCounter = 0;
    		}
    	} 
    	else {
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
    
    // when bear gets dmg, it runs away
    public void damageReaction(){
        actionLockCounter = 0;
//      direction = gp.player.direction;
        onPath = true;
    }
    
    public void checkDrop () {
    	// CAST A DIE
    	int i = new Random().nextInt(100)+1;
    	
    	// SET THE MONSTER DROP
    	if (i < 50) {
    		dropItem(new OBJ_Coin_Gold(gp));
    	}
    	if (i >= 50 && i < 75) {
    		dropItem(new OBJ_Heart(gp));
    	}
    	if (i >= 75 && i < 100) {
    		dropItem(new OBJ_ManaCrystal(gp));
    	}
    }
    
    public void update() {
    	super.update();
    	int xDistance = Math.abs(worldX - gp.player.worldX);
    	int yDistance = Math.abs(worldY - gp.player.worldY);
    	int tileDistance = (xDistance + yDistance) / gp.tileSize;
    	if (onPath == false && tileDistance < 5) {
    		int i = new Random().nextInt(100)+1;
    		if (i > 50) {
    			onPath = true;
    		}    		
    	}
    	// If the polar bear is on a path and the player is more than 8 tiles away, the polar bear would stop following the path.
    	if (onPath == true && tileDistance > 8) {
    		onPath = false;
    	}
    }
}
