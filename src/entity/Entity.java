// This entity will be the parent class
package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {
	public GamePanel gp;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
	public BufferedImage image, image2, image3;
    public boolean collision = false;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
	//creating invincible time
    String dialogues[] = new String[20];
	

	//STATE
    public int worldX, worldY;
	public String direction = "down";
	public int spriteNum = 1;
	int dialogueIndex = 0;
	public boolean collisionOn = false;
	public boolean invincible = false;
	public boolean attacking = false;
	public boolean alive = true;
	public boolean dying = false;
	public boolean hpBarOn = false;
	
	//COUNTER
	public int spriteCounter = 0;
	public int actionLockCounter = 0;
	public int invincibleCounter = 0;
	public int shotAvailableCounter = 0;
	public int dyingCounter = 0;
	public int hpBarcounter = 0;
	
	//CHARACTER ATTRIBUTES
	public String name;
	public int speed;
	public int maxLife;
    public int life;
	public int maxMana;
	public int mana;
	public int ammo;
	public int level;
	public int strength;
	public int dexterity;
	public int attack;
	public int defense;
	public int exp;
	public int nextLevelExp;
	public int coin;
	public Entity currentWeapon;
	public Entity currentShield;
    public Projectile projectile;

	//ITEM ATTRIBUTES
    public int value;
	public int attackValue;
	public int defenseValue;
	public String description = "";
	public int useCost;
	
	//TYPE
	public int type; 
// 0 for player, 1 for npc, 2 for monster (for example), so that we can make classifications for each type
	public final int type_player = 0;
	public final int type_npc = 1;
	public final int type_monster = 2;
	public final int type_sword = 3;
	public final int type_axe = 4;
	public final int type_shield = 5;
	public final int type_consumable = 6;
	public final int type_pickupOnly = 7;
	
	// CHARACTER STATUS
    public Entity(GamePanel gp) {
    	this.gp = gp;
    }
    
    public void setAction () {}
	public void damageReaction(){		
	}
    public void speak () {
    	if (dialogues[dialogueIndex] == null) {
    		dialogueIndex = 0;
    	}
    	gp.ui.currentDialogue = dialogues[dialogueIndex];
    	dialogueIndex++;
    	
    	// Switch cases to make the NPC looks toward the main character when interacting
    	switch(gp.player.direction) {
    	case "up":
    		direction = "down";
    		break;
    	case "down":
    		direction = "up";
    		break;
    	case "left":
    		direction = "right";
    		break;
    	case "right":
    		direction = "left";  		
    		break;
    	}
    }

    public void checkDrop() {}
    public void dropItem(Entity droppedItem) {
    	for (int i = 0; i < gp.obj[1].length; i++) {
    		if (gp.obj[gp.currentMap][i] == null) {
    			gp.obj[gp.currentMap][i] = droppedItem;
    			gp.obj[gp.currentMap][i].worldX = worldX;	// The d3ad monster's worldX
    			gp.obj[gp.currentMap][i].worldY = worldY;	// The d3ad monster's worldY
    			break;
    		}
    	}
    }
    
	public Color getParticleColor () {
		Color color = null; 
		return color;
	}
	
	public int getParticleSize () {
		int size = 0; 	// Blueprint so dont need to clarify the value
		return size;
	}
	
	public int getParticleSpeed () {
		int speed = 0;
		return speed;
	}
	
	public int getParticleMaxLife () {
		int maxLife = 0;
		return maxLife;
	}
    
	public void generateParticle (Entity generator, Entity target) {
		Color color = generator.getParticleColor();
		int size = generator.getParticleSize();
		int speed = generator.getParticleSpeed();
		int maxLife = generator.getParticleMaxLife();
		
		Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -2, -1);	//down left
																		 //xd and yd, respectively	
		Particle p2 = new Particle(gp, target, color, size, speed, maxLife, 2, -1);		//up right
		Particle p3 = new Particle(gp, target, color, size, speed, maxLife, -2, 1);		//up left
		Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1); 		//down right
		gp.particleList.add(p1);
		gp.particleList.add(p2);
		gp.particleList.add(p3);
		gp.particleList.add(p4);

	}
	
	public void use(Entity entity) {		
	}
	
    public void update () {
    	setAction();
    	collisionOn = false;
    	gp.cChecker.checkTile(this);
    	gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		gp.cChecker.checkEntity(this, gp.iTile);
    	boolean contactPlayer = gp.cChecker.checkPlayer(this);

		if(this.type == type_monster && contactPlayer == true){
			damagePlayer(attack);
		}
    	
        // IF COLLISION IS FALSE, THE PLAYER CAN MOVE
        if(collisionOn == false){
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
        spriteCounter++;
        if(spriteCounter > 12){
            if (spriteNum == 1){
                spriteNum = 2;
            }
            else if (spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
		if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 40){
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if(shotAvailableCounter < 30){
            shotAvailableCounter++;
        }
    }
        
    public void damagePlayer(int attack) {
		if(gp.player.invincible == false){
			//damage can be taken
			gp.playSE(6);

			int damage = attack - gp.player.defense;
            if(damage < 0){
                damage = 0;
            }

			gp.player.life -= damage;
			gp.player.invincible = true;
		}
    }
    
	public void draw(Graphics2D g2) {		
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {        	
	        switch (direction) {
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
			
			//MONSTER HP BAR
			if(type == 2 && hpBarOn == true){

				double oneScale = (double)gp.tileSize/maxLife;
				double hpBarValue = oneScale * life;
				g2.setColor(new Color(35,35,35));
				g2.fillRect(screenX - 1, screenY - 16, gp.tileSize + 2, 12);

				g2.setColor(new Color(255, 0, 30));
				g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);

				hpBarcounter++;

				if(hpBarcounter > 600){
					hpBarcounter = 0;
					hpBarOn = false;
				}
			}
			
			if(invincible == true){
				hpBarOn = true;
				hpBarcounter = 0;
				changeAlpha(g2, 0.4f);
				
			}    
			if(dying == true){
				dyingAnimation(g2);
			} 	
            g2.drawImage(image, screenX, screenY, null);
			changeAlpha(g2, 1f);
        }
	}
	public void dyingAnimation(Graphics2D g2){
		dyingCounter++;
		int i = 5;

		if(dyingCounter  <= i){
			changeAlpha(g2, 0f);
		}
		if(dyingCounter  > i && dyingCounter <= i * 2){
			changeAlpha(g2, 1f);
		}
		if(dyingCounter  > i * 2 && dyingCounter <= i * 3){
			changeAlpha(g2, 0f);
		}
		if(dyingCounter  > i * 3 && dyingCounter <= i *4){
			changeAlpha(g2, 1f);
		}
		if(dyingCounter  > i * 4 && dyingCounter <= i * 5){
			changeAlpha(g2, 0f);
		}
		if(dyingCounter  > i * 5 && dyingCounter <= i * 6){
			changeAlpha(g2, 1f);
		}
		if(dyingCounter  > i * 6 && dyingCounter <= i * 7){
			changeAlpha(g2, 0f);
		}
		if(dyingCounter  > i * 7 && dyingCounter <= i * 8){
			changeAlpha(g2, 1f);
		}
		if(dyingCounter > 8){
			alive = false;
		}


	}
	//we use this in order to code faster to dyingCounter, dying, alive
	public void changeAlpha(Graphics2D g2, float alphaValue){
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
	}
	public BufferedImage setup(String imagePath, int width, int height) {
	    UtilityTool uTool = new UtilityTool();
	    BufferedImage image = null;
	    
	    try {
	        image = ImageIO.read(new File(imagePath + ".png"));
	        image = uTool.scaleImage(image, width, height);
	    }catch(IOException e) {
	        e.printStackTrace();
	    }
	    return image;
	}
}
