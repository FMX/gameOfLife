package com.fmx;

import com.fmx.async.Advancer;
import com.fmx.async.Updatable;
import com.fmx.config.Constant;
import com.fmx.coordinate.ElementSquar;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

public class GameOfLifeGraph extends Application implements Updatable {

    private GameOfLifeLogic gamelogic = new GameOfLifeLogic();

    private GraphicsContext canvasGraphieContext = null;

    @Override
    public void update(double moment) {
        System.out.println("update graph :" + moment);
        ElementSquar[][] grid = gamelogic.getElementGrid();
        for (int i = 0; i < GameOfLifeLogic.getWidthCount(); i++) {
            for (int j = 0; j < GameOfLifeLogic.getHeighCount(); j++) {
                canvasGraphieContext.setFill(grid[i][j].getColor());
                double x = grid[i][j].getX();
                double y = grid[i][j].getY();
                canvasGraphieContext.fillRect(x, y, 10, 10);
            }
        }
    }

    @Override
    public void init() throws Exception {
        Advancer advancer = new Advancer(gamelogic);
        new Thread(() -> {
            while (true) {
                advancer.advance(this);
            }
        }).start();
//        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        primaryStage.setTitle("Drawing Operations Test");
        Group root = new Group();
        Canvas canvas = new Canvas(1200, 800);
        canvas.setOnMouseClicked(event -> {
//            double x = event.getX();
//            double y = event.getY();
//            gamelogic.activeCell((int) x, (int) y);
            System.out.println("Random life");
            Random random = new Random();
            for (int i = 0; i < 500; i++) {
                int x = random.nextInt(Constant.fieldWidth);
                int y = random.nextInt(Constant.fieldHeight);
                gamelogic.activeCell(x, y);
            }

        });

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        canvasGraphieContext = canvas.getGraphicsContext2D();
        canvasGraphieContext.setFill(Color.BLACK);
        canvasGraphieContext.fillRect(0, 0, 1200, 800);

    }

    public static void main(String[] args) {
        launch(args);
    }

}
