package gui.monster;

import gui.entity.Entity;
import gui.main.GamePanel;

import java.util.Random;

public class MON_GreenSlime extends Entity {
    GamePanel gp;
    public MON_GreenSlime(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = 2;
        name = "Green Slime";
        direction = "down";
        speed = 1;
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 30;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage(){

        up1 = getImage("/monster/greenslime_down_1.png", gp.tileSize, gp.tileSize);
        up2 = getImage("/monster/greenslime_down_2.png", gp.tileSize, gp.tileSize);
        down1 = getImage("/monster/greenslime_down_1.png", gp.tileSize, gp.tileSize);
        down2 = getImage("/monster/greenslime_down_2.png", gp.tileSize, gp.tileSize);
        left1 = getImage("/monster/greenslime_down_1.png", gp.tileSize, gp.tileSize);
        left2 = getImage("/monster/greenslime_down_2.png", gp.tileSize, gp.tileSize);
        right1 = getImage("/monster/greenslime_down_1.png", gp.tileSize, gp.tileSize);
        right2 = getImage("/monster/greenslime_down_2.png", gp.tileSize, gp.tileSize);
    }

    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 60) {
            Random random = new Random();
            int i = random.nextInt(100);
            if (i <= 25) {
                direction = "up";
            } else if (i <= 50) {
                direction = "down";
            } else if (i <= 75) {
                direction = "left";
            } else {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
}
