package gui.entity;

import gui.main.GamePanel;
import gui.main.KeyHandler;
import gui.object.OBJ_Key;
import gui.object.OBJ_Shield_Wood;
import gui.object.OBJ_Sword_Normal;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Entity {

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public ArrayList<Entity> inventory  = new ArrayList<>();
    public final int inventorySize = 20;


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
        setItems();
    }

    public void setDefaultValues(){

        worldX=gp.tileSize * 23;
        worldY= gp.tileSize * 21;
        speed=4;
        direction="down";

        //Player status
        level = 1;
        maxLife = 20;
        life = maxLife;
        strength = 5;
        dexterity = 5;
        exp = 0;
        nextLevelExp = 5;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        attack = getAttack();
        defense = getDefense();
    }
    public void setItems(){
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Key(gp));
    }

    public int getAttack(){
        return attack = strength * currentWeapon.attackValue;
    }
    public int getDefense(){
        return defense = dexterity * currentShield.defenseValue;
    }
    public int magic(){
        if (life < maxLife){
            return life += 3;
        }
        return life;
    }
    public void getPlayerImage() {
        up1 = getImage("/player/blueBoy/boy_up_1.png",gp.tileSize,gp.tileSize);
        up2 = getImage("/player/blueBoy/boy_up_2.png",gp.tileSize,gp.tileSize);
//        up3 = setUp("/player/onionKnightUp3");
        down1 = getImage("/player/blueBoy/boy_down_1.png",gp.tileSize,gp.tileSize);
        down2 = getImage("/player/blueBoy/boy_down_2.png",gp.tileSize,gp.tileSize);
//        down3 = setUp("/player/onionKnightDown3");
        left1 = getImage("/player/blueBoy/boy_left_1.png",gp.tileSize,gp.tileSize);
        left2 = getImage("/player/blueBoy/boy_left_2.png",gp.tileSize,gp.tileSize);
//        left3 = setUp("/player/onionKnightLeft3");
        right1 = getImage("/player/blueBoy/boy_right_1.png",gp.tileSize,gp.tileSize);
        right2 = getImage("/player/blueBoy/boy_right_2.png",gp.tileSize,gp.tileSize);
//        right3 = setUp("/player/onionKnightRight3");
    }
    public void getPlayerAttackImage(){
        attackUp1 = getImage("/player/blueBoy/boy_attack_up_1.png",gp.tileSize,gp.tileSize*2);
        attackUp2 = getImage("/player/blueBoy/boy_attack_up_2.png",gp.tileSize,gp.tileSize*2);
        attackDown1 = getImage("/player/blueBoy/boy_attack_down_1.png",gp.tileSize,gp.tileSize*2);
        attackDown2 = getImage("/player/blueBoy/boy_attack_down_2.png",gp.tileSize,gp.tileSize*2);
        attackLeft1 = getImage("/player/blueBoy/boy_attack_left_1.png",gp.tileSize*2,gp.tileSize);
        attackLeft2 = getImage("/player/blueBoy/boy_attack_left_2.png",gp.tileSize*2,gp.tileSize);
        attackRight1 = getImage("/player/blueBoy/boy_attack_right_1.png",gp.tileSize*2,gp.tileSize);
        attackRight2 = getImage("/player/blueBoy/boy_attack_right_2.png",gp.tileSize*2,gp.tileSize);
    }

    public void update(){
       if (!isDead()) {
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
               int objIndex = gp.cChecker.checkObject(this, true);
               pickUpObject(objIndex);

               //Check NPC collision
               int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
               interactNPC(npcIndex);


               contactMonster();
               //Check Event
               gp.eHandler.checkEvent();

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
                   spriteCounter = 0;
               }
           }
           if (invincible) {
               invincibleCounter++;
               if (invincibleCounter > 120) {
                   invincible = false;
                   invincibleCounter = 0;
               }
           }
       }
           if (isDead()){
               handleDeath();
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
            } else if (gp.keyH.enterPressed && blueSlimeDerrotado) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak1Boss();
            }else if (gp.keyH.enterPressed && goblinDerrotado) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak2Boss();
            }
        }
    }
    public void contactMonster() {
        int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
        if (monsterIndex != 999 && !invincible) {
            decreaseLife(1);
            invincible = true;
            invincibleCounter++;
        }
        if (invincibleCounter >= 15){
            invincible = false;
        }
        invincibleCounter = 0;
    }
    public void damageMonster(int i){
        int damage  = attack - gp.monster[i].defense;

        gp.monster[i].life -= damage;
        if (gp.monster[i].life <= 0){

        }
    }



    public void decreaseLife(int amount){
        life -= amount;
    }
//    public void characterDeath(){
//        if (life <= 0){
//            gp.gameState = gp.deadState;
//            System.out.println("Game state: "+ gp.gameState);
//        }
//    }
    public boolean isDead(){
        return life <=0;
    }
    private void handleDeath(){
        gp.gameState = gp.deadState;
        System.out.println("El jugador ha muerto, que pelele");

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
