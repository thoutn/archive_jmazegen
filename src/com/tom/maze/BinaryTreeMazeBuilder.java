package com.tom.maze;

import java.util.ArrayList;
import java.util.Random;

public class BinaryTreeMazeBuilder implements MazeBuilder {
    Grid grid;
    private ArrayList<Cell> neighbours = new ArrayList<>();
    private Random rand = new Random();

    public BinaryTreeMazeBuilder() {
        this.grid = new Grid(10, 10);
    }

    public BinaryTreeMazeBuilder(Grid grid) {
        this.grid = grid;
    }

    private Cell chooseNeighbourOf(Cell cell) {
        if (cell.top != null) {
            neighbours.add(cell.top);
        }
        if (cell.right != null) {
            neighbours.add(cell.right);
        }

        if (!neighbours.isEmpty()) {
            Cell neighbour = neighbours.get(rand.nextInt(neighbours.size()));
            neighbours.clear();
            return neighbour;
        } else {
            return null;
        }
    }

    @Override
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void buildMaze() {
        for (ArrayList<Cell> row : grid.cells) {
            for (Cell cell : row) {
                Cell neighbour = chooseNeighbourOf(cell);

                if (neighbour != null) {
                    cell.linkTo(neighbour);
                }
            }
        }
    }

}
