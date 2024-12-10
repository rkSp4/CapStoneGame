package Object;

import Entity.Entity;
import Main.GamePanel;
import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_CLAWS extends Entity {
    public OBJ_CLAWS(GamePanel gp) {
        super(gp);
        name = "claws";
        down1 = setup("/objects/key");

    }
}

