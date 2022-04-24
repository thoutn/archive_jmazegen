package com.tom.maze;

import java.util.ArrayList;
import java.util.Random;

public class PrimsMazeBuilder implements MazeBuilder {
    Grid grid;
    private ArrayList<Cell> frontierCells = new ArrayList<>();
    private ArrayList<Cell> inCells = new ArrayList<>();
    private Random rand = new Random();

    public PrimsMazeBuilder() {
        this.grid = new Grid(10, 10);
    }

    public PrimsMazeBuilder(Grid grid) {
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
            Cell currentCell = frontierCells.remove(rand.nextInt(frontierCells.size()));

            for (Cell neighbour : currentCell.neighbours()) {
                if (neighbour.hasLinkedCells().isEmpty()) {
                    if (!frontierCells.contains(neighbour)) {
                        frontierCells.add(neighbour);
                    }
                } else {
                    inCells.add(neighbour);
                }
            }

            if (!inCells.isEmpty()) {
                Cell inCell = inCells.get(rand.nextInt(inCells.size()));
                inCell.linkTo(currentCell);
                inCells.clear();
            } else {
                currentCell.linkTo(currentCell);
            }
        }
    }
}
