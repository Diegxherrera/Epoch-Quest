package gui.main;

import gui.entity.Entity;
import gui.entity.Player;
import gui.monster.MON_GreenSlime;
import gui.tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable{
    //Screen settings
    final int originalTileSize = 16; // 16x16 tiles size
    final int scale = 3;

    public int tileSize = originalTileSize * scale; //48x48 tiles size
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //World Settings
    public final int maxWorldCol = 110;
    public final int maxWorldRow = 110;

    //FPS
    int FPS = 60;
    //System
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker =new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;

    //Entity and object
    public Player player = new Player(this, keyH);
    public Entity[] obj = new Entity[10];
    public Entity[] npc = new Entity[10];
    public Entity[] monster = new Entity[20];
    ArrayList<Entity> entityList = new ArrayList<>();
    BufferedImage[] monsterImages;
    int currentEnemyIndex = 999; // Inicializado a -1 para indicar que no hay enemigo actual




    //Game State
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int battleState = 4;
    public final int deadState = 5;
    public final int characterState = 6;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void setupGame() {
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setBlueSlime();

        String[] monsterImagePaths = {
                "/monster/spr_Blue_slime_idle_0.png",
                "/monster/spr_goblin_idle_0.png",
                // Otras rutas de imágenes según sea necesario
        };
        int monsterImageWidth = tileSize;
        int monsterImageHeight = tileSize;
        monsterImages = new BufferedImage[monsterImagePaths.length];
        for (int i = 0; i < monsterImagePaths.length; i++) {
            monsterImages[i] = getMonsterImage(i, monsterImagePaths[i], monsterImageWidth, monsterImageHeight);
        }


        // playMusic(0);
        gameState = titleState;
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                // Update
                update();

                // Draw
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        if (gameState == playState) {
            // Player
            player.update();
            // NPC
            for (Entity entity : npc) {
                if (entity != null) {
                    entity.update();
                }
            }
            // Monstruos
            for (Entity entity : monster) {
                if (entity != null) {
                    entity.update();
                }
            }
        }
        if (gameState == pauseState){
            //nothing for now
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        //Debug
        long drawStart = 0;
        if (keyH.checkDrawTime) {
            drawStart = System.nanoTime();
        }
        //Title Screen
        if (gameState == titleState){
            ui.draw(g2);
        }
        //Battle Screen
        else if (gameState == battleState){
            ui.draw(g2);
            //Implementar metodo para la logica de combate que funcione con la pantalla de batalla y que use los command num para determinar el comando
            //1 es para ataque normal, 2 es para acceder al inventario (añadir logica de los objetos de la interfaz me puedo encargar yo)
            //3 es para un ataque magico (se puede hacer que aparezca un menu pequeño en medio de la pantalla para elegirlo)
            //4 hace que escapes del combate que ya esta medianamente implementado
        }
        else if (gameState == deadState) {
            ui.draw(g2);
        }
        else{
            //Tiles
            tileM.draw(g2);

            //Add entities to the list
            entityList.add(player);

            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null){
                    entityList.add(npc[i]);
                }
            }
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null){
                    entityList.add(obj[i]);
                }
            }
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null){
                    entityList.add(monster[i]);
                }
            }
            //Sort
            entityList.sort((e1, e2) -> {
                int result = Integer.compare(e1.worldY, e2.worldY);
                return result;
            });
            //Draw Entities
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }
            //Empty entity list
            entityList.clear();
            //UI
            ui.draw(g2);
        }

        //Debug
        if (keyH.checkDrawTime) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }

        g2.dispose();
    }

    public BufferedImage getMonsterImage(int index, String imagePath, int width, int height) {
        if (monster[index] != null) {
            return monster[index].getImage(imagePath, width, height);
        }
        return null;
    }

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public void playSE(int i){
        se.setFile(i);
        se.play();
    }
}
