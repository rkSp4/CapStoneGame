package Main;

import Entity.NPC;
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
        gp.obj[0] = new OBJ_KEY(gp);
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1] = new OBJ_KEY(gp);
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;

        gp.obj[2] = new OBJ_DOOR(gp);
        gp.obj[2].worldX = 38 * gp.tileSize;
        gp.obj[2].worldY = 8 * gp.tileSize;

        gp.obj[3] = new OBJ_DOOR(gp);
        gp.obj[3].worldX = 10 * gp.tileSize;
        gp.obj[3].worldY = 11 * gp.tileSize;

        gp.obj[4] = new OBJ_DOOR(gp);
        gp.obj[4].worldX = 8 * gp.tileSize;
        gp.obj[4].worldY = 28 * gp.tileSize;

        gp.obj[5] = new OBJ_DOOR(gp);
        gp.obj[5].worldX = 12 * gp.tileSize;
        gp.obj[5].worldY = 22 * gp.tileSize;

        gp.obj[6] = new OBJ_CHEST(gp);
        gp.obj[6].worldX = 10 * gp.tileSize;
        gp.obj[6].worldY = 7 * gp.tileSize;

        gp.obj[7] = new OBJ_BOOTS(gp);
        gp.obj[7].worldX = 37 * gp.tileSize;
        gp.obj[7].worldY = 42 * gp.tileSize;

        gp.obj[8] = new OBJ_CLAWS();
        gp.obj[8].worldX = gp.tileSize*24;
        gp.obj[8].worldY = gp.tileSize*24;

        gp.obj[9] = new OBJ_SHADOW();
        gp.obj[9].worldX = gp.tileSize*25;
        gp.obj[9].worldY = gp.tileSize*23;

    }

    public void setNPC(){
        gp.npc[0] = new NPC(gp);
        gp.npc[0].worldX = gp.tileSize*21;
        gp.npc[0].worldY = gp.tileSize*21;
    }
}
