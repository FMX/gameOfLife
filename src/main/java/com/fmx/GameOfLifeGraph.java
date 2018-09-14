package com.fmx;

import com.fmx.async.Advancer;
import com.fmx.async.Updatable;
import com.fmx.config.Constant;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

public class GameOfLifeGraph extends Application implements Updatable {

    private final GameOfLifeLogic gamelogic = new GameOfLifeLogic();

    private GraphicsContext canvasGraphieContext = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void update(double moment) {
        System.out.println("update graph :" + moment);
        int[][] grid = gamelogic.getCurGrid();
        for (int i = 0; i < GameOfLifeLogic.getWidthCount(); i++) {
            for (int j = 0; j < GameOfLifeLogic.getHeighCount(); j++) {
                if (grid[i][j] == 1) {
                    canvasGraphieContext.setFill(Color.WHITE);
                } else {
                    canvasGraphieContext.setFill(Color.BLACK);
                }
                canvasGraphieContext.fillRect(i * Constant.squareSize, j * Constant.squareSize, Constant.squareSize, Constant.squareSize);
            }
        }
    }

    @Override
    public void init() {
        Advancer advancer = new Advancer(gamelogic);
        new Thread(() -> {
            while (true) {
                advancer.advance(this);
            }
        }).start();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.setTitle("Drawing Operations Test");
        Group root = new Group();
        Canvas canvas = new Canvas(1200, 800);
        canvas.setOnMouseClicked(event -> {
            System.out.println("Random life");
            Random random = new Random();
            for (int i = 0; i < Constant.random_size; i++) {
                int x = random.nextInt(Constant.fieldWidth);
                int y = random.nextInt(Constant.fieldHeight);
                gamelogic.activeCell(x, y);
            }
        });

        canvas.setOnMousePressed(event -> {
            double x = event.getX();
            double y = event.getY();
            gamelogic.activeCell((int) x, (int) y);
        });

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        canvasGraphieContext = canvas.getGraphicsContext2D();
        canvasGraphieContext.setFill(Color.BLACK);
        canvasGraphieContext.fillRect(0, 0, 1200, 800);

    }

}
