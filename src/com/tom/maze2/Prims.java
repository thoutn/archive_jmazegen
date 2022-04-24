package com.tom.maze2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Prims implements MazeBuilder {
    Grid grid;
    private HashSet<Cell> frontierCells = new HashSet<>();
    private ArrayList<Cell> inCells = new ArrayList<>();
    private Random rand = new Random();

    public Prims() {
        this.grid = new Grid(10, 10);
    }

    public Prims(Grid grid) {
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
            Cell currentCell = frontiers.get(rand.nextInt(frontiers.size()));
            frontierCells.remove(currentCell);

            for (Cell neighbours : currentCell.neighbours()) {
                if (neighbours.hasLinkedCells().isEmpty()) {
                    frontierCells.add(neighbours);
                } else {
                    inCells.add(neighbours);
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
