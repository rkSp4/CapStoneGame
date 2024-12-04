package Object;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_KEY extends SuperObject {

    public OBJ_KEY() {

        name = "key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
