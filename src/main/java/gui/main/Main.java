package gui.main;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Crear una instancia de JFrame
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Juego 2D");
//        ImageIcon icon = new ImageIcon("/logo.png");
//        window.setIconImage(icon.getImage());d

        GamePanel gamePanel = new GamePanel();

        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
