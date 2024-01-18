package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Gold;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

public class MON_Orc extends Entity {
     GamePanel gp;

    public MON_Orc(GamePanel gp){
        super(gp);
        this.gp = gp;
        type = type_monster;
        name = "Orc";
		defaultSpeed=1;
		speed=defaultSpeed;
        getImage();
        getAttackImage();
        speed = 1;
        maxLife = 10;
        life = maxLife;
        attack = 3;
        defense = 2;
        exp = 4;
	
        
       
        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.height = 20;
        attackArea.width = 20;
        motion1_duration = 40;
        motion2_duration = 85;

    }
    int i = 1;
    public void getImage(){
       up1 = setup("res/monster/orc_up_1", i*gp.tileSize, i*gp.tileSize);
       up2 = setup("res/monster/orc_up_2", i*gp.tileSize, i*gp.tileSize);
       down1 = setup("res/monster/orc_down_1", i*gp.tileSize, i*gp.tileSize);
       down2 = setup("res/monster/orc_down_2", i*gp.tileSize, i*gp.tileSize);
       left1 = setup("res/monster/orc_left_1", i*gp.tileSize, i*gp.tileSize);
       left2 = setup("res/monster/orc_left_2", i*gp.tileSize, i*gp.tileSize);
       right1 = setup("res/monster/orc_right_1", i*gp.tileSize, i*gp.tileSize);
       right2 = setup("res/monster/orc_right_2", i*gp.tileSize, i*gp.tileSize);
    }
    public void getAttackImage(){
        
            attackUp1 = setup("res/monster/orc_attack_up_1", gp.tileSize, gp.tileSize*2);
            attackUp2 = setup("res/monster/orc_attack_up_2", gp.tileSize, gp.tileSize*2);
            attackDown1 = setup("res/monster/orc_attack_down_1", gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("res/monster/orc_attack_down_2", gp.tileSize, gp.tileSize*2);
            attackLeft1 = setup("res/monster/orc_attack_left_1", gp.tileSize*2, gp.tileSize);
            attackLeft2 = setup("res/monster/orc_attack_left_2", gp.tileSize*2, gp.tileSize);
            attackRight1 = setup("res/monster/orc_attack_right_1", gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("res/monster/orc_attack_right_2", gp.tileSize*2, gp.tileSize);
    }

    

    public void setAction() {
		if (onPath == true) {
			// If the polar bear is on a path and the player is more than 8 tiles away, the polar bear would stop following the path.
			checkStopChasingOrNot(gp.player, 10 , 100);
			
			//search the direction to go
    		searchPath(getGoalCol(gp.player), getGoalRow(gp.player));    

			//Check if 	it shoots a projectile	
			
		}else {
			//check if it starts chasing
			checkStartChasingOrNot(gp.player, 10, 100);
			speed = 1;
			 //when the bear is outrange the player, its speed is back to normal
			//Get a random direction
			getRandomDirection();
		}
        //check if it attacks
        if(attacking == false){
            checkAttackOrNot(30, gp.tileSize*4, gp.tileSize);
        }
    }
    
    
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
