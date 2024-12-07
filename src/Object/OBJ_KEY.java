package Object;

import Main.GamePanel;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_KEY extends SuperObject {

    public OBJ_KEY(GamePanel gp) {

        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
