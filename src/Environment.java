import java.util.Scanner;
import java.util.concurrent.TimeUnit;
/*

public class Environment {
    private int gravity;
    private static int windowX = 500, windowY = 800;
    private Bird newBird;
    private Pipe[] allPipes;

    public void setup() {

        allPipes = new Pipe[3];
        gravity = 10;
        newBird = new Bird();
        newBird.setX(50);
        newBird.setY(Environment.getWindowY()/2);
        gravity = 10;

        for (int i = 0; i < 3; i++) {
            allPipes[i] = new Pipe();
            allPipes[i].generatePipe(i + 1);
            //System.out.println("Pipe Number " + i);
            //allPipes[i].printPipeToConsole();
        }
    }

    public int getGravity() {return gravity;}
    public static int getWindowX() {return windowX;}
    public static int getWindowY() {return windowY;}

    public void setGravity(int newG) {gravity = newG;}

    public void shiftPipes() {
        allPipes[0] = allPipes[1];
        allPipes[1] = allPipes[2];
        allPipes[2].generatePipe(1);
    }

    private Boolean collision() {
        //System.out.println("Bird x = " + newBird.getX() + "\nBird y = " + newBird.getY() + "\nBird speed = " + newBird.getSpeed());
        for (int i = 0; i < 3; i++) {
            if(newBird.getX() > allPipes[i].getCurrentX()-5 && newBird.getX() < allPipes[i].getCurrentX()+5) {
                System.out.println("x value in range");
                if (newBird.getY() > allPipes[i].getHeightUp() || newBird.getY() < allPipes[i].getHeightDown()) {
                    System.out.println("collision1 = true");
                    return true;
                }
            }
        }
        if (newBird.getY() <= 0 || newBird.getY() >= Environment.getWindowY()) {
            System.out.println("collision2 = true");
            return true;
        }
        return false;
    }

    public Boolean gameloop(Player newPlayer) {
        Drawing Window = new Drawing(newBird.getX(), newBird.getY(), allPipes[0], allPipes[1], allPipes[2], newPlayer);
        Window.Draw(newBird.getX(), newBird.getY(), allPipes[0], allPipes[1], allPipes[2], newPlayer);
        //Scanner in = new Scanner(System.in);
        while (true) {
            if (collision()) {
                break;
            }
            //System.out.println("running-");
            //check if a key is pressed


            System.out.println("currentKey = " + Window.getCurrentKey());

            if (Window.getCurrentKey() == ' ') {
                System.out.println("jumping up");
                newBird.jump();
            }
            //System.out.println("bird y = " + newBird.getY());
            Window.updateWindow(newBird.getX(), newBird.getY(), allPipes[0], allPipes[1], allPipes[2], newPlayer);
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
}
*/
