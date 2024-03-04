package gui.main;

import gui.entity.Entity;
import gui.entity.Player;
import utils.DIContainer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private DIContainer container;
    private UI keyHandlerUi;
    GamePanel gp;
    Entity entity;
    Player player;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
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
        this.player = new Player(gp,this);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        //TEST
        if (code == KeyEvent.VK_U){
            gp.player.decreaseLife(1);
            System.out.println("Vida: " + gp.player.life);
            System.out.println("Holaaaaaaa");
        }
        //Dead State
        if (gp.gameState == gp.deadState){
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    //Añadir sistema de guardado para cargar la partida desde el ultimo punto de guardado
                    gp.player.life += gp.player.maxLife/2;
                    gp.gameState = gp.playState;
                }
                if (gp.ui.commandNum == 1) {
                    gp.player.life = gp.player.maxLife;
                    gp.gameState = gp.ui.titleScreenState = 0;
                    gp.stopMusic();
                }
                if (gp.ui.commandNum == 2) {
                    System.exit(0);
                }
            }
        }
        //Title State
        if (gp.gameState == gp.titleState){
            if (gp.ui.titleScreenState == 0) {
                if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 2;
                    }
                }
                if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 2) {
                        gp.ui.commandNum = 0;
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    if (gp.ui.commandNum == 0) {
                        gp.ui.titleScreenState = 1;
                    }
                    if (gp.ui.commandNum == 1) {
                        //Add later
                    }
                    if (gp.ui.commandNum == 2) {
                        System.exit(0);
                    }
                }
            }
            else if (gp.ui.titleScreenState == 1) {
                if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 3;
                    }
                }
                if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 3) {
                        gp.ui.commandNum = 0;
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    if (gp.ui.commandNum == 0) {   //Picking a fighter
                        gp.gameState = gp.playState;
//                        gp.playMusic(0);
                    }
                    if (gp.ui.commandNum == 1) {  //Picking a thief
                        gp.gameState = gp.playState;
//                        gp.playMusic(0);
                    }
                    if (gp.ui.commandNum == 2){  //Picking a sorcerer
                        gp.gameState = gp.playState;
//                        gp.playMusic(0);
                    }
                    if (gp.ui.commandNum == 3) {
                        gp.ui.titleScreenState =0;
                    }
                }
            }
        }
        if (gp.gameState == gp.battleState) {
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.ui.commandNum = (gp.ui.commandNum - 1 + 4) % 4;
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum = (gp.ui.commandNum + 1) % 4;
            }
            if (code == KeyEvent.VK_ENTER) {
                switch (gp.ui.commandNum) {
                    case 0:
                        // Sistema para atacar
                        break;
                    case 1:
                        // Sistema para acceder al menú y elegir objetos desde allí
                        break;
                    case 2:
                        // Sistema para elegir la magia que quieras hacer
                        break;
                    case 3:
                        gp.gameState = gp.playState;
                            entity.invincible = true;
                            entity.actionLockCounter++;
                            if (entity.actionLockCounter == 120) {
                                entity.invincible = false;
                        }
                        break;
                }
            }
        }
        if (gp.gameState == gp.playState) {
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
//                case KeyEvent.VK_0:
//                    index = 9;
//                    break;
//                case KeyEvent.VK_1:
//                    index = 0;
//                    break;
//                case KeyEvent.VK_2:
//                    index = 1;
//                    break;
//                case KeyEvent.VK_3:
//                    index = 2;
//                    break;
//                case KeyEvent.VK_4:
//                    index = 3;
//                    break;
//                case KeyEvent.VK_5:
//                    index = 4;
//                    break;
//                case KeyEvent.VK_6:
//                    index = 5;
//                    break;
//                case KeyEvent.VK_7:
//                    index = 6;
//                    break;
//                case KeyEvent.VK_8:
//                    index = 7;
//                    break;
//                case KeyEvent.VK_9:
//                    index = 8;
//                    break;
                case KeyEvent.VK_ENTER:
                    enterPressed = true;
                    break;
            }

//            if (index != -1) {
//                keyHandlerUi.selectSlot(index);
//            }
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.pauseState;
            }

            //Debug
            if (code == KeyEvent.VK_T) {
                checkDrawTime = !checkDrawTime;

            }
        }
        //Pause State
       else if (gp.gameState == gp.pauseState){
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.playState;
            }
        }
        //Dialogue State
       else if (gp.gameState == gp.dialogueState){
            if (code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
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
