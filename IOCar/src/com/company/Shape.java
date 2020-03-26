package com.company;

public abstract class Shape {
    public int row;
    public int column;
    public int length;
    public int width;
    public String output = " ";

    public Shape(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public abstract void draw();

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}


