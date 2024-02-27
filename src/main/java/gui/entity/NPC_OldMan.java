package gui.entity;

import gui.main.GamePanel;
import java.util.Random;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp){
        super(gp);
        direction = "down";
        speed = 1;
        getImage();
        setDialgue();
    }
    public void getImage() {

        up1 = setUp("/npc/oldman_up_1",gp.tileSize,gp.tileSize);
        up2 = setUp("/npc/oldman_up_2",gp.tileSize,gp.tileSize);
        down1 = setUp("/npc/oldman_down_1",gp.tileSize,gp.tileSize);
        down2 = setUp("/npc/oldman_down_2",gp.tileSize,gp.tileSize);
        left1 = setUp("/npc/oldman_left_1",gp.tileSize,gp.tileSize);
        left2 = setUp("/npc/oldman_left_2",gp.tileSize,gp.tileSize);
        right1 = setUp("/npc/oldman_right_1",gp.tileSize,gp.tileSize);
        right2 = setUp("/npc/oldman_right_2",gp.tileSize,gp.tileSize);

    }
    public void setDialgue(){
        dialogues[0] = "Hey, you. You're finally awake.";
        dialogues[1] = "You were trying to cross the border, right? \nWalked right into that Imperial ambush, \nsame as us, and that thief over there.";
        dialogues[2] = "Damn you Stormcloaks. \nSkyrim was fine until you came along.";
        dialogues[3] = "Empire was nice and lazy. \nIf they hadn't been looking for you, \nI could've stolen that horse and \nbe halfway to Hammerfell.";
    }
    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 120) {
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
    public void speak() {
        super.speak();
    }
}
