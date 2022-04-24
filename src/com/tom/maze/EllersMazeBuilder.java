package com.tom.maze;

import java.util.*;

public class EllersMazeBuilder implements MazeBuilder {
    Grid grid;
    private Random rand = new Random();
    private List<List<Cell>> treeSets = new ArrayList<>();

    public EllersMazeBuilder() {
        this(new Grid(10, 10));
    }

    public EllersMazeBuilder(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    private int getTreeIndex(Cell cell) {
        int i = 0;
        for (List<Cell> set : treeSets) {
            if (set.contains(cell)) {
                return i;
            }
            i++;
        }

        return -1;
    }

    private List<Cell> createLinkToNextRow(List<Cell> treeSet) {
//        ArrayList<Cell> treeSet = new ArrayList<>(set);
        Collections.shuffle(treeSet);
        List<Cell> newSet = new ArrayList<>();

        for (int i = 0; i < rand.nextInt(1, treeSet.size() + 1); i++) {
            Cell cell = treeSet.get(i);
            newSet.add(cell.bottom);
            cell.linkTo(cell.bottom);
        }

        assert newSet.size() > 0;
        return newSet;
    }

    @Override
    public void buildMaze() {
        for (ArrayList<Cell> row : grid.cells) {
            boolean isLastRow = row == grid.cells.get(grid.cells.size() - 1);

            for (Cell cell : row) {
                if (cell != null) {
                    int i;
                    if (cell.hasLinkedCells().isEmpty()) {
                        treeSets.add(new ArrayList<>() {{add(cell);}});
                        cell.linkTo(cell);
                        i = treeSets.size() - 1;
                    } else {
                        i = getTreeIndex(cell);
                        assert i != -1;
                    }

                    boolean isEachInSameTree = cell.left != null && treeSets.get(i).contains(cell.left);

                    if (cell.left != null && !isEachInSameTree) {
                        boolean isToBeJoined = rand.nextBoolean() || isLastRow;

                        if (isToBeJoined) {
                            int iLeft = getTreeIndex(cell.left);
                            cell.linkTo(cell.left);
                            treeSets.get(iLeft).addAll(treeSets.get(i));
                            treeSets.remove(i);
                        }
                    }
                }
            }

            if (!isLastRow) {
                for (List<Cell> treeSet : new ArrayList<>(treeSets)) {
                    treeSets.remove(treeSet);
                    treeSets.add(createLinkToNextRow(treeSet));
                }
                assert treeSets.size() > 0;
            }
        }
        treeSets.clear();
    }
}
