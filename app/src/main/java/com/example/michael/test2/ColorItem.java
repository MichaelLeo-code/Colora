package com.example.michael.test2;

import android.graphics.Color;

public class ColorItem {

    private int r;
    private int g;
    private int b;
    private String name = "";

    public ColorItem(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHexValue() {
        return String.format("#%02x%02x%02x", r, g, b);
    }


    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getColor() {
        return Color.rgb(r, g, b);
    }
}
