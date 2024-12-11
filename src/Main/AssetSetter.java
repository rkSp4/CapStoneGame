package Main;

import Entity.NPC_Boy;
//import Entity.NPC_Red;
//import monster.MON_GreenSlime;
//import monster.MON_Rat;
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

        gp.obj/*[mapNum]*/[2] = new OBJ_KEY(gp);
        gp.obj/*[mapNum]*/[2].worldX = 33 * gp.tileSize;
        gp.obj[/*[mapNum]*/2].worldY = 7 * gp.tileSize;

        gp.obj/*[mapNum]*/[3] = new OBJ_DOOR(gp);
        gp.obj/*[mapNum]*/[3].worldX = 10 * gp.tileSize;
        gp.obj/*[mapNum]*/[3].worldY = 12 * gp.tileSize;

        gp.obj/*[mapNum]*/[4] = new OBJ_DOOR(gp);
        gp.obj/*[mapNum]*/[4].worldX = 8 * gp.tileSize;
        gp.obj/*[mapNum]*/[4].worldY = 28 * gp.tileSize;

        gp.obj/*[mapNum]*/[5] = new OBJ_DOOR(gp);
        gp.obj/*[mapNum]*/[5].worldX = 14 * gp.tileSize;
        gp.obj/*[mapNum]*/[5].worldY = 28 * gp.tileSize;

        gp.obj/*[mapNum]*/[6] = new OBJ_CHEST(gp);
        gp.obj/*[mapNum]*/[6].worldX = 10 * gp.tileSize;
        gp.obj/*[mapNum]*/[6].worldY = 8 * gp.tileSize;

        gp.obj/*[mapNum]*/[7] = new OBJ_BOOTS(gp);
        gp.obj/*[mapNum]*/[7].worldX = 37 * gp.tileSize;
        gp.obj/*[mapNum]*/[7].worldY = 42 * gp.tileSize;


    }

    public void setNPC() {
        //int mapNum = 0;
        //int i = 0;
        gp.npc/*[mapNum]*/[0] = new NPC_Boy(gp);
        gp.npc/*[mapNum]*/[0].worldX = gp.tileSize * 21;
        gp.npc/*[mapNum]*/[0].worldY = gp.tileSize * 21;

        //  gp.npc/*[mapNum]*/[1] = new NPC_Red(gp);
        //  gp.npc/*[mapNum]*/[1].worldX = gp.tileSize*25;
        //  gp.npc/*[mapNum]*/[1].worldY = gp.tileSize*69;
    }
}
    /*
    public void setMonster() {

        gp.monster[0] = new MON_GreenSlime(gp);
        gp.monster[0].worldX = gp.tileSize*23;
        gp.monster[0].worldY = gp.tileSize*36;

        gp.monster[1] = new MON_GreenSlime(gp);
        gp.monster[1].worldX = gp.tileSize*23;
        gp.monster[1].worldY = gp.tileSize*37;

        gp.monster[2] = new MON_Rat(gp);
        gp.monster[2].worldX = gp.tileSize*23;
        gp.monster[2].worldY = gp.tileSize*61;

        gp.monster[3] = new MON_Rat(gp);
        gp.monster[3].worldX = gp.tileSize*25;
        gp.monster[3].worldY = gp.tileSize*62;

        gp.monster[4] = new MON_Rat(gp);
        gp.monster[4].worldX = gp.tileSize*13;
        gp.monster[4].worldY = gp.tileSize*85;

    }
}
*/