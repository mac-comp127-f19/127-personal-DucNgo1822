package Breakout;

import comp127graphics.CanvasWindow;
import comp127graphics.FontStyle;
import comp127graphics.GraphicsText;
import java.awt.*;


public class BreakoutGame {

    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 1000;

    private CanvasWindow canvas;

    private GraphicsText livesText, loseText, winText;
    private int livesCount = 3;
    private Ball ball;
    private BrickWall wall;
    private Paddle paddle;

    /**
     * Constructor
     *
     */
    public BreakoutGame() {
        canvas = new CanvasWindow ("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(new Color(176, 235, 255, 158));
    }

    /**
     * Main function to run the program once
     * @param args
     */
    public static void main(String[] args){
        BreakoutGame program = new BreakoutGame();
        program.createEverything();
        program.makepaddlemove();
        program.runRound();
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
        wall = new BrickWall(canvas);
        wall.addBrick();
    }

    public void createBall(){
        ball = new Ball(400,400);
        canvas.add(ball);
    }

    public void createPaddle(){
        paddle = new Paddle(400,700);
        canvas.add(paddle);
    }

    public void createLivesText(){
        livesText = new GraphicsText("Number of lives: "+livesCount, 300, 600);
        if (livesCount == 3){
            livesText.setFillColor(new Color(250,250,60));
            livesText.setFont(FontStyle.BOLD, 16);
        }
        else if (livesCount == 2){
            livesText.setFillColor(new Color(0,250,0));
            livesText.setFont(FontStyle.BOLD,16);
        }
        else if (livesCount == 1){
            livesText.setFillColor(new Color(55, 134,150));
            livesText.setFont(FontStyle.BOLD, 16);
        }
        canvas.add(livesText);
    }

    /**
     * Run a round and check if win or lose
     */
    public void runRound() {
            canvas.animate(() ->{
                if (livesCount > 0 || !wall.isEmpty()) {
                    ball.updatePosition();
                    ball.hitScreen(this);
                    ball.hitBrick(canvas, wall);
                    ball.hitPaddle(canvas, paddle);
                    canvas.pause(25);
                }
                if (livesCount == 0) {
                    lose();
                }
                else if (wall.isEmpty()) {
                    win();
                }
            });

    }

    /**
     * If win: remove all from the canvas and add a winning text and exit the program
     */
    public void win(){
        winText = new GraphicsText("YOU WON!!!", 150, CANVAS_HEIGHT/2 +50);
        canvas.add(winText);
        canvas.draw();
        canvas.pause(5000);
        canvas.removeAll();
        System.exit(0);

    }

    /**
     * If lose: remove all from the canvas and add a losing text and exit the program
     */
    public void lose(){
        loseText  = new GraphicsText(" YOU LOST :(((" , 300, 700);
        canvas.add(loseText);
        canvas.draw();
        canvas.pause(5000);
        canvas.removeAll();
        System.exit(0);
    }
    public int getCanvasWidth(){
        return CANVAS_WIDTH;
    }

    public CanvasWindow getCanvas(){
        return canvas;
    }

    public int getCanvasHeight(){
        return CANVAS_HEIGHT;
    }

    public int getLivesCount(){
        return livesCount;
    }

    public void updateLives(){
        livesCount--;
        canvas.remove(livesText);
        createLivesText();
    }

    public void makepaddlemove() {
        canvas.onMouseMove(i -> {
            paddle.setCenter(i.getPosition().getX(), 700);
        });
    }



}