package com.fmx;

import com.fmx.async.Updatable;

public class GameOfLifeLogic implements Updatable {
    int[][] grid = new int[120][80];

    @Override
    public void update(double moment) {
        System.out.println("Update Logic");
    }
}
