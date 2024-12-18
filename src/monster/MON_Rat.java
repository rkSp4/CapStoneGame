package monster;

import Entity.Entity;
import Main.GamePanel;

import java.util.Random;

public class MON_Rat extends Entity {

    public MON_Rat(GamePanel gp) {
        super(gp);
        type = 2;
        name = "Rat";
        speed = 1;
        maxLife = 3;
        life = maxLife;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        SolidAreaDefaultX = solidArea.x;
        SolidAreaDefaultY = solidArea.y;
        getImage();
        speed = speed/2;

    }
    public void getImage() {
        up1 = setup("/monster/rat_up_1");
        up2 = setup("/monster/rat_up_2");
        down1 = setup("/monster/rat_down_1");
        down2 = setup("/monster/rat_down_2");
        left1 = setup("/monster/rat_left_1");
        left2 = setup("/monster/rat_left_2");
        right1 = setup("/monster/rat_right_1");
        right2 = setup("/monster/rat_right_2");
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
            if (i > 75) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
}

