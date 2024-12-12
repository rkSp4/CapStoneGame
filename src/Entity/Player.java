package Entity;

import Main.GamePanel;
import Main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class Player extends Entity{


    public BufferedImage title;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int haskey = 0;
    public boolean hasKilled = false;
    //CLAW
    public boolean clawActive = false;
    public boolean clawCD;
    public final int clawDuration = 3000;
    public int clawCoolDown = 10000;
    public int dashDistance = 1;
    public int targetX, targetY;
    public int dashSpeed = 24;
    public boolean isDashing = false;

    //SPRINT
    public boolean sprintActive = false;
    public boolean sprintCD;
    public final int sprintDuration = 5000;
    public int sprintCoolDown = 10000;

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
        getPlayerImage(gp.keyH.choice);
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

        public void getPlayerImage(boolean choice) {
            tab = setup("/player/tab");
            white = setup("/player/white");
            Icon = setup("/player/cathead");
            title = setup("/player/pawpaw");

            if(choice){
                getCat1();
            }else{
                getCat2();
            }
        }

    public void getCat1(){
        up1 = setup("/player/cat1_up_1");
        up2 = setup("/player/cat1_up_2");
        down1 = setup("/player/cat1_down_1");
        down2 = setup("/player/cat1_down_2");
        left1 = setup("/player/cat1_left_1");
        left2 = setup("/player/cat1_left_2");
        right1 = setup("/player/cat1_right_1");
        right2 = setup("/player/cat1_right_2");
    }

    public void getCat2(){
        up1 = setup("/player/cat_up_1");
        up2 = setup("/player/cat_up_2");
        down1 = setup("/player/cat_down_1");
        down2 = setup("/player/cat_down_2");
        left1 = setup("/player/cat_left_1");
        left2 = setup("/player/cat_left_2");
        right1 = setup("/player/cat_right_1");
        right2 = setup("/player/cat_right_2");
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

            //CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);
            //CHECK EVENT
            gp.eHandler.checkEvent();

            gp.keyH.enterPressed = false;


            //IF COLLISION FALSE, PLAYER CAN MOVE
            if(!collisionOn) {
                if(!gp.devMode) {
                    if (sprintActive) {
                        speed = 8;
                    } else {
                        speed = 4;
                    }
                }
                switch (direction) {
                    case "up":
                        worldY -= speed;
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

        if(isDashing) {
            if (worldX < targetX) {
                worldX = Math.min(worldX + dashSpeed, targetX);
            } else if (worldX > targetX) {
                worldX = Math.max(worldX - dashSpeed, targetX);
            }

            if (worldY < targetY) {
                worldY = Math.min(worldY + dashSpeed, targetY);
            } else if (worldY > targetY) {
                worldY = Math.max(worldY - dashSpeed, targetY);
            }

            // Check if the entity has reached the target
            if (worldX == targetX && worldY == targetY) {
                isDashing = false;
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

        //LIFE
        if(life<=0){
            gp.gameState = gp.overState;
        }
    }

    public void Claw(){
        if(isDashing){
            return;
        }
        if(invincible) {
            invincibleCounter++;
            if(invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        clawActive = true;
        isDashing = true;
        gp.ui.showMessage("You attack!");
        targetX = worldX;
        targetY = worldY;

        switch(direction){
            case "up":
                targetY -= dashDistance * gp.tileSize;
                break;
            case "down":
                targetY += dashDistance * gp.tileSize;
                break;
            case "left":
                targetX -= dashDistance * gp.tileSize;
                break;
            case "right":
                targetX += dashDistance * gp.tileSize;
                break;
        }

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
        if(gp.devMode){
            clawCoolDown = 0;
        }else if(hasKilled){
            clawCoolDown = 5000;
            hasKilled = false;
        }else{
            clawCoolDown = 10000;
        }
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
        if(gp.devMode){
            sprintCoolDown = 0;
        }else{
            sprintCoolDown = 10000;
        }
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
                    case "Chest":
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
                hasKilled = true;
            }
            if(gp.keyH.enterPressed) {
                gp.gameState = gp.dialogueState;
                gp.npc/*[gp.currentMap]*/[i].speak();
            }
        }
    }
    public void contactMonster(int i) {
        if(i != 999) {
            if(clawActive){
                gp.monster/*[gp.currentMap]*/[i]=null;
                hasKilled = true;
            }
            if(!invincible) {
                life -= 1;
                invincible = true;
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
        //PARA NI NIG MA DAMAGE ANG PLAYER, MU TRANSPARENT PERO DI SYA MU GANA KAY MURAG WAY setComposite sa
        //Intellij(?)

        //if(invincible == true) {
        //    a2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
      //  }
      //  a2.drawImage(image, screenX, screenY, null);
        //restart alpha
     //  a2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        //debug
//        a2.setFont(new Font("Arial",Font.PLAIN, 26));
//        a2.setColor(Color.white);
//        a2.drawString("Invincible:"+invincibleCounter, 10, 400);
    }
}
