package com.tom.maze;

import java.util.*;

public class Old423KruskalsMazeBuilder implements MazeBuilder {
    Grid grid;

    public Old423KruskalsMazeBuilder() {
        this(new Grid(10, 10));
    }

    public Old423KruskalsMazeBuilder(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void buildMaze() {
        Cell[][] treeSets = new Cell[grid.getSize()][grid.getSize()];
        int[] lengthOfSets = new int[grid.getSize()];
        int[] idOfSet = new int[grid.getSize()];
        ArrayList<Cell[]> walls = new ArrayList<>();

        int i = 0;
        for (ArrayList<Cell> row : grid.cells) {
            int j = 0;
            for (Cell cell : row) {
                treeSets[i][j] = cell;
                lengthOfSets[i] = 0;
                j++;
                idOfSet[i] = i;

                for (Cell neighbour : Arrays.asList(cell.bottom, cell.right)) {
                    if (neighbour != null) {
                        Cell[] entry = {cell, neighbour};
                        walls.add(entry);
                    }
                }
                i++;
            }
        }

        int setCount = grid.getSize();
        Collections.shuffle(walls);

        while (!walls.isEmpty() && setCount > 1) {
            Cell[] unpack = walls.remove(walls.size() - 1);
            Cell cell = unpack[0];
            Cell neighbour = unpack[1];

            int iC = idOfSet[cell.row * grid.width + cell.column];
            int iN = idOfSet[neighbour.row * grid.width + neighbour.column];
            if (iC != iN) {
                cell.linkTo(neighbour);

                for (int k = 0; k < lengthOfSets[iN]; k++) {
                    treeSets[iC][lengthOfSets[iC]] = treeSets[iN][k];
                    int row = treeSets[iN][k].row;
                    int col = treeSets[iN][k].column;
                    idOfSet[row * grid.width + col] = iC;
                    lengthOfSets[iC] += 1;
                }

                setCount--;
            }
        }
    }
}
