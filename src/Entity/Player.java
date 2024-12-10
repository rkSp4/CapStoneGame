package Entity;

import Main.GamePanel;
import Main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class Player extends Entity{


    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int haskey = 0;
    //CLAW
    public boolean clawActive = false;
    public boolean clawCD;
    public final int clawDuration = 5000;
    public final int clawCoolDown = 10000;
    //SPRINT
    public boolean sprintActive = false;
    public boolean sprintCD;
    public final int sprintDuration = 5000;
    public final int sprintCoolDown = 10000;

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

        //player status
         maxLife = 6;
         life = maxLife;
    }

    public void getPlayerImage() {
        up1 = setup("/player/cat_up_1");
        up2 = setup("/player/cat_up_2");
        down1 = setup("/player/cat_down_1");
        down2 = setup("/player/cat_down_2");
        left1 = setup("/player/cat_left_1");
        left2 = setup("/player/cat_left_2");
        right1 = setup("/player/cat_right_1");
        right2 = setup("/player/cat_right_2");
        tab = setup("/player/tab");
        white = setup("/player/white");
    }


    public void update() {
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }

            collisionOn = false;
            gp.cChecker.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUp0bject(objIndex);

            //CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //CHECK EVENT
            gp.eHandler.checkEvent();

            gp.keyH.enterPressed = false;


            //IF COLLISION FALSE, PLAYER CAN MOVE
            if(!collisionOn) {
                if(sprintActive){
                    speed=8;
                }else{
                    speed=4;
                }
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
        //CLAW ABILITY
        if(keyH.claw) {
            if (clawCD) {
                gp.ui.showMessage("Claw is on cooldown!");
            }else if(clawActive){
                gp.ui.showMessage("Your claws are sharp!");
            }else{
                Claw();
            }
            keyH.claw = false;
        }

        //SPRINT ABILITY
        if(keyH.sprint) {
            if (sprintCD) {
                gp.ui.showMessage("Sprint is on cooldown!");
            }else if(sprintActive){
                gp.ui.showMessage("You are bursting with energy!");
            }else{
                Sprint();
            }
            keyH.sprint = false;
        }
    }

    public void Claw(){
        clawActive = true;
        gp.ui.showMessage("You are now dangerous!");

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                clawActive = false;
                gp.ui.showMessage("You retract your claws.");
                clawCoolDown();
            }
        }, clawDuration);
    }

    public void clawCoolDown(){
        clawCD = true;

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                clawCD = false;
                gp.ui.showMessage("Claw is ready!");
            }
        }, clawCoolDown);
    }

    //SPRINT ABILITY
    public void Sprint(){
        sprintActive = true;
        gp.ui.showMessage("You are now agile!");

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                sprintActive = false;
                gp.ui.showMessage("You grow tired.");
                sprintCoolDown();
            }
        }, sprintDuration);
    }

    public void sprintCoolDown(){
        sprintCD = true;

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                sprintCD = false;
                gp.ui.showMessage("Sprint is ready!");
            }
        }, sprintCoolDown);
    }

    public void pickUp0bject(int i){
            if(i != 999) {
                String objectName = gp.obj/*[gp.currentMap]*/[i].name;
                /*[gp.currentMap]*/
                switch (objectName) {
                    case "Key":
                        gp.playSE(1);
                        haskey++;
                        gp.obj/*[gp.currentMap]*/[i] = null;
                        gp.ui.showMessage("You got a key!");
                        break;
                    case "door":

                        if (haskey > 0) {
                            gp.playSE(3);
                            gp.obj/*[gp.currentMap]*/[i] = null;
                            haskey--;
                            gp.ui.showMessage("You have opened the door!");
                        } else {
                            gp.ui.showMessage("You need a key dawg! :/");
                        }
                        break;
                    case "chest":
                        gp.obj/*[gp.currentMap]*/[i] = null;
                        gp.stopMusic();
                        gp.playSE(4);
                        gp.ui.showMessage("CONGRATULATIONS!!! :>");
                        gp.ui.gameFinished = true;
                        break;
                    case "boots":
                        gp.playSE(2);
                        speed += 1;
                        gp.obj/*[gp.currentMap]*/[i] = null;
                        gp.gameState=gp.dialogueState;
                        gp.ui.currentDialogue="You have become agile!";
                        break;
                }
            }

    }
    public void interactNPC(int i) {
        if(i != 999) {
            if(clawActive){
                gp.npc/*[gp.currentMap]*/[0]=null;
            }
            if(gp.keyH.enterPressed) {
                gp.gameState = gp.dialogueState;
                gp.npc/*[gp.currentMap]*/[i].speak();
            }
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
        a2.drawImage(image, screenX, screenY, null);
    }
}
