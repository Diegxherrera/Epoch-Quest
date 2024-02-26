package gui.main;


import gui.entity.Entity;
import gui.object.OBJ_Heart;
import utils.DIContainer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class UI {
    GamePanel gp;
    Entity entity;
    Graphics2D g2;
    Font maruMonica;
    BufferedImage heart_full,heart_half,heart_blank;
    private final int slotCount = 10;
    private int selectedSlot = 0;

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
            drawPlayerlife();
            drawBattleScreen();
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
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 90F));
            String text = "España Boy Adventure";
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
        g2.setColor(new Color(0, 0, 0, 220)); // Fondo semi-transparente negro
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Marco del área de batalla
        g2.setColor(new Color(255, 255, 255, 220));
        g2.drawRoundRect(gp.tileSize / 2, gp.tileSize / 2, gp.screenWidth - gp.tileSize, gp.screenHeight - gp.tileSize, 35, 35);

        // Player Image
        playerX = gp.screenWidth / 5 - gp.tileSize / 2;
        playerY = gp.screenHeight / 2;
        g2.drawImage(gp.player.right1, playerX, playerY, gp.tileSize * 4 / 5, gp.tileSize * 4 / 5, null);

        // Enemy Image
        enemyX = (gp.screenWidth - gp.screenWidth / 5) - gp.tileSize / 2;
        enemyY = gp.screenHeight * 2 / 3;
        // g2.drawImage(entity.left1, enemyX, enemyY, gp.tileSize * 2, gp.tileSize * 2, null);
        // Se necesita un método para determinar qué monstruo es

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


    public int getXForCentered(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth/2 - length/2;
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
