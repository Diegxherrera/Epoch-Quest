package gui.main;


import gui.entity.Entity;
import gui.object.OBJ_Heart;
import utils.DIContainer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;


public class UI {
    GamePanel gp;
    Entity entity;
    Graphics2D g2;
    Font maruMonica;
    BufferedImage heart_full,heart_half,heart_blank;
    private final int slotCount = 10;
    private int selectedSlot = 0;

    public int slotCol = 0;
    public int slotRow = 0;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    private GamePanel gamePanel;
    private KeyHandler keyHandler;
    private DIContainer container;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0; // 0: the first screen 1: the second screen
    private int currentEnemyIndex = -1; // Inicializado a -1 para indicar que no hay enemigo actual
    private String[] monsterImagePath = new String[3];
    public boolean battleMenuVisible = false;



    public UI(GamePanel gp){

        this.gp = gp;
        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            assert is != null;
            maruMonica = Font.createFont(Font.TRUETYPE_FONT,is);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        //Create HUD object
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;

        monsterImagePath[0] = "/monster/spr_Blue_slime_idle_0.png";
        monsterImagePath[1] = "/monster/spr_goblin_idle_0.png";
        monsterImagePath[2] = "/monster/boy_red_left_1.png";
    }

    public UI(DIContainer container) {
        this.container = container;
        this.keyHandler = container.getKeyHandler();
    }

    public void setDependencies(DIContainer container) {
        this.keyHandler = container.getKeyHandler();
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(maruMonica);
        g2.setColor(Color.white);
        //Title State
        if (gp.gameState == gp.titleState){
            drawTitleScreen();
        }

        if (gp.gameState == gp.playState){
            drawPlayerlife();
        }
        if (gp.gameState == gp.pauseState){
            drawPlayerlife();
            drawPauseScreen();
        }

//        if (gp.gameState != gp.pauseState){
//            drawInventory();
//        }
        if (gp.gameState == gp.dialogueState){
            drawPlayerlife();
            drawDialogueScreen();
        }
        if (gp.gameState == gp.battleState){
            drawBattleScreen();
            drawPlayerlife();
       }
        if (gp.gameState == gp.deadState){
            drawDeathScreen();
        }
        if (gp.gameState == gp.characterState){
            drawCharacterScreen();
            drawInventory();
        }

    }

//    private void drawInventory() {
//        int slotWidth = 50; // Width of each slot
//        int slotHeight = 50; // Height of each slot
//        int spacing = 10; // Spacing between slots
//        int startY = gp.screenHeight - slotHeight - 20; // Start drawing from the bottom
//        int totalWidth = (slotWidth * slotCount) + (spacing * (slotCount - 1));
//        int startX = (gp.screenWidth - totalWidth) / 2; // Center the inventory bar
//
//        g2.setColor(Color.WHITE); // Slot background color
//        for (int i = 0; i < slotCount; i++) {
//            g2.fillRect(startX + (i * (slotWidth + spacing)), startY, slotWidth, slotHeight);
//            g2.setColor(Color.GRAY);
//            g2.drawRect(startX + (i * (slotWidth + spacing)), startY, slotWidth, slotHeight);
//        }
//    }
    public void drawPlayerlife(){
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        //Draw max life
        while (i < gp.player.maxLife/2){
            g2.drawImage(heart_blank,x,y,null);
            i++;
            x += gp.tileSize;
        }
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;
        //Draw Current life
        while (i < gp.player.life){
            g2.drawImage(heart_half,x,y,null);
            i++;
            if (i < gp.player.life){
                g2.drawImage(heart_full,x,y,null);
            }
            i++;
            x += gp.tileSize;
        }
    }
    public void drawTitleScreen(){
        if (titleScreenState == 0) {
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            //Tilte Name
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 100F));
            String text = "EPOCH QUEST";
            int x = getXForCentered(text);
            int y = gp.tileSize * 3;
            //Shadow
            g2.setColor(Color.gray);
            g2.drawString(text, x + 5, y + 5);
            //Main color
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
            //Player Image
            x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
            y += gp.tileSize * 2;
            g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
            //Menu
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
            text = "NEW GAME";
            x = getXForCentered(text);
            y += gp.tileSize * 3.5;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            text = "LOAD GAME";
            x = getXForCentered(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            text = "QUIT";
            x = getXForCentered(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        }
        else if (titleScreenState == 1){
            //class Selection Screen
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));
            String text = "Select your class!";
            int x = getXForCentered(text);
            int y = gp.tileSize*3;
            g2.drawString(text,x,y);

            text = "Fighter";
            x = getXForCentered(text);
            y += gp.tileSize*3;
            g2.drawString(text,x,y);
            if (commandNum == 0){
                g2.drawString(">",x-gp.tileSize,y);
            }
            text = "Thief";
            x = getXForCentered(text);
            y += gp.tileSize;
            g2.drawString(text,x,y);
            if (commandNum == 1){
                g2.drawString(">",x-gp.tileSize,y);
            }
            text = "Sorcerer";
            x = getXForCentered(text);
            y += gp.tileSize;
            g2.drawString(text,x,y);
            if (commandNum == 2){
                g2.drawString(">",x-gp.tileSize,y);
            }
            text = "Back";
            x = getXForCentered(text);
            y += gp.tileSize*2;
            g2.drawString(text,x,y);
            if (commandNum == 3){
                g2.drawString(">",x-gp.tileSize,y);
            }
        }
    }
    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXForCentered(text);
        int y = gp.screenHeight/2;

        g2.drawString(text,x,y);
    }
    public void drawDialogueScreen(){
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;
        drawDialogueWindow(x,y,width,height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));
        x += gp.tileSize;
        y += gp.tileSize;
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }
    public void drawDialogueWindow(int x, int y, int width, int height){
        Color c = new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,35,35);
        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,width-10,height-10,25,25);
    }

    public void drawBattleScreen() {
        int x;
        int y;
        int playerX;
        int playerY;
        int enemyX;
        int enemyY;
        // Fondo
        g2.setColor(new Color(0, 0, 0, 245)); // Fondo semi-transparente negro
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Marco del área de batalla
        g2.setColor(new Color(255, 255, 255, 220));
        g2.drawRoundRect(gp.tileSize / 2, gp.tileSize / 2, gp.screenWidth - gp.tileSize, gp.screenHeight - gp.tileSize, 35, 35);

        // Player Image
        playerX = gp.screenWidth / 5 - gp.tileSize / 2;
        playerY = gp.screenHeight / 2 - gp.tileSize * 2 ;
        g2.drawImage(gp.player.right1, playerX, playerY, gp.tileSize * 2, gp.tileSize * 2, null);

//        System.out.println("Current Enemy Index: " + gp.currentEnemyIndex);

        if (!gp.monster[10].blueSlimeDerrotado){
            enemyX = (gp.screenWidth - gp.screenWidth / 5) - gp.tileSize / 2;
            enemyY = gp.screenHeight / 2 - gp.tileSize * 2;

            BufferedImage image = null;
            try {
                image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/monster/spr_Blue_slime_idle_0.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (image != null) {
                g2.drawImage(image, enemyX, enemyY, gp.tileSize * 2, gp.tileSize * 2, null);
            }
        }
        // Enemy Image
        if (gp.monster[11].blueSlimeDerrotado) {
            enemyX = (gp.screenWidth - gp.screenWidth / 5) - gp.tileSize / 2;
            enemyY = gp.screenHeight / 2 - gp.tileSize * 2;

            BufferedImage image = null;
            try {
                image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/monster/spr_goblin_idle_0.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (image != null) {
                g2.drawImage(image, enemyX, enemyY, gp.tileSize * 2, gp.tileSize * 2, null);
            }

        }else if (gp.monster[12].blueSlimeDerrotado && gp.monster[12].goblinDerrotado) {
            enemyX = (gp.screenWidth - gp.screenWidth / 5) - gp.tileSize / 2;
            enemyY = gp.screenHeight / 2 - gp.tileSize * 2;

            BufferedImage image = null;
            try {
                image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/monster/boy_red_left_1.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (image != null) {
                g2.drawImage(image, enemyX, enemyY, gp.tileSize * 2, gp.tileSize * 2, null);
            }

        }



        // Menú
        drawBattleMenu();
    }

    public void drawBattleMenu() {
        String text;
        int x, y;

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));

        // ATTACK
        text = "ATTACK";
        x = getXForBattleMenu(text, gp.screenWidth / 4);
        y = gp.screenHeight * 6 / 8;
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        // MAGIC
        text = "MAGIC";
        x = getXForBattleMenu(text, gp.screenWidth / 4);
        y = gp.screenHeight * 7 / 8;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        // OBJECTS
        text = "OBJECTS";
        x = getXForBattleMenu(text, gp.screenWidth * 3 / 4);
        y = gp.screenHeight * 6 / 8;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        // ESCAPE
        text = "ESCAPE";
        x = getXForBattleMenu(text, gp.screenWidth * 3 / 4);
        y = gp.screenHeight * 7 / 8;
        g2.drawString(text, x, y);
        if (commandNum == 3) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }
    public void drawDeathScreen(){
        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 100F));
        String text = "HAS MUERTO";
        int x = getXForCentered(text);
        int y = gp.tileSize * 3;
        //Shadow
        g2.setColor(Color.gray);
        g2.drawString(text, x + 5, y + 5);
        //Main color
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        //Player Image
        x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
        y += gp.tileSize * 2;
        g2.drawImage(gp.player.up1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
        //Menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        text = "CONTINUE";
        x = getXForCentered(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }
        text = "MAIN MENU";
        x = getXForCentered(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }
        text = "QUIT";
        x = getXForCentered(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }

    }

    public void drawCharacterScreen(){
        //Create a Frame
        final int frameX = gp.tileSize;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize * 5;
        final int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        //Text
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 35;

        //Names
        g2.drawString("Level", textX,textY);
        textY += lineHeight;
        g2.drawString("Life", textX,textY);
        textY += lineHeight;
        g2.drawString("Strength", textX,textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX,textY);
        textY += lineHeight;
        g2.drawString("Attack", textX,textY);
        textY += lineHeight;
        g2.drawString("Defense", textX,textY);
        textY += lineHeight;
        g2.drawString("Exp", textX,textY);
        textY += lineHeight;
        g2.drawString("Next Level", textX,textY);
        textY += lineHeight + 20;
        g2.drawString("Weapon", textX,textY);
        textY += lineHeight + 15;
        g2.drawString("Shield", textX,textY);
        textY += lineHeight;

        //Values
        int tailX = (frameX + frameWidth) - 30;
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX,textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX,textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.strength);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX,textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.dexterity);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX,textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.attack);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX,textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.defense);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX,textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.exp);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX,textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX,textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.down1,tailX - gp.tileSize, textY - 14, null );
        textY += gp.tileSize;
        g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 14,null);

    }
    public void drawInventory(){
        int frameX = gp.tileSize * 9;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 6;
        int frameHeight = gp.tileSize * 5;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize + 3;
        for (int i = 0; i < gp.player.inventory.size(); i++) {
            g2.drawImage(gp.player.inventory.get(i).down1,slotX,slotY,null);

            slotX += slotSize;
            if (i == 4 || i == 9 || i == 14){
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        int cursorX = slotXstart + (slotSize * slotCol);
        int cursorY = slotYstart + (slotSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX,cursorY,cursorWidth,cursorHeight,10,10);

    }
    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,35,35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,width-10,height-10,25,25);
    }
    public int getXForCentered(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth/2 - length/2;
    }
    public int getXForAlignToRightText(String text, int tailX){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return tailX - length;
    }
    public int getXForBattleMenu(String text, int position){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return position - length/2;
    }

    public void selectSlot(int index) {
        this.selectedSlot = index;
        gp.repaint();
    }
}
