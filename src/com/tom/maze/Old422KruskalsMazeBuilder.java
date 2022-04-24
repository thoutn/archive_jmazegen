package com.tom.maze;

import java.util.*;

public class Old422KruskalsMazeBuilder implements MazeBuilder {
    Grid grid;
//    HashMap<Integer, List<Cell>> treeSets = new HashMap<>();
//    ArrayList<Integer> setIdOfCell = new ArrayList<>();
//    ArrayList<Cell[]> walls = new ArrayList<>();

    public Old422KruskalsMazeBuilder() {
        this(new Grid(10, 10));
    }

    public Old422KruskalsMazeBuilder(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void buildMaze() {
        float[][] treeSets = new float[grid.getSize()][grid.getSize() + 1];
        int[] idOfSet = new int[grid.getSize()];
        ArrayList<Cell[]> walls = new ArrayList<>();

        int i = 0;
        for (ArrayList<Cell> row : grid.cells) {
            for (Cell cell : row) {
                treeSets[i][0] = 1;
                treeSets[i][1] = cell.row + cell.column / 10000f;
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

                for (int j = 1; j < (int)treeSets[iN][0]; j++) {
                    treeSets[iC][0] += 1;
                    treeSets[iC][(int)treeSets[iC][0]] = treeSets[iN][j];
                    int row = (int)treeSets[iN][j];
                    int col = Math.round((treeSets[iN][j] - row) * 10000);
                    idOfSet[row * grid.width + col] = iC;
                }

                setCount--;
            }
        }

//        treeSets.clear();
//        walls.clear();
//        setIdOfCell.clear();
    }
}
