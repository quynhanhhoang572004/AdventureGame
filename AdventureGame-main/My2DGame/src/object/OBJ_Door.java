package object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class OBJ_Door extends SuperObject {
    GamePanel gp;
    UtilityTool uTool;
    public OBJ_Door (GamePanel gp) {
        this.gp = gp;
        this.uTool = new UtilityTool();
        name = "Door";
        try {
            image = ImageIO.read(new File("My2DGame/res/objects/door.png")); // using getResourceAsStream will cause error.
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true; // The player cannot go though this object
    }
}

    

