package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("maps/world01.txt"); // Text to generate the map
    }

    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File("res/tile/ice.png")); // Change your willing tile file here

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("res/tile/path.png")); 

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("res/tile/water1.png"));
            tile[2].collision = true; // This line is to prevent the player from going through the object
                                      // Remember if the tile YOU want to be collided, move this code to the that tile, AND REMEMBER TO CHANGE the number in tile[...]
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new File("res/tile/wall.png"));
            tile[3].collision = true; 

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){
        try{
            InputStream is = new FileInputStream(new File(filePath));
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();
                String  numbers[] = line.split(" ");
                while(col < gp.maxWorldCol){
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch(Exception e){
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

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

                    g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }
            
            worldCol++;
        
            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                
                worldRow++;
                
            }
        }
    }
}