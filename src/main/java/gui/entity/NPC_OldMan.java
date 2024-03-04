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

        up1 = getImage("/npc/oldman_up_1.png",gp.tileSize,gp.tileSize);
        up2 = getImage("/npc/oldman_up_2.png",gp.tileSize,gp.tileSize);
        down1 = getImage("/npc/oldman_down_1.png",gp.tileSize,gp.tileSize);
        down2 = getImage("/npc/oldman_down_2.png",gp.tileSize,gp.tileSize);
        left1 = getImage("/npc/oldman_left_1.png",gp.tileSize,gp.tileSize);
        left2 = getImage("/npc/oldman_left_2.png",gp.tileSize,gp.tileSize);
        right1 = getImage("/npc/oldman_right_1.png",gp.tileSize,gp.tileSize);
        right2 = getImage("/npc/oldman_right_2.png",gp.tileSize,gp.tileSize);

    }
    public void setDialgue(){
        dialogues[0] = "Hey, tu. Por fin te has despertado.";
        dialogues[1] = "Nuestra region se encuentra en gran peligro\n y solo tu nos puedes salvar";
        dialogues[2] = "Dirigete al sureste alli encontraras\n a un enemigo peligroso \n al que debes derrotar.";
        dialogues[3] = "Ten cuidado con los limos que te encontraras,\n si los tocas atacaran. Buena suerte.";
        dialogues[4] = "Bien hecho derrotando a ese monstruo.";
        dialogues[5] = "Por desgracia eso no es todo.";
        dialogues[6] = "La proxima bestia se encuentra en una ciudad\n pasando el lugar donde derrotaste al limo azul";
        dialogues[7] = "Buena suerte.";
        dialogues[8] = "Bien hecho. \n Has derrotado a otro adversario.";
        dialogues[9] = "Solo queda uno mas, pero este sera\n el combate mas peligroso.";
        dialogues[10] = "Se encuentra en un castillo al este de aqui.";
        dialogues[11] = "Alli deberas enfrentarte a una vision\n de ti mismo, ten cuidado.\n Y buena suerte";
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
    public void speak1Boss(){
        super.speak1Boss();
    }
}
