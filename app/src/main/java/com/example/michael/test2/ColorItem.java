package com.example.michael.test2;

public class ColorItem {

    private int r;
    private int g;
    private int b;
    private String name;
    private String hexValue;

    public ColorItem(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.hexValue = String.format("#%02x%02x%02x", r, g, b);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHexValue() {
        return hexValue;
    }

    public void setHexValue(String hexValue) {
        this.hexValue = hexValue;
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

}
