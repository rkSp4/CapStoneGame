package Entity;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class NPC_Boy extends Entity {
    public NPC_Boy(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 1;
        getImage();
        setDialogue();
    }

    public void getImage() {
        up1 = setup("/npc/boy_up_1");
        up2 = setup("/npc/boy_up_2");
        down1 = setup("/npc/boy_down_1");
        down2 = setup("/npc/boy_down_2");
        left1 = setup("/npc/boy_left_1");
        left2 = setup("/npc/boy_left_2");
        right1 = setup("/npc/boy_right_1");
        right2 = setup("/npc/boy_right_2");
    }

    public void setDialogue() {
        dialogue[0] = "Hey, watch where you are going- a cat!?";
        dialogue[1] = "What's a cat doing here?";
        dialogue[2] = "Here kitty kitty kitty";
        dialogue[3] = "Hey wait come back!";

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

    public void speak() {
        super.speak();
    }
}