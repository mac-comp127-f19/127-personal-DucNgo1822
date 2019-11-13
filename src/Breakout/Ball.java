package Breakout;

import comp127graphics.CanvasWindow;
import comp127graphics.Ellipse;
import comp127graphics.GraphicsObject;

import java.awt.*;

public class Ball extends Ellipse{
    private static final double RADIUS = 10;
    private double xDirection = 4, yDirection = 4, upperX, upperY;

    /**
     *
     *
     * Constructor to create the ellipse object and initialize its instance variables.
     * The default creates an ellipse at position x,y with a bounding rectangle of width and height.
     * The ellipse is drawn with a 1 pixel black stroke outline by default.
     *
     * @param ulX    upper left position for the ball
     * @param ulY    upper left position for the ball
     */
    public Ball(double ulX, double ulY) {
        super(ulX,ulY,RADIUS*2,RADIUS*2);
        this.upperX = ulX;
        this.upperY = ulY;
        color(new Color(50,50,100,200));
    }

    public void color(Color ballColor) {
        this.setFilled(true);
        this.setFillColor(ballColor);
    }

    public double getLeftX() {
        return this.upperX;
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
        upperX = upperX+xDirection;
        upperY = upperY +yDirection;
        this.moveBy(xDirection,yDirection);
    }

    public void hitScreen(BreakoutGame canvas) {
        if (this.upperX+RADIUS*2 >= canvas.getCanvasWidth() || this.upperX <= 0) {
            this.changeXDirection();
        }
        if (this.upperY == 0) {
            this.changeYDirection();

        }
        if ( this.upperY + RADIUS * 2 >= canvas.getCanvasHeight()){
            resetBall(canvas);
            canvas.updateLives();
        }
    }


    public void hitBrick(CanvasWindow canvas, BrickWall wall){
        GraphicsObject upperRight = canvas.getElementAt(upperX+RADIUS*2,upperY);
        GraphicsObject upperLeft = canvas.getElementAt(upperX,upperY);
        GraphicsObject lowerRight = canvas.getElementAt(upperX+RADIUS*2,upperY+RADIUS*2);
        GraphicsObject lowerLeft = canvas.getElementAt(upperX,upperY+RADIUS*2);

        if(upperRight instanceof Brick){
            this.changeYDirection();
            canvas.remove(upperRight);
            wall.isHit();
        }

        if (upperLeft instanceof Brick && !upperLeft.equals(upperRight)){
            this.changeYDirection();
            canvas.remove(upperLeft);
            wall.isHit();
        }

        if(lowerLeft instanceof Brick &&!lowerLeft.equals(upperLeft) && !lowerLeft.equals(upperRight)){
            this.changeYDirection();
            canvas.remove(lowerLeft);
            wall.isHit();
        }

        if (lowerRight instanceof Brick && !lowerRight.equals(lowerLeft) && !lowerRight.equals(upperRight)){
            this.changeYDirection();
            canvas.remove(lowerRight);
            wall.isHit();
        }
    }

    public void hitPaddle(CanvasWindow canvas, Paddle paddle){
        GraphicsObject lowerLeft = canvas.getElementAt(upperX,upperY+RADIUS*2);
        GraphicsObject lowerRight = canvas.getElementAt(upperX+RADIUS*2,upperY+RADIUS*2);

        if ((lowerLeft instanceof Paddle || lowerRight instanceof Paddle) || (upperY + RADIUS * 2 == paddle.getPaddleY() || (upperY + RADIUS *2 == paddle.getPaddleX() + paddle.getPaddleHeight()))) {
            this.changeYDirection();
        }
    }

    public void resetBall(BreakoutGame canvas){
        canvas.getCanvas().remove(this);
        canvas.createBall();
    }






}
