import java.util.Random;

/**
 * Pipe class
 * saves a pipes height and x value
 */
public class Pipe {
    private int heightUp, heightDown;
    private int currentX;
    private int width;
    private int space;

    public Pipe() {
        width = 60;
        space = 150;
    }

    public int getHeightUp() {return heightUp;}
    public int getHeightDown() {return heightDown;}
    public int getCurrentX() {return currentX;}
    public int getWidth() {return width;}

    public void setHeightUp(int newHU) {heightUp = newHU;}
    public void setHeightDown(int newHD) {heightDown = newHD;}
    public void setCurrentX(int newX) {currentX = newX;}

    /**
     * prints all the pipe informations to console
     */
    public void printPipeToConsole() {
        System.out.println("x = " + currentX + "\n" + "heightUp = " + heightUp + "\n" + "heightDown = " + heightDown);
    }

    /**
     * generates a pipe
     * distance between 2 pipes is always the same size
     * @param a     differs the different pipes to create -> for initialisation
     */
    public void generatePipe(int a) {
        int full = FlappyBird.WIDTH;
        int b = full/3 - 2*width/3;
        if (a == 2) {
            currentX = 3*b + 2*width;
        } else if (a == 1) {
            currentX = 2*b + width;
        } else if (a == 0) {
            currentX = b;
        }
        int min = space;
        int max = FlappyBird.HEIGHT - 2*min - space;
        Random randomNumber = new Random();
        heightDown = min + randomNumber.nextInt(max);
        heightUp = FlappyBird.HEIGHT - heightDown - space;
    }
}
