package com.fmx.coordinate;


import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class DrawAxis {
    private static int PRE_CENTER_X=80;
    private static int PRE_CENTER_Y=80;
    private static int PRE_AXIS_LENGTH=40;

    private int centerX;
    private int centerY;
    private int axisLength;

    //init axis Tip
    double  xPoint[] = new double [6];
    //注意坐标是将第四象限作为第一象限
    double  yPoint[] = new double [6];

    /**
     * 坐标轴的箭头
     */
    private Polygon xAxisTip;
    private Polygon yAxisTip;
    /**
     * 坐标轴
     */
    private DrawHLine xAxis;
    private DrawVLine yAxis;

    /**
     * Creates an empty instance of DrawAxis
     */
    public DrawAxis() {
        //the parameters is undefined,so set default values;
        centerX = 40;
        centerY = 40;
        axisLength = 20;
        xAxis = new DrawHLine(centerX, centerY, axisLength);
        yAxis = new DrawVLine(centerX, centerY, axisLength);
        initPara(centerX, centerY, axisLength);
    }

    /**
     * Creates a new instance of Polygon.
     * @param (centerX , centerY) is the coordinate of Axis;
     * @param axisLenth is the length of axis;
     */
    public DrawAxis(int centerX , int centerY , int axisLength){

        xAxis = new DrawHLine(centerX, centerY, axisLength);
        yAxis = new DrawVLine(centerX, centerY, axisLength);
        initPara(centerX, centerY, axisLength);
    }

    public void selfDefine() {

        //设置画线使用虚线，实线长度，虚线长度
        //getStrokeDashArray().setAll();
    }

    public void initPara(int x,int y,int length) {
        this.centerX=x;
        this.centerY=y;
        this.axisLength=length;

        System.out.println(centerX);
        System.out.println(centerY);
        System.out.println(axisLength);

        //init axis Tip
        xPoint[0] = centerX+axisLength/2+10;
        xPoint[1] = centerY;
        xPoint[2] = centerX+axisLength/2-1;
        xPoint[3] = centerY+4;
        xPoint[4] = centerX+axisLength/2-1;
        xPoint[5] = centerY-4;

        //注意坐标是将第四象限作为第一象限
        yPoint[0] = centerX;
        yPoint[1] = centerY-10-axisLength/2;
        yPoint[2] = centerX-4;
        yPoint[3] = centerY-axisLength/2-1;
        yPoint[4] = centerX+4;
        yPoint[5] = centerY-axisLength/2-1;
        //
        xAxisTip = new Polygon(xPoint);
        yAxisTip = new Polygon(yPoint);
        xAxisTip.setFill(Color.GREEN);
        yAxisTip.setFill(Color.GREEN);

    }

    /**
     *
     * @return xAxisTip
     */
    public Polygon getxAxisTip() {
        return xAxisTip;
    }

    /**
     *
     * @return yAxisTip
     */
    public Polygon getyAxisTip() {
        return yAxisTip;
    }

    /**
     *
     * @return yAxisTip
     */
    public DrawHLine getxAxis() {
        return xAxis;
    }

    /**
     *
     * @return yAxisTip
     */
    public DrawVLine getyAxis() {
        return yAxis;
    }

}
