import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FlappyBird extends JPanel {
    public static int WIDTH = 500, HEIGHT = 800;
    private Player newPlayer = new Player();
    private Bird newBird;
    private Pipe[] allPipes;
    private char currentKey;
    private int gravity;

    public FlappyBird() {
    }

    public void paint(Graphics g) {
        //Graphics2D g2 = (Graphics2D) g;
        //g2.setColor(Color.BLUE);
        //g2.fillRect(0, 0, WIDTH, HEIGHT);

        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g.fillOval(newBird.getX(), newBird.getY(), newBird.getThickness(), newBird.getThickness());
        g.drawString("Score: " + newPlayer.getScore(), 10,30);
        g2.setColor(Color.GREEN);
        g2.fillRect(allPipes[0].getCurrentX(), 0, allPipes[0].getWidth(), allPipes[0].getHeightDown());
        g2.fillRect(allPipes[0].getCurrentX(), HEIGHT-allPipes[0].getHeightUp(), allPipes[0].getWidth(), allPipes[0].getHeightUp());
        g2.fillRect(allPipes[1].getCurrentX(), 0, allPipes[1].getWidth(), allPipes[1].getHeightDown());
        g2.fillRect(allPipes[1].getCurrentX(), HEIGHT-allPipes[1].getHeightUp(), allPipes[1].getWidth(), allPipes[1].getHeightUp());
        g2.fillRect(allPipes[2].getCurrentX(), 0, allPipes[2].getWidth(), allPipes[2].getHeightDown());
        g2.fillRect(allPipes[2].getCurrentX(), HEIGHT-allPipes[2].getHeightUp(), allPipes[2].getWidth(), allPipes[2].getHeightUp());
    }

    public void repaint(Graphics g) {
        /*
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g.fillOval(newBird.getX(), newBird.getY(), newBird.getThickness(), newBird.getThickness());
        g.drawString("Score: " + newPlayer.getScore(), 10,30);
        g2.setColor(Color.GREEN);
        g2.fillRect(allPipes[0].getCurrentX(), 0, allPipes[0].getWidth(), allPipes[0].getHeightDown());
        g2.fillRect(allPipes[0].getCurrentX(), HEIGHT-allPipes[0].getHeightUp(), allPipes[0].getWidth(), allPipes[0].getHeightUp());
        g2.fillRect(allPipes[1].getCurrentX(), 0, allPipes[1].getWidth(), allPipes[1].getHeightDown());
        g2.fillRect(allPipes[1].getCurrentX(), HEIGHT-allPipes[1].getHeightUp(), allPipes[1].getWidth(), allPipes[1].getHeightUp());
        g2.fillRect(allPipes[2].getCurrentX(), 0, allPipes[2].getWidth(), allPipes[2].getHeightDown());
        g2.fillRect(allPipes[2].getCurrentX(), HEIGHT-allPipes[2].getHeightUp(), allPipes[2].getWidth(), allPipes[2].getHeightUp());
        */
    }

    public void setup() {
        allPipes = new Pipe[3];
        gravity = 10;
        newBird = new Bird();
        newBird.setX(50);
        newBird.setY(HEIGHT/2);
        gravity = 10;

        for (int i = 0; i < 3; i++) {
            allPipes[i] = new Pipe();
            allPipes[i].generatePipe(i + 1);
            //System.out.println("Pipe Number " + i);
            //allPipes[i].printPipeToConsole();
        }
    }

    private Boolean collision() {
        for (int i = 0; i < 3; i++) {
            if(newBird.getX() > allPipes[i].getCurrentX()-5 && newBird.getX() < allPipes[i].getCurrentX()+5) {
                System.out.println("x value in range");
                if (newBird.getY() > allPipes[i].getHeightUp() || newBird.getY() < allPipes[i].getHeightDown()) {
                    System.out.println("collision1 = true");
                    return true;
                }
            }
        }
        if (newBird.getY() <= 0 || newBird.getY() >= HEIGHT) {
            System.out.println("collision2 = true");
            return true;
        }
        return false;
    }

    public Boolean gameloop(JFrame frame) {
        //final JLabel label = new JLabel();
        //frame.add(label);
        frame.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                    currentKey = ' ';
                }
                //label.setText("Key Typed: " + ke.getKeyChar());
                System.out.println("Key Typed" + ke.getKeyChar());
            }
            public void keyPressed(KeyEvent ke) {}
            public void keyReleased(KeyEvent ke) {
                //currentKey = (char) 0;
                //label.setText("Key Released");
            }
        });

        while (true) {
            System.out.println("running");
            if (collision()) {
                break;
            }


            if (currentKey == ' ') {
                System.out.println("jumping up");
                newBird.jump();
                currentKey = (char) 0;
            }
            //System.out.println("bird y = " + newBird.getY());
            repaint();
            //decrease pipe x
            //shift pipe
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static void main(String [] args) {
        FlappyBird flappyBird = new FlappyBird();
        flappyBird.currentKey = (char) 0;
        //setup frame with key listener
        JFrame frame = new JFrame("Flappy Bird");
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
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