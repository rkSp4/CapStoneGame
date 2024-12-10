package Object;

import Entity.Entity;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_SHADOW extends Entity {
    public OBJ_SHADOW(GamePanel gp) {
        super(gp);
        name = "shadow";
        down1 = setup("/objects/boots");

    }
}

