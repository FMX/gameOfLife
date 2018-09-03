package com.fmx.coordinate;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class DrawVLine extends Line {
    double startX;
    double startY;
    double endX;
    double endY;

    public DrawVLine() {
        // TODO Auto-generated constructor stub
    }

    public DrawVLine(double centerX,double centerY ,double lineLength) {
        super(centerX, centerY-lineLength/2, centerX, centerY+lineLength/2);
        // TODO Auto-generated constructor stub
        getLinePara(centerX, centerY, lineLength);

        //可补充
        selfDefine();
    }

    public void getLinePara(double x,double y,double lineLength) {
        startX = x ;
        endX = x ;
        startY = y-lineLength/2;
        endY = y+lineLength/2;
    }

    public void selfDefine() {
        setStroke(Color.GREEN);
        //设置画线使用虚线，实线长度，虚线长度
        //getStrokeDashArray().setAll();
    }

}
