package Object;

import Main.GamePanel;
import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_CLAWS extends SuperObject{
    public OBJ_CLAWS(GamePanel gp) {
        name = "claws";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
