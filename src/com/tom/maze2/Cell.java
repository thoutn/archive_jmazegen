package com.tom.maze2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Cell {
    final int row;
    final int column;

    HashMap<Cell, Boolean> links = new HashMap<>();

    Cell top = null;
    Cell bottom = null;
    Cell right = null;
    Cell left = null;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void linkTo(Cell cell) {
        this.linkTo(cell, true);
    }

    public void linkTo(Cell cell, boolean bidirect) {
        links.put(cell, true);
        if (bidirect) {
            cell.linkTo(this, false);
        }
    }

    public Set<Cell> hasLinkedCells() {
        return links.keySet();
    }

    public boolean isLinkedTo(Cell cell) {
        return links.containsKey(cell);
    }

    public ArrayList<Cell> neighbours() {
        ArrayList<Cell> lst = new ArrayList<>();

        if (top != null) {
            lst.add(top);
        }
        if (bottom != null) {
            lst.add(bottom);
        }
        if (right != null) {
            lst.add(right);
        }
        if (left != null) {
            lst.add(left);
        }

        return lst;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Cell that)) {
            return false;
        }

        return this.row == that.row && this.column == that.column;
    }

    @Override
    public int hashCode() {
        return 37 * (1000 * row + column);
    }
}
