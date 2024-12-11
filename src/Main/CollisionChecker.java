package Main;

import Entity.Entity;

import java.util.Objects;

public class    CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }
    public void checkTile(Entity entity) {

        //DEVELOPER MODE
        if(gp.devMode) {return;}
        //DEVELOPER MODE

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;


        switch(entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum/*[gp.currentMap]*/[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum/*[gp.currentMap]*/[entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum/*[gp.currentMap]*/[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum/*[gp.currentMap]*/[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
                case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum/*[gp.currentMap]*/[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum/*[gp.currentMap]*/[entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum/*[gp.currentMap]*/[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum/*[gp.currentMap]*/[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
    public int checkObject(Entity entity, boolean player){
        int index = 999;

        for(int i = 0; i < Objects.requireNonNull(gp.obj)/*[i]*/.length; i++){
            if(gp.obj/*[currentMap]*/[i] != null)
            {
                //get entity solid area pos
                    entity.solidArea.x = entity.worldX + entity.solidArea.x;
                    entity.solidArea.y = entity.worldY + entity.solidArea.y;
                // get object solid area pos
                    gp.obj/*[currentMap]*/[i].solidArea.x = gp.obj/*[currentMap]*/[i].worldX + gp.obj/*[currentMap]*/[i].solidArea.x;
                    gp.obj/*[currentMap]*/[i].solidArea.y = gp.obj/*[currentMap]*/[i].worldY + gp.obj/*[currentMap]*/[i].solidArea.y;

                    switch(entity.direction) {
                        case "up": entity.solidArea.y -= entity.speed;break;
                        case "down": entity.solidArea.y += entity.speed;break;
                        case "right": entity.solidArea.x += entity.speed;break;
                        case "left": entity.solidArea.x -= entity.speed;break;
                    }
                if(entity.solidArea.intersects(gp.obj/*[currentMap]*/[i].solidArea)) {
                    entity.collisionOn = true;
                    index = i;
                }
            }

            //AYAW NI HILABTA SIGEG NULL POINTER ;-;
//            entity.solidArea.x = entity.SolidAreaDefaultX;
//            entity.solidArea.y = entity.SolidAreaDefaultY;
//            gp.obj[i].solidArea.x = gp.obj[i].SolidAreaDefaultX;
//            gp.obj[i].solidArea.y = gp.obj[i].SolidAreaDefaultY;

            if (entity != null && entity.solidArea != null) {
                entity.solidArea.x = entity.SolidAreaDefaultX;
                entity.solidArea.y = entity.SolidAreaDefaultY;
            }

            if (gp.obj != null && i >= 0 && i < gp.obj.length && gp.obj/*[currentMap]*/[i] != null && gp.obj/*[currentMap]*/[i].solidArea != null) {
                gp.obj/*[currentMap]*/[i].solidArea.x = gp.obj/*[currentMap]*/[i].SolidAreaDefaultX;
                gp.obj/*[currentMap]*/[i].solidArea.y = gp.obj/*[currentMap]*/[i].SolidAreaDefaultY;
            }

        }

        return index;
    }
    //NPC COLLISION AND MONSTER COLLISION
    public int checkEntity(Entity entity, Entity[]/*[]*/ target) {
        int index = 999;

        for(int i = 0; i < target/*[i]*/.length; i++){
            if(target/*[gp.currentMap]*/[i] != null)
            {
                //get entity solid area pos
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                // get object solid area pos
                target/*[gp.currentMap]*/[i].solidArea.x = target/*[gp.currentMap]*/[i].worldX + target/*[gp.currentMap]*/[i].solidArea.x;
                target/*[gp.currentMap]*/[i].solidArea.y = target/*[gp.currentMap]*/[i].worldY + target/*[gp.currentMap]*/[i].solidArea.y;

                switch(entity.direction)
                {
                    case "up": entity.solidArea.y -= entity.speed;break;
                    case "down": entity.solidArea.y += entity.speed;break;
                    case "right": entity.solidArea.x += entity.speed;break;
                    case "left": entity.solidArea.x -= entity.speed;break;
                }
                if(entity.solidArea.intersects(target[i].solidArea)) {
                    if(target[i] != entity) {
                        entity.collisionOn = true;
                        index = i;
                    }
                }
            }

            //NA FIX NA GYUD ANG NULL POINTER SAMOKKKKK
            entity.solidArea.x = entity.SolidAreaDefaultX;
            entity.solidArea.y = entity.SolidAreaDefaultY;
            if (target/*[gp.currentMap]*/[i] != null) {
                target/*[gp.currentMap]*/[i].solidArea.x = target/*[gp.currentMap]*/[i].SolidAreaDefaultX;
            }
            if (target/*[gp.currentMap]*/[i] != null) {
                target/*[gp.currentMap]*/[i].solidArea.y = entity.SolidAreaDefaultY;
            }
            /*if (entity != null && entity.solidArea != null) {
                entity.solidArea.x = entity.SolidAreaDefaultX;
                entity.solidArea.y = entity.SolidAreaDefaultY;
            }
            if (target != null && i >= 0 && i < target.length && target[i] != null && target.!= null) {
                target[i].solidArea.x = target[i].SolidAreaDefaultX;
                target[i].solidArea.y = target[i].SolidAreaDefaultY;
            }

             */
        }


        return index;
    }
    public boolean checkPlayer(Entity entity) {

        boolean contactPlayer = false;

        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
        // get object solid area pos
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch(entity.direction)
        {
            case "up":
                entity.solidArea.y -= entity.speed;
                if(entity.solidArea.intersects(gp.player.solidArea))
                {
                    entity.collisionOn = true;
                    if(!gp.player.clawActive) {
                        gp.gameState = gp.overState;
                    }
                }
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                if(entity.solidArea.intersects(gp.player.solidArea))
                {
                    entity.collisionOn = true;
                    if(!gp.player.clawActive) {
                        gp.gameState = gp.overState;
                    }
                }
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                if(entity.solidArea.intersects(gp.player.solidArea))
                {
                    entity.collisionOn = true;
                    if(!gp.player.clawActive) {
                        gp.gameState = gp.overState;
                    }
                }
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                if(entity.solidArea.intersects(gp.player.solidArea))
                {
                    entity.collisionOn = true;
                    if(!gp.player.clawActive) {
                        gp.gameState = gp.overState;
                    }
                }
                break;
        }
        if(entity.solidArea.intersects(gp.player.solidArea)) {
            entity.collisionOn = true;
            contactPlayer = true;
            //gp.gameState= gp.overState;
        }

        entity.solidArea.x = entity.SolidAreaDefaultX;
        entity.solidArea.y = entity.SolidAreaDefaultY;
        gp.player.solidArea.x = gp.player.SolidAreaDefaultX;
        gp.player.solidArea.y = gp.player.SolidAreaDefaultY;

        return contactPlayer;
    }

}

