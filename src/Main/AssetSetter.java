package Main;

import Entity.NPC;
import Entity.NPC_Boy;
import Object.OBJ_KEY;
import Object.OBJ_DOOR;
import Object.OBJ_BOOTS;
import Object.OBJ_CHEST;
import Object.OBJ_SHADOW;
import Object.OBJ_CLAWS;


public class AssetSetter {

    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;

    }
    public void setObject() {
        //int mapNum = 0;
        //int i = 0;
        /*[mapNum]*/
        gp.obj/*[mapNum]*/[0] = new OBJ_KEY(gp);
        gp.obj/*[mapNum]*/[0].worldX = 23 * gp.tileSize;
        gp.obj/*[mapNum]*/[0].worldY = 7 * gp.tileSize;

        gp.obj/*[mapNum]*/[1] = new OBJ_KEY(gp);
        gp.obj/*[mapNum]*/[1].worldX = 23 * gp.tileSize;
        gp.obj/*[mapNum]*/[1].worldY = 40 * gp.tileSize;

        gp.obj/*[mapNum]*/[2] = new OBJ_DOOR(gp);
        gp.obj/*[mapNum]*/[2].worldX = 38 * gp.tileSize;
        gp.obj[/*[mapNum]*/2].worldY = 8 * gp.tileSize;

        gp.obj/*[mapNum]*/[3] = new OBJ_DOOR(gp);
        gp.obj/*[mapNum]*/[3].worldX = 10 * gp.tileSize;
        gp.obj/*[mapNum]*/[3].worldY = 11 * gp.tileSize;

        gp.obj/*[mapNum]*/[4] = new OBJ_DOOR(gp);
        gp.obj/*[mapNum]*/[4].worldX = 8 * gp.tileSize;
        gp.obj/*[mapNum]*/[4].worldY = 28 * gp.tileSize;

        gp.obj/*[mapNum]*/[5] = new OBJ_DOOR(gp);
        gp.obj/*[mapNum]*/[5].worldX = 12 * gp.tileSize;
        gp.obj/*[mapNum]*/[5].worldY = 22 * gp.tileSize;

        gp.obj/*[mapNum]*/[6] = new OBJ_CHEST(gp);
        gp.obj/*[mapNum]*/[6].worldX = 10 * gp.tileSize;
        gp.obj/*[mapNum]*/[6].worldY = 7 * gp.tileSize;

        gp.obj/*[mapNum]*/[7] = new OBJ_BOOTS(gp);
        gp.obj/*[mapNum]*/[7].worldX = 37 * gp.tileSize;
        gp.obj/*[mapNum]*/[7].worldY = 42 * gp.tileSize;

        gp.obj/*[mapNum]*/[8] = new OBJ_CLAWS();
        gp.obj/*[mapNum]*/[8].worldX = gp.tileSize*24;
        gp.obj/*[mapNum]*/[8].worldY = gp.tileSize*24;

        gp.obj/*[mapNum]*/[9] = new OBJ_SHADOW();
        gp.obj/*[mapNum]*/[9].worldX = gp.tileSize*25;
        gp.obj/*[mapNum]*/[9].worldY = gp.tileSize*23;

    }

    public void setNPC(){
        //int mapNum = 0;
        //int i = 0;
        gp.npc/*[mapNum]*/[0] = new NPC_Boy(gp);
        gp.npc/*[mapNum]*/[0].worldX = gp.tileSize*21;
        gp.npc/*[mapNum]*/[0].worldY = gp.tileSize*21;
    }
}
