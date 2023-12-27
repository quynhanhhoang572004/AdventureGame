package object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class OBJ_Boots extends SuperObject {
    GamePanel gp;
    UtilityTool uTool; 

    public OBJ_Boots (GamePanel gp) {
        this.gp = gp;
        this.uTool = new UtilityTool(); 
        name = "Boots";
        try {
            image = ImageIO.read(new File("res/objects/boots.png")); 
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
         // getResourceAsStream will cause errors when compiling the code due to the outdated syntaxes
        } catch (IOException e) {                                     
            e.printStackTrace();
        }
    }
}

