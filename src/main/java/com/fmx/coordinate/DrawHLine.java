package com.fmx.coordinate;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class DrawHLine extends Line {
    double startX;
    double startY;
    double endX;
    double endY;

    public DrawHLine() {
    }

    public DrawHLine(double centerX, double centerY, double lineLength) {
        super(centerX - lineLength / 2, centerY, centerX + lineLength / 2, centerY);
        getLinePara(centerX, centerY, lineLength);
        //可补充
        selfDefine();
    }

    public void getLinePara(double x, double y, double lineLength) {
        startX = x - lineLength / 2;
        endX = x + lineLength / 2;
        startY = y;
        endY = y;
    }

    public void selfDefine() {
        setStroke(Color.GREEN);
        //设置画线使用虚线，实线长度，虚线长度
        //getStrokeDashArray().setAll();
    }

}
