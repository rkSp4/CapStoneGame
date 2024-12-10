package Object;

import Entity.Entity;
import Main.GamePanel;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_KEY extends Entity {

    public OBJ_KEY(GamePanel gp) {
        super(gp);
        name = "Key";
        down1 = setup("/objects/key");

    }

}
