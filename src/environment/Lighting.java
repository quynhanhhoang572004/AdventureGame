package environment;

import main.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Lighting {
    GamePanel gp;
    BufferedImage darknessFilter;

    public Lighting ( GamePanel gp, int circleSize){
        //create buffered image
        darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 =(Graphics2D)darknessFilter.getGraphics();
    }


}
