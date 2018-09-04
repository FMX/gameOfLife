package com.fmx;

import com.fmx.async.Updatable;
import com.fmx.config.Constant;
import com.fmx.coordinate.ElementSquar;

public class GameOfLifeLogic implements Updatable {


    volatile int[][] curGrid = new int[Constant.widthCount][Constant.heighCount];
    volatile int[][] lstGrid = new int[Constant.widthCount][Constant.heighCount];

    volatile ElementSquar[][] elementGrid = new ElementSquar[Constant.widthCount][Constant.heighCount];

    public GameOfLifeLogic() {
        for (int i = 0; i < Constant.widthCount; i++) {
            for (int j = 0; j < Constant.heighCount; j++) {
                elementGrid[i][j] = new ElementSquar(i, j);
                curGrid[i][j] = 0;
                lstGrid[i][j] = 0;
            }
        }
    }

    @Override
    public void update(double moment) {
        System.out.println("Update Logic :" + moment);
        lstGrid = curGrid;
        curGrid = new int[Constant.widthCount][Constant.heighCount];
        for (int i = 0; i < Constant.widthCount; i++) {
            for (int j = 0; j < Constant.heighCount; j++) {
                if (judgeLife(i, j)) {
                    elementGrid[i][j].setWhite();
                } else {
                    elementGrid[i][j].setBlack();
                }
            }
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean judgeLife(int idx, int idy) {
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

        if (activeCells >= Constant.liveLowerThreshold && activeCells <= Constant.liveUpperThreshold) {
            curGrid[idx][idy] = 1; //alive
            return true;
        } else {
            curGrid[idx][idy] = 0; //dead
            return false;
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

    public ElementSquar[][] getElementGrid() {
        return elementGrid;
    }

    public static int getWidthCount() {
        return Constant.widthCount;
    }

    public static int getHeighCount() {
        return Constant.heighCount;
    }

    public void activeCell(int x, int y) {
        int idx = x / Constant.squareSize;
        int idy = y / Constant.squareSize;
        curGrid[idx][idy] = 1;
    }

}
