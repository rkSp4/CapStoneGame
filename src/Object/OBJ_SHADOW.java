package Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_SHADOW extends SuperObject{
    public OBJ_SHADOW() {

        name = "shadow";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
