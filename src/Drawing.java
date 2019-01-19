import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.*;
import javax.swing.*;
import java.lang.InterruptedException;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class Drawing extends JPanel implements KeyListener {
    private int x, y;
    private int s = 0;
    private Pipe p1 = new Pipe(), p2 = new Pipe(), p3 = new Pipe();
    private char currentKey = 'n';

    public Drawing(int birdX, int birdY, Pipe np1, Pipe np2, Pipe np3, Player newPlayer) {
        this.setPreferredSize(new Dimension(Environment.getWindowX(), Environment.getWindowY()));
        updateWindow(birdX, birdY, np1, np2, np3, newPlayer);
        addKeyListener(this);
    }

    public char getCurrentKey() {return currentKey;}

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    public void keyPressed(KeyEvent e) { }

    public void keyReleased(KeyEvent e) {
        currentKey = (char) 0;
    }

    public void keyTyped(KeyEvent e) {
        currentKey = e.getKeyChar();
    }

    public void Draw(int birdX, int birdY, Pipe np1, Pipe np2, Pipe np3, Player newPlayer) {
        updateWindow(birdX, birdY, np1, np2, np3, newPlayer);
        JFrame frame = new JFrame("Yet another Flappy Bird clone");
        frame.getContentPane().add(new Drawing(birdX, birdY, np1, np2, np3, newPlayer));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void updateWindow(int birdX, int birdY, Pipe np1, Pipe np2, Pipe np3, Player newPlayer) {
        this.x = birdX;
        this.y = birdY;
        this.p1 = np1;
        this.p2 = np2;
        this.p3 = np3;
        this.s = newPlayer.getScore();
        repaint();
    }

    public void paint(Graphics g) {
        //g.clearRect();
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g.fillOval(this.x, this.y, Bird.getThickness(), Bird.getThickness());
        g.drawString("Score: " + s, 10,30);
        g2.setColor(Color.GREEN);
        g2.fillRect(this.p1.getCurrentX(), 0, this.p1.getWidth(), this.p1.getHeightDown());
        g2.fillRect(this.p1.getCurrentX(), Environment.getWindowY()-this.p1.getHeightUp(), this.p1.getWidth(), this.p1.getHeightUp());
        g2.fillRect(this.p2.getCurrentX(), 0, this.p2.getWidth(), this.p2.getHeightDown());
        g2.fillRect(this.p2.getCurrentX(), Environment.getWindowY()-this.p2.getHeightUp(), this.p2.getWidth(), this.p1.getHeightUp());
        g2.fillRect(this.p3.getCurrentX(), 0, this.p3.getWidth(), this.p3.getHeightDown());
        g2.fillRect(this.p3.getCurrentX(), Environment.getWindowY()-this.p3.getHeightUp(), this.p3.getWidth(), this.p1.getHeightUp());
    }
}