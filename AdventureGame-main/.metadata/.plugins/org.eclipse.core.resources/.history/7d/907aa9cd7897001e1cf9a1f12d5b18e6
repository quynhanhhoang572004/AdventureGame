package main;

import object.*;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter (GamePanel gp) {
        this.gp = gp;
    }

    public void setObject () {
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 23 * gp.tileSize; // X coordinate of the object in the matrix  
        gp.obj[0].worldY = 26 * gp.tileSize; // Y coordinate of the object in the matrix
                                             // Key này được khai báo ở (x;y) = (23;26)
                                             // Vị trí thực tế của object sẽ bằng vị trí được khai báo + 1
                                             // => (x;y) thực tế = (24;27)
        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = 24 * gp.tileSize; 
        gp.obj[1].worldY = 12 * gp.tileSize;
        
        gp.obj[2] = new OBJ_Key();
        gp.obj[2].worldX = 27 * gp.tileSize; 
        gp.obj[2].worldY = 10 * gp.tileSize;

        gp.obj[3] = new OBJ_Door();
        gp.obj[3].worldX = 28 * gp.tileSize; 
        gp.obj[3].worldY = 11 * gp.tileSize;

        gp.obj[4] = new OBJ_Door();
        gp.obj[4].worldX = 26 * gp.tileSize; 
        gp.obj[4].worldY = 25 * gp.tileSize;

        gp.obj[5] = new OBJ_Door();
        gp.obj[5].worldX = 24 * gp.tileSize; 
        gp.obj[5].worldY = 25 * gp.tileSize;

        gp.obj[6] = new OBJ_Chest();
        gp.obj[6].worldX = 28 * gp.tileSize; 
        gp.obj[6].worldY = 16 * gp.tileSize;        
    }   
}
