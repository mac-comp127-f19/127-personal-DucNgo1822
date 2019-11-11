package Breakout;

import comp127graphics.Rectangle;

import java.awt.*;

public class Brick extends Rectangle {
    private static final int WIDTH = 10;
    private static final int HEIGHT = 20;
    private double upperX, upperY;

    /**
     * Constructor to create the a rectangle and initialize its instance variables.
     * The default creates a brick with the exact variable.
     *
     *
     * @param upperX    upper left position for x
     * @param upperY    upper left position for y
     *
     */
    public Brick(double upperX, double upperY, Color color) {
        super(upperX, upperY, WIDTH, HEIGHT);
        this.setFilled(true);
        this.setFillColor(color);
        this.upperX = upperX;
        this.upperY = upperY;
    }
    public double getX(){
        return upperX;
    }

    public double getY(){
        return upperY;
    }

    public int getBrickWidth(){
        return WIDTH;
    }

    public int getBrickHeight(){
        return HEIGHT;
    }

}
