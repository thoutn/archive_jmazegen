package com.tom.maze;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Old3PrimsMazeBuilder implements MazeBuilder {
    Grid grid;
    private HashSet<Cell> frontierCells = new HashSet<>();
//    private ArrayList<Cell> frontierCells = new ArrayList<>();
    private HashSet<Cell> neighbours = new HashSet<>();
    private ArrayList<Cell> inCells = new ArrayList<>();
    private Random rand = new Random();

    public Old3PrimsMazeBuilder() {
        this.grid = new Grid(10, 10);
    }

    public Old3PrimsMazeBuilder(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void buildMaze() {
        frontierCells.add(grid.getRandomCell());
        ArrayList<Cell> frontiers = new ArrayList<>();

        while (!frontierCells.isEmpty()) {
            frontiers.addAll(frontierCells);
            Cell currentCell = frontiers.get(rand.nextInt(frontiers.size()));
            frontiers.clear();
            frontierCells.remove(currentCell);
//            Cell currentCell = frontierCells.remove(rand.nextInt(frontierCells.size()));

            for (Cell neighbour : currentCell.neighbours()) {
                if (neighbour.hasLinkedCells().isEmpty()) {
                    neighbours.add(neighbour);
//                    frontierCells.add(neighbour);
                } else {
                    inCells.add(neighbour);
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
