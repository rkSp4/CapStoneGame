package Object;

import Entity.Entity;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_BOOTS extends Entity {

    public OBJ_BOOTS(GamePanel gp) {
        super(gp);
        name = "boots";
        image = setup("/objects/boots");

    }
}
