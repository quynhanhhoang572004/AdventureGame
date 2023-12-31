package entity;


import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.dnd.Autoscroll;
import java.awt.image.BufferedImage;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public String hasKey;
    int standCounter = 0;

    public Player(GamePanel gp, KeyHandler keyH){
    	super(gp);
        this.keyH = keyH;
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 22;
        speed = 4;
        direction = "down";
        
        // PLAYER STATUS
        maxLife = 6; 
        // 1 maxLife unit = a heart half
        // => 6 maxLife unit = 3 hearts
        life = maxLife;
        
    }
    
    public void getPlayerImage() {
            up1 = setup("res/player/FroGi_up1"); 
            up2 = setup("res/player/FroGi_up2");
            down1 = setup("res/player/FroGi_down1");
            down2 = setup("res/player/FroGi_down2");
            left1 = setup("res/player/FroGi_left3");
            left2 = setup("res/player/FroGi_left2");
            right1 = setup("res/player/FroGi_right3");
            right2 = setup("res/player/FroGi_right2");
    }

    public void getPlayerAttackImage(){
        attackUp1 = setup("res/player/FroGi_attack_up1");
        attackUp2 = setup("res/player/FroGi_attack_up1");
        attackDown1 = setup("res/player/FroGi_attack_up1");
        attackDown2 = setup("res/player/FroGi_attack_up1");
        attackLeft1 = setup("res/player/FroGi_attack_up1");
        attackLeft2 = setup("res/player/FroGi_attack_up1");
        attackRight1 = setup("res/player/FroGi_attack_up1");
        attackRight2 = setup("res/player/FroGi_attack_up1");

    }

    public void update(){ 
    	// This update method gets called 60 times per second
        // So in every frame it get called and increase this counter by 1
        if(keyH.upPressed == true||keyH.downPressed == true||keyH.leftPressed == true
            ||keyH.rightPressed == true || keyH.enterPressed == true){
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
            if(collisionOn == false && keyH.enterPressed == false){
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
    
    public void pickupObject(int i) {
        if (i != 999) {           
        }
    }
    
    public void interactNPC(int i) {
    	if (i != 999) {  
    		if (gp.keyH.enterPressed == true) {
        		gp.gameState = gp.dialogueState;
        		gp.npc[i].speak();
    		}
        }
    }
    public void contactMonster(int i){
        if(i != 999){

            if(invincible == false){
                life -= 1;
                invincible = true;
            }
            
        }
    }
    public void draw (Graphics2D g2){
        //g2.setColor(Color.white); 				// Sets a color to use for drawing object
        //g2.fillRect(x,y,gp.tileSize,gp.tileSize); // This method draw a rectangle on the screen
        BufferedImage image = null;
        switch (direction){
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }
        //visual effect to invincible state, like when you lose heal, you get stunned effect LOL
        if(invincible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));

        }

        g2.drawImage(image,screenX, screenY,gp.tileSize,gp.tileSize,null);

        // RESET ALPHA
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));


        //DEBUG
//        g2.setFont(new Font("Arial", Font.PLAIN, 26));
//        g2.setColor(Color.WHITE);
//        g2.drawString("Invincible:"+invincibleCounter, 10, 400);
    }
}
