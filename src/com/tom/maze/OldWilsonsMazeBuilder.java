package com.tom.maze;

import java.util.HashMap;
import java.util.Random;

public class OldWilsonsMazeBuilder implements MazeBuilder {
    Grid grid;
    private Random rand = new Random();

    public OldWilsonsMazeBuilder() {
        this(new Grid(10, 10));
    }

    public OldWilsonsMazeBuilder(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    private HashMap<Cell, Cell> doRandomWalk(Cell currentCell) {
        HashMap<Cell, Cell> walkedPath = new HashMap<>();

        while (true) {
            Cell neighbour = currentCell.neighbours().get(rand.nextInt(currentCell.neighbours().size()));
            walkedPath.put(currentCell, neighbour);
            if (neighbour.hasLinkedCells().isEmpty()) {
                currentCell = neighbour;
            } else {
                return walkedPath;
            }
        }
    }

    @Override
    public void buildMaze() {
        Cell cell = grid.getRandomCell();
        cell.linkTo(cell);
        int unvisitedCount = grid.getSize() - 1;

        while (unvisitedCount > 0) {
            Cell startCell;
            do {
                startCell = grid.getRandomCell();
            } while (!startCell.hasLinkedCells().isEmpty());

            HashMap<Cell, Cell> path = doRandomWalk(startCell);

            Cell currentCell = startCell;
            while (!path.isEmpty()) {
                unvisitedCount--;
                if (path.get(currentCell).hasLinkedCells().isEmpty()) {
                    currentCell.linkTo(path.get(currentCell));
                    currentCell = path.get(currentCell);
                } else {
                    currentCell.linkTo(path.get(currentCell));
                    break;
                }
            }
        }
    }
}
