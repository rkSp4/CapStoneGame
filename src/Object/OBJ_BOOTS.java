package Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_BOOTS extends SuperObject{
    public OBJ_BOOTS() {

        name = "boots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
