package com.example.flappybird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.MotionEvent;
import android.view.View;

/**
 * Main Class.
 */
public class FlappyBird extends View {
    /**
     * width and height of the window.
     */
    public static int WIDTH = 500, reWidth = 500, HEIGHT = 800, reHeight = 800;
    /**
     * the amount of pipes in a frame.
     */
    public final static int AMOUNTPIPES = 3;
    /**
     * player with score and name.
     */
    private Player newPlayer;
    /**
     * the bird to jump through pipes.
     */
    private Bird newBird;
    /**
     * pipe array to store the 3 pipes in.
     */
    private Pipe[] allPipes;
    /**
     * the time since the last jump.
     */
    private int timeSinceLastJump;
    /**
     * image for the bird.
     */
    private Bitmap birdImg;
    /**
     * height and width of the bird img in pixels.
     */
    private final int BIRDIMGHEIGHT = 535, BIRDIMGWIDTH = 726;
    /**
     * background image.
     */
    private Bitmap backgroundImg;
    /**
     * height and width of the background img in pixels.
     */
    private final int BACKHEIGHT = 1200, BACKWIDTH = 727;
    /**
     * pipe img for the pipe looking downwards and upwards.
     */
    private Bitmap pipeDownImg, pipeUpImg;
    /**
     * height and width of the pipe images in pixels.
     */
    private final int PIPEHEIGHT = 246, PIPEWIDTH = 79;
    /**
     * true if gameover, false if still running.
     */
    private boolean gameOver;
    /**
     * game only start if jump key was pressed once.
     */
    private boolean firstPressed;
    /**
     * true if player clicked on restart, false if not.
     */
    private boolean clickedRestart;

    private boolean init;

    Paint myPaint = new Paint();

    /**
     * creates the bird.
     */
    private void createBird() {
        newBird = new Bird();
        newBird.setX(WIDTH / 10);
        newBird.setY(HEIGHT / 2);
    }

    /**
     * creates the pipes.
     */
    private void createPipes() {
        allPipes = new Pipe[AMOUNTPIPES];
        for (int i = 0; i < AMOUNTPIPES; i++) {
            allPipes[i] = new Pipe();
            allPipes[i].generatePipe(i);
            allPipes[i].setCurrentX(allPipes[i].getCurrentX() + WIDTH / 5);
            //System.out.println("Pipe Number " + i);
            //allPipes[i].printPipeToConsole();
        }
    }

    private void createPlayer() {
        newPlayer = new Player();
        newPlayer.setName("Felix");
    }

    /**
     * reads in the images.
     */
    private void readImg() {
        birdImg = BitmapFactory.decodeResource(getResources(), R.drawable.charizard);
        birdImg = Bitmap.createScaledBitmap(birdImg, (Bird.getThickness() * BIRDIMGWIDTH / BIRDIMGHEIGHT)*15/10, (Bird.getThickness())*15/10, false);
        backgroundImg = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        backgroundImg = Bitmap.createScaledBitmap(backgroundImg, WIDTH, HEIGHT, false);
        pipeDownImg = BitmapFactory.decodeResource(getResources(), R.drawable.pipedown);
        pipeUpImg = BitmapFactory.decodeResource(getResources(), R.drawable.pipeup);
    }

    /**
     * initialises the constants.
     */
    private void initConstants() {
        this.clickedRestart = false;
        this.firstPressed = false;
        this.gameOver = false;
        timeSinceLastJump = 0;
    }

    /**
     * calls functions:
     * - constants.
     * - read img.
     * - create bird.
     * - create pipe.
     */
    private void setup() {
        initConstants();
        readImg();
        createBird();
        createPipes();
        createPlayer();
        init = false;
    }

    FlappyBird(Context context) {
        super(context);
        setup();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        /*
        if (firstPressed) {
            canvas.drawText("Touched!", 50, 50, myPaint);
        }
        */
        HEIGHT = canvas.getHeight();
        WIDTH = canvas.getWidth();
        Bird.setThickness(HEIGHT / reHeight * 40);

        if (!init) {
            setup();
            init = true;
        }

        super.onDraw(canvas);

        canvas.drawBitmap(backgroundImg, 0, 0, myPaint);

        /*
        Rect p1d = new Rect(allPipes[0].getCurrentX(), 0, allPipes[0].getCurrentX() + allPipes[0].getWidth(), allPipes[0].getHeightDown());
        Rect p1u = new Rect(allPipes[0].getCurrentX(), HEIGHT - allPipes[0].getHeightUp(), allPipes[0].getCurrentX() + allPipes[0].getWidth(), HEIGHT);
        Rect p2d = new Rect(allPipes[1].getCurrentX(), 0, allPipes[1].getCurrentX() + allPipes[1].getWidth(), allPipes[1].getHeightDown());
        Rect p2u = new Rect(allPipes[1].getCurrentX(), HEIGHT - allPipes[1].getHeightUp(), allPipes[1].getCurrentX() + allPipes[1].getWidth(), HEIGHT);
        Rect p3d = new Rect(allPipes[2].getCurrentX(), 0, allPipes[2].getCurrentX() + allPipes[2].getWidth(), allPipes[2].getHeightDown());
        Rect p3u = new Rect(allPipes[2].getCurrentX(), HEIGHT - allPipes[2].getHeightUp(), allPipes[2].getCurrentX() + allPipes[2].getWidth(), HEIGHT);


        myPaint.setColor(Color.GREEN);
        canvas.drawRect(p1d, myPaint);
        canvas.drawRect(p1u, myPaint);
        canvas.drawRect(p2d, myPaint);
        canvas.drawRect(p2u, myPaint);
        canvas.drawRect(p3d, myPaint);
        canvas.drawRect(p3u, myPaint);
        */

        //Bitmap.createScaledBitmap(pipeDownImg, allPipes[0].getWidth(), allPipes[0].getHeightDown(), false);
        //Bitmap.createScaledBitmap(pipeUpImg, allPipes[0].getWidth(), allPipes[0].getHeightUp(), false);
        canvas.drawBitmap(Bitmap.createScaledBitmap(pipeDownImg, allPipes[0].getWidth(), allPipes[0].getHeightDown(), false), allPipes[0].getCurrentX(), 0, myPaint);
        canvas.drawBitmap(Bitmap.createScaledBitmap(pipeDownImg, allPipes[1].getWidth(), allPipes[1].getHeightDown(), false), allPipes[1].getCurrentX(), 0, myPaint);
        canvas.drawBitmap(Bitmap.createScaledBitmap(pipeDownImg, allPipes[2].getWidth(), allPipes[2].getHeightDown(), false), allPipes[2].getCurrentX(), 0, myPaint);
        canvas.drawBitmap(Bitmap.createScaledBitmap(pipeUpImg, allPipes[0].getWidth(), allPipes[0].getHeightUp(), false), allPipes[0].getCurrentX(), HEIGHT - allPipes[0].getHeightUp(), myPaint);
        canvas.drawBitmap(Bitmap.createScaledBitmap(pipeUpImg, allPipes[1].getWidth(), allPipes[1].getHeightUp(), false), allPipes[1].getCurrentX(), HEIGHT - allPipes[1].getHeightUp(), myPaint);
        canvas.drawBitmap(Bitmap.createScaledBitmap(pipeUpImg, allPipes[2].getWidth(), allPipes[2].getHeightUp(), false), allPipes[2].getCurrentX(), HEIGHT - allPipes[2].getHeightUp(), myPaint);

        myPaint.setColor(Color.BLACK);
        canvas.drawBitmap(birdImg, (newBird.getX()-Bird.getThickness()/2), newBird.getY()-Bird.getThickness()/2, myPaint);
        //canvas.drawCircle(newBird.getX(), newBird.getY(), Bird.getThickness(), myPaint);

        Typeface t = Typeface.create("Arial", Typeface.ITALIC);
        myPaint.setTextSize(24 * HEIGHT / reHeight);
        myPaint.setTypeface(t);
        String text = "Score: " + newPlayer.getScore();

        Rect bounds = new Rect();
        myPaint.getTextBounds(text, 0, text.length(), bounds);

        int scoreX = (WIDTH - bounds.width()) / 2;
        canvas.drawText(text, scoreX, HEIGHT/14, myPaint);

        if (this.gameOver) {
            myPaint.setTextSize(50 * HEIGHT / reHeight);
            String lost = "GameOver!";
            myPaint.getTextBounds(lost, 0, lost.length(), bounds);
            int gameOverX = (WIDTH - bounds.width()) / 2;
            canvas.drawText(lost, gameOverX, HEIGHT / 2, myPaint);
        }

        invalidate();
    }

    @Override
    public boolean performClick() {
        firstPressed = true;
        newBird.setSpeed(-15 * HEIGHT / reHeight * 3 / 4);
        return super.performClick();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        performClick();
        return super.onTouchEvent(event);
    }

    /**
     * checks whether the bird went out of window, or hit a pipe
     *
     * @return true if there is a collision, false if there is no collision
     */
    private boolean collision() {
        for (int i = 0; i < AMOUNTPIPES; i++) {
            if (newBird.getX() > allPipes[i].getCurrentX() - Bird.getThickness() && newBird.getX() < allPipes[i].getCurrentX() + allPipes[i].getWidth() / 2 + Bird.getThickness()) {
                if (newBird.getY() > allPipes[i].getHeightDown() && newBird.getY() < HEIGHT - allPipes[i].getHeightUp() - Bird.getThickness()) {
                } else {
                    return true;
                }
            }
        }

        //checks if too high or too low
        if (newBird.getY() <= 0 || newBird.getY() >= HEIGHT) {
            return true;
        }
        return false;
    }

    /**
     * function to restart the game.
     */
    private boolean restart() {
        System.out.println("button now here");
        setup();
        return clickedRestart;
    }

    /**
     * the actual gameloop.
     * implements a keylistener -> you can jump up by pressing any button.
     *
     * @return true if gameover
     */
    public void gameloop() {

        if (gameOver) {
            restart();
            gameOver = false;

            return;
        }

        if (firstPressed) {
            //check for collision
            if (collision()) {
                this.gameOver = true;
                //restart();
                return;
            }

            //set the birds new y position according to the current speed
            newBird.setSpeed(newBird.getSpeed() + 1 * HEIGHT / reHeight);
            newBird.setY(newBird.getY() + newBird.getSpeed() * 3 / 4);

            //shift everything a few pixels to the left;
            int pixel = 2 * HEIGHT / reHeight;
            for (int i = 0; i < AMOUNTPIPES; i++) {
                allPipes[i].setCurrentX(allPipes[i].getCurrentX() - pixel);
            }

            //increase the score of the player
            for (int i = 0; i < AMOUNTPIPES; i++) {
                if (newBird.getX() > allPipes[i].getCurrentX() && newBird.getX() < allPipes[i].getCurrentX() + 10 && !allPipes[i].getCounted()) {
                    allPipes[i].setCounted(true);
                    newPlayer.setScore(newPlayer.getScore() + 1);
                }
            }

            //shift the pipes to generate a new one
            for (int i = 0; i < AMOUNTPIPES; i++) {
                if (allPipes[i].getCurrentX() < -allPipes[i].getWidth()) {
                    allPipes[i].generatePipe(2);
                }
            }
        }
    }

    /**
     * starts the gameloop.
     */
    public void startGame() {
        gameloop();
        if (gameOver) {
            //restart();
            System.out.println("You lost!");
            System.out.println(newPlayer.getName() + " got " + newPlayer.getScore() + " points!");
        }
    }
}
