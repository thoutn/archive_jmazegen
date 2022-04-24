package com.tom.maze;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class RecursiveDivisionMazeBuilder implements MazeBuilder {
    Grid grid;
    private Random rand = new Random();

    public RecursiveDivisionMazeBuilder() {
        this(new Grid(10, 10));
    }

    public RecursiveDivisionMazeBuilder(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    private enum Orientation {
        HORIZONTAL,
        VERTICAL;
    }

    private Orientation chooseOrientation(int width, int height) {
        if (width < height) {
            return Orientation.HORIZONTAL;
        } else if (width > height) {
            return Orientation.VERTICAL;
        } else {
            int rand = new Random().nextInt(0,2);
            if (rand == 0) {
                return Orientation.HORIZONTAL;
            } else {
                return Orientation.VERTICAL;
            }
        }
    }

    @Override
    public void buildMaze() {
        int x1 = 0;
        int y1 = 0;
        int x2 = grid.width - 1;
        int y2 = grid.height - 1;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {x1, y1, x2, y2});

        while (!queue.isEmpty()) {
            int[] unpack = queue.poll();
            x1 = unpack[0];
            y1 = unpack[1];
            x2 = unpack[2];
            y2 = unpack[3];

            if (chooseOrientation(x2 - x1, y2 - y1) == Orientation.HORIZONTAL) {
                int bisectY;
                if (y2 - y1 + 1 > 2) {
                    bisectY = rand.nextInt(y1, y2);
                } else {
                    bisectY = y1;
                }
                int col;
                if (x2 - x1 + 1 > 2) {
                    col = rand.nextInt(x1, x2 + 1);
                } else {
                    col = x1;
                }

                Cell cell = grid.cells.get(bisectY).get(col);
                if (cell.bottom != null) {
                    cell.linkTo(cell.bottom);
                }

                if (x2 - x1 + 1 >= 2 || bisectY - y1 > 0) {
                    queue.add(new int[] {x1, y1, x2, bisectY});
                }
                if (x2 - x1 + 1 >= 2 || y2 - bisectY > 1) {
                    queue.add(new int[] {x1, bisectY + 1, x2, y2});
                }
            } else {
                int bisectX;
                if (x2 - x1 + 1 > 2) {
                    bisectX = rand.nextInt(x1, x2);
                } else {
                    bisectX = x1;
                }
                int row;
                if (y2 - y1 + 1 > 2) {
                    row = rand.nextInt(y1, y2 + 1);
                } else {
                    row = y1;
                }

                Cell cell = grid.cells.get(row).get(bisectX);
                if (cell.right != null) {
                    cell.linkTo(cell.right);
                }

                if (y2 - y1 + 1 >= 2 || bisectX - x1 > 0) {
                    queue.add(new int[] {x1, y1, bisectX, y2});
                }
                if (y2 - y1 + 1 >=2 || x2 - bisectX > 1) {
                    queue.add(new int[] {bisectX + 1, y1, x2, y2});
                }
            }
        }
    }
}
