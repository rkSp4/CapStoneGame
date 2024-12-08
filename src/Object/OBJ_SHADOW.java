package Object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_SHADOW extends SuperObject{
    public OBJ_SHADOW(GamePanel gp) {

        name = "shadow";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
