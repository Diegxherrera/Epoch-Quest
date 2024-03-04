package gui.tile;

import gui.main.GamePanel;
import gui.main.UtilityTool;

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
    public int[][] mapTileNum;

    public TileManager(GamePanel gp){
        this.gp=gp;

        tile = new Tile[100];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/worldV4.txt");
    }

    public void getTileImage(){
        //No borrar los primeros diez, son para que después al dibujar en el txt sea más fácil el posicionamiento y el
        // programa no se equivoque entre numeros de 1 cifra y de 2 cifras
        //PlaceHolder
        setUp(0,"grass00", false);
        setUp(1,"grass00", false);
        setUp(2,"grass00", false);
        setUp(3,"grass00", false);
        setUp(4,"grass00", false);
        setUp(5,"grass00", false);
        setUp(6,"grass00", false);
        setUp(7,"grass00", false);
        setUp(8,"grass00", false);
        setUp(9,"grass00", false);
        //Tiles
        setUp(10,"floor01",false);
        setUp(14,"grass00", false);
        setUp(11,"grass01", false);
        setUp(45,"water00", true);
        setUp(13,"water01", true);
        setUp(47,"water02", true);
        setUp(48,"water03", true);
        setUp(49,"water04", true);
        setUp(50,"water05", true);
        setUp(51,"water06", true);
        setUp(65,"water07", true);
        setUp(20,"water08", true);
        setUp(21,"water09", true);
        setUp(22,"water10", true);
        setUp(23,"water11", true);
        setUp(24,"water12", true);
        setUp(25,"water13", true);
        setUp(26,"road00", false);
        setUp(27,"road01", false);
        setUp(28,"road02", false);
        setUp(29,"road03", false);
        setUp(30,"road04", false);
        setUp(31,"road05", false);
        setUp(32,"road06", false);
        setUp(33,"road07", false);
        setUp(34,"road08", false);
        setUp(35,"road09", false);
        setUp(36,"road10", false);
        setUp(37,"road11", false);
        setUp(38,"road12", false);
        setUp(12,"earth", false);
        setUp(44,"wall", true);
        setUp(43,"tree", true);
        setUp(60,"fountain_v1_1_0", true);
        setUp(61,"fountain_v1_1_1", true);
        setUp(62,"fountain_v1_1_7", true);
        setUp(63,"fountain_v1_1_8", true);
        setUp(19,"House_Hay_Stone_1", true);
        setUp(20,"House_Hay_Stone_2", true);
        setUp(21,"House_Hay_Stone_3", true);
        setUp(22,"House_Hay_Stone_4", true);
        setUp(23,"hut", true);

    }

    public void setUp(int index, String imageName, boolean collision){
        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + imageName + ".png")));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while (col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();

                while (col < gp.maxWorldCol){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }

                if (col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize< gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize>gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize< gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }
            worldCol++;

            if (worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
