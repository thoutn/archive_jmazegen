package com.tom.maze;

import java.util.*;

public class Old52KruskalsMazeBuilder implements MazeBuilder {
    Grid grid;

    public Old52KruskalsMazeBuilder() {
        this(new Grid(10, 10));
    }

    public Old52KruskalsMazeBuilder(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void buildMaze() {
        List<List<Cell>> treeSets = new ArrayList<>(grid.getSize());
        int[][] setIdOfCell = new int[grid.height][grid.width];
        List<Cell[]> walls = new ArrayList<>();

        int i = 0;
        for (int row = 0; row < grid.height; row++) {
            for (int col = 0; col < grid.width; col++) {
                treeSets.add(new ArrayList<>());
//                int id = grid.width * row + col;
                treeSets.get(i).add(grid.cells.get(row).get(col));
                setIdOfCell[row][col] = i;

                Cell cell;
                if ((cell = grid.cells.get(row).get(col).bottom) != null) {
                    Cell[] entry = {cell.top, cell};
                    walls.add(entry);
                }
                if ((cell = grid.cells.get(row).get(col).right) != null) {
                    Cell[] entry = {cell.left, cell};
                    walls.add(entry);
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

            int iC = setIdOfCell[cell.row][cell.column];
            int iN = setIdOfCell[neighbour.row][neighbour.column];
            if (iC != iN) {
                cell.linkTo(neighbour);

                treeSets.get(iC).addAll(treeSets.get(iN));
                treeSets.set(iN, null);

                for (Cell key : treeSets.get(iC)) {
                    setIdOfCell[key.row][key.column] = iC;
                }
//                for (ArrayList<Cell> row : grid.cells) {
//                    for (Cell cell_ : row) {
//                        if (setIdOfCell[cell_.row][cell_.column] == iN) {
//                            setIdOfCell[cell_.row][cell_.column] = iC;
//                        }
//                    }
//                }

                setCount--;
            }
        }
    }
}
