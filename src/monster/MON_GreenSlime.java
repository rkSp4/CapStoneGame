/*package monster;

import Entity.Entity;
import Main.GamePanel;

import java.util.Random;

public class MON_GreenSlime extends Entity {

    public MON_GreenSlime(GamePanel gp) {
        super(gp);

        type = 2;
        name = "Green Slime";
        speed = 1;
        getImage();
        maxLife = 3;
        life = maxLife;
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        SolidAreaDefaultX = solidArea.x;
        SolidAreaDefaultY = solidArea.y;
    }
    public void getImage() {
        up1 = setup("/monster/greenslime_down_1");
        up2 = setup("/monster/greenslime_down_1");
        down1 = setup("/monster/greenslime_down_1");
        down2 = setup("/monster/greenslime_down_2");
        left1 = setup("/monster/greenslime_down_1");
        left2 = setup("/monster/greenslime_down_2");
        right1 = setup("/monster/greenslime_down_1");
        right2 = setup("/monster/greenslime_down_2");
    }

    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 120) {

            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
}
 */