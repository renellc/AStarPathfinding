package views;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {
    private int cellSize;

    public GridPanel(int cellSize) {
        this.cellSize = cellSize;
        setBackground(Color.white);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g.create();
        for (int y = 0; y < getHeight(); y += cellSize) {
            for (int x = 0; x < getWidth(); x += cellSize) {
                g2D.setColor(Color.black);
                g2D.fillRect(x, y, cellSize, cellSize);
                g2D.setColor(Color.white);
                g2D.fillRect(x, y, cellSize - 1, cellSize - 1);
            }
        }
    }
}
