package com.fmx;

import com.fmx.async.Advancer;
import com.fmx.async.Updatable;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

public class GameOfLifeGraph extends Application implements Updatable {
    @Override
    public void update(double moment) {
        System.out.println("update graph");
        Random random = new Random();
        canvasGraphieContext.strokeLine(random.nextDouble() * 1200, random.nextDouble() * 800, random.nextDouble() * 1200, random.nextDouble() * 800);
    }

    private GraphicsContext canvasGraphieContext = null;

    @Override
    public void init() throws Exception {
        Advancer advancer = new Advancer(new GameOfLifeLogic());
        new Thread(() -> {
            while (true) {
                advancer.advance(this);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        primaryStage.setTitle("Drawing Operations Test");
        Group root = new Group();
        Canvas canvas = new Canvas(1200, 800);
        canvas.setOnMouseClicked(event -> {
            double x = event.getX();
            double y = event.getY();
            double z = event.getZ();
            System.out.println("x: " + x + " y: " + y + " z: " + z);
        });

        canvasGraphieContext = canvas.getGraphicsContext2D();

        drawGrid(canvasGraphieContext);

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public void drawGrid(GraphicsContext gc) {
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(1);
        gc.strokeLine(0, 0, 1200, 800);
    }


    public static void main(String[] args) {
        launch(args);
    }

}
