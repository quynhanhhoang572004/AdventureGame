package main;

import entity.*;
import monster.MON_Orc;
import monster.MON_OrcLord;
import monster.MON_PolarBear;
import object.OBJ_Axe;
import object.OBJ_Coin_Gold;
// import object.*;
import object.OBJ_Door;
import object.OBJ_Heart;
import object.OBJ_Chest;
import object.OBJ_Key;
import object.OBJ_ManaCrystal;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Diamond;
import object.OBJ_Shield_Metal;
import object.OBJ_Sword_LV2;
import object.OBJ_Lantern;
import object.OBJ_Tent;
import tile_interactive.IT_DryTree;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter (GamePanel gp) {
        this.gp = gp;
    }

    public void setObject () {	
    	int mapNum = 0;
        int i = 0;
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*41;
        gp.obj[mapNum][i].worldY = gp.tileSize*8;
        i++;

        gp.obj[mapNum][i] = new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*34;
        gp.obj[mapNum][i].worldY = gp.tileSize*10;
        i++;
        // gp.obj[mapNum][i] = new OBJ_Shield_Diamond(gp);
        // gp.obj[mapNum][i].worldX = gp.tileSize*32;
        // gp.obj[mapNum][i].worldY = gp.tileSize*21;
        // i++;
        gp.obj[mapNum][i] = new OBJ_Axe(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*29;
        gp.obj[mapNum][i].worldY = gp.tileSize*10;
        i++;
        // gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
        // gp.obj[mapNum][i].worldX = gp.tileSize*30;
        // gp.obj[mapNum][i].worldY = gp.tileSize*17;
        // i++;
        // gp.obj[mapNum][i] = new OBJ_Coin_Gold(gp);
        // gp.obj[mapNum][i].worldX = gp.tileSize*23;
        // gp.obj[mapNum][i].worldY = gp.tileSize*7;
        // i++;
        gp.obj[mapNum][i] = new OBJ_Heart(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*23;
        gp.obj[mapNum][i].worldY = gp.tileSize*8;
        i++;

        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*14;
        gp.obj[mapNum][i].worldY = gp.tileSize*9;
        i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Key(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*14;
        gp.obj[mapNum][i].worldY = gp.tileSize*5;
        i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Key(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*44;
        gp.obj[mapNum][i].worldY = gp.tileSize*30;
        i++;

        // gp.obj[mapNum][i] = new OBJ_Lantern(gp);
        // gp.obj[mapNum][i].worldX = gp.tileSize*25;
        // gp.obj[mapNum][i].worldY = gp.tileSize*24;
        // i++;

        // gp.obj[mapNum][i] = new OBJ_Tent(gp);        
        // gp.obj[mapNum][i].worldX = gp.tileSize*26;
        // gp.obj[mapNum][i].worldY = gp.tileSize*24;
        // i++;

        // gp.obj[mapNum][i] = new OBJ_Sword_LV2(gp);
        // gp.obj[mapNum][i].worldX = gp.tileSize*28;
        // gp.obj[mapNum][i].worldY = gp.tileSize*10;
        // i++;
        
        mapNum = 1;
        i = 0;
     /*    //MAP 2
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*38;
        gp.obj[mapNum][i].worldY = gp.tileSize*45;
        i++;
    */

    }
    
    public void setNPC () {
    	// MAP 1
    	int mapNum = 0;
    	int i = 0;
    	gp.npc[mapNum][i] = new NPC_Santa(gp);
    	gp.npc[mapNum][i].worldX = gp.tileSize * 21;
    	gp.npc[mapNum][i].worldY = gp.tileSize * 21;
    	
    	// MAP 2
    	mapNum = 2;
    	i = 0;
    	gp.npc[mapNum][i] = new NPC_Merchant(gp);
    	gp.npc[mapNum][i].worldX = gp.tileSize * 13;
    	gp.npc[mapNum][i].worldY = gp.tileSize * 9;
    }

    public void setMonster(){
        int mapNum = 0; 
        int i = 0;
        gp.monster[mapNum][i] = new MON_PolarBear(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 12;
        gp.monster[mapNum][i].worldY = gp.tileSize * 23;
        i++;
        gp.monster[mapNum][i] = new MON_PolarBear(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 28;
        gp.monster[mapNum][i].worldY = gp.tileSize * 28;
        i++;
        gp.monster[mapNum][i] = new MON_PolarBear(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 25;
        gp.monster[mapNum][i].worldY = gp.tileSize * 28;
        i++;
        gp.monster[mapNum][i] = new MON_PolarBear(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 16;
        gp.monster[mapNum][i].worldY = gp.tileSize * 20;
        i++;
        
        mapNum = 1; 
        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 35;
        gp.monster[mapNum][i].worldY = gp.tileSize * 45;
        i++;
        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 31;
        gp.monster[mapNum][i].worldY = gp.tileSize * 42;
        i++;
        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 32;
        gp.monster[mapNum][i].worldY = gp.tileSize * 24;
        i++;
        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 33;
        gp.monster[mapNum][i].worldY = gp.tileSize * 12;
        i++;
        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 21;
        gp.monster[mapNum][i].worldY = gp.tileSize * 10;
        i++;
        gp.monster[mapNum][i] = new MON_OrcLord(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 19;
        gp.monster[mapNum][i].worldY = gp.tileSize * 5;
        i++; 


    }
    
    public void setInteractiveTile() {
        int mapNum = 0;
        int i = 0;
        
        for (int x = 9; x <= 22; x++) {
            for (int y = 11; y <= 17; y++) {
                gp.iTile[mapNum][i] = new IT_DryTree(gp, x, y);
                i++;
            }
        }
        for (int x = 19; x <= 22; x++) {
            for (int y = 1; y <= 10; y++) {
                gp.iTile[mapNum][i] = new IT_DryTree(gp, x, y);
                i++;
            }
        }
        for (int x = 37; x <= 48; x++) {
            gp.iTile[mapNum][i] = new IT_DryTree(gp, x, 26);
            i++;
        }
        for (int y = 28; y <= 34; y++) {
            gp.iTile[mapNum][i] = new IT_DryTree(gp, 38, y);
            i++;
        }
        for (int x = 38; x <= 46; x++) {
            gp.iTile[mapNum][i] = new IT_DryTree(gp, x, 28);
            i++;
        }
        for (int x = 30; x <= 36; x++) {
            for (int y = 9; y <= 12; y++) {
                if(x == 33 && y == 11 || x == 34 && y == 10 || x == 33 && y == 10){
                    continue;
                }
                else{
                    gp.iTile[mapNum][i] = new IT_DryTree(gp, x, y);
                    i++;
                }
            }
        }
        
    }
}

