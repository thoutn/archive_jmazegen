package com.tom.maze2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Old2KruskalsMazeBuilder implements MazeBuilder {
    Grid grid;

    public Old2KruskalsMazeBuilder() {
        this(new Grid(10, 10));
    }

    public Old2KruskalsMazeBuilder(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    private class State {
        Grid grid;
        private ArrayList<Cell[]> neighbours = new ArrayList<>();
        private HashMap<Cell, Integer> setIdOfCell = new HashMap<>();
        private HashMap<Integer, ArrayList<Cell>> cellsInSet = new HashMap<>();

        public State(Grid grid) {
            this.grid = grid;
            initialise();
        }

        private void initialise() {
            int i = 0;
            for (ArrayList<Cell> row : grid.cells) {
                for (Cell cell : row) {
                    setIdOfCell.put(cell, i);
                    cellsInSet.put(i, new ArrayList<>() {{add(cell);}});
                    i++;

                    if (cell.bottom != null) {
                        Cell[] entry = {cell, cell.bottom};
                        neighbours.add(entry);
                    }
                    if (cell.right != null) {
                        Cell[] entry = {cell, cell.right};
                        neighbours.add(entry);
                    }
                }
            }
        }

        private boolean isToBeJoined(Cell left, Cell right) {
            return !setIdOfCell.get(left).equals(setIdOfCell.get(right));
        }

        private void joinSets(Cell left, Cell right) {
            left.linkTo(right);

            int iL = setIdOfCell.get(left);
            int iR = setIdOfCell.get(right);
            ArrayList<Cell> temp = cellsInSet.get(iR);

            for (Cell cell : temp) {
                cellsInSet.get(iL).add(cell);
                setIdOfCell.replace(cell, iL);
            }

            cellsInSet.remove(iR);
        }

    }
    @Override
    public void buildMaze() {
        State state = new State(grid);

        ArrayList<Cell[]> neighbours = state.neighbours;
        Collections.shuffle(neighbours);

        while (!neighbours.isEmpty()) {
            Cell[] unpack = neighbours.remove(neighbours.size() - 1);
            Cell left = unpack[0];
            Cell right = unpack[1];

            if (state.isToBeJoined(left, right)) {
                state.joinSets(left, right);
            }
        }
    }
}
