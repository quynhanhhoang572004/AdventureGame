package tile;

import java.awt.Color;
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
import entity.Entity;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];
    boolean drawPath = true;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[50];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("maps/worldV2.txt", 0);
        						 // 0: map number (order of the map)
        loadMap("maps/interior01.txt", 1);
        						 // this map is for the test case for the code as mentioned (this map is from the author)
    }

    public void getTileImage(){
        // Change your willing tile file here
        // Scale the image to improve rendering performance
        setup(0, "ice", false);
        setup(1, "path", false);
        setup(2, "water01", true);
        setup(3, "wall", true);
        setup(4, "pinetree_snowpath", true);
        setup(5, "rock1", true);
        
        // TEST CASE (I USE AUTHOR'S TILE FOR THIS, WILL CHANGE IN THE FUTURE FOR OUR MAP)
        // SAMPLE TEST CASE MAP: interior01.txt
        setup(6, "033", false);		// Hut.png (033.png)
        setup(7, "034", false);		// Floor01.png (034.png)
        setup(8, "035", true);		// Table01.png (035.png)
        
    }

    public void setup(int index, String imagePath, boolean collision) {
        UtilityTool uTool = new UtilityTool();  
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(new File("res/tile/" + imagePath + ".png"));
            BufferedImage scaledImage = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].setImage(scaledImage);
            tile[index].collision = collision;
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadMap(String filePath, int map){
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
                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

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
        if (drawPath == true) {
        	g2.setColor(new Color(255, 0, 0, 70));
        	for (int i = 0; i < gp.pFinder.pathList.size(); i++) {
                int worldX = gp.pFinder.pathList.get(i).col * gp.tileSize;
                int worldY = gp.pFinder.pathList.get(i).row * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;
                g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
        	}
        }
        
        // If the entity is not the NPC, then we will not draw the fillRect line
//        Entity entity = new Entity(gp);
//        int type = 0;
//        if (type == 1) {
//            if (drawPath) {
//                g2.setColor(new Color(255, 0, 0, 70));
//                for (int i = 0; i < gp.pFinder.pathList.size(); i++) {
//                    int worldX = gp.pFinder.pathList.get(i).col * gp.tileSize;
//                    int worldY = gp.pFinder.pathList.get(i).row * gp.tileSize;
//                    int screenX = worldX - gp.player.worldX + gp.player.screenX;
//                    int screenY = worldY - gp.player.worldY + gp.player.screenY;
//                    g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
//                }
//            }
//        }
    }    
}
