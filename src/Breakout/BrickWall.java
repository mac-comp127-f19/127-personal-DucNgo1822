package Breakout;

import comp127graphics.CanvasWindow;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BrickWall {
    private int numBricks = 400;
    private List<Brick> bricks = new ArrayList<>();
    private CanvasWindow canvas;
    private int startX = 0, startY = 30;
    private Color color;

    public BrickWall(CanvasWindow canvas) {
        this.canvas = canvas;
    }

    public void addBrick() {
        for (int i=0;i < numBricks; i++) {
            if (i < 40) {
                color = new Color(250, 38, 20);
            }
            else if (i < 80){
                color = new Color(255, 134, 7);
            }
            else if (i < 120) {
                color = new Color(255, 253, 138);
            }
            else if (i < 160) {
                color = new Color(159, 255, 46);
            }
            else if (i < 200) {
                color = new Color(94, 210, 132);
            }
            else if (i < 240) {
                color = new Color(37, 195, 255);
            }
            else if (i < 280) {
                color = new Color(17, 83, 255);
            }
            else if (i < 320) {
                color = new Color(107, 51, 255);
            }
            else if (i< 360){
                color = new Color(183, 26, 255);
            }
            else if (i < 400) {
                color = new Color(242, 255, 229);
            }
            Brick brick = new Brick(startX,startY,color);
            canvas.add(brick);
            bricks.add(brick);
            startX = startX + brick.getBrickWidth();
            if (startX == 800) {
                startY = startY + brick.getBrickHeight();
                startX = 0;
            }
        }
    }

    public void removeAllBricks(){
        for(Brick b : bricks){
            canvas.remove(b);
        }
        bricks.clear();
    }

    public void isHit(){
        numBricks--;
    }

    public boolean isEmpty(){
        if (numBricks == 0){
            return true;
        }
        else{
            return false;
        }
    }


    public int getNumBricks(){
        return numBricks;
    }

    public void setNumBricks(int newNumBricks){
        numBricks = newNumBricks;
    }



}
