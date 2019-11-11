package Breakout;

import comp127graphics.CanvasWindow;
import comp127graphics.GraphicsText;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class BreakoutGame extends CanvasWindow implements MouseMotionListener {

    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 1000;

    private GraphicsText livesText, loseText, winText;
    private int livesCount = 4;
    private Ball ball;
    private BrickWall wall;
    private Paddle paddle;

    /**
     * Constructor
     *
     */
    public BreakoutGame() {
        super("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);
        this.setBackground(new Color(176, 235, 255, 158));

    }

    /**
     * Main function to run the program once
     * @param args
     */
    public static void main(String[] args){
        BreakoutGame program = new BreakoutGame();
        program.createEverything();
    }

    /**
     * Create everything to display
     */
    public void createEverything() {
        this.createPaddle();
        this.createBrickWall();
        this.createLivesText();
        this.createBall();
    }

    public void createBrickWall() {
        wall = new BrickWall(this);
        wall.addBrick();
    }

    public void createBall(){
        ball = new Ball(300,300);
        this.add(ball);
    }

    public void createPaddle(){
        paddle = new Paddle(0,900);
        this.add(paddle);
    }

    public void createLivesText(){
        livesText = new GraphicsText(""+livesCount,CANVAS_WIDTH/2,CANVAS_HEIGHT/2);
        if (livesCount == 4){
            livesText.setFillColor(new Color(250,250,60));
        }
        else if (livesCount == 3){
            livesText.setFillColor(new Color(0,250,0));
        }
        else if (livesCount == 2){
            livesText.setFillColor(new Color(55, 134,150));
        }
        else if (livesCount == 1) {
            livesText.setFillColor(new Color(250, 3, 6));
        }
        this.add(livesText);
    }

    /**
     * Run a round and check if win or lose
     */
    public void runRound() {
        while (livesCount > 0 && !wall.isEmpty()) {
            ball.updatePosition();
            ball.hitScreen(this);
            ball.hitBrick(this,wall);
            ball.hitPaddle(this, paddle);
            this.pause(25);
        }
        if (livesCount == 0) {
            lose();
        }
        if (wall.isEmpty()) {
            win();
        }
    }

    /**
     * If win: remove all from the canvas and add a winning text and exit the program
     */
    public void win(){
        this.removeAll();
        winText = new GraphicsText("YOU WON!!!", 150, CANVAS_HEIGHT/2 +50);
        this.add(winText);
        pause(5000);
        System.exit(0);

    }

    /**
     * If lose: remove all from the canvas and add a losing text and exit the program
     */
    public void lose(){
        this.removeAll();
        loseText  = new GraphicsText(" YOU LOST :(((" , 150, CANVAS_HEIGHT/2 +50);
        this.add(loseText);
        pause(5000);
        System.exit(0);
    }
    public int getCanvasWidth(){
        return CANVAS_WIDTH;
    }

    public int getCanvasHeight(){
        return CANVAS_HEIGHT;
    }

    public int getLivesCount(){
        return livesCount;
    }

    public void updateLives(){
        livesCount--;
        remove(livesText);
        createLivesText();
    }


    @Override
    public void mouseDragged(MouseEvent e) {
    }


    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        if ((x+paddle.getPaddleWidth()) <= CANVAS_WIDTH){
            paddle.updatePosition(x);
        }
    }
}