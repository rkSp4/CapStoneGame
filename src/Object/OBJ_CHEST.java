package Object;

import Entity.Entity;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_CHEST extends Entity {

    public OBJ_CHEST(GamePanel gp) {
        super(gp);
        name = "Chest";
        image = setup("/objects/chest");
        down1 = setup("/objects/chest");
    }
}
