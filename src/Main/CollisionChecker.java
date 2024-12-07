package Main;

import Entity.Entity;

import java.util.Objects;

public class    CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }
    public void checkTile(Entity entity) {

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
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
                case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean player){
        int index = 999;

        for(int i = 0; i < Objects.requireNonNull(gp.obj).length; i++){
            if(gp.obj[i] != null)
            {
                //get entity solid area pos
                    entity.solidArea.x = entity.worldX + entity.solidArea.x;
                    entity.solidArea.y = entity.worldY + entity.solidArea.y;
                // get object solid area pos
                    gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                    gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                    switch(entity.direction)
                    {
                        case "up":
                            entity.solidArea.y -= entity.speed;
                            if(entity.solidArea.intersects(gp.obj[i].solidArea))
                            {
                                if(gp.obj[i].collision)
                                {
                                    entity.collisionOn = true;
                                }

                                if(player)
                                {
                                    index = i;
                                }
                            }
                            break;
                        case "down":
                            entity.solidArea.y += entity.speed;
                            if(entity.solidArea.intersects(gp.obj[i].solidArea))
                            {
                                if(gp.obj[i].collision)
                            {
                                entity.collisionOn = true;
                            }

                                if(player)
                                {
                                    index = i;
                                }
                            }
                            break;
                        case "right":
                            entity.solidArea.x += entity.speed;
                            if(entity.solidArea.intersects(gp.obj[i].solidArea))
                            {
                                if(gp.obj[i].collision)
                                {
                                    entity.collisionOn = true;
                                }

                                if(player)
                                {
                                    index = i;
                                }
                            }
                            break;
                        case "left":
                            entity.solidArea.x -= entity.speed;
                            if(entity.solidArea.intersects(gp.obj[i].solidArea))
                            {
                                if(gp.obj[i].collision)
                                {
                                    entity.collisionOn = true;
                                }

                                if(player)
                                {
                                    index = i;
                                }
                            }
                            break;
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

            if (gp.obj != null && i >= 0 && i < gp.obj.length && gp.obj[i] != null && gp.obj[i].solidArea != null) {
                gp.obj[i].solidArea.x = gp.obj[i].SolidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].SolidAreaDefaultY;
            }

        }

        return index;
    }
    //NPC COLLISION AND MONSTER COLLISION
    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;

        for(int i = 0; i < target.length; i++){
            if(target[i] != null)
            {
                //get entity solid area pos
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                // get object solid area pos
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch(entity.direction)
                {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea))
                        {
                                entity.collisionOn = true;
                                index = i;
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea))
                        {
                                entity.collisionOn = true;
                                index = i;
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea))
                        {
                               entity.collisionOn = true;
                                index = i;
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea))
                        {
                                entity.collisionOn = true;
                                index = i;
                        }
                        break;
                }
            }

            //NA FIX NA GYUD ANG NULL POINTER SAMOKKKKK
            entity.solidArea.x = entity.SolidAreaDefaultX;
            entity.solidArea.y = entity.SolidAreaDefaultY;
            if (target[i] != null) {
                target[i].solidArea.x = target[i].SolidAreaDefaultX;
            }
            if (target[i] != null) {
                target[i].solidArea.y = entity.SolidAreaDefaultY;
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
    public void checkPlayer(Entity entity) {
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
                }
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                if(entity.solidArea.intersects(gp.player.solidArea))
                {
                    entity.collisionOn = true;

                }
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                if(entity.solidArea.intersects(gp.player.solidArea))
                {
                    entity.collisionOn = true;

                }
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                if(entity.solidArea.intersects(gp.player.solidArea))
                {
                    entity.collisionOn = true;
                }
                break;
        }
        entity.solidArea.x = entity.SolidAreaDefaultX;
        entity.solidArea.y = entity.SolidAreaDefaultY;
        gp.player.solidArea.x = gp.player.SolidAreaDefaultX;
        gp.player.solidArea.y = gp.player.SolidAreaDefaultY;
    }
}

