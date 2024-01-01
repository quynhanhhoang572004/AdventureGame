package main;

import entity.NPC_Santa;
import monster.MON_PolarBear;
// import object.*;
import object.OBJ_Door;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter (GamePanel gp) {
        this.gp = gp;
    }

    public void setObject () {
        gp.obj[0] = new OBJ_Door(gp);
        gp.obj[0].worldX = gp.tileSize*26;
        gp.obj[0].worldY = gp.tileSize*26;
    }
    
    public void setNPC () {
    	gp.npc[0] = new NPC_Santa(gp);
    	gp.npc[0].worldX = gp.tileSize * 21;
    	gp.npc[0].worldY = gp.tileSize * 21;
    }

    public void setMonster(){

        int i = 0;
        gp.monster[i] = new MON_PolarBear(gp);
        gp.monster[i].worldX = gp.tileSize * 23;
        gp.monster[i].worldY = gp.tileSize * 23;
        i++;
        gp.monster[i] = new MON_PolarBear(gp);
        gp.monster[i].worldX = gp.tileSize * 28;
        gp.monster[i].worldY = gp.tileSize * 28;
        i++;
        gp.monster[i] = new MON_PolarBear(gp);
        gp.monster[i].worldX = gp.tileSize * 25;
        gp.monster[i].worldY = gp.tileSize * 28;
        i++;
        gp.monster[i] = new MON_PolarBear(gp);
        gp.monster[i].worldX = gp.tileSize * 16;
        gp.monster[i].worldY = gp.tileSize * 20;
        i++;

    }
}

