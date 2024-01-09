package environment;

import main.GamePanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Lighting {
    GamePanel gp;
    BufferedImage darknessFilter;

    public Lighting ( GamePanel gp, int circleSize){
        //create buffered image
        darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 =(Graphics2D)darknessFilter.getGraphics();

        // create a screen-sized rectangle area
        Area screenArea = new Area(new Rectangle2D.Double(0,0,gp.screenWidth, gp.screenHeight));

        // Get the center x and center y of the light circle 
        int centerX=gp.player.screenX + (gp.tileSize)/2;
        int centerY=gp.player.screenY+(gp.tileSize)/2;

        // get the top-left and the top light circle
        double x= centerX - (circleSize/2);
        double y = centerY - (circleSize/2);

        //create light circle shape
        Shape circleShape = new Ellipse2D.Double(x,y,circleSize,circleSize);

        // create a light circle area 
        Area lightArea = new Area (circleShape);

        // subtract the light circle from the screen rectangle 
        screenArea.subtract(lightArea);

        // set color (black) to draw the rectangle
        g2.setColor(new Color(0,0,0,0.95f));

        // Draw the screen rectangle without the light circle area
        g2.fill(screenArea);
        g2.dispose();
    }
    public void draw ( Graphics2D g2){
        g2.drawImage(darknessFilter,0,0,null);
    }



        

    }

