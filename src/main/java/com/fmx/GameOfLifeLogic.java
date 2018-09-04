package com.fmx;

import com.fmx.async.GridRowProcessor;
import com.fmx.async.Updatable;
import com.fmx.config.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GameOfLifeLogic implements Updatable {

    volatile int[][] curGrid = new int[Constant.widthCount][Constant.heighCount];
    volatile int[][] lstGrid = new int[Constant.widthCount][Constant.heighCount];

    private ExecutorService fixedExecutors = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public GameOfLifeLogic() {
        for (int i = 0; i < Constant.widthCount; i++) {
            for (int j = 0; j < Constant.heighCount; j++) {
                curGrid[i][j] = 0;
                lstGrid[i][j] = 0;
            }
        }
    }

    public static int getWidthCount() {
        return Constant.widthCount;
    }

    public static int getHeighCount() {
        return Constant.heighCount;
    }

    @Override
    public void update(double moment) {
        System.out.println("Update Logic :" + moment);
        lstGrid = curGrid;
        curGrid = new int[Constant.widthCount][Constant.heighCount];
        List<Future> tasks = new ArrayList<>();
        for (int i = 0; i < Constant.widthCount; i++) {
            tasks.add(fixedExecutors.submit(new GridRowProcessor(lstGrid, curGrid, i)));
        }
        for (Future task : tasks) {
            try {
                task.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public int[][] getCurGrid() {
        return curGrid;
    }

    public void activeCell(int x, int y) {
        int idx = x / Constant.squareSize;
        int idy = y / Constant.squareSize;
        curGrid[idx][idy] = 1;
    }

}
