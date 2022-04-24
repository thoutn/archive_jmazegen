package com.tom.maze;

import java.util.ArrayList;
import java.util.Random;

public class OldSidewinderMazeBuilder implements MazeBuilder {
    Grid grid;

    public OldSidewinderMazeBuilder() {
        this.grid = new Grid(10, 10);
    }

    public OldSidewinderMazeBuilder(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void buildMaze() {
        for (ArrayList<Cell> row : grid.cells) {
            ArrayList<Cell> run = new ArrayList<>();

            for (Cell cell : row) {
                run.add(cell);

                Random rand = new Random();
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
