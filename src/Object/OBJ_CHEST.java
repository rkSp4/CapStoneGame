package Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_CHEST extends SuperObject{
    public OBJ_CHEST() {

        name = "chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
