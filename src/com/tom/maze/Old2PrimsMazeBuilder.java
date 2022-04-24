package com.tom.maze;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Old2PrimsMazeBuilder implements MazeBuilder {
    Grid grid;
    private HashSet<Cell> frontierCells = new HashSet<>();
    private HashSet<Cell> neighbours = new HashSet<>();
    private ArrayList<Cell> inCells = new ArrayList<>();
    private Random rand = new Random();

    public Old2PrimsMazeBuilder() {
        this.grid = new Grid(10, 10);
    }

    public Old2PrimsMazeBuilder(Grid grid) {
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

            for (Cell cell : currentCell.neighbours()) {
                if (cell.hasLinkedCells().isEmpty()) {
                    neighbours.add(cell);
                } else {
                    inCells.add(cell);
                }
            }
            frontierCells.addAll(neighbours);
            neighbours.clear();

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
