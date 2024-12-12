package Object;

import Entity.Entity;
import Main.GamePanel;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_PAW extends Entity {

    public OBJ_PAW(GamePanel gp) {
        super(gp);
        name = "paw";
        down1 = setup("/objects/paw");
        image = setup("/objects/paw");

    }

}
