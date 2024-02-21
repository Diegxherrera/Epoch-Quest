package gui.main;

import utils.DIContainer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private DIContainer container;
    private UI keyHandlerUi;
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    //Debug
    boolean checkDrawTime = false;
    int index = 0;

    public KeyHandler(DIContainer container) {
        this.container = container;
        if (container != null) {
            this.keyHandlerUi = container.getUI();
        }
    }

    public KeyHandler(GamePanel gp) {
        this((DIContainer) null);
        this.gp=gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W:
                upPressed = true;
                break;
            case KeyEvent.VK_S:
                downPressed = true;
                break;
            case KeyEvent.VK_A:
                leftPressed = true;
                break;
            case KeyEvent.VK_D:
                rightPressed = true;
                break;
            case KeyEvent.VK_0:
                index = 9;
                break;
            case KeyEvent.VK_1:
                index = 0;
                break;
            case KeyEvent.VK_2:
                index = 1;
                break;
            case KeyEvent.VK_3:
                index = 2;
                break;
            case KeyEvent.VK_4:
                index = 3;
                break;
            case KeyEvent.VK_5:
                index = 4;
                break;
            case KeyEvent.VK_6:
                index = 5;
                break;
            case KeyEvent.VK_7:
                index = 6;
                break;
            case KeyEvent.VK_8:
                index = 7;
                break;
            case KeyEvent.VK_9:
                index = 8;
                break;

            default:
                break;
        }

        if (index != -1) {
            keyHandlerUi.selectSlot(index);
        }

        if (code == KeyEvent.VK_A){
            leftPressed = true;
        }

        if (code == KeyEvent.VK_D){
            rightPressed= true;
        }

        if (code == KeyEvent.VK_ESCAPE){
            if (gp.gameState == gp.playState){
                gp.gameState = gp.pauseState;
            } else if (gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
            }
        }
        //Debug
        if (code == KeyEvent.VK_T){
            if (!checkDrawTime){
                checkDrawTime = true;
            } else if (checkDrawTime) {
                checkDrawTime = false;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code= e.getKeyCode();

        if (code == KeyEvent.VK_W){
            upPressed = false;
        }
        if (code == KeyEvent.VK_S){
            downPressed = false;
        }
        if (code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D){
            rightPressed= false;
        }
    }

    public void setDependencies(DIContainer container) {
        this.container = container;
    }
}
