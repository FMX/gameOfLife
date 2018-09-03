package com.fmx.coordinate;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ElementSquar {
    double x, y;
    double w, h = 10;

    int idx, idy;

    int status = 0;

    public ElementSquar(int idx, int idy) {
        this.idx = idx;
        this.idy = idy;
        calculateByIndex(idx, idy);
    }

    private void calculateByIndex(int xindex, int yindex) {
        x = xindex * 10;
        y = yindex * 10;
    }

    public void setBlack() {
        this.status = 1;
    }

    public void setWhite() {
        this.status = 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getW() {
        return w;
    }

    public double getH() {
        return h;
    }

    public Paint getColor() {
        if (status == 1)
            return Color.BLACK;
        else
            return Color.WHITE;
    }

}
