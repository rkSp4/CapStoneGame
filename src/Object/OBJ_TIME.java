package Object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_TIME extends SuperObject{

    GamePanel gp;

    public OBJ_TIME(GamePanel gp) {

        this.gp = gp;

        name = "time";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/time.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }    }
}
