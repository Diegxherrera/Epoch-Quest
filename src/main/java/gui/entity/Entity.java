package gui.entity;

import gui.main.GamePanel;
import gui.main.UtilityTool;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Entity {
    GamePanel gp;
    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public BufferedImage attackUp1,attackUp2,attackDown1,attackDown2,attackLeft1,attackLeft2,attackRight1,attackRight2;
    public BufferedImage image,image2,image3;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    String[] dialogues = new String[20];

    //State
    public int worldX, worldY;
    public String direction = "down";
    public int spriteNum = 1;
    int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    boolean attacking = false;
    boolean contactPlayer;
    public boolean blueSlimeDerrotado = false;
    public boolean goblinDerrotado = false;

    //Counter
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;

    //Character Stats
     public int type; //0 = players, 1 = npc, 2 = monster
     public String name;
     public int speed;
     public int maxLife;
     public int life;
     public int level;
     public int strength;
     public int dexterity;
     public int attack;
     public int defense;
     public int exp;
     public int nextLevelExp;
     public Entity currentWeapon;
     public Entity currentShield;

     //Item attributes
    public int attackValue;
    public int defenseValue;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }
    public void setAction(){}

    public void speak(){
        if (dialogueIndex > 3 ){
            dialogueIndex = 0;
        }
        if (dialogueIndex == 3){
            gp.aSetter.setBlueSlime();
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction){
            case"up":
                direction = "down";
                break;
            case"down":
                direction = "up";
                break;
            case"left":
                direction = "right";
                break;
            case"right":
                direction = "left";
                break;
        }

    }
    public void speak1Boss(){
        if (blueSlimeDerrotado){
            dialogueIndex = 4;
            if (dialogueIndex >= 7){
                dialogueIndex = 4;
                gp.aSetter.setGoblin();
            }
            gp.ui.currentDialogue = dialogues[dialogueIndex];
            dialogueIndex++;

            switch (gp.player.direction){
                case"up":
                    direction = "down";
                    break;
                case"down":
                    direction = "up";
                    break;
                case"left":
                    direction = "right";
                    break;
                case"right":
                    direction = "left";
                    break;
            }

        }
    }
    public void speak2Boss(){
        if (goblinDerrotado){
            dialogueIndex = 8;
            if (dialogueIndex >= 11){
                dialogueIndex = 8;
                gp.aSetter.setRedBoy();
            }
            gp.ui.currentDialogue = dialogues[dialogueIndex];
            dialogueIndex++;

            switch (gp.player.direction){
                case"up":
                    direction = "down";
                    break;
                case"down":
                    direction = "up";
                    break;
                case"left":
                    direction = "right";
                    break;
                case"right":
                    direction = "left";
                    break;
            }

        }
    }
    public void playerContact(){
        if (contactPlayer && !invincible) {
            gp.player.decreaseLife(1);
            invincible = true;
            invincibleCounter++;
        }
        if (invincibleCounter >= 60){
            invincible = false;
        }
        invincibleCounter = 0;
    }
    public void update() {
        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        contactPlayer = gp.cChecker.checkPlayer(this);
        playerContact();


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
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            switch (direction) {
                case "up":
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                    break;
                case "left":
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                    break;
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
    public BufferedImage getImage(String imagePath, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            InputStream inputStream = getClass().getResourceAsStream(imagePath);
            if (inputStream != null) {
                image = ImageIO.read(inputStream);
                image = uTool.scaleImage(image, width, height);
            } else {
                System.out.println("Error: InputStream is null for imagePath: " + imagePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }



     }
