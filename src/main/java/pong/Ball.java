package main.java.pong;

import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle {
    private Random random;
    private int xVelocity;
    private int yVelocity;
    private int initialSpeed = 3;

    public Ball(int x, int y, int width, int height) {
        super(x ,y, width, height);
        random = new Random();
        int randomXDirection = random.nextInt(2);

        if (randomXDirection == 0) randomXDirection--;

        setXDirection(randomXDirection * initialSpeed);

        int randomYDirection = random.nextInt(2);

        if (randomYDirection == 0) randomYDirection--;

        setYDirection(randomYDirection * initialSpeed);
    }

    public int getXVelocity() {
        return xVelocity;
    }

    public int getYVelocity() {
        return yVelocity;
    }

    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setYVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public void setXDirection(int randomXDirection) {
        xVelocity = randomXDirection;
    }
    public void setYDirection(int randomYDirection) {
        yVelocity = randomYDirection;
    }

    public void move() {
        x += xVelocity;
        y += yVelocity;
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, height, width);
    }

}
