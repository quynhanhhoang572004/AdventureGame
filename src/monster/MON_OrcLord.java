package monster;
import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Gold;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;
public class MON_OrcLord extends Entity{
     GamePanel gp;
     public static final String monName = "Lord of The Orcs";

    public MON_OrcLord(GamePanel gp){
        super(gp);
        this.gp = gp;
        type = type_monster;
        name = monName;
		defaultSpeed=1;
		speed=defaultSpeed;
        getImage();
        getAttackImage();
        speed = 1;
        maxLife = 100;
        life = maxLife;
        attack = 8;
        defense = 3;
        exp = 50;
	
        
        int size = gp.tileSize*2;
        solidArea.x = 40;
        solidArea.y = 40;
        solidArea.width = size - 40;
        solidArea.height = size - 40;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.height= 70;
        attackArea.width= 70;


    }
    int i = 3;
    public void getImage(){
       up1 = setup("res/monster/orcLord_up1", i*gp.tileSize, i*gp.tileSize);
       up2 = setup("res/monster/orcLord_up2", i*gp.tileSize, i*gp.tileSize);
       down1 = setup("res/monster/orcLord_down1", i*gp.tileSize, i*gp.tileSize);
       down2 = setup("res/monster/orcLord_down2", i*gp.tileSize, i*gp.tileSize);
       left1 = setup("res/monster/orcLord_left1", i*gp.tileSize, i*gp.tileSize);
       left2 = setup("res/monster/orcLord_left2", i*gp.tileSize, i*gp.tileSize);
       right1 = setup("res/monster/orcLord_right1", i*gp.tileSize, i*gp.tileSize);
       right2 = setup("res/monster/orcLord_right2", i*gp.tileSize, i*gp.tileSize);
    }
    public void getAttackImage(){
        
        attackUp1 = setup("res/monster/orcLord_attack_up1", gp.tileSize*i, gp.tileSize*2*i);
        attackUp2 = setup("res/monster/orcLord_attack_up2", gp.tileSize*i, gp.tileSize*2*i);
        attackDown1 = setup("res/monster/orcLord_attack_down1", gp.tileSize*i, gp.tileSize*2*i);
        attackDown2 = setup("res/monster/orcLord_attack_down2", gp.tileSize*i, gp.tileSize*2*i);
        attackLeft1 = setup("res/monster/orcLord_attack_left1", gp.tileSize*2*i, gp.tileSize*i);
        attackLeft2 = setup("res/monster/orcLord_attack_left2", gp.tileSize*2*i, gp.tileSize*i);
        attackRight1 = setup("res/monster/orcLord_attack_right1", gp.tileSize*2*i, gp.tileSize*i);
        attackRight2 = setup("res/monster/orcLord_attack_right2", gp.tileSize*2*i, gp.tileSize*i);
    }

    

    public void setAction() {
		if (getTileDistance(gp.player) < 50) {
			
			checkStopChasingOrNot(gp.player, 50 , 100);
			
			
    		searchPath(getGoalCol(gp.player), getGoalRow(gp.player));    

			
			
		}else {
			//check if it starts chasing
			checkStartChasingOrNot(gp.player, 20, 100);
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
        
    }      

}

