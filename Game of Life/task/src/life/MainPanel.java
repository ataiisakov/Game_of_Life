package life;

import javax.swing.*;
import java.awt.*;

class MainPanel extends JPanel {

    private int cellSize;
    private Grid grid;
    private int gridSize;
    private boolean[][] currGen;

    public MainPanel(int cellSize, Grid grid) {
        this.cellSize = cellSize;
        this.grid = grid;
        this.gridSize = grid.getSize();
        currGen = grid.getCurrGen();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                boolean state = currGen[i][j];
                if (state) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.LIGHT_GRAY);
                }
                g.fillRect(i * cellSize, j * cellSize, cellSize - 2, cellSize - 2);

            }
        }

    }

}
