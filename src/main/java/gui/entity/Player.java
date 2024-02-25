package gui.entity;

import gui.main.GamePanel;
import gui.main.KeyHandler;
import gui.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

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

        setDefaultValues();
        getPlayerImage();
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

        up1 = setUp("/player/onionKnightUp1");
        up2 = setUp("/player/onionKnightUp2");
        up3 = setUp("/player/onionKnightUp3");
        down1 = setUp("/player/onionKnightDown1");
        down2 = setUp("/player/onionKnightDown2");
        down3 = setUp("/player/onionKnightDown3");
        left1 = setUp("/player/onionKnightLeft1");
        left2 = setUp("/player/onionKnightLeft2");
        left3 = setUp("/player/onionKnightLeft3");
        right1 = setUp("/player/onionKnightRight1");
        right2 = setUp("/player/onionKnightRight2");
        right3 = setUp("/player/onionKnightRight3");
    }


    public void update(){
        if (keyH.upPressed ==true ||keyH.downPressed ==true ||
                keyH.leftPressed ==true ||keyH.rightPressed ==true ) {
            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
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
            gp.keyH.enterPressed = false;


            //if collision is false, player can move
            if (!collisionOn) {
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

            spriteCounter++;
            if (spriteCounter > 13) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        if (invincible == true){
            invincibleCounter++;
            if (invincibleCounter >60){
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
            if (gp.keyH.enterPressed == true) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }

        }
    }
    public void contactMonster(int i){
        if (i !=999){
            if (invincible == false) {
                life -= 1;
                invincible = true;
            }
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
                if (spriteNum==3){
                    image = up3;
                }
                break;
            case "down":
                if (spriteNum==1) {
                    image = down1;
                }
                if (spriteNum==2){
                    image = down2;
                }
                if (spriteNum==3){
                    image = down3;
                }
                break;
            case "left":
                if (spriteNum==1) {
                    image = left1;
                }
                if (spriteNum==2){
                    image = left2;
                }
                if (spriteNum==3){
                    image = left3;
                }
                break;
            case "right":
                if (spriteNum==1) {
                    image = right1;
                }
                if (spriteNum==2){
                    image = right2;
                }
                if (spriteNum==3){
                    image = right3;
                }
                break;


        }
        if (invincible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image,screenX,screenY,null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));


//        g2.setFont(new Font("Arial",Font.PLAIN,26));
//        g2.setColor(Color.white);
//        g2.drawString("Invincible: " + invincibleCounter,10,400);
    }
}
