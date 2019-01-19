public class Bird {
    private int x, y, speed;
    private static int thickness = 20;

    public void Bird(int startX, int startY) {
        x = startX;
        y = startY;
        speed = 0;
    }

    public int getX() {return x;}
    public int getY() {return y;}

    public void setX(int newX) {x = newX;}
    public void setY(int newY) {y = newY;}

    public void jump() {y = y + 100;}

    public static int getThickness() {return thickness;}
    public int getSpeed() {return speed;}
}
