package Object;

import Entity.Entity;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_TIME extends Entity {

    GamePanel gp;

    public OBJ_TIME(GamePanel gp) {
        super(gp);
        name = "time";
        down1 = setup("/objects/time");
    }
}

