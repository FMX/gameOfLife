package com.fmx.async;

import com.fmx.config.Constant;

public class GridRowProcessor implements Runnable {
    private int[][] lstGrid;
    private int[][] curGrid;
    private int rowIndex;

    public GridRowProcessor(int[][] lst, int[][] cur, int row) {
        lstGrid = lst;
        curGrid = cur;
        rowIndex = row;
    }

    @Override
    public void run() {
        for (int j = 0; j < Constant.heighCount; j++) {
            judgeLife(rowIndex, j);
        }

    }

    public void judgeLife(int idx, int idy) {
        int activeCells = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (validateWidth(idx + i) && validateHeight(idy + j)) {
                    if (lstGrid[idx + i][idy + j] == 1) {
                        activeCells++;
                    }
                }
            }
        }

        if (Constant.aliveCondition.contains(activeCells)) {
            curGrid[idx][idy] = 1; //alive
        } else {
            curGrid[idx][idy] = 0; //dead
        }

    }

    public boolean validateWidth(int index) {
        if (index >= 0 && index < Constant.widthCount) {
            return true;
        }
        return false;
    }

    public boolean validateHeight(int index) {
        if (index >= 0 && index < Constant.heighCount) {
            return true;
        }
        return false;
    }


}
