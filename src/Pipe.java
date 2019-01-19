import java.util.Random;

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

    public void printPipeToConsole() {
        System.out.println("x = " + currentX + "\n" + "heightUp = " + heightUp + "\n" + "heightDown = " + heightDown);
    }

    public void generatePipe(int a) {
        if (a == 1) {
            currentX = Environment.getWindowX() - width;
        } else if (a == 2) {
            currentX = Environment.getWindowX()/3*2 - width;
        } else if (a == 3) {
            currentX = Environment.getWindowX()/3 - width;
        }
        int min = space;
        int max = Environment.getWindowY() - 2*min - space;
        Random randomNumber = new Random();
        heightDown = min + randomNumber.nextInt(max);
        heightUp = Environment.getWindowY() - heightDown - space;
    }
}
