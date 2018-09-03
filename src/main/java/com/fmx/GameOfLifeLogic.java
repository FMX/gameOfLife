package com.fmx;

import com.fmx.async.Updatable;
import com.fmx.coordinate.ElementSquar;

public class GameOfLifeLogic implements Updatable {
    public static final int widthCount = 120;
    public static final int heighCount = 80;

    int[][] grid = new int[widthCount][heighCount];
    ElementSquar[][] elementGrid = new ElementSquar[widthCount][heighCount];

    public GameOfLifeLogic() {
        for (int i = 0; i < 120; i++) {
            for (int j = 0; j < 80; j++) {
                grid[i][j] = 0;
                elementGrid[i][j] = new ElementSquar(i, j);
            }
        }
    }

    @Override
    public void update(double moment) {
        System.out.println("Update Logic :" + moment);
        int[][] newGrid = new int[widthCount][heighCount];
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
}
