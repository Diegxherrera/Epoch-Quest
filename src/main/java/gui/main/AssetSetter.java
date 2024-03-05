package gui.main;

import gui.entity.NPC_OldMan;
import gui.monster.MON_BlueSlime;
import gui.monster.MON_Goblin;
import gui.monster.MON_GreenSlime;
import gui.monster.MON_RedBoy;

public class AssetSetter {

    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){
    }
    public void setNPC(){
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize*21;
        gp.npc[0].worldY = gp.tileSize*21;
    }
    public void setMonster(){
        gp.monster[0] = new MON_GreenSlime(gp);
        gp.monster[0].worldX = gp.tileSize*20;
        gp.monster[0].worldY = gp.tileSize*51;

        gp.monster[1] = new MON_GreenSlime(gp);
        gp.monster[1].worldX = gp.tileSize*23;
        gp.monster[1].worldY = gp.tileSize*64;

        gp.monster[2] = new MON_GreenSlime(gp);
        gp.monster[2].worldX = gp.tileSize*105/3;
        gp.monster[2].worldY = gp.tileSize*40;

        gp.monster[3] = new MON_GreenSlime(gp);
        gp.monster[3].worldX = gp.tileSize*165/3;
        gp.monster[3].worldY = gp.tileSize*41;

        gp.monster[4] = new MON_GreenSlime(gp);
        gp.monster[4].worldX = gp.tileSize*243/3;
        gp.monster[4].worldY = gp.tileSize*40;

        gp.monster[5] = new MON_GreenSlime(gp);
        gp.monster[5].worldX = gp.tileSize*255/3;
        gp.monster[5].worldY = gp.tileSize*65;

        gp.monster[6] = new MON_GreenSlime(gp);
        gp.monster[6].worldX = gp.tileSize*156/3;
        gp.monster[6].worldY = gp.tileSize*58;

        gp.monster[7] = new MON_GreenSlime(gp);
        gp.monster[7].worldX = gp.tileSize*189/3;
        gp.monster[7].worldY = gp.tileSize*58;

        gp.monster[8] = new MON_GreenSlime(gp);
        gp.monster[8].worldX = gp.tileSize*192/3;
        gp.monster[8].worldY = gp.tileSize*68;

        gp.monster[9] = new MON_GreenSlime(gp);
        gp.monster[9].worldX = gp.tileSize*150/3;
        gp.monster[9].worldY = gp.tileSize*68;


    }
    public void setBlueSlime(){
        gp.monster[10] = new MON_BlueSlime(gp);
        gp.monster[10].worldX = gp.tileSize*168/3;
        gp.monster[10].worldY = gp.tileSize*63;
    }
    public void setGoblin(){
        gp.monster[11] = new MON_Goblin(gp);
        gp.monster[11].worldX = gp.tileSize*261/3;
        gp.monster[11].worldY = gp.tileSize*88;
    }
    public void setRedBoy(){
        gp.monster[12] = new MON_RedBoy(gp);
        gp.monster[12].worldX = gp.tileSize*243/3;
        gp.monster[12].worldY = gp.tileSize*21;
    }

}
