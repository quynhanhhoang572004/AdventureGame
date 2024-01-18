package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Boots;
import object.OBJ_Key;
import object.OBJ_Shield_Wood;
import object.OBJ_Splash_Poison;
import object.OBJ_Sword_Normal;

public class Player extends Entity {
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public String hasKey;
    int standCounter = 0;
    public boolean attackCanceled = false;
    public boolean lightUpdated = false;

    public Player(GamePanel gp, KeyHandler keyH){
    	super(gp);
        this.keyH = keyH;
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        //SOLID AREA
        solidArea = new Rectangle();
        solidArea.x = 10;
        solidArea.y = 8;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 28;
        solidArea.height = 32;
        //ATTACK AREA
        //attackArea.width = 36;
        //attackArea.height = 36;

        setDefaultValues();
    }
    
    public void setDefaultValues(){
        worldX = gp.tileSize * 25;
        worldY = gp.tileSize * 18;
//	    worldX = gp.tileSize * 12;
//	    worldY = gp.tileSize * 13;  
        defaultSpeed=4;
        speed =defaultSpeed;
        direction = "down";
        
        // PLAYER STATUS
        level = 1;
        maxLife = 6; 
        // 1 maxLife unit = a heart half
        // => 6 maxLife unit = 3 hearts
        life = maxLife;
        strength = 1; //the more strength player has, the more damage he can deal to mobs
        dexterity = 1;//the more dexterity player has, the more damage he can knock the dmg from mobs
        exp = 0;
        nextLevelExp = 5;
//       coin = 0;
        coin = 100;
     	currentWeapon = new OBJ_Sword_Normal(gp);
//      currentWeapon = new OBJ_Axe(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        projectile = new OBJ_Splash_Poison(gp);
//      projectile = new OBJ_Rock(gp);
        attack = getAttack(); 		//the total attack value is decided by strength + weapon
        defense = getDefense();		//the total def value is decided by dexterity + shield
        maxMana = 4;
        mana = maxMana;
        ammo = 10;

        getPlayerImage();
        getPlayerAttackImage();
        setItems();
//        setDialogue();
    }
    
    public void setItems(){
    	inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Boots(gp));
                
    }
    
    public int getAttack(){
        attackArea = currentWeapon.attackArea;
        // motion1_duration = currentWeapon.motion1_duration;
        // motion2_duration = currentWeapon.motion2_duration;
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefense(){
        attackArea = currentWeapon.attackArea;
        return defense = dexterity * currentShield.defenseValue;
    }
    
    public void getPlayerImage() {
            up1 = setup("res/player/FroGi_up1", gp.tileSize, gp.tileSize); 
            up2 = setup("res/player/FroGi_up2", gp.tileSize, gp.tileSize);
            down1 = setup("res/player/FroGi_down1", gp.tileSize, gp.tileSize);
            down2 = setup("res/player/FroGi_down2", gp.tileSize, gp.tileSize);
            left1 = setup("res/player/FroGi_left3", gp.tileSize, gp.tileSize);
            left2 = setup("res/player/FroGi_left2", gp.tileSize, gp.tileSize);
            right1 = setup("res/player/FroGi_right3", gp.tileSize, gp.tileSize);
            right2 = setup("res/player/FroGi_right2", gp.tileSize, gp.tileSize);
    }

    public void getSleepingImage(BufferedImage image) {
            up1 = image;
            up2 = image;
            down1 = image;
            down2 = image;
            left1 = image;
            left2 = image;
            right1 = image;
            right2 = image;
    }

    public void getPlayerAttackImage(){
        if(currentWeapon.type == type_sword){
            attackUp1 = setup("res/player/attack/FroGi_sword_up2", gp.tileSize, gp.tileSize*2);
            attackUp2 = setup("res/player/attack/FroGi_sword_up2", gp.tileSize, gp.tileSize*2);
            attackDown1 = setup("res/player/attack/FroGi_sword_down2", gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("res/player/attack/FroGi_sword_down2", gp.tileSize, gp.tileSize*2);
            attackLeft1 = setup("res/player/attack/Frogi_sword_left", gp.tileSize*2, gp.tileSize);
            attackLeft2 = setup("res/player/attack/Frogi_sword_left", gp.tileSize*2, gp.tileSize);
            attackRight1 = setup("res/player/attack/Frogi_sword_right", gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("res/player/attack/Frogi_sword_right", gp.tileSize*2, gp.tileSize);
        }
        if(currentWeapon.type == type_axe){
            attackUp1 = setup("res/player/attack/FroGi_axe_down2", gp.tileSize, gp.tileSize*2);
            attackUp2 = setup("res/player/attack/FroGi_axe_down2", gp.tileSize, gp.tileSize*2);
            attackDown1 = setup("res/player/attack/FroGi_axe_front2", gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("res/player/attack/FroGi_axe_front2", gp.tileSize, gp.tileSize*2);
            attackLeft1 = setup("res/player/attack/FroGi_axe_left", gp.tileSize*2, gp.tileSize);
            attackLeft2 = setup("res/player/attack/FroGi_axe_left", gp.tileSize*2, gp.tileSize);
            attackRight1 = setup("res/player/attack/FroGi_axe_right", gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("res/player/attack/FroGi_axe_right", gp.tileSize*2, gp.tileSize);
        }        
    }

    public void update(){ 
        if(attacking == true){
            attacking();
        }
    	// This update method gets called 60 times per second
        // So in every frame it get called and increase this counter by 1
        else if(keyH.upPressed == true||keyH.downPressed == true||keyH.leftPressed == true
            ||keyH.rightPressed == true || keyH.enterPressed == true ||keyH.leftMouse == true||keyH.FPressed == true){
            if(keyH.upPressed == true){
                direction = "up";  
            }
            else if (keyH.downPressed == true){
                direction = "down";  
            }
            else if (keyH.leftPressed == true){
                direction = "left";
            }
            else if (keyH.rightPressed == true){
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION;
            int objIndex = gp.cChecker.checkObject(this, true);
            pickupObject(objIndex);

            // CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);
            // CHECK EVENT
            gp.eHandler.checkEvent();
        	
            // CHECK INTERACTIVE TILE COLLISION
            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
                                                                        
            // IF COLLISION IS FALSE, THE PLAYER CAN MOVE
            //In order not to let player moves while pressing feature key, add it in the if condition \/
            if(collisionOn == false && keyH.enterPressed == false && keyH.leftMouse == false && keyH.FPressed == false 
                && keyH.shotKeyPressed == false){
                switch(direction){
                    case "up":
                        worldY -= speed; // X values increases to the right; Y values increases as they go down
                        break;
                    case "down":
                        worldY+= speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            

            //attackCanceled = false;
            gp.keyH.enterPressed = false;
            gp.keyH.leftMouse = false;
            gp.keyH.FPressed = false;
            
            spriteCounter++;
            if(spriteCounter > 10){
                if (spriteNum == 1){
                    spriteNum = 2;
                }
                else if (spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
            
        }
        if(gp.keyH.shotKeyPressed == true && projectile.alive == false && shotAvailableCounter == 30 
        	&& projectile.haveResource(this) == true){

            // SET DEFAULT COORDINATE, DIRECTION AND USER
            projectile.set(worldX, worldY, direction, true, this);
            
            // SUBSTRACT THE COST (MANA, AMMO,....)
            projectile.substractResource(this);

          
                //CHECK VANCANCY
            for (int i=0; i<gp.projectile[1].length; i++){
                if(gp.projectile[gp.currentMap][i]==null){
                    gp.projectile[gp.currentMap][i]=projectile;
                    break;
                }
            }
            shotAvailableCounter = 0;
            gp.playSE(13);
            }
        
        // This needs to be outside of key if statement!
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if(shotAvailableCounter < 30){
            shotAvailableCounter++;
        }
    	if(life > maxLife) {
    		life = maxLife;
    	}
    	if(mana > maxMana) {
    		mana = maxMana;
    	}
    	if(life <= 0) {
    		gp.gameState = gp.gameOverState;
    		gp.ui.commandNum = -1;
    		gp.stopMusic();
//    		gp.playMusic(index);
    		gp.playSE(16);
    	}
    }
    
    //Showing attacking image 
    
    
    public void pickupObject(int i) {
        if (i != 999) {      
        	// PICKUP-ONLY ITEMS
        	if (gp.obj[gp.currentMap][i].type == type_pickupOnly) {
        		gp.obj[gp.currentMap][i].use(this);
        		gp.obj[gp.currentMap][i] = null;
        	}
            
            //OBSTACLE
            else if(gp.obj[gp.currentMap][i].type==type_obstacle){
                if(keyH.enterPressed == true || keyH.FPressed == true){
                    attackCanceled = true;
                    gp.obj[gp.currentMap][i].interact();
                }
            }


        	// INVENTORY ITEMS
        	else {
	            String text;

             
	
	            if(canObtainItem(gp.obj[gp.currentMap][i]) == true){
	                gp.playSE(1);
	                text = "Got a " + gp.obj[gp.currentMap][i].name + "!";	
	            }   
	            else {
	                	text = "You cannot carry any more!";
		            }  
		            gp.ui.addMessage(text);
		            gp.obj[gp.currentMap][i] = null;
        }
    }
}
    
    public void interactNPC(int i) {
        if(gp.keyH.enterPressed == true || gp.keyH.FPressed == true){
            if (i != 999) {  
                gp.npc[gp.currentMap][i].speak();
    		}  
            //else{
              //  gp.playSE(5);
              //  attacking = true;
           // } 
        }
        if(gp.keyH.leftMouse == true){
            gp.playSE(5);
            attacking = true;
        }        	
    }
    
    public void contactMonster(int i){
    	if (i != 999) {
    		if (invincible == false && gp.monster[gp.currentMap][i].dying == false) {
    			gp.playSE(6);
    			int damage = gp.monster[gp.currentMap][i].attack - defense;
    			if (damage < 0) {
    				damage = 0;
    			}
    			life -= damage;
    			invincible = true;
    		}
    	}
    }

    public void damageMonster(int i, int attack, int knockBackPower){
        if(i != 999){
            if(gp.monster[gp.currentMap][i].invincible == false){
                
                gp.playSE(7);

                if(knockBackPower>0){
                    knockBack(gp.monster[gp.currentMap][i], knockBackPower);
                }

                int damage = attack - gp.monster[gp.currentMap][i].defense;
                if(damage < 0){
                    damage = 0;
                }

                gp.monster[gp.currentMap][i].life -= damage;
                gp.ui.addMessage(damage + " damage");

                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();

                if(gp.monster[gp.currentMap][i].life <= 0){
                    gp.playSE(7);
                    gp.monster[gp.currentMap][i].dying = true;
                    gp.ui.addMessage("Killed the " + gp.monster[gp.currentMap][i].name + "!");
                    gp.ui.addMessage("Exp + " + gp.monster[gp.currentMap][i].exp);
                    exp += gp.monster[gp.currentMap][i].exp;
                    checkLevelUp();
                }
            }
        }
    }
    public void damageProjectile(int i){
        if(i!=999){
            Entity projectile = gp.projectile[gp.currentMap][i];
            projectile.alive=false;
            generateParticle(projectile, projectile);
        }

        // When the weapon hit the projectile the projectile die
    }
    
    public void checkLevelUp(){
        if(exp >= nextLevelExp){
            level++;
            nextLevelExp = nextLevelExp*2;
            maxLife += 2;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();

            gp.playSE(8);
            gp.gameState =  gp.dialogueState;
            dialogues[0][0] = "Level up: Level" + level;
            startDialogue(this,0);
        }
    }
    public void knockBack(Entity entity, int knockBackPower){
        entity.direction= direction;
        entity.speed+=knockBackPower;
        entity.knowBack=true;
    }

    public void damageInteractiveTile(int i) {
    	if (i != 999 && gp.iTile[gp.currentMap][i].destructible == true && gp.iTile[gp.currentMap][i].isCorrectItem(this) == true
    		&& gp.iTile[gp.currentMap][i].invincible == false) {
    		gp.iTile[gp.currentMap][i].life--;
    		gp.iTile[gp.currentMap][i].playSE();
    		gp.iTile[gp.currentMap][i].invincible = true;
    		// Generate particle
    		generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);    		
    		if(gp.iTile[gp.currentMap][i].life == 0) {
    		gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
    	}    	    	
    }
}
    
    public void selectItem(){
        int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);
        if(itemIndex < inventory.size()){
            Entity selectedItem = inventory.get(itemIndex);
            if(selectedItem.type == type_sword || selectedItem.type == type_axe){
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();
            }
            if(selectedItem.type == type_shield){
                currentShield = selectedItem;
                defense = getDefense();
            }
            if(selectedItem.type == type_light){
                if(currentLight == selectedItem){
                    currentLight = null;
                }
                else {
                    currentLight = selectedItem;
                }
                lightUpdated=true;
                // whenever we unequip the lantern we set the light update is true 
            }
            if(selectedItem.type == type_consumable){
                if(selectedItem.use(this) == true){
                    if(selectedItem.amount >1){
                        selectedItem.amount--;
                    }
                    else{
                    inventory.remove(itemIndex);}
                }
            }
        }
    }
    // whenever we got new item , we call this method, and pass the item name the it return the index of the item 
    public int searchItemInInventory(String itemName){
        int itemIndex=999;
        for (int i= 0; i<inventory.size();i++){
            if(inventory.get(i).name.equals(itemName)){
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
        
    }
    public boolean canObtainItem(Entity item){
        boolean canObtain = false;
        // Check if stackable
        if(item.stackable == true){
            int index = searchItemInInventory(item.name);

            if(index != 999){// it mean that we already have the same item in inventory
            inventory.get(index).amount++;
            canObtain = true;
            }
            else{// new item so need to check vacancy
                if(inventory.size() != maxInventorySize){
                    inventory.add(item);
                    canObtain = true;
                }
            }
        }
        else{ // NOT STACKABLE so check vacacy
              if(inventory.size() != maxInventorySize){
                    inventory.add(item);
                    canObtain = true;
                }
        }
        return canObtain;
    } 
    
    public void draw (Graphics2D g2){
        //g2.setColor(Color.white); 				// Sets a color to use for drawing object
        //g2.fillRect(x,y,gp.tileSize,gp.tileSize); // This method draw a rectangle on the screen
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;
        switch (direction){
            case "up":
                if(attacking == false){
                    if(spriteNum == 1) {
                        image = up1;
                    }
                    if(spriteNum == 2){
                        image = up2;
                    }
                }
                if(attacking == true){
                    tempScreenY = screenY - gp.tileSize;
                    if(spriteNum == 1) {
                        image = attackUp1;
                    }
                    if(spriteNum == 2){
                        image = attackUp2;
                    }
                }
                
                break;
            case "down":
                if(attacking == false){
                    if(spriteNum == 1) {
                        image = down1;
                    }
                    if(spriteNum == 2){
                        image = down2;
                    }
                }
                if(attacking == true){
                    if(spriteNum == 1) {
                        image = attackDown1;
                    }
                    if(spriteNum == 2){
                        image = attackDown2;
                    }
                }
                
                break;
            case "left":
                if(attacking == false){
                    if(spriteNum == 1) {
                        image = left1;
                    }
                    if(spriteNum == 2){
                        image = left2;
                    }
                }
                if(attacking == true){
                    tempScreenX = screenX - gp.tileSize;
                    if(spriteNum == 1) {
                        image = attackLeft1;
                    }
                    if(spriteNum == 2){
                        image = attackLeft2;
                    }
                }
                
                break;
            case "right":
                if(attacking == false){
                    if(spriteNum == 1) {
                        image = right1;
                    }
                    if(spriteNum == 2){
                        image = right2;
                    }
                }
                if(attacking == true){
                    if(spriteNum == 1) {
                        image = attackRight1;
                    }   
                    if(spriteNum == 2){
                        image = attackRight2;
                    }
                }                
                break;
        }
        
        // Visual effect to invincible state, like when you lose heal, you get stunned effect
        if(invincible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        g2.drawImage(image, tempScreenX, tempScreenY,null);

        // RESET ALPHA
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));


        //DEBUG
//        g2.setFont(new Font("Arial", Font.PLAIN, 26));
//        g2.setColor(Color.WHITE);
//        g2.drawString("Invincible:"+invincibleCounter, 10, 400);
    }
    
    public void setDefaultPositions() {
        worldX = gp.tileSize * 25;
        worldY = gp.tileSize * 18;
        direction = "down";
    }
    
    public void setDialogue(){
        
    }
//    public void setDialogue(){
//        dialogues[0][0] = "Level complete: " +"\n You win";
//    }
    
    public void restoreLifeAndMana() {
    	life = maxLife;
    	mana = maxMana;
        speed = defaultSpeed;
    	invincible = false;
    	
    }
}
