package main;

import entity.*;
import monster.MON_Orc;
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
        gp.obj[mapNum][i].worldX = gp.tileSize*33;
        gp.obj[mapNum][i].worldY = gp.tileSize*10;
        i++;
        gp.obj[mapNum][i] = new OBJ_Shield_Diamond(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*32;
        gp.obj[mapNum][i].worldY = gp.tileSize*21;
        i++;
        gp.obj[mapNum][i] = new OBJ_Axe(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*29;
        gp.obj[mapNum][i].worldY = gp.tileSize*10;
        i++;
        gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*20;
        gp.obj[mapNum][i].worldY = gp.tileSize*7;
        i++;
        gp.obj[mapNum][i] = new OBJ_Coin_Gold(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*22;
        gp.obj[mapNum][i].worldY = gp.tileSize*7;
        i++;
        gp.obj[mapNum][i] = new OBJ_Heart(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*15;
        gp.obj[mapNum][i].worldY = gp.tileSize*7;
        i++;
        gp.obj[mapNum][i] = new OBJ_ManaCrystal(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*33;
        gp.obj[mapNum][i].worldY = gp.tileSize*5;
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

        gp.obj[mapNum][i] = new OBJ_Lantern(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*25;
        gp.obj[mapNum][i].worldY = gp.tileSize*24;
        i++;

        gp.obj[mapNum][i] = new OBJ_Tent(gp);        
        gp.obj[mapNum][i].worldX = gp.tileSize*26;
        gp.obj[mapNum][i].worldY = gp.tileSize*24;
        i++;

        gp.obj[mapNum][i] = new OBJ_Sword_LV2(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*28;
        gp.obj[mapNum][i].worldY = gp.tileSize*10;
        i++;
        //MAP 2
  


    }
    
    public void setNPC () {
    	// MAP 1
    	int mapNum = 0;
    	int i = 0;
    	gp.npc[mapNum][i] = new NPC_Santa(gp);
    	gp.npc[mapNum][i].worldX = gp.tileSize * 21;
    	gp.npc[mapNum][i].worldY = gp.tileSize * 21;
    	
    	// MAP 2
    	mapNum = 1;
    	i = 0;
    	gp.npc[mapNum][i] = new NPC_Merchant(gp);
    	gp.npc[mapNum][i].worldX = gp.tileSize * 12;
    	gp.npc[mapNum][i].worldY = gp.tileSize * 7;
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
        gp.monster[mapNum][i].worldX = gp.tileSize * 30;
        gp.monster[mapNum][i].worldY = gp.tileSize * 40;
        i++;
        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 30;
        gp.monster[mapNum][i].worldY = gp.tileSize * 41;
        i++;

    }
    
    public void setInteractiveTile() {
    	int mapNum = 0; 
    	int i = 0;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 31, 4); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 31, 3); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 30, 3); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 29, 3); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 29, 4); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 29, 5); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 30, 5); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 31, 5); i++;

    }
}

