package com.tom.maze;

import java.util.*;

public class OldHuntAndKillMazeBuilder implements MazeBuilder {
    Grid grid;

    public OldHuntAndKillMazeBuilder() {
        this(new Grid(10, 10));
    }

    public OldHuntAndKillMazeBuilder(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    private Cell chooseNeighbourOf(Cell cell, Set<Cell> neighbours) {
        Cell neighbour = new ArrayList<>(neighbours).get(new Random().nextInt(neighbours.size()));
        cell.linkTo(neighbour);
        return neighbour;
    }

    @Override
    public void buildMaze() {
        Cell currentCell = grid.getRandomCell();
        List<Integer> rowsWithUnvisited = new ArrayList<>();

        for (int i = 0; i < grid.height; i++) {
            rowsWithUnvisited.add(i);
        }

        while (true) {
            Set<Cell> neighbours = new HashSet<>();
            for (Cell n : currentCell.neighbours()) {
                if (n.hasLinkedCells().isEmpty()) {
                    neighbours.add(n);
                }
            }

            if (!neighbours.isEmpty()) {
                currentCell = chooseNeighbourOf(currentCell, neighbours);
            } else {
                boolean isUnvisitedFound = false;

                search:
                for (int row : new ArrayList<>(rowsWithUnvisited)) {
                    for (Cell cell : grid.cells.get(row)) {
                        if (cell.hasLinkedCells().isEmpty()) {
                            neighbours = new HashSet<>();
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
