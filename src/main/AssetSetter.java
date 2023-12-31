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
        gp.monster[0] = new MON_PolarBear(gp);
        gp.monster[0].worldX = gp.tileSize * 23;
        gp.monster[0].worldY = gp.tileSize * 23;

        gp.monster[1] = new MON_PolarBear(gp);
        gp.monster[1].worldX = gp.tileSize * 28;
        gp.monster[1].worldY = gp.tileSize * 28;
    }
}

