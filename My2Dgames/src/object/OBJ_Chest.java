package object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Chest extends SuperObject {
    public OBJ_Chest () {
        name = "Chest";
        try {
            image = ImageIO.read(new File("res/objects/chest.png")); // Using getResourceAsStream when running code will get error
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
