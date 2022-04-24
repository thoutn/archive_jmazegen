package com.tom.maze;

import java.util.*;

public class OldEllersMazeBuilder implements MazeBuilder {
    Grid grid;
    private Random rand = new Random();

    public OldEllersMazeBuilder() {
        this(new Grid(10, 10));
    }

    public OldEllersMazeBuilder(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    private int getTreeIndex(Cell cell, List<Set<Cell>> container) {
        assert container != null;

        for (Set<Cell> set : container) {
            if (set.contains(cell)) {
                return container.indexOf(set);
            }
        }

        return -1;
    }

    private Set<Cell> createLinkToNextRow(Set<Cell> set) {
        ArrayList<Cell> treeSet = new ArrayList<>(set);
        Collections.shuffle(treeSet);
        Set<Cell> newSet = new HashSet<>();

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
        List<Set<Cell>> treeSets = new ArrayList<>();

        for (ArrayList<Cell> row : grid.cells) {
            boolean isLastRow = row == grid.cells.get(grid.cells.size() - 1);

            for (Cell cell : row) {
                if (cell != null) {
                    int i;
                    if (cell.hasLinkedCells().isEmpty()) {
                        treeSets.add(new HashSet<>());
                        treeSets.get(treeSets.size() - 1).add(cell);
                        cell.linkTo(cell);
                        i = treeSets.size() - 1;
                    } else {
                        i = getTreeIndex(cell, treeSets);
                        assert i != -1;
                    }

                    boolean isEachInSameTree = cell.left != null && treeSets.get(i).contains(cell.left);

                    if (cell.left != null && !isEachInSameTree) {
                        boolean isToBeJoined = rand.nextBoolean() || isLastRow;

                        if (isToBeJoined) {
                            int iLeft = getTreeIndex(cell.left, treeSets);
                            cell.linkTo(cell.left);
                            treeSets.get(iLeft).addAll(treeSets.get(i));
                            treeSets.remove(i);
                        }
                    }
                }
            }

            if (!isLastRow) {
                for (Set<Cell> treeSet : new ArrayList<>(treeSets)) {
                    treeSets.remove(treeSet);
                    treeSets.add(createLinkToNextRow(treeSet));
                }
                assert treeSets.size() > 0;
            }
        }
    }
}
