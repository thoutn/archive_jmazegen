package com.tom.maze2;

import java.util.ArrayList;
import java.util.Random;


public class Grid {
    final int width;
    final int height;

    ArrayList<ArrayList<Cell>> cells;

    private Random rand = new Random();

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;

        this.cells = new ArrayList<>(height);

        prepareGrid();
        configureGrid();
    }

    private void prepareGrid() {
        for (int row_ = 0; row_ < height; row_++) {
            cells.add(new ArrayList<>(width));
            for (int col_ = 0; col_ < width; col_++) {
                cells.get(row_).add(new Cell(row_, col_));
            }
        }
    }

    private void configureGrid() {
        for (ArrayList<Cell> row : cells) {
            for (Cell cell : row) {
                int row_ = cell.row;
                int col_ = cell.column;

                cell.top = createNeighbours(row_ - 1, col_);
                cell.bottom = createNeighbours(row_ + 1, col_);
                cell.right = createNeighbours(row_, col_ + 1);
                cell.left = createNeighbours(row_, col_ - 1);
            }
        }
    }

    private Cell createNeighbours(int row, int col) {
        if ((0 <= row && row <= width - 1) && (0 <= col && col <= height - 1)) {
            return cells.get(row).get(col);
        } else {
            return null;
        }
    }

    public Cell getRandomCell() {
        int row_ = rand.nextInt(cells.size());
        int col_ = rand.nextInt(cells.get(row_).size());
        return cells.get(row_).get(col_);
    }

    public int getSize() {
        return width * height;
    }
}
