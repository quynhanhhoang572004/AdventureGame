package data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.xml.crypto.Data;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Axe;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Lantern;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Diamond;
import object.OBJ_Shield_Metal;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_LV2;
import object.OBJ_Sword_Normal;
import object.OBJ_Tent;

public class SaveLoad {
    GamePanel gp;

    public SaveLoad(GamePanel gp) {
        this.gp = gp;
    }
    public Entity getObject(String itemName) {
        Entity obj = null;
        switch(itemName) {
            case "Wooden Axe": obj = new OBJ_Axe(gp); break;
            case "Boots" : obj = new OBJ_Boots(gp); break;
            case "Key" : obj = new OBJ_Key(gp); break;
            case "Lantern" : obj = new OBJ_Lantern(gp); break;
            case "Life Potion" : obj = new OBJ_Potion_Red(gp); break;
            case "Diamond Shield" : obj = new OBJ_Shield_Diamond(gp); break;
            case "Metal Shield" : obj = new OBJ_Shield_Metal(gp); break;
            case "Wood Shield" : obj = new OBJ_Shield_Wood(gp); break;
            case "Big Sword" : obj = new OBJ_Sword_LV2(gp); break;
            case "Normal Sword" : obj = new OBJ_Sword_Normal(gp); break;
            case "Tent" : obj = new OBJ_Tent(gp); break;
            case "Door" : obj = new OBJ_Door(gp); break;
            case "Chest" : obj = new OBJ_Chest(gp); break;
        }
        return obj;
    }
    public void save(){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
            DataStorage ds = new DataStorage();

            ds.level = gp.player.level;
            ds.maxLife = gp.player.maxLife;
            ds.life = gp.player.life;
            ds.maxMana = gp.player.maxMana;
            ds.mana = gp.player.mana;
            ds.strength = gp.player.strength;
            ds.dexterity = gp.player.dexterity;
            ds.exp = gp.player.exp;
            ds.nextLevelExp = gp.player.nextLevelExp;
            ds.coin = gp.player.coin;

            //Write the DataStorage object
            oos.writeObject(ds);
        }
        catch(Exception e) {
            System.out.println("Save Exception!");
        }
        
    }
    public void load() {
        try {
            //Read the DataStorage object
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));
            DataStorage ds = (DataStorage)ois.readObject();
            //PLAYER STATS
            gp.player.level = ds.level;
            gp.player.maxLife = ds.maxLife;
            gp.player.life = ds.life;
            gp.player.maxMana = ds.maxMana;
            gp.player.mana = ds.mana;
            gp.player.strength = ds.strength;
            gp.player.dexterity = ds.dexterity;
            gp.player.exp = ds.exp;
            gp.player.nextLevelExp = ds.nextLevelExp;
            gp.player.coin = ds.coin;

            //PLAYER INVENTORY
            gp.player.inventory.clear();
            for (int i = 0; i < ds.itemNames.size(); i++) {
                gp.player.inventory.add(getObject(ds.itemNames.get(i)));
                gp.player.inventory.get(i).amount = ds.itemAmounts.get(i);
            }
        } catch (Exception e) {
            System.out.println("Load Exception!");
        }
    }
}
