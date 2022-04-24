package com.tom.maze;

import java.util.Random;

public class AldousBroderMazeBuilder implements MazeBuilder {
    Grid grid;
    private Random rand = new Random();

    public AldousBroderMazeBuilder() {
        this.grid = new Grid(10, 10);
    }

    public AldousBroderMazeBuilder(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void buildMaze() {
        int unvisitedCount = grid.getSize() - 1;
        Cell currentCell = grid.getRandomCell();

        while (unvisitedCount > 0) {
            Cell neighbour = currentCell.neighbours().get(rand.nextInt(currentCell.neighbours().size()));
            if (neighbour.hasLinkedCells().isEmpty()) {
                currentCell.linkTo(neighbour);
                currentCell = neighbour;

                unvisitedCount--;
            } else {
                currentCell = neighbour;
            }
        }
    }
}
