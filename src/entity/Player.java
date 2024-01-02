package entity;


import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Boots;
import object.OBJ_Key;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

public class Player extends Entity {
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public String hasKey;
    int standCounter = 0;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;

    public Player(GamePanel gp, KeyHandler keyH){
    	super(gp);
        this.keyH = keyH;
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        //SOLIDE AREA
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;
        //ATTACK AREA
        //attackArea.width = 36;
        //attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }
    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 22;
        speed = 4;
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
        coin = 0;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        attack = getAttack(); //the total attack value is decided by strength + weapon
        defense = getDefense();//the total def value is decided by dexterity + shield
    }
    public void setItems(){
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Boots(gp));
        inventory.add(new OBJ_Boots(gp));
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Boots(gp));
        inventory.add(new OBJ_Boots(gp));
        inventory.add(new OBJ_Key(gp));
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        

    }
    public int getAttack(){
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

    public void getPlayerAttackImage(){
        if(currentWeapon.type == type_sword){
            attackUp1 = setup("res/player/FroGi_attack_up1", gp.tileSize, gp.tileSize*2);
            attackUp2 = setup("res/player/FroGi_attack_up1", gp.tileSize, gp.tileSize*2);
            attackDown1 = setup("res/player/FroGi_attack_up1", gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("res/player/FroGi_attack_up1", gp.tileSize, gp.tileSize*2);
            attackLeft1 = setup("res/player/FroGi_attack_up1", gp.tileSize*2, gp.tileSize);
            attackLeft2 = setup("res/player/FroGi_attack_up1", gp.tileSize*2, gp.tileSize);
            attackRight1 = setup("res/player/FroGi_attack_up1", gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("res/player/FroGi_attack_up1", gp.tileSize*2, gp.tileSize);
        }
        if(currentWeapon.type == type_axe){
            attackUp1 = setup("res/player/FroGi_axe_attack_up1", gp.tileSize, gp.tileSize*2);
            attackUp2 = setup("res/player/FroGi_axe_attack_up1", gp.tileSize, gp.tileSize*2);
            attackDown1 = setup("res/player/FroGi_axe_attack_up1", gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("res/player/FroGi_axe_attack_up1", gp.tileSize, gp.tileSize*2);
            attackLeft1 = setup("res/player/FroGi_axe_attack_up1", gp.tileSize*2, gp.tileSize);
            attackLeft2 = setup("res/player/FroGi_axe_attack_up1", gp.tileSize*2, gp.tileSize);
            attackRight1 = setup("res/player/FroGi_axe_attack_up1", gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("res/player/FroGi_axe_attack_up1", gp.tileSize*2, gp.tileSize);
        }
        

    }

    public void update(){ 

        if(attacking == true){
            attacking();
        }
    	// This update method gets called 60 times per second
        // So in every frame it get called and increase this counter by 1
        if(keyH.upPressed == true||keyH.downPressed == true||keyH.leftPressed == true
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
        	
            
            // IF COLLISION IS FALSE, THE PLAYER CAN MOVE
            //In order not to let player moves while pressing feature key, add it in the if condition \/
            if(collisionOn == false && keyH.enterPressed == false && keyH.leftMouse == false && keyH.FPressed == false){
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
        //this needs to be outside of key if statment!
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    //showing attacking image 
    public void attacking(){
        spriteCounter++;
        if(spriteCounter <=5){
            spriteNum = 1;
        }
        if(spriteCounter > 5 && spriteCounter <= 25){
            spriteNum = 2;

            //save the current worldX, worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //Adjust player's worldX/Y for the attackArea
            switch(direction){
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;

            }
            //attackingArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            //check monster collision with the updated worldX, Y, solidArea
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            //after checking the collison, restore the original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if(spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }
    public void pickupObject(int i) {
        if (i != 999) {  

            String text;

            if(inventory.size() != maxInventorySize){
                inventory.add(gp.obj[i]);
                gp.playSE(1);
                text = "Got a "+ gp.obj[i].name + "!";

            }   
            else{
                text = "You cannot carry any more!\nGreedy -.-";
            }  
            gp.ui.addMessage(text);
            gp.obj[i] = null;
        }
    }
    
    public void interactNPC(int i) {
        if(gp.keyH.enterPressed == true || gp.keyH.FPressed == true){
            if (i != 999) {  
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
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
        if(i != 999 && gp.monster[i].dying == false){
            if(invincible == false){
                gp.playSE(6);

                int damage = gp.monster[i].attack - defense;
                if(damage < 0){
                    damage = 0;
                }

                life -= damage;
                invincible = true;
            }
            
        }
    }

    public void damageMonster(int i){
        if(i != 999){
            if(gp.monster[i].invincible == false){
                gp.playSE(7);

                int damage = attack - gp.monster[i].defense;
                if(damage < 0){
                    damage = 0;
                }

                gp.monster[i].life -= damage;
                gp.ui.addMessage(damage + "damage!");

                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();

                if(gp.monster[i].life <= 0){
                    gp.playSE(7);
                    gp.monster[i].dying = true;
                    gp.ui.addMessage("Killed the " + gp.monster[i].name + "!");
                    gp.ui.addMessage("Exp +" + gp.monster[i].exp);
                    exp += gp.monster[i].exp;
                    checkLevelUp();
                }
            }
        }
    }
    public void checkLevelUp(){
        if(exp >= nextLevelExp){
            level++;
            nextLevelExp = nextLevelExp*2;
            maxLife +=2;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();

            gp.playSE(8);
            gp.gameState =  gp.dialogueState;
            gp.ui.currentDialogue = "Level up: " + level +"\n Increase strength";
        }
    }

    public void selectItem(){

        int itemIndex = gp.ui.getItemIndexOnSlot();

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
            if(selectedItem.type == type_consumable){

                selectedItem.use(this);
                inventory.remove(itemIndex);
            }
        }
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
        //visual effect to invincible state, like when you lose heal, you get stunned effect LOL
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
}
