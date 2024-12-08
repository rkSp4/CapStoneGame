package Main;

import java.awt.*;
import java.awt.Rectangle;


public class EventHandler {
    GamePanel gp;
    EventRec[][] eventRect;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventRect = new EventRec/*[gp.maxMap]*/[gp.maxWorldCol][gp.maxWorldRow];
        //int map = 0;
        int col = 0;
        int row = 0;
        while (/*map < gp.maxMap &&*/col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect/*[map]*/[col][row] = new EventRec();
            eventRect/*[map]*/[col][row].x = 23;
            eventRect/*[map]*/[col][row].y = 23;
            eventRect/*[map]*/[col][row].width = 2;
            eventRect/*[map]*/[col][row].height = 2;
            eventRect/*[map]*/[col][row].eventRectDefaultX = eventRect/*[map]*/[col][row].x;
            eventRect/*[map]*/[col][row].eventRectDefaultY = eventRect/*[map]*/[col][row].y;

            col++;
            if(col == gp.maxWorldCol) {
                col = 0;
                row++;
                /*
                if(row == gp.maxWorldCol) {
                    col = 0;
                    row++;

                    if(row == gp.maxWorldRow) {
                        row = 0;
                        map++;
                    }
                }*/
            }
        }
    }
    // EVENTS
    public void checkEvent() {

        //Check player character is more than 1 tile away from the last event
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance > gp.tileSize) {
            canTouchEvent = true;
        }

        if(canTouchEvent) {
            //REMOVE THE COL AND ROW IN DAMAGPIT AND HEALINGPOOL
            if(hit(/*0*/27, 16, "right")) {
                damagePit(27, 26, gp.dialogueState);
            }
            //FOR TELEPORT
            //if(hit(27,16,"right") == true) {
            //    teleport(gp.dialogueState);
            // }
            else if(hit(/*0*/23, 12, "up")) {
                healingPool(23, 12, gp.dialogueState);
            }
            /*
            //Have to properly put where I put the "door/dungeon"
            else if (hit(0, 10, 39, "any") == true) {
                //NUMBERS HERE IS JUST AN EXAMPLE, I HAVE TO EDIT IT OUT LATURS
                teleport(1, 12, 13);
            }
            else if (hit(1, 12, 13, "any") == true) {
                //NUMBERS HERE IS JUST AN EXAMPLE, I HAVE TO EDIT IT OUT LATURS
                teleport((0, 10, 39,);
            }
             */
        }
    }

    //CHECK OBJECT COLLISION ON THE TILE LEL
    public boolean hit(/*int map, */int col, int row, String reqDirection) {
        boolean hit = false;
/*
        if(map == gp.currentMap) {
            //put the code below in here
        }*/
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect/*[map]*/[col][row].x = col*gp.tileSize + eventRect/*[map]*/[col][row].x;
        eventRect/*[map]*/[col][row].y = row*gp.tileSize + eventRect/*[map]*/[col][row].y;

        if(gp.player.solidArea.intersects(eventRect/*[map]*/[col][row]) && eventRect/*[map]*/[col][row].eventDone == false) {
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;

                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }

        gp.player.solidArea.x = gp.player.SolidAreaDefaultX;
        gp.player.solidArea.y = gp.player.SolidAreaDefaultY;
        eventRect/*[map]*/[col][row].x = eventRect/*[map]*/[col][row].eventRectDefaultX;
        eventRect/*[map]*/[col][row].y = eventRect/*[map]*/[col][row].eventRectDefaultY;

        //UMAYYY KAAYO
        //gp.currentMap = map;
        //gp.player.worldX = gp.tileSize * col;
        //gp.player.worldY = gp.tileSize * row;
        //previousEventX = gp.player.worldX;
        //canTouchEvent = false;
        //gp.playSE();

        return hit;
    }

    //TELEPORT TO ANY TILES ON THE MAP
   /* public void teleport(int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Teleport";
        gp.player.worldX = gp.tileSize*37;
        gp.player.worldY = gp.tileSize*10;
    }*/
    //TILE PIT THAT DAMAGE
    //REMOVE THE INT COL, INT ROW
    public void damagePit(int col, int row, int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "That's a pit mate";
        gp.player.life -= 1;
//eventRect[col][row].eventDone = true;
        canTouchEvent =false;

    }
    //CAT DRINKING WATERRR (DI NI BASTOS NANG LICK LICK LICK HA!)
    //REMOVE THE INT COL, INT ROW
    public void healingPool(int col, int row, int gameState) {
        if (gp.keyH.enterPressed) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "Lick lick lick";
            gp.player.life = gp.player.maxLife;
        }
    }
    /*
    public void teleport(int map, int col, int row) {

    }
     */
}
