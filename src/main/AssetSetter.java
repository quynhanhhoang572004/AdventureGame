package main;

import entity.*;
import monster.MON_PolarBear;
import object.OBJ_Axe;
import object.OBJ_Coin_Gold;
// import object.*;
import object.OBJ_Door;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_ManaCrystal;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Diamond;
import object.OBJ_Shield_Metal;
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
        gp.obj[mapNum][i].worldX = gp.tileSize*20;
        gp.obj[mapNum][i].worldY = gp.tileSize*23;
        i++;
        gp.obj[mapNum][i] = new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*21;
        gp.obj[mapNum][i].worldY = gp.tileSize*23;
        i++;
        gp.obj[mapNum][i] = new OBJ_Shield_Metal(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*22;
        gp.obj[mapNum][i].worldY = gp.tileSize*23;
        i++;
        gp.obj[mapNum][i] = new OBJ_Shield_Diamond(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*23;
        gp.obj[mapNum][i].worldY = gp.tileSize*23;
        i++;
        gp.obj[mapNum][i] = new OBJ_Axe(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*24;
        gp.obj[mapNum][i].worldY = gp.tileSize*23;
        i++;
        gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*25;
        gp.obj[mapNum][i].worldY = gp.tileSize*23;
        i++;
        gp.obj[mapNum][i] = new OBJ_Coin_Gold(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*26;
        gp.obj[mapNum][i].worldY = gp.tileSize*23;
        i++;
        gp.obj[mapNum][i] = new OBJ_Coin_Gold(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*27;
        gp.obj[mapNum][i].worldY = gp.tileSize*23;
        i++;
        gp.obj[mapNum][i] = new OBJ_Heart(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*28;
        gp.obj[mapNum][i].worldY = gp.tileSize*23;
        i++;
        gp.obj[mapNum][i] = new OBJ_ManaCrystal(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*29;
        gp.obj[mapNum][i].worldY = gp.tileSize*23;
        i++;
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
        gp.monster[mapNum][i].worldX = gp.tileSize * 23;
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
        
//      mapNum = 1; //Spawn monster in the second map
//      gp.monster[mapNum][i] = new MON_PolarBear(gp);
//      gp.monster[mapNum][i].worldX = gp.tileSize * 16;
//      gp.monster[mapNum][i].worldY = gp.tileSize * 20;
//      i++;
    }
    
    public void setInteractiveTile() {
    	int mapNum = 0; 
    	int i = 0;
    	gp.iTile[mapNum][i] = new IT_DryTree(gp, 26, 24); i++;
    	gp.iTile[mapNum][i] = new IT_DryTree(gp, 27, 24); i++;
	    gp.iTile[mapNum][i] = new IT_DryTree(gp, 28, 24); i++;	
	    gp.iTile[mapNum][i] = new IT_DryTree(gp, 29, 24); i++;	 
	    gp.iTile[mapNum][i] = new IT_DryTree(gp, 30, 24); i++;	
	    gp.iTile[mapNum][i] = new IT_DryTree(gp, 31, 24); i++;	
	    gp.iTile[mapNum][i] = new IT_DryTree(gp, 32, 24); i++;
	    gp.iTile[mapNum][i] = new IT_DryTree(gp, 33, 24); i++;

    }
}

