package com.tom.maze;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class OldPrimsMazeBuilder implements MazeBuilder {
    Grid grid;
    HashSet<Cell> frontierCells = new HashSet<>();

    public OldPrimsMazeBuilder() {
        this.grid = new Grid(10, 10);
    }

    public OldPrimsMazeBuilder(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void buildMaze() {
        frontierCells.add(grid.getRandomCell());

        while (!frontierCells.isEmpty()) {
            ArrayList<Cell> frontiers = new ArrayList<>(frontierCells);
            Cell currentCell = frontiers.get(new Random().nextInt(frontiers.size()));
            frontierCells.remove(currentCell);

            HashSet<Cell> neighbours = new HashSet<>();
            ArrayList<Cell> inCells = new ArrayList<>();
            for (Cell cell : currentCell.neighbours()) {
                if (cell.hasLinkedCells().isEmpty()) {
                    neighbours.add(cell);
                } else {
                    inCells.add(cell);
                }
            }
            frontierCells.addAll(neighbours);

            if (!inCells.isEmpty()) {
                Cell inCell = inCells.get(new Random().nextInt(inCells.size()));
                inCell.linkTo(currentCell);
            } else {
                currentCell.linkTo(currentCell);
            }
        }
    }
}
