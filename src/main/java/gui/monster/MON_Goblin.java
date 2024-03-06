package gui.monster;

import gui.entity.Entity;
import gui.main.GamePanel;

import java.util.Random;

public class MON_Goblin extends Entity {
    GamePanel gp;
    public MON_Goblin(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = 2;
        name = "Goblin";
        speed = 1;
        maxLife = 10;
        life = maxLife;
        strength = 3;
        dexterity = 3;
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        esBoss = true;
        dead = false;
        if (isDead()){
            handleDeath();
        }
        getImage();


    }
    public boolean isDead(){
        if (life <= 0){
            return dead = true;
        }
        return dead;
    }
    private void handleDeath(){
        gp.gameState = gp.playState;
        System.out.println("Has derrotado al enemigo, bien hecho.");

    }
    public int getAttack(){
        return attack = strength * (int) (Math.random()*3+2);
    }
    public int getDefense(){
        return defense = dexterity * (int) (Math.random()*3+2);
    }

    public void getImage(){

        up1 = getImage("/monster/spr_goblin_walk_3.png", gp.tileSize, gp.tileSize);
        up2 = getImage("/monster/spr_goblin_walk_0.png", gp.tileSize, gp.tileSize);
//        down1 = getImage("/monster/spr_Blue_slime_idle_0.png", gp.tileSize, gp.tileSize);
//        down2 = getImage("/monster/spr_Blue_slime_idle_2.png", gp.tileSize, gp.tileSize);
//        left1 = getImage("/monster/spr_Blue_slime_idle_0.png", gp.tileSize, gp.tileSize);
//        left2 = getImage("/monster/spr_Blue_slime_idle_2.png", gp.tileSize, gp.tileSize);
//        right1 = getImage("/monster/spr_Blue_slime_idle_0.png", gp.tileSize, gp.tileSize);
//        right2 = getImage("/monster/spr_Blue_slime_idle_2.png", gp.tileSize, gp.tileSize);
    }

}
