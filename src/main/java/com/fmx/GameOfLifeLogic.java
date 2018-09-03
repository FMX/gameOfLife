package com.fmx;

import com.fmx.async.Updatable;
import com.fmx.coordinate.ElementSquar;

public class GameOfLifeLogic implements Updatable {
    public static final int widthCount = 120;
    public static final int heighCount = 80;

    int[][] curGrid = new int[widthCount][heighCount];
    int[][] lstGrid = new int[widthCount][heighCount];

    ElementSquar[][] elementGrid = new ElementSquar[widthCount][heighCount];

    public GameOfLifeLogic() {
        for (int i = 0; i < 120; i++) {
            for (int j = 0; j < 80; j++) {
                elementGrid[i][j] = new ElementSquar(i, j);
            }
        }
    }

    @Override
    public void update(double moment) {
        System.out.println("Update Logic :" + moment);
        lstGrid = curGrid;
        curGrid = new int[widthCount][heighCount];
        for (int i = 0; i < widthCount; i++) {
            for (int j = 0; j < heighCount; j++) {
                if (judgeLife(i, j)) {
                    elementGrid[i][j].setBlack();
                } else {
                    elementGrid[i][j].setWhite();
                }
            }
        }


        try {
            Thread.sleep(500);
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

        if (activeCells == 2 || activeCells == 3) {
            curGrid[idx][idy] = 1; //alive
            return true;
        } else {
            curGrid[idx][idy] = 0; //dead
            return false;
        }

    }

    public boolean validateWidth(int index) {
        if (index >= 0 && index < widthCount) {
            return true;
        }
        return false;
    }

    public boolean validateHeight(int index) {
        if (index >= 0 && index < heighCount) {
            return true;
        }
        return false;
    }

    public ElementSquar[][] getElementGrid() {
        return elementGrid;
    }

    public static int getWidthCount() {
        return widthCount;
    }

    public static int getHeighCount() {
        return heighCount;
    }

    public void activeCell(int x, int y) {
        int idx = x / 10;
        int idy = y / 10;
        curGrid[idx][idy] = 1;
    }

}
