package gui.entity;

import gui.main.GamePanel;
import gui.main.KeyHandler;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;


    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;
        type = 0;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues(){

        worldX=gp.tileSize * 23;
        worldY= gp.tileSize * 21;
        speed=4;
        direction="down";

        //Player status
        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage() {
        up1 = setUp("/player/blueBoy/boy_up_1",gp.tileSize,gp.tileSize);
        up2 = setUp("/player/blueBoy/boy_up_2",gp.tileSize,gp.tileSize);
//        up3 = setUp("/player/onionKnightUp3");
        down1 = setUp("/player/blueBoy/boy_down_1",gp.tileSize,gp.tileSize);
        down2 = setUp("/player/blueBoy/boy_down_2",gp.tileSize,gp.tileSize);
//        down3 = setUp("/player/onionKnightDown3");
        left1 = setUp("/player/blueBoy/boy_left_1",gp.tileSize,gp.tileSize);
        left2 = setUp("/player/blueBoy/boy_left_2",gp.tileSize,gp.tileSize);
//        left3 = setUp("/player/onionKnightLeft3");
        right1 = setUp("/player/blueBoy/boy_right_1",gp.tileSize,gp.tileSize);
        right2 = setUp("/player/blueBoy/boy_right_2",gp.tileSize,gp.tileSize);
//        right3 = setUp("/player/onionKnightRight3");
    }
    public void getPlayerAttackImage(){
        attackUp1 = setUp("/player/blueBoy/boy_attack_up_1",gp.tileSize,gp.tileSize*2);
        attackUp2 = setUp("/player/blueBoy/boy_attack_up_2",gp.tileSize,gp.tileSize*2);
        attackDown1 = setUp("/player/blueBoy/boy_attack_down_1",gp.tileSize,gp.tileSize*2);
        attackDown2 = setUp("/player/blueBoy/boy_attack_down_2",gp.tileSize,gp.tileSize*2);
        attackLeft1 = setUp("/player/blueBoy/boy_attack_left_1",gp.tileSize*2,gp.tileSize);
        attackLeft2 = setUp("/player/blueBoy/boy_attack_left_2",gp.tileSize*2,gp.tileSize);
        attackRight1 = setUp("/player/blueBoy/boy_attack_right_1",gp.tileSize*2,gp.tileSize);
        attackRight2 = setUp("/player/blueBoy/boy_attack_right_2",gp.tileSize*2,gp.tileSize);
    }

    public void update(){
        if (keyH.upPressed || keyH.downPressed ||
                keyH.leftPressed || keyH.rightPressed ||
                keyH.enterPressed) {
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }

            //Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //Check Object collison
            int objIndex = gp.cChecker.checkObject(this,true);
            pickUpObject(objIndex);

            //Check NPC collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //Check monster collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            //Check Event
            gp.eHandler.checkEvent();

            //DIES
            characterDeath();

            //if collision is false, player can move
            if (!collisionOn && !keyH.enterPressed) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;

                    case "down":
                        worldY += speed;
                        break;

                    case "left":
                        worldX -= speed;
                        break;

                    case "right":
                        worldX += speed;
                        break;
                }
            }
            gp.keyH.enterPressed = false;

            spriteCounter++;
            if (spriteCounter > 15) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
//                else if (spriteNum == 2) {
//                    spriteNum = 3;
//                } else if (spriteNum == 3) {
//                    spriteNum = 1;
//                }
                spriteCounter = 0;
            }
        }
        if (invincible){
            invincibleCounter++;
            if (invincibleCounter >120){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void pickUpObject(int i){
        if (i != 999){

        }
    }
    public void interactNPC(int i){
        if (i != 999){
            if (gp.keyH.enterPressed) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
    }
    public void contactMonster(int i){
        if (i !=999 && !invincible ){
            gp.gameState = gp.battleState;
        }

    }
    public void decreaseLife(int amount){
        life -= amount;
    }
    public void characterDeath(){
        if (life <= 0){
            gp.gameState = gp.deadState;
            System.out.println("Game state: "+ gp.gameState);
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch (direction){
            case "up":
                if (spriteNum==1) {
                    image = up1;
                }
                if (spriteNum==2){
                    image = up2;
                }
//                if (spriteNum==3){
//                    image = up3;
//                }
                break;
            case "down":
                if (spriteNum==1) {
                    image = down1;
                }
                if (spriteNum==2){
                    image = down2;
                }
//                if (spriteNum==3){
//                    image = down3;
//                }
                break;
            case "left":
                if (spriteNum==1) {
                    image = left1;
                }
                if (spriteNum==2){
                    image = left2;
                }
//                if (spriteNum==3){
//                    image = left3;
//                }
                break;
            case "right":
                if (spriteNum==1) {
                    image = right1;
                }
                if (spriteNum==2){
                    image = right2;
                }
//                if (spriteNum==3){
//                    image = right3;
//                }
                break;


        }
        if (invincible){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image,screenX,screenY,null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));


//        g2.setFont(new Font("Arial",Font.PLAIN,26));
//        g2.setColor(Color.white);
//        g2.drawString("Invincible: " + invincibleCounter,10,400);
    }
}
