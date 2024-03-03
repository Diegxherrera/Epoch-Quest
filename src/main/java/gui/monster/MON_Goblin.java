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
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();

        // Verifica si las imágenes se han cargado correctamente
        if (up1 == null || up2 == null || down1 == null || down2 == null || left1 == null || left2 == null || right1 == null || right2 == null) {
            System.out.println("Error: Al menos una de las imágenes es nula en MON_GreenSlime.");
        }
    }

    public void getImage(){
        System.out.println("Loading images for GreenSlime");

        up1 = getImage("/monster/spr_goblin_walk_3.png", gp.tileSize, gp.tileSize);
        up2 = getImage("/monster/spr_goblin_walk_0.png", gp.tileSize, gp.tileSize);
//        down1 = getImage("/monster/spr_Blue_slime_idle_0.png", gp.tileSize, gp.tileSize);
//        down2 = getImage("/monster/spr_Blue_slime_idle_2.png", gp.tileSize, gp.tileSize);
//        left1 = getImage("/monster/spr_Blue_slime_idle_0.png", gp.tileSize, gp.tileSize);
//        left2 = getImage("/monster/spr_Blue_slime_idle_2.png", gp.tileSize, gp.tileSize);
//        right1 = getImage("/monster/spr_Blue_slime_idle_0.png", gp.tileSize, gp.tileSize);
//        right2 = getImage("/monster/spr_Blue_slime_idle_2.png", gp.tileSize, gp.tileSize);
    }


    public void setAction(){
        actionLockCounter++;
        if (actionLockCounter == 60) {
            Random random = new Random();
            int i = random.nextInt(100);
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
}
