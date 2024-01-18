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
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;
import entity.Entity;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];
    boolean drawPath = true;
    ArrayList<String> fileNames = new ArrayList<>();
    ArrayList<String> collisionStatus = new ArrayList<>();
    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[100];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("maps/worldV1.txt", 0);
        						 // 0: map number (order of the map)
        loadMap("maps/worldV2.txt", 1);

        loadMap("maps/interior01.txt", 2);
        					
    }

    public void getTileImage(){
        // Change your willing tile file here
        // Scale the image to improve rendering performance
        setup(0, "pinetree", true);
        setup(1, "ice_tilemap", false);
        setup(2, "pinetree", true);
        setup(3, "rock", true);
        setup(4, "wall", true);
        setup(5, "path", false);
        setup(6, "path_two", false);
        setup(7, "flower", true);
        setup(8, "flowertwo", true);
        setup(9, "tree_one", true);
        setup(10, "tree_two", true);
        setup(11, "tree_three", true);
        setup(12, "tree_four", true);
        setup(13, "tree_five", true);
        setup(14, "tree_six", true);
        setup(15, "tree_seven", true);
        setup(16, "tree_eight", true);
        setup(17, "tree_nine", true);
        setup(18, "tree_ten", true);
        setup(19, "tree_eleven", true);
        setup(20, "tree_twelve", true);
        setup(21, "tree_thirdteen", true);
        setup(22, "tree_fourteen", true);
        setup(23, "tree_fifteen", true);
        setup(24, "tree_sixteen", true);
        setup(25, "tree_seventeen", true);
        setup(26, "tree_eighteen", true);
        setup(27, "tree_nineteen", true);
        setup(28, "tree-twenty", true);
        setup(29, "tree_twentyone", true);
        setup(30, "tree_twentytwo", true);
        setup(31, "tree_twentythree", true);
        setup(32, "tree_twentyfour", true);
        setup(33, "tree_twentyfive", true);
        setup(34, "tree_twentysix", true);
        setup(35, "tree_twentyseven", true);
        setup(36, "tree_twentyeight", true);
        setup(37, "tree_twentynine", true);
        setup(38, "tree_thirdty", true);
        setup(39, "tree_thirdtyone", true);
        setup(40, "tree_thirdtytwo", true);
        setup(41, "tree_thirdtythree", true);
        setup(42, "tree_thirdtyfour", true);
        setup(43, "sign", true);
        setup(44, "sleigh", true);
        setup(45, "ice", true);
        setup(46, "warning_sign", true);
        setup(47, "red_wall", true);
        setup(48, "penguin", true);
        setup(49, "49", true);
        setup(50, "50", true);
        setup(51, "51", true);
        setup(52, "52", true);
        setup(53, "53", true);
        setup(54, "54", true);
        setup(55, "55", false);
        setup(56, "56", true);
        setup(57, "57", true);
        setup(58, "58", true);
        setup(59, "59", true);
        setup(60, "60", true);
        setup(61, "61", true);
        setup(62, "62", true);
        setup(63, "63", true);
        setup(64, "64", true);
        setup(65, "65", true);
        setup(66, "66", true);
        setup(67, "67", true);
        setup(68, "68", true);
        setup(69, "69", true);
        setup(70, "70", true);
        setup(71, "71", true);
        setup(72, "72", true);
        setup(73, "73", true);
        setup(74, "74", true);
        setup(75, "75", true);
        setup(76, "76", true);
        setup(77, "77", true);
        setup(78, "78", true);
        setup(79, "79", true);
        setup(80, "80", true);
        setup(81, "81", true);
        setup(82, "82", true);
        setup(83, "83", true);
        setup(84, "84", true);
        setup(85, "85", true);
        setup(86, "86", true);
        setup(87, "teleport", false);







        // Table01.png (035.png)


        
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
            if(tile != null && tileNum>=0 && tileNum<tile.length && tile[tileNum] != null){

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
            }          
            	worldCol++;
        
            if(worldCol == gp.maxWorldCol){
                worldCol = 0;                
                worldRow++;                
            }

        // if (drawPath == true) {
        // 	g2.setColor(new Color(255, 0, 0, 70));
        // 	for (int i = 0; i < gp.pFinder.pathList.size(); i++) {
        //         int worldX = gp.pFinder.pathList.get(i).col * gp.tileSize;
        //         int worldY = gp.pFinder.pathList.get(i).row * gp.tileSize;
        //         int screenX = worldX - gp.player.worldX + gp.player.screenX;
        //         int screenY = worldY - gp.player.worldY + gp.player.screenY;
        //         g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
        // 	}
        // }
        
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
}
