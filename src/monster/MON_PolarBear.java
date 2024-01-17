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
		defaultSpeed=1;
		speed=defaultSpeed;
        getImage();
        speed = 1;
        maxLife = 12;
        life = maxLife;
        attack = 2;
        defense = 0;
        exp = 2;
        projectile = new OBJ_Rock(gp);	
        
        int size = 1*gp.tileSize;
        solidArea.x = 0;
        solidArea.y = 10;
        solidArea.width = size;
        solidArea.height = size - 20;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
    int i = 2;
    public void getImage(){
       up1 = setup("res/monster/Bear_up1", i*gp.tileSize, i*gp.tileSize);
       up2 = setup("res/monster/Bear_up2", i*gp.tileSize, i*gp.tileSize);
       down1 = setup("res/monster/Bear_down2", i*gp.tileSize, i*gp.tileSize);
       down2 = setup("res/monster/Bear_down3", i*gp.tileSize, i*gp.tileSize);
       left1 = setup("res/monster/Bear_left2", i*gp.tileSize, i*gp.tileSize);
       left2 = setup("res/monster/Bear_left3", i*gp.tileSize, i*gp.tileSize);
       right1 = setup("res/monster/Bear_right1", i*gp.tileSize, i*gp.tileSize);
       right2 = setup("res/monster/Bear_right2", i*gp.tileSize, i*gp.tileSize);
    }

    

    public void setAction() {
		if (onPath == true) {
			// If the polar bear is on a path and the player is more than 8 tiles away, the polar bear would stop following the path.
			checkStopChasingOrNot(gp.player, 20 , 100);
			
			//search the direction to go
    		searchPath(getGoalCol(gp.player), getGoalRow(gp.player));    

			//Check if 	it shoots a projectile	
			checkShootOrNot(200, 30); // when the bear is near to the player (Path == true), its speed increases by 1 (1+1 = 2)
		}else {
			//check if it starts chasing
			checkStartChasingOrNot(gp.player, 10, 100);
			speed = 1;
			 //when the bear is outrange the player, its speed is back to normal
			//Get a random direction
			getRandomDirection();
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
}
