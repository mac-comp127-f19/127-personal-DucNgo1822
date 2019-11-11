package Breakout;

import comp127graphics.Rectangle;

import java.awt.*;

public class Paddle extends Rectangle {
    private static final int WIDTHPaddle = 150;
    private static final int HEIGHTPaddle = 10;
    private int x, y;

    /**
     * Constructor to create the a rectangle and initialize its instance variables.
     * The default creates a brick with the exact variable.
     *
     *
     * @param upperX    upper left position for x
     * @param upperY    upper left position for y
     *
     */
    public Paddle(int upperX, int upperY) {
        super(upperX, upperY, WIDTHPaddle, HEIGHTPaddle);
        color(new Color(0,255,0));
        this.x = x;
        this.y = y;
    }

    public void color(Color Paddle) {
        this.setFilled(true);
        this.setFillColor(Paddle);
    }

    public void updatePosition(int upperX) {
        this.x = x;
        this.setPosition(x,this.y);
    }

    public int getPaddleX(){
        return x;
    }

    public int getPaddleY(){
        return y;
    }

    public int getPaddleWidth(){
        return WIDTHPaddle;
    }

    public int getPaddleHeight(){
        return HEIGHTPaddle;
    }


}

