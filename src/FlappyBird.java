import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FlappyBird extends JPanel {
    public static int WIDTH = 500, HEIGHT = 800;
    private Player newPlayer = new Player();
    private Bird newBird;
    private Pipe[] allPipes;
    private static char JUMP = 'J';
    private char currentKey;
    private int gravity;
    private int timeSinceLastJump;

    public FlappyBird() {
        currentKey = (char) 0;
    }

    public void paint(Graphics g) {
        //Graphics2D g2 = (Graphics2D) g;
        //g2.setColor(Color.BLUE);
        //g2.fillRect(0, 0, WIDTH, HEIGHT);

        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g.fillOval(newBird.getX(), newBird.getY(), Bird.getThickness(), Bird.getThickness());
        g.drawString("Score: " + newPlayer.getScore(), 10,30);
        g2.setColor(Color.GREEN);
        g2.fillRect(allPipes[0].getCurrentX(), 0, allPipes[0].getWidth(), allPipes[0].getHeightDown());
        g2.fillRect(allPipes[0].getCurrentX(), HEIGHT-allPipes[0].getHeightUp(), allPipes[0].getWidth(), allPipes[0].getHeightUp());
        g2.fillRect(allPipes[1].getCurrentX(), 0, allPipes[1].getWidth(), allPipes[1].getHeightDown());
        g2.fillRect(allPipes[1].getCurrentX(), HEIGHT-allPipes[1].getHeightUp(), allPipes[1].getWidth(), allPipes[1].getHeightUp());
        g2.fillRect(allPipes[2].getCurrentX(), 0, allPipes[2].getWidth(), allPipes[2].getHeightDown());
        g2.fillRect(allPipes[2].getCurrentX(), HEIGHT-allPipes[2].getHeightUp(), allPipes[2].getWidth(), allPipes[2].getHeightUp());
    }

    public void updateWindow(JFrame jframe) {
        jframe.getContentPane().validate();
        jframe.repaint();
    }

    public void setup() {
        allPipes = new Pipe[3];
        gravity = 10;
        newBird = new Bird();
        newBird.setX(50);
        newBird.setY(HEIGHT/2);
        timeSinceLastJump = 0;

        for (int i = 0; i < 3; i++) {
            allPipes[i] = new Pipe();
            allPipes[i].generatePipe(i);
            //System.out.println("Pipe Number " + i);
            //allPipes[i].printPipeToConsole();
        }
    }

    private Boolean collision() {
        Boolean collisionOn = true;
        if (collisionOn) {
            for (int i = 0; i < 3; i++) {
                if(newBird.getX() > allPipes[i].getCurrentX()-Bird.getThickness() && newBird.getX() <  allPipes[i].getCurrentX()+allPipes[i].getWidth()/2+Bird.getThickness()) {
                    //System.out.println("x value in range");
                    if (newBird.getY() > allPipes[i].getHeightDown() && newBird.getY() < HEIGHT - allPipes[i].getHeightUp() - Bird.getThickness()) {
                        //System.out.println("no collision");
                        //return true;
                    } else {
                        //System.out.println("collision1 = true");
                        return true;
                    }
                }
            }
        }

        //checks if too hight or too low
        if (newBird.getY() <= 0 || newBird.getY() >= HEIGHT) {
            System.out.println("collision2 = true");
            return true;
        }
        return false;
    }

    public Boolean gameloop(JFrame frame) {
        int delayTime = 30;

        //add key listener
        frame.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent ke) {
                //if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                    //System.out.println("jumping up");
                    //newBird.jump();
                    newBird.setSpeed(-15);
                    currentKey = (char) 0;
                //}
                //System.out.println("Key Typed" + ke.getKeyChar());
            }
            public void keyPressed(KeyEvent ke) {}
            public void keyReleased(KeyEvent ke) {
                //currentKey = (char) 0;
            }
        });

        updateWindow(frame);

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
            newBird.setY(newBird.getY() + newBird.getSpeed()*3/4);

            //shift everything a few pixels to the left;
            int pixel = 1;
            for (int i = 0; i < 3; i++) {
                allPipes[i].setCurrentX(allPipes[i].getCurrentX() - pixel);
            }

            //increase the score of the player
            for (int i = 0; i < 3; i++) {
                if (newBird.getX() == allPipes[i].getCurrentX()) {
                    newPlayer.setScore(newPlayer.getScore() + 1);
                }
            }

            //shift the pipes to generate a new one
            for (int i = 0; i < 3; i++) {
                if (allPipes[i].getCurrentX() == -allPipes[i].getWidth()) {
                    allPipes[i].generatePipe(2);
                }
            }
        }
        updateWindow(frame);
        return true;
    }

    public static void main(String [] args) {
        FlappyBird flappyBird = new FlappyBird();
        flappyBird.currentKey = (char) 0;
        JFrame frame = new JFrame("Flappy Bird");
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        frame.getContentPane().add(flappyBird);

        frame.setVisible(true);

        flappyBird.newPlayer.setName("Felix");
        flappyBird.setup();
        Boolean gameOver = flappyBird.gameloop(frame);
        if (gameOver == true) {
            System.out.println("You lost!");
            System.out.println(flappyBird.newPlayer.getName() + " got " + flappyBird.newPlayer.getScore() + " points!");
        }
        return;
    }
}