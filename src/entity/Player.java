package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    //public int hasKey = 0;
    public String hasKey;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
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
        direction="down";

    }
    public void getPlayerImage() {
            up1 = setup("FroGi_up1");
            up2 = setup("FroGi_up2");
            down1 = setup("FroGi_down1");
            down2 = setup("FroGi_down2");
            left1 = setup("FroGi_Left1");
            left2 = setup("FroGi_left2");
            right1 = setup("FroGi_right1");
            right2 = setup("FroGi_right2");
    }

    public BufferedImage setup(String imageName) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        
        try {
            image = ImageIO.read(new File("res/player/" + imageName+".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void update(){ // This update method gets called 60 times per second
        // So in every frame it get called and increase this counter by 1
        if(keyH.upPressed == true||keyH.downPressed == true||keyH.leftPressed == true||keyH.rightPressed == true){
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
    }
    
    public void pickupObject(int i) {
        if (i != 999) {
           
        }
    }
  
    public void draw (Graphics2D g2){
        //g2.setColor(Color.white); // Sets a color to use for drawing object
        //g2.fillRect(x,y,gp.tileSize,gp.tileSize); //this method draw a rectangle on the screen
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
        g2.drawImage(image,screenX, screenY,gp.tileSize,gp.tileSize,null);
    }
}
