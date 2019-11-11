package Breakout;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BrickWall {
    private int numBricks = 100;
    private List<Brick> bricks = new ArrayList<>();
    private BreakoutGame window;
    private int startX = 0, startY = 30;
    private Color color;

    public BrickWall(BreakoutGame window) {
        this.window = window;
    }

    public void addBrick() {
        for (int i=0;i < numBricks; i++) {
            if (i < 10) {
                color = new Color(250, 38, 20);
            }
            else if (i <20){
                color = new Color(255, 134, 7);
            }
            else if (i < 30) {
                color = new Color(255, 253, 138);
            }
            else if (i < 40) {
                color = new Color(159, 255, 46);
            }
            else if (i < 50) {
                color = new Color(94, 210, 132);
            }
            else if (i < 60) {
                color = new Color(37, 195, 255);
            }
            else if (i < 70) {
                color = new Color(17, 83, 255);
            }
            else if (i < 80) {
                color = new Color(107, 51, 255);
            }
            else if (i<90){
                color = new Color(183, 26, 255);
            }
            else if (i < 100) {
                color = new Color(242, 255, 229);
            }
            Brick brick = new Brick(startX,startY,color);
            window.add(brick);
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
            window.remove(b);
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
