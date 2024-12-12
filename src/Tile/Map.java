package Tile;

import Main.GamePanel;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class Map {
    GamePanel gp;
    BufferedImage worldMapImage;
    BufferedImage catIcon;
    public Map(GamePanel gp) {
        super();
        this.gp = gp;
        createWorldMap();
    }

    private void createWorldMap() {
        int mapWidth = gp.maxWorldCol * gp.tileSize;
        int mapHeight = gp.maxWorldRow * gp.tileSize;

        worldMapImage = new BufferedImage(mapWidth, mapHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = worldMapImage.createGraphics();

        int col = 0;
        int row = 0;

        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            int tileNum = gp.tileM.mapTileNum[col][row];
            int x = col * gp.tileSize;
            int y = row * gp.tileSize;
            g2.drawImage(gp.tileM.tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
        g2.dispose();
    }

    public void draw(Graphics2D g2) {
        if (gp.mapToggled) {
            if (worldMapImage != null) {
                int screenWidth = gp.screenWidth;
                int screenHeight = gp.screenHeight;

                int drawWidth = screenWidth / 2;
                int drawHeight = screenHeight / 2;

                int fillRectWidth = drawWidth + 60;
                int fillRectHeight = drawHeight + 60;

                int centerX = (screenWidth - fillRectWidth) / 2;
                int centerY = (screenHeight - fillRectHeight) / 2;

                g2.setColor(Color.BLACK);
                g2.fillRect(centerX, centerY, fillRectWidth, fillRectHeight);

                g2.drawImage(worldMapImage, centerX + 20, centerY + 20, drawWidth + 20, drawHeight + 20, null);

                int playerMapX = (gp.player.worldX / gp.tileSize) * (drawWidth / gp.maxWorldCol);
                int playerMapY = (gp.player.worldY / gp.tileSize) * (drawHeight / gp.maxWorldRow);

                g2.setColor(Color.RED);
//                g2.fillOval(centerX + playerMapX - 5, centerY + playerMapY - 5, 10, 10);
                g2.drawImage(gp.player.Icon, centerX + playerMapX - 5, centerY + playerMapY - 5, 25, 25, null);
            }
        }
    }
}
