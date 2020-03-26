package com.company;

public class Rectangle extends Shape {


    public Rectangle(int length, int width) {
        super(length, width);
    }

    @Override
    public void draw() {

        for (this.row = 0; this.row <= this.length ; this.row++) {
            this.output = "X";
            for ( this.column = 0; this.column <= this.width ; this.column++) {
                this.output = this.output + " X";
            }
            System.out.println(output);
            }


        }


    }






