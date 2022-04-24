package com.tom.maze;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class RecursiveBacktrackerMazeBuilder implements MazeBuilder {
    Grid grid;
    private final Stack<Cell> stack = new Stack<>();

    public RecursiveBacktrackerMazeBuilder() {
        this.grid = new Grid(10, 10);
    }

    public RecursiveBacktrackerMazeBuilder(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void buildMaze() {
        stack.push(grid.getRandomCell());

        ArrayList<Cell> neighbours = new ArrayList<>();
        Random rand = new Random();

        while (!stack.empty()) {
            Cell current_cell = stack.peek();

            for (Cell cell : current_cell.neighbours()) {
                if (cell.hasLinkedCells().isEmpty()) {
                    neighbours.add(cell);
                }
            }

            if (!neighbours.isEmpty()) {
                Cell neighbour = neighbours.get(rand.nextInt(neighbours.size()));

                stack.push(neighbour);
                current_cell.linkTo(neighbour);

                neighbours.clear();
            } else {
                stack.pop();
            }
        }
    }


}
