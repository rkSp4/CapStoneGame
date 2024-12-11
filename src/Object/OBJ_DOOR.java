package Object;

import Entity.Entity;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_DOOR extends Entity {

    public OBJ_DOOR(GamePanel gp) {
        super(gp);
        name = "door";
        image = setup("/objects/door_iron");
        down1 = setup("/objects/door_iron");
        collision = true;
    }
}
