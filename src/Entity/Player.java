package Entity;

import Main.GamePanel;
import Main.KeyHandler;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLOutput;

public class Player extends Entity{

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int haskey = 0;


    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        SolidAreaDefaultX = solidArea.x;
        SolidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 16;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/cat_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/cat_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/cat_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/cat_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/cat_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/cat_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/cat_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/cat_right_2.png"));
            tab = ImageIO.read(getClass().getResourceAsStream("/player/tab.png"));
            white = ImageIO.read(getClass().getResourceAsStream("/player/white.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {

            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }

            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //CHECK OBJECT COLLISION
           int objIndex = gp.cChecker.checkObject(this, true);
            pickUp0bject(objIndex);

            //CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //IF COLLISION FALSE, PLAYER CAN MOVE
            if(collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        //worldY = worldY - speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        }

    }

    public void pickUp0bject(int i){
            if(i != 999)
            {
                String objectName = gp.obj[i].name;

                switch (objectName)
                {
                    case "key":
                        gp.playSE(1);
                        haskey++;
                        gp.obj[i] = null;
                        gp.ui.showMessage("You got a key!");
                        break;
                    case "door":

                        if(haskey > 0)
                        {
                            gp.playSE(3);
                            gp.obj[i] = null;
                            haskey--;
                            gp.ui.showMessage("You have opened the door!");
                        }else {
                            gp.ui.showMessage("You need a key dawg! :/");
                        }
                        break;
                    case "chest":
                        gp.ui.gameFinished = true;
                        gp.stopMusic();
                        gp.playSE(4);
                        gp.ui.showMessage("CONGRATULATIONS!!! :>");
                        break;
                    case "boots":
                        gp.playSE(2);
                        speed += 1;
                        gp.obj[i] = null;
                        gp.ui.showMessage("Speed up by 1.25x!");
                        break;
                }
            }
    }

    public void interactNPC(int i){
        if(i != 999){
            gp.gameState = gp.overState;
        }
    }

    public void draw(Graphics a2) {
        //   a2.setColor(Color.red);
        //   a2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        switch(direction) {
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                break;
            case  "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        a2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
