package com.fmx.async;

import com.fmx.config.Constant;

public class GridRowProcessor implements Runnable {
    private final int[][] lstGrid;
    private final int[][] curGrid;
    private final int rowIndex;

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

    private void judgeLife(int idx, int idy) {
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

    private boolean validateWidth(int index) {
        return index >= 0 && index < Constant.widthCount;
    }

    private boolean validateHeight(int index) {
        return index >= 0 && index < Constant.heighCount;
    }


}
