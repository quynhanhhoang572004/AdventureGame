package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("maps/world01.txt");//text to make map
    }

    public void getTileImage(){
        // Change your willing tile file here
        // Scale the image to improve rendering performance
        setup(0, "ice", false);
        setup(1, "path", false);
        setup(2, "water", true);
        setup(3, "wall", true);
        setup(4, "pinetree_snowpath", true);
        setup(7, "new_bottomleft", true);
        setup(8, "new_bottomright", true);
        setup(5, "new_topleft", true);
        setup(6, "new_topright", true);
        setup(7, "rock1", true);
    }

    public void setup(int index, String imagePath, boolean collision) {
        UtilityTool uTool = new UtilityTool();
    
        try {
            tile[index] = new Tile();
            //nhớ sửa lại cho đúng form của team
            tile[index].image = ImageIO.read(new File("res/tile/" + imagePath + ".png"));
            BufferedImage scaledImage = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].setImage(scaledImage);
            tile[index].collision = collision;
        } catch(IOException e) {
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
