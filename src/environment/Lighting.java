package environment;

import main.GamePanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
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

        // create a gradation effect within the light circle 
        Color color[] = new Color[12]; 
        float fraction[]= new float[12];// this number decide number of divides level of the gradation so increase this number 
        //create more detail gradation data  
        
        // they are all black but they have different alpha
        
		color[0] = new Color(0,0,0,0.1f);
		color[1] = new Color(0,0,0,0.42f);
		color[2] = new Color(0,0,0,0.52f);
		color[3] = new Color(0,0,0,0.61f);
		color[4] = new Color(0,0,0,0.69f);
		color[5] = new Color(0,0,0,0.76f);
		color[6] = new Color(0,0,0,0.82f);
		color[7] = new Color(0,0,0,0.87f);
		color[8] = new Color(0,0,0,0.91f);
		color[9] = new Color(0,0,0,0.94f);
		color[10] = new Color(0,0,0,0.96f);
		color[11] = new Color(0,0,0,0.98f);

        fraction[0] = 0f;
		fraction[1] = 0.4f;
		fraction[2] = 0.5f;
		fraction[3] = 0.6f;
		fraction[4] = 0.65f;
		fraction[5] = 0.7f;
		fraction[6] = 0.75f;
		fraction[7] = 0.8f;
		fraction[8] = 0.85f;
		fraction[9] = 0.9f;
		fraction[10] = 0.95f;
		fraction[11] = 1f;
		

        // Create a gradiation paint setting 

        RadialGradientPaint gPaint = new RadialGradientPaint(centerX,centerY,(circleSize/2),fraction,color);

        //set the gradient data on g2
        g2.setPaint(gPaint);

        //Draw the light circle
        g2.fill(lightArea);

        // Draw the screen rectangle without the light circle area
        g2.fill(screenArea);
        g2.dispose();
    }
    public void draw ( Graphics2D g2){
        g2.drawImage(darknessFilter,0,0,null);
    }



        

    }

