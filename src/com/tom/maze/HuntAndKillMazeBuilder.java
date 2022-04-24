package com.tom.maze;

import java.util.*;

public class HuntAndKillMazeBuilder implements MazeBuilder {
    Grid grid;
    private Random rand = new Random();

    public HuntAndKillMazeBuilder() {
        this(new Grid(10, 10));
    }

    public HuntAndKillMazeBuilder(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    private Cell chooseNeighbourOf(Cell cell, List<Cell> neighbours) {
        Cell neighbour = neighbours.get(rand.nextInt(neighbours.size()));
        cell.linkTo(neighbour);
        return neighbour;
    }

    @Override
    public void buildMaze() {
        Cell currentCell = grid.getRandomCell();
        List<Integer> rowsWithUnvisited = new ArrayList<>();
        List<Cell> neighbours = new ArrayList<>();

        for (int i = 0; i < grid.height; i++) {
            rowsWithUnvisited.add(i);
        }

        while (true) {
            for (Cell n : currentCell.neighbours()) {
                if (n.hasLinkedCells().isEmpty()) {
                    neighbours.add(n);
                }
            }

            if (!neighbours.isEmpty()) {
                currentCell = chooseNeighbourOf(currentCell, neighbours);
                neighbours.clear();
            } else {
                boolean isUnvisitedFound = false;

                search:
                for (int row : new ArrayList<>(rowsWithUnvisited)) {
                    for (Cell cell : grid.cells.get(row)) {
                        if (cell.hasLinkedCells().isEmpty()) {
                            for (Cell n : cell.neighbours()) {
                                if (!n.hasLinkedCells().isEmpty()) {
                                    neighbours.add(n);
                                }
                            }

                            if (!neighbours.isEmpty()) {
                                currentCell = chooseNeighbourOf(currentCell, neighbours);
                                isUnvisitedFound = true;
                                break search;
                            }
                        }
                    }
                    rowsWithUnvisited.remove((Integer) row);
                }

                if (!isUnvisitedFound) {
                    break;
                }
            }
        }

    }
}
