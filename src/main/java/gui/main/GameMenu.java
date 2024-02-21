package gui.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameMenu extends JFrame {

    private final int slotCount = 10; // Number of slots in the menu
    private final GamePanels gamePanels; // The panel where the menu will be drawn

    public GameMenu() {
        setTitle("Game Menu Example");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        gamePanels = new GamePanels();
        add(gamePanels);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            GameMenu frame = new GameMenu();
            frame.setVisible(true);
        });
    }

    private class GamePanels extends JPanel {
        public GamePanels() {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    // Determine which slot was clicked based on mouse position
                    int slotWidth = getWidth() / slotCount;
                    int clickedSlot = e.getX() / slotWidth;
                    JOptionPane.showMessageDialog(null, "Clicked Slot: " + clickedSlot);
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Draw the slots
            int slotWidth = getWidth() / slotCount;
            for (int i = 0; i < slotCount; i++) {
                // Customize this to make it look more like Minecraft slots
                g.drawRect(i * slotWidth, getHeight() / 2 - 25, slotWidth, 50);
            }
        }
    }
}
