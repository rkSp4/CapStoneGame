package Tile;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][]/*/[]/*/ mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[110];
        mapTileNum = new int/*[gp.maxMap]*/[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/worldV8.txt"/*, 0*/);
        //loadMap("maps/interior01.txt", 1);

    }
    public void getTileImage() {

        //PLACEHOLDER//

        setup(0, "cliff0", true);
        setup(1, "cliff1", true);
        setup(2, "cliff2", true);
        setup(3, "cliff3", true);
        setup(4, "cliff4", true);
        setup(5, "cliff5", true);
        setup(6, "cliff6", false);
        setup(7, "cliff7", false);
        setup(8, "cliff8", false);
        setup(9, "watery9", false);

        //PLACEHOLDER//
        setup(10, "grass00", false);
        setup(11, "grass01", false);
        setup(12, "water00", true);
        setup(13, "water01", true);
        setup(14, "water02", true);
        setup(15, "water03", true);
        setup(16, "water04", true);
        setup(17, "water05", true);
        setup(18, "water06", true);
        setup(19, "water07", true);
        setup(20, "water08", true);
        setup(21, "water09", true);
        setup(22, "water10", true);
        setup(23, "water11", true);
        setup(24, "water12", true);
        setup(25, "water13", true);
        setup(26, "road00", false);
        setup(27, "road01", false);
        setup(28, "road02", false);
        setup(29, "road03", false);
        setup(30, "road04", false);
        setup(31, "road05", false);
        setup(32, "road06", false);
        setup(33, "road07", false);
        setup(34, "road08", false);
        setup(35, "road09", false);
        setup(36, "road10", false);
        setup(37, "road11", false);
        setup(38, "road12", false);
        setup(39, "earth", false);
        setup(40, "wall", true);
        setup(41, "tree", true);
        setup(42, "bridge01", false);
        setup(43, "bridge02", false);
        setup(44, "bridge", false);
        //dessert
        setup(45, "cacti43", true);
        setup(46, "cliff77", true);
        setup(47, "cliff78", true);
        setup(48, "cliff79", true);
        setup(49, "cliff80", true);
        setup(50, "cliff81", true);
        setup(51, "cliff82", true);
        setup(52, "cliff83", true);
        setup(53, "cliff84", true);
        setup(54, "cliff85", true);
        setup(55, "cliff86", true);
        setup(56, "cliff87", true);
        setup(57, "cliff88", true);
        setup(58, "rock42", true);
        setup(59, "sandy48", false);
        setup(60, "sandy49", false);
        setup(61, "sandy50", false);
        setup(62, "sandy51", false);
        setup(63, "sandy52", false);
        setup(64, "sandy53", false);
        setup(65, "sandy54", false);
        setup(66, "sandy55", false);
        setup(67, "sandy56", false);
        setup(68, "sandy57", false);
        setup(69, "sandy58", false);
        setup(70, "sandy59", false);
        setup(71, "sandy60", false);
        setup(72, "sandy61", false);
        setup(73, "sandy62", false);
        setup(74, "tree44", true);
        setup(75, "tree45", true);
        setup(76, "tree46", true);
        setup(77, "wall47", true);
        setup(78, "watery63", true);
        setup(79, "watery64", true);
        setup(80, "watery65", true);
        setup(81, "watery66", true);
        setup(82, "watery67", true);
        setup(83, "watery68", true);
        setup(84, "watery69", true);
        setup(85, "watery70", true);
        setup(86, "watery71", true);
        setup(87, "watery72", true);
        setup(88, "watery73", true);
        setup(89, "watery74", true);
        setup(90, "watery75", true);
        setup(91, "watery76", true);
        setup(92, "bridge00", false);
        setup(93, "cliff93", true);
        setup(94, "cliff94", true);
        setup(95, "cliff95", true);
        setup(96, "cliff96", true);
        setup(97, "cliff97", true);

    }

    public void setup(int index, String imageName, boolean collision) {

        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + imageName + ".png")));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath/* , int map */) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while(col < gp.maxWorldCol) {
                    String[] numbers= line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    //kanang map is for transitioning of maps
                    mapTileNum/*[map]*/[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum/*[gp.currentMap]*/[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(     worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
            {
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
