package com.fmx.coordinate;

public class ElementSquar {
    double x, y;
    double w, h = 10;

    int idx, idy;

    public ElementSquar(int idx, int idy) {
        this.idx = idx;
        this.idy = idy;

        calculateByIndex(idx, idy);

    }

    private void calculateByIndex(int xindex, int yindex) {
        x = xindex * 10;
        y = yindex * 10;
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
}
