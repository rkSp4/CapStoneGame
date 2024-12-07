package Object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_BOOTS extends SuperObject{

    public OBJ_BOOTS(GamePanel gp) {

        name = "Boots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
