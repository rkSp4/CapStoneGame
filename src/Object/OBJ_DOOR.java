package Object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_DOOR extends SuperObject{

    public OBJ_DOOR(GamePanel gp) {

        name = "door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door_iron.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
