package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class NPC extends Entity{

    public NPC(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;

        getNPCImage();
    }
    public void getNPCImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/cat_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/cat_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/cat_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/cat_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/cat_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/cat_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/cat_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/cat_right_2.png"));

        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void setAction(){

        actionLockCounter++;

        if(actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1;


            if(i<=25){
                direction = "up";
            }
            if(i>25 && i <= 50){
                direction = "down";
            }
            if(i>50 && i<=75){
                direction = "left";
            }
            if(i>75 && i<=100){
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }
}
