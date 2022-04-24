package com.tom.maze;

import java.util.ArrayList;
import java.util.Random;

public class OldBinaryTreeMazeBuilder implements MazeBuilder {
    Grid grid;
//    private ArrayList<Cell> neighbours = new ArrayList<>();
    private Random rand = new Random();

    public OldBinaryTreeMazeBuilder() {
        this.grid = new Grid(10, 10);
    }

    public OldBinaryTreeMazeBuilder(Grid grid) {
        this.grid = grid;
    }

    private Cell chooseNeighbourOf(Cell cell) {
        ArrayList<Cell> neighbours = new ArrayList<>();
        if (cell.top != null) {
            neighbours.add(cell.top);
        }
        if (cell.right != null) {
            neighbours.add(cell.right);
        }

        if (!neighbours.isEmpty()) {
            return neighbours.get(rand.nextInt(neighbours.size()));
//            Cell neighbour = neighbours.get(new Random().nextInt(neighbours.size()));
//            neighbours.clear();
//            return neighbour;
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
