package Object;

import Entity.Entity;
import Main.GamePanel;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_CLAW extends Entity {

    public OBJ_CLAW(GamePanel gp) {
        super(gp);
        name = "claw";
        down1 = setup("/objects/claw");
        image = setup("/objects/claw");

    }

}
