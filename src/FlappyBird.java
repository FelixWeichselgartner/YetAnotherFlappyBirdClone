import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

/**
 * Main Class.
 */
public class FlappyBird extends JPanel {
    /**
     * width and height of the window.
     */
    public final static int WIDTH = 500, HEIGHT = 800;
    /**
     * the amount of pipes in a frame.
     */
    public final static int AMOUNTPIPES = 3;
    /**
     * player with score and name.
     */
    private Player newPlayer = new Player();
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
    private Image birdImg;
    /**
     * height and width of the bird img in pixels.
     */
    private final int BIRDIMGHEIGHT = 535, BIRDIMGWIDTH = 726;
    /**
     * background image.
     */
    private Image backgroundImg;
    /**
     * height and width of the background img in pixels.
     */
    private final int BACKHEIGHT = 1200, BACKWIDTH = 727;
    /**
     * pipe img for the pipe looking downwards and upwards.
     */
    private Image pipeDownImg, pipeUpImg;
    /**
     * height and width of the pipe images in pixels.
     */
    private final int PIPEHEIGHT = 246, PIPEWIDTH = 79;
    /**
     * faktor to make the collision easier -> less frustration.
     */
    private final int EASIERFACTOR = 20;


    private FlappyBird() {
        allPipes = new Pipe[AMOUNTPIPES];
        newBird = new Bird();
        newBird.setX(50);
        newBird.setY(HEIGHT / 2);
        timeSinceLastJump = 0;
        try {
            pipeUpImg = ImageIO.read(new File("img/PipeUp.png"));
            pipeDownImg = ImageIO.read(new File("img/PipeDown.png"));
            birdImg = ImageIO.read(new File("img/Charizard.png"));
            backgroundImg = ImageIO.read(new File("img/Background.png"));
        } catch (IOException e) {

        }


        for (int i = 0; i < AMOUNTPIPES; i++) {
            allPipes[i] = new Pipe();
            allPipes[i].generatePipe(i);
            //System.out.println("Pipe Number " + i);
            //allPipes[i].printPipeToConsole();
        }
    }

    /**
     * paints the pipes, bird and the score
     *
     * @param g
     */
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(backgroundImg, 0, 0, WIDTH, HEIGHT, this);
        g2.drawImage(pipeDownImg, allPipes[0].getCurrentX(), 0,
                allPipes[0].getWidth(), allPipes[0].getHeightDown(), this);
        g2.drawImage(pipeUpImg, allPipes[0].getCurrentX(), HEIGHT - allPipes[0].getHeightUp(),
                allPipes[0].getWidth(), allPipes[0].getHeightUp(), this);
        g2.drawImage(pipeDownImg, allPipes[1].getCurrentX(), 0,
                allPipes[1].getWidth(), allPipes[1].getHeightDown(), this);
        g2.drawImage(pipeUpImg, allPipes[1].getCurrentX(), HEIGHT - allPipes[1].getHeightUp(),
                allPipes[1].getWidth(), allPipes[1].getHeightUp(), this);
        g2.drawImage(pipeDownImg, allPipes[2].getCurrentX(), 0,
                allPipes[2].getWidth(), allPipes[2].getHeightDown(), this);
        g2.drawImage(pipeUpImg, allPipes[2].getCurrentX(), HEIGHT - allPipes[2].getHeightUp(),
                allPipes[2].getWidth(), allPipes[2].getHeightUp(), this);
        g.setColor(Color.BLACK);
        g.drawString("Score: " + newPlayer.getScore(), 10, 30);
        g2.drawImage(birdImg, newBird.getX(), newBird.getY(),
                Bird.getThickness() * BIRDIMGWIDTH / BIRDIMGHEIGHT, Bird.getThickness(), this);
    }

    /**
     * updates the window
     *
     * @param JFrame frame to update
     */
    private void updateWindow(JFrame jframe) {
        jframe.getContentPane().validate();
        jframe.repaint();
    }

    /**
     * checks whether the bird went out of window, or hit a pipe
     *
     * @return true if there is a collision, false if there is no collision
     */
    private boolean collision() {
        boolean collisionOn = true;
        if (collisionOn) {
            for (int i = 0; i < AMOUNTPIPES; i++) {
                if (newBird.getX() > allPipes[i].getCurrentX() - Bird.getThickness() + EASIERFACTOR && newBird.getX() < allPipes[i].getCurrentX() + allPipes[i].getWidth() / 2 + Bird.getThickness() - EASIERFACTOR) {
                    if (newBird.getY() > allPipes[i].getHeightDown() && newBird.getY() < HEIGHT - allPipes[i].getHeightUp() - Bird.getThickness()) {
                    } else {
                        return true;
                    }
                }
            }
        }

        //checks if too hight or too low
        if (newBird.getY() <= 0 || newBird.getY() >= HEIGHT) {
            return true;
        }
        return false;
    }

    /**
     * the actual gameloop.
     * implements a keylistener -> you can jump up by pressing any button.
     *
     * @param frame window frame
     * @return true if gameover
     */
    private boolean gameloop(JFrame frame) {
        final int delayTime = 30;

        //add key listener
        frame.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent ke) {
                newBird.setSpeed(-15);
            }

            public void keyPressed(KeyEvent ke) {
            }

            public void keyReleased(KeyEvent ke) {

            }
        });

        while (true) {
            //update the current window
            updateWindow(frame);

            //check for collision
            if (collision()) {
                break;
            }

            //delay the program a few seconds so its not to fast
            try {
                Thread.sleep(delayTime);
                timeSinceLastJump = timeSinceLastJump + delayTime;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //set the birds new y position according to the current speed
            newBird.setSpeed(newBird.getSpeed() + 1);
            newBird.setY(newBird.getY() + newBird.getSpeed() * 3 / 4);

            //shift everything a few pixels to the left;
            int pixel = 1;
            for (int i = 0; i < AMOUNTPIPES; i++) {
                allPipes[i].setCurrentX(allPipes[i].getCurrentX() - pixel);
            }

            //increase the score of the player
            for (int i = 0; i < AMOUNTPIPES; i++) {
                if (newBird.getX() == allPipes[i].getCurrentX()) {
                    newPlayer.setScore(newPlayer.getScore() + 1);
                }
            }

            //shift the pipes to generate a new one
            for (int i = 0; i < AMOUNTPIPES; i++) {
                if (allPipes[i].getCurrentX() == -allPipes[i].getWidth()) {
                    allPipes[i].generatePipe(2);
                }
            }
        }
        updateWindow(frame);
        return true;
    }

    /**
     * main method.
     * sets the JFrame, player, bird,
     * initialises with the setup and starts the gameloop.
     *
     * @param args
     */
    public static void main(String[] args) {
        FlappyBird flappyBird = new FlappyBird();
        JFrame frame = new JFrame("Flappy Bird");
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(flappyBird);
        frame.setVisible(true);
        flappyBird.newPlayer.setName("Felix");
        boolean gameOver = flappyBird.gameloop(frame);
        if (gameOver == true) {
            System.out.println("You lost!");
            System.out.println(flappyBird.newPlayer.getName() + " got " + flappyBird.newPlayer.getScore() + " points!");
        }
    }
}
