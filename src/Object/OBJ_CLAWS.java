package Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_CLAWS extends SuperObject{
    public OBJ_CLAWS() {

        name = "claws";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
