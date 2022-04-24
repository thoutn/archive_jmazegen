package com.tom.maze;

import java.util.*;

public class Old3KruskalsMazeBuilder implements MazeBuilder {
    Grid grid;

    public Old3KruskalsMazeBuilder() {
        this(new Grid(10, 10));
    }

    public Old3KruskalsMazeBuilder(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void buildMaze() {
        HashMap<Cell, List<Cell>> treeSets = new HashMap<>();
        ArrayList<Cell[]> walls = new ArrayList<>();
        for (ArrayList<Cell> row : grid.cells) {
            for (Cell cell : row) {
//                treeSets.put(cell, new HashSet<>(Arrays.asList(cell)));
                treeSets.put(cell, new ArrayList<>() {{add(cell);}});

                for (Cell neighbour : Arrays.asList(cell.bottom, cell.right)) {
                    if (neighbour != null) {
                        Cell[] entry = {cell, neighbour};
                        walls.add(entry);
                    }
                }
            }
        }

        int setCount = grid.getSize();
        Collections.shuffle(walls);

        while (!walls.isEmpty() && setCount > 1) {
            Cell[] unpack = walls.remove(walls.size() - 1);
            Cell cell = unpack[0];
            Cell neighbour = unpack[1];

            if (!treeSets.get(cell).contains(neighbour)) {
                cell.linkTo(neighbour);

                treeSets.get(neighbour).addAll(treeSets.get(cell));
                List<Cell> temp = treeSets.get(neighbour);

                for (Cell key : temp) {
                    treeSets.put(key, temp);
                }

                setCount--;
            }
        }
    }
}
