package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp=gp;
        this.keyH=keyH;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed =4;
        direction="up";

    }
    public void getPlayerImage() {
        try {
            up1= ImageIO.read(getClass(). getResourceAsStream("/player/FroGi_up1.png"));
            up2=ImageIO.read(getClass(). getResourceAsStream("/player/FroGi_up2.png"));
            down1=ImageIO.read(getClass().getResourceAsStream("/player/FroGi_down1.png"));
            down2=ImageIO.read(getClass(). getResourceAsStream("/player/FroGi_down2.png"));
            left1=ImageIO.read(getClass(). getResourceAsStream("/player/FroGi_Left1.png"));
            left2=ImageIO.read(getClass(). getResourceAsStream("/player/FroGi_left2.png"));
            right1=ImageIO.read(getClass(). getResourceAsStream("/player/FroGi_right1.png"));
            right2=ImageIO.read(getClass(). getResourceAsStream("/player/FroGi_right2.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){// this update method gét called60 times per second
        // so in everyframe it get call and increase this counter by 1
        if(keyH.upPressed == true||keyH.downPressed == true||keyH.leftPressed == true||keyH.rightPressed == true){
            if(keyH.upPressed == true){
                direction="up";
                y -= speed; // X values increases to the right; Y values increases as they go down
            }
            else if (keyH.downPressed == true){
                direction="down";
                y+= speed;
            }
            else if (keyH.leftPressed == true){
                direction="left";
                x -= speed;
            }
            else if (keyH.rightPressed == true){
                direction="right";
                x += speed;}
            spriteCounter++;
            if(spriteCounter>10){
                if (spriteNum ==1){
                    spriteNum =2;
                }
                else if (spriteNum == 2){
                    spriteNum =1;
                }
                spriteCounter=0;
            }
        }


    }
    public void draw (Graphics2D g2){
        //g2.setColor(Color.white);// Sets a color to use for drawing object
        //g2.fillRect(x,y,gp.tileSize,gp.tileSize);//this method draw a rectangle on the screen
        BufferedImage image = null;
        switch (direction){
            case "up":
                if(spriteNum==1) {
                    image = up1;
                }
                if(spriteNum==2){
                    image=up2;
                }
                break;
            case "down":
                if(spriteNum==1) {
                    image = down1;
                }
                if(spriteNum==2){
                    image=down2;
                }
                break;
            case "left":
                if(spriteNum==1) {
                    image = left1;
                }
                if(spriteNum==2){
                    image=left2;
                }
                break;
            case "right":
                if(spriteNum==1) {
                    image = right1;
                }
                if(spriteNum==2){
                    image=right2;
                }
                break;

        }
        g2.drawImage(image,x,y,gp.tileSize,gp.tileSize,null);
    }
}
