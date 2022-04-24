package com.tom.maze;

import java.util.*;

public class Old43KruskalsMazeBuilder implements MazeBuilder {
    Grid grid;
    Random rand = new Random();

    public Old43KruskalsMazeBuilder() {
        this(new Grid(10, 10));
    }

    public Old43KruskalsMazeBuilder(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void buildMaze() {
        HashMap<Integer, List<Cell>> treeSets = new HashMap<>();
        ArrayList<Integer> setIdOfCell = new ArrayList<>();
        int arraySize = (grid.width - 1) * (grid.height - 1) * 2 + grid.width + grid.height - 1;
        Cell[][] walls = new Cell[arraySize][2];

        int i = 0;
        int j = 0;
        for (ArrayList<Cell> row : grid.cells) {
            for (Cell cell : row) {
                treeSets.put(i, new ArrayList<>() {{add(cell);}});
                setIdOfCell.add(i);

                for (Cell neighbour : Arrays.asList(cell.bottom, cell.right)) {
                    if (neighbour != null) {
                        Cell[] entry = {cell, neighbour};
                        walls[j] = entry;
                        j++;
                    }
                }
                i++;
            }
        }

        int setCount = grid.getSize();
//        Collections.shuffle(walls);

        while (setCount > 1) {
            Cell[] unpack = walls[rand.nextInt(arraySize - 1)];
            Cell cell = unpack[0];
            Cell neighbour = unpack[1];

            int iC = setIdOfCell.get(cell.row * grid.width + cell.column);
            int iN = setIdOfCell.get(neighbour.row * grid.width + neighbour.column);
            if (iC != iN) {
                cell.linkTo(neighbour);

                treeSets.get(iC).addAll(treeSets.get(iN));
                List<Cell> temp = treeSets.get(iC);
                treeSets.remove(iN);

                for (Cell key : temp) {
                    setIdOfCell.set(key.row * grid.width + key.column, iC);
                }

                setCount--;
            }
        }
    }
}
