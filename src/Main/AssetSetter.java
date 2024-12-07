package Main;

import Entity.NPC_Boy;
import Object.OBJ_KEY;
import Object.OBJ_DOOR;
import Object.OBJ_BOOTS;
import Object.OBJ_CHEST;

public class AssetSetter{

    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;

    }
    public void setObject() {

    }
    public void setNPC() {
        gp.npc[0] = new NPC_Boy(gp);
        gp.npc[0].worldX = gp.tileSize*21;
        gp.npc[0].worldY = gp.tileSize*21;
    }
}
