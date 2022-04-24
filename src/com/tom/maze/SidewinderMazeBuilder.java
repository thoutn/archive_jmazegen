package com.tom.maze;

import java.util.ArrayList;
import java.util.Random;

public class SidewinderMazeBuilder implements MazeBuilder {
    Grid grid;
    private ArrayList<Cell> run = new ArrayList<>();
    private Random rand = new Random();

    public SidewinderMazeBuilder() {
        this.grid = new Grid(10, 10);
    }

    public SidewinderMazeBuilder(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void buildMaze() {
        for (ArrayList<Cell> row : grid.cells) {
            for (Cell cell : row) {
                run.add(cell);

                boolean isPlaceToCloseRun = (cell.right == null) || (cell.top != null) && (rand.nextInt(2) == 0);

                if (isPlaceToCloseRun) {
                    Cell cell_ = run.get(rand.nextInt(run.size()));
                    if (cell_.top != null) {
                        cell_.linkTo(cell_.top);
                        run.clear();
                    }
                } else {
                    cell.linkTo(cell.right);
                }
            }
        }
    }
}
