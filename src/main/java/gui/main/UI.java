package gui.main;

import gui.object.OBJ_Key;
import utils.DIContainer;

import java.awt.*;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    private final int slotCount = 10;
    private int selectedSlot = 0;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    private GamePanel gamePanel;
    private KeyHandler keyHandler;
    private DIContainer container;

    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN,40);
        arial_80B = new Font("Arial",Font.BOLD,80);
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

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        if (gp.gameState == gp.playState){
            //Do playState stuff later
        }
        if (gp.gameState == gp.pauseState){
            drawPauseScreen();
        }

        if (gp.gameState != gp.pauseState){
            drawInventory();
        }

    }

    private void drawInventory() {
        int slotWidth = 50; // Width of each slot
        int slotHeight = 50; // Height of each slot
        int spacing = 10; // Spacing between slots
        int startY = gp.screenHeight - slotHeight - 20; // Start drawing from the bottom
        int totalWidth = (slotWidth * slotCount) + (spacing * (slotCount - 1));
        int startX = (gp.screenWidth - totalWidth) / 2; // Center the inventory bar

        g2.setColor(Color.WHITE); // Slot background color
        for (int i = 0; i < slotCount; i++) {
            g2.fillRect(startX + (i * (slotWidth + spacing)), startY, slotWidth, slotHeight);
            g2.setColor(Color.GRAY);
            g2.drawRect(startX + (i * (slotWidth + spacing)), startY, slotWidth, slotHeight);
        }
    }

    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXForCentered(text);
        int y = gp.screenHeight/2;

        g2.drawString(text,x,y);
    }

    public int getXForCentered(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth/2 - length/2;
    }

    public void selectSlot(int index) {
        this.selectedSlot = index;
        gp.repaint();
    }
}
