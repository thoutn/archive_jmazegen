package com.tom.maze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Cell {
    int row;
    int column;

    HashMap<Cell, Boolean> links = new HashMap<>();

    Cell top = null;
    Cell bottom = null;
    Cell right = null;
    Cell left = null;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;

//        this.links = new HashMap<Cell, Boolean>();
//
//        this.top = null;
//        this.bottom = null;
//        this.right = null;
//        this.left = null;
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
}
