package com.tom.maze;

import java.util.*;

public class Old42KruskalsMazeBuilder implements MazeBuilder {
    Grid grid;
//    HashMap<Integer, List<Cell>> treeSets = new HashMap<>();
//    ArrayList<Integer> setIdOfCell = new ArrayList<>();
//    ArrayList<Cell[]> walls = new ArrayList<>();

    public Old42KruskalsMazeBuilder() {
        this(new Grid(10, 10));
    }

    public Old42KruskalsMazeBuilder(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void buildMaze() {
        HashMap<Integer, List<Cell>> treeSets = new HashMap<>();
        int[] setIdOfCell = new int[grid.getSize()];
        ArrayList<Cell[]> walls = new ArrayList<>();

        int i = 0;
        for (ArrayList<Cell> row : grid.cells) {
            for (Cell cell : row) {
                treeSets.put(i, new ArrayList<>() {{add(cell);}});
                setIdOfCell[i] = i;

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

            int iC = setIdOfCell[cell.row * grid.width + cell.column];
            int iN = setIdOfCell[neighbour.row * grid.width + neighbour.column];
            if (iC != iN) {
                cell.linkTo(neighbour);

                treeSets.get(iC).addAll(treeSets.get(iN));
                List<Cell> temp = treeSets.get(iC);
                treeSets.remove(iN);

                for (Cell key : temp) {
                    setIdOfCell[key.row * grid.width + key.column] = iC;
                }

                setCount--;
            }
        }

//        treeSets.clear();
//        walls.clear();
//        setIdOfCell.clear();
    }
}
