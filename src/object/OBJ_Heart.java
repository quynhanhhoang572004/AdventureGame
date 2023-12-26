package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class OBJ_Heart extends SuperObject {
    GamePanel gp;
    UtilityTool uTool;
    
    public OBJ_Heart (GamePanel gp) {
        this.gp = gp;
        this.uTool = new UtilityTool();
        name = "Heart";
        try {
            image = ImageIO.read(new File("res/objects/heart_full.png")); 
            image2 = ImageIO.read(new File("res/objects/heart_half.png")); 
            image3 = ImageIO.read(new File("res/objects/heart_blank.png")); 
            // getResourceAsStream will cause errors when compiling the code due to the outdated syntaxes
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);             
            uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
            uTool.scaleImage(image3, gp.tileSize, gp.tileSize);           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
