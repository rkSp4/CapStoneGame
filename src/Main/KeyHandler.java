package Main;

import Entity.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener{

    GamePanel gp;

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, ctrlPressed, claw, sprint;
    boolean checkDrawTime = false;
    public int look = 0; //0-Down 1-Right 2-Up 3-Left

    public KeyHandler(GamePanel gp){
        this.gp=gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        //KILLSWITCH
        if(gp.devMode && code == KeyEvent.VK_DELETE){
            System.exit(0);
        }

        //TITLE STATE
        if(gp.gameState == gp.titleState ) {
            if(gp.ui.titleScreenState == 0) {
                if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 1;
                    }
                }

                if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 1) {
                        gp.ui.commandNum = 0;
                    }
                }

                if (code == KeyEvent.VK_ENTER) {
                    enterPressed = true;
                }
            }
            else if(gp.ui.titleScreenState == 1) {
                if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 1) {
                        gp.ui.commandNum = 0;
                    }
                }

                if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 1) {
                        gp.ui.commandNum = 0;
                    }
                }

                if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                    gp.ui.commandNum = 0;
                }

                if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    gp.ui.commandNum = 2;
                }

                if (code == KeyEvent.VK_ENTER) {
                    enterPressed = true;
                }
            }
        }
        //PLAY STATE
        if(gp.gameState == gp.playState){
            //DEV MODE
            if(code == KeyEvent.VK_CAPS_LOCK){
                if(!gp.devMode) {
                    System.out.println("Developer options are turned on!");
                    gp.devMode = true;
                    gp.player.speed += 4;
                }else{
                    System.out.println("Developer options are turned off!");
                    gp.devMode = false;
                    gp.player.speed -= 4;
                }
                //IMPLEMENT DEV OPTS LIKE NO COLLISIONS, INF HP

            }
            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                upPressed = true;
            }

            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                downPressed = true;
            }

            if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
                leftPressed = true;
            }

            if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
                rightPressed = true;
            }

            if(code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }

            //ABILITIES
            //MEOW
            if(code == KeyEvent.VK_SPACE){
                gp.playSE(5);
            }
            //CLAW
            if(code == KeyEvent.VK_C){
                claw = true;
                gp.playSE(1);
            }

            //SPRINT
            if(code == KeyEvent.VK_V){
                sprint = true;
                gp.playSE(2);
            }

            if(code == KeyEvent.VK_ESCAPE){
                gp.gameState = gp.optionState;
                gp.ui.commandNum=0;

            }
        }

        //Debug
        if(gp.devMode && code == KeyEvent.VK_T){
            if(!checkDrawTime) {
                checkDrawTime = true;
            } else if (checkDrawTime) {
                checkDrawTime = false;
            }
        }
        //PAUSE
        if(code == KeyEvent.VK_P){
            if (gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
                    gp.stopMusic();
                gp.ui.commandNum=0;
            }
        }

        //WAKO KAHIBAW ASA NI GIKAN NING PANG REFRESH SA MAP, UNTA KAHIBAW MO INGNA LANG KO

//        if(code == KeyEvent.VK_R) {
//            switch(gp.currentMap) {
//            case 0: gp.tileM.loadMap("/maps/worldV3.txt", 0); break;
//            case 0: gp.tileM.loadMap("/maps/interior01.txt", 1); break;

        //PAUSE STATE
        if (gp.gameState == gp.pauseState){

            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            }

            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }

            if (code == KeyEvent.VK_ENTER) {
                enterPressed=true;
            }
        }
        //OVERSTATE
        if(gp.gameState == gp.overState){
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 1;
                }
            }

            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 1) {
                    gp.ui.commandNum = 0;
                }
            }

            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }
        }
        //DIALOGUE STATE
        if(gp.gameState == gp.dialogueState) {
            if(code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
            }
        }

        //OPTIONS STATE
        if(gp.gameState == gp.optionState){

            if(code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }

            int maxCommandNum = 0;
            switch(gp.ui.substate){
                case 0: maxCommandNum = 3;
                    break;
                case 1:
                    break;
            }

            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = maxCommandNum;
                }
            }

            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > maxCommandNum) {
                    gp.ui.commandNum = 0;
                }
            }

            if(code == KeyEvent.VK_A){
                if(gp.ui.substate == 0){
                    if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0){
                        gp.music.volumeScale--;
                        gp.music.checkVolume();
                        //gp.playSE();
                    }
                    if(gp.ui.commandNum == 2 && gp.se.volumeScale > 0){
                        gp.se.volumeScale--;
                        //gp.playSE();
                    }
                }
            }
            if(code == KeyEvent.VK_D){
                if(gp.ui.substate == 0){
                    if(gp.ui.commandNum == 1 && gp.music.volumeScale < 5){
                        gp.music.volumeScale++;
                        gp.music.checkVolume();
                        //gp.playSE();
                    }
                    if(gp.ui.commandNum == 2 && gp.se.volumeScale < 5){
                        gp.se.volumeScale++;
                        //gp.playSE();
                    }
                }
            }

        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            upPressed = false;
        }

        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            downPressed = false;
        }

        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            leftPressed = false;
        }

        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }

        if(code == KeyEvent.VK_CONTROL){
            ctrlPressed = false;
        }
    }
}
