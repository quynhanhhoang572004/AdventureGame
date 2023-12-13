package object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class OBJ_Key extends SuperObject {
    GamePanel gp;
    UtilityTool uTool;
    public OBJ_Key (GamePanel gp) {
        this.gp = gp;
        this.uTool = new UtilityTool();
        name = "Key";
        try {
            image = ImageIO.read(new File("res/objects/key.png")); // using getResourceAsStream will cause error.
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
