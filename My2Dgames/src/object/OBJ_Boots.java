package object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Boots extends SuperObject {
	public OBJ_Boots () {
		name = "Boots";
	        try {
	            image = ImageIO.read(new File("res/objects/socks.png")); 
	            // getResourceAsStream is an old structure so it may cause errors
	        } catch (IOException e) {									 
	            e.printStackTrace();
	        }
	}
}

