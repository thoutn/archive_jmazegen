package com.tom.maze;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImagePresenter {
    Grid grid;
    private final int cellSize;
    private final int wallThickness;
    private final int size;

    private final String name;

    private final int w;
    private final int h;
    private BufferedImage bimage;

    public ImagePresenter(Grid grid) {
        this(grid, "Maze");
    }

    public ImagePresenter(Grid grid, String name) {
        this(grid, name, 20, 2);
    }

    public ImagePresenter(Grid grid, String name, int cellSize, int wallThickness) {
        this.grid = grid;
        this.cellSize = cellSize;
        this.wallThickness = wallThickness;
        this.size = cellSize + wallThickness;

        this.name = name;

        w = setSize(grid.width);
        h = setSize(grid.height);
    }

    private int setSize(int size) {
        return size * cellSize + (size + 1) * wallThickness;
    }

    private void buildImg() {
        bimage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D img = bimage.createGraphics();

        img.setColor(Color.LIGHT_GRAY);
        img.fillRect(0, 0, w, h);
        img.setColor(Color.BLACK);
        img.setStroke(new BasicStroke(wallThickness));

        for (ArrayList<Cell> row : grid.cells) {
            for (Cell cell : row) {
                int x1 = cell.column * size;
                int y1 = cell.row * size;
                int x2 = (cell.column + 1) * size;
                int y2 = (cell.row + 1) * size;

                int offset1 = wallThickness / 2;
                int offset2 = 1;

                if (cell.top == null) {
                    img.drawLine(x1, y1 + offset1, x2, y1 + offset1);
                }
                if (cell.left == null) {
                    img.drawLine(x1 + offset1, y1, x1 + offset1, y2 + offset1);
                }

                if (!cell.isLinkedTo(cell.bottom)) {
                    img.drawLine(x1 + offset1, y2 + offset1, x2 + offset1, y2 + offset1);
                }
                if (!cell.isLinkedTo(cell.right)) {
                    img.drawLine(x2 + offset1, y1 + offset1, x2 + offset1, y2 + offset1);
                }
            }
        }
    }

    private static class RenderPanel extends JPanel {
        BufferedImage img;

        public RenderPanel(BufferedImage image) {
            this.img = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
        }
    }

    public void render() {
        buildImg();
        JFrame f = new JFrame(name);
        RenderPanel p = new RenderPanel(bimage);

        f.add(p);
        f.pack();
        f.setSize(w, h);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public void saveImage(String fileName) {
        buildImg();
        try {
            ImageIO.write(bimage, "PNG", new File("./" + fileName));
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
}
