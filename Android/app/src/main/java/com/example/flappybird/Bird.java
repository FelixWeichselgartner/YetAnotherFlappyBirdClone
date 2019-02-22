package com.example.flappybird;

/**
 * Bird class
 * keeps track of speed and position and thickness
 */
public class Bird {
    private int x, y, speed;
    private static int thickness = 60;

    public void Bird(int startX, int startY) {
        //thickness = FlappyBird.HEIGHT/FlappyBird.reHeight*60;
        x = startX;
        y = startY;
        speed = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int newX) {
        x = newX;
    }

    public void setY(int newY) {
        y = newY;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public static int getThickness() {
        return thickness;
    }

    public static void setThickness(int thickness) {
        Bird.thickness = thickness;
    }

    public int getSpeed() {
        return speed;
    }
}
