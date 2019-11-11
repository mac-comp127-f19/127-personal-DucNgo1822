package Breakout;

import comp127graphics.Ellipse;
import comp127graphics.GraphicsObject;

import java.awt.*;

public class Ball extends Ellipse {
    private static final double RADIUS = 10;
    private double xDirection = 20, yDirection = 20, leftX, upperY;

    /**
     *
     *
     * Constructor to create the ellipse object and initialize its instance variables.
     * The default creates an ellipse at position x,y with a bounding rectangle of width and height.
     * The ellipse is drawn with a 1 pixel black stroke outline by default.
     *
     * @param ulX    upper left position
     * @param ulY    upper left position
     */
    public Ball(double ulX, double ulY) {
        super(ulX,ulY,RADIUS*2,RADIUS*2);
        this.leftX = ulX;
        this.upperY = ulY;
        color(new Color(50,50,100,200));
    }

    public void color(Color ballColor) {
        this.setFilled(true);
        this.setFillColor(ballColor);
    }

    public double getLeftX() {
        return this.leftX;
    }

    public double getUpperY() {
        return this.upperY;
    }

    public double getRadius(){
        return RADIUS;
    }

    public double getxDirection(){
        return xDirection;
    }

    public double getyDirection(){
        return yDirection;
    }

    public void changeXDirection(){
        xDirection = -xDirection;
    }

    public void changeYDirection(){
        yDirection = -yDirection;
    }

    public void updatePosition() {
        leftX = leftX+xDirection;
        upperY = upperY +yDirection;
        this.moveBy(xDirection,yDirection);
    }

    public void hitScreen(BreakoutGame window) {
        if (this.leftX+RADIUS*2 >= window.getCanvasWidth() || this.leftX == 0) {
            this.changeXDirection();
        }
        if (this.upperY == 0) {
            this.changeYDirection();
        }
        if (this.upperY+RADIUS*2 >=window.getCanvasHeight()){
            resetBall(window);
            window.updateLives();
        }
    }


    public void hitBrick(BreakoutGame window, BrickWall wall){
        GraphicsObject upperRight = window.getElementAt(leftX+RADIUS*2,upperY);
        GraphicsObject upperLeft = window.getElementAt(leftX,upperY);
        GraphicsObject lowerRight = window.getElementAt(leftX+RADIUS*2,upperY+RADIUS*2);
        GraphicsObject lowerLeft = window.getElementAt(leftX,upperY+RADIUS*2);

        if(upperRight instanceof Brick){
            this.changeYDirection();
            window.remove(upperRight);
            wall.isHit();
        }

        if (upperLeft instanceof Brick && !upperLeft.equals(upperRight)){
            this.changeYDirection();
            window.remove(upperLeft);
            wall.isHit();
        }

        if(lowerLeft instanceof Brick &&!lowerLeft.equals(upperLeft) && !lowerLeft.equals(upperRight)){
            this.changeYDirection();
            window.remove(lowerLeft);
            wall.isHit();
        }

        if (lowerRight instanceof Brick && !lowerRight.equals(lowerLeft) && !lowerRight.equals(upperRight)){
            this.changeYDirection();
            window.remove(lowerRight);
            wall.isHit();
        }
    }

    public void hitPaddle(BreakoutGame window, Paddle paddle){
        GraphicsObject lowerLeft = window.getElementAt(leftX,upperY+RADIUS*2);
        GraphicsObject lowerRight = window.getElementAt(leftX+RADIUS*2,upperY+RADIUS*2);

        if ((lowerLeft instanceof Paddle || lowerRight instanceof Paddle)
                && (upperY+RADIUS*2) <= paddle.getPaddleY() ){
            this.changeYDirection();
        }
    }

    public void resetBall(BreakoutGame window){
        window.remove(this);
        window.createBall();
    }


}
