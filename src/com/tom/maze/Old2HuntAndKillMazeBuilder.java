package com.tom.maze;

import java.util.*;

public class Old2HuntAndKillMazeBuilder implements MazeBuilder {
    Grid grid;
    private Random rand = new Random();

    public Old2HuntAndKillMazeBuilder() {
        this(new Grid(10, 10));
    }

    public Old2HuntAndKillMazeBuilder(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    private Cell chooseNeighbourOf(Cell cell, Set<Cell> neighbours) {
        Cell neighbour = new ArrayList<>(neighbours).get(rand.nextInt(neighbours.size()));
        cell.linkTo(neighbour);
        return neighbour;
    }

    @Override
    public void buildMaze() {
        Cell currentCell = grid.getRandomCell();
        List<Integer> rowsWithUnvisited = new ArrayList<>();
        Set<Cell> neighbours = new HashSet<>();

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
