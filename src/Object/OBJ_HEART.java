package Object;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_HEART extends Entity {

    public OBJ_HEART(GamePanel gp) {

        super(gp);
        name = "heart";
        image = setup("/objects/heart_full");
        image2 = setup("/objects/heart_half");
        image3 = setup("/objects/heart_blank");

        //collision = true;
    }
}