package com.tom.maze;

import java.util.*;

public class Kruskals1MazeBuilder implements MazeBuilder {
    Grid grid;
    private final ArrayList<Set<Cell>> treeSets = new ArrayList<>();

    public Kruskals1MazeBuilder() {
        this(new Grid(10, 10));
    }

    public Kruskals1MazeBuilder(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    private int getTreeIndex(Cell cell) {
        for (Set<Cell> set : treeSets) {
            if (set.contains(cell)) {
                return treeSets.indexOf(set);
            }
        }
        return -1;
    }

    @Override
    public void buildMaze() {
//        treeSets.ensureCapacity(grid.getSize());
        ArrayList<Cell[]> walls = new ArrayList<>();
        for (ArrayList<Cell> row : grid.cells) {
            for (Cell cell : row) {
                treeSets.add(new HashSet<>() {{add(cell);}});

                for (Cell neighbour : Arrays.asList(cell.bottom, cell.right)) {
                    if (neighbour != null) {
                        Cell[] entry = {cell, neighbour};
                        walls.add(entry);
                    }
                }
            }
        }

        Collections.shuffle(walls);
        while (!walls.isEmpty()) {
            Cell[] unpack = walls.remove(walls.size() - 1);
            Cell cell = unpack[0];
            Cell neighbour = unpack[1];

            int i1 = getTreeIndex(cell);
            assert i1 != -1;

            if (!treeSets.get(i1).contains(neighbour)) {
                int i2 = getTreeIndex(neighbour);
                cell.linkTo(neighbour);

                treeSets.get(i1).addAll(treeSets.get(i2));
                treeSets.remove(i2);
            }
        }

        treeSets.clear();
    }
}
