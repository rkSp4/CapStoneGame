package Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_DOOR extends SuperObject{
    public OBJ_DOOR() {

        name = "door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door_iron.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
