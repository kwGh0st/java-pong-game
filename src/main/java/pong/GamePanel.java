package main.java.pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {
    private static final int GAME_WIDTH = 1000;
    private static final int GAME_HEIGHT = 555;
    private static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    private static final int BALL_DIAMETER = 20;
    private static final int PADDLE_WIDTH = 25;
    private static final int PADDLE_HEIGHT = 100;
    private Thread gameThread;
    private Image image;
    private Graphics graphics;
    private Random random;
    private Paddle paddle1;
    private Paddle paddle2;
    private Ball ball;
    private Score score;


    public GamePanel() {
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void newBall () {
        random = new Random();
        ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), (GAME_HEIGHT  / 2) - (BALL_DIAMETER / 2), BALL_DIAMETER, BALL_DIAMETER);
    }
    public void newPaddles() {
        paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }
    public void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
    }

    public void move() {
        paddle1.move();
        paddle2.move();
        ball.move();
    }

    public void checkCollision() {
        if (ball.y <= 0) ball.setYDirection(-ball.getYVelocity());
        if (ball.y >= GAME_HEIGHT-BALL_DIAMETER) ball.setYDirection(-ball.getYVelocity());

        if (ball.intersects(paddle1)) {
            ball.setXVelocity(Math.abs(ball.getXVelocity()));
            ball.setXVelocity(ball.getXVelocity() + 1);
            if (ball.getYVelocity() > 0) ball.setYVelocity(ball.getYVelocity() + 1);
            else ball.setYVelocity(ball.getYVelocity() - 1);

            ball.setXDirection(ball.getXVelocity());
            ball.setYDirection(ball.getYVelocity());
        }

        if (ball.intersects(paddle2)) {
            ball.setXVelocity(Math.abs(ball.getXVelocity()));
            ball.setXVelocity(ball.getXVelocity() + 1);
            if (ball.getYVelocity() > 0) ball.setYVelocity(ball.getYVelocity() + 1);
            else ball.setYVelocity(ball.getYVelocity() - 1);

            ball.setXDirection(-ball.getXVelocity());
            ball.setYDirection(ball.getYVelocity());
        }

        if (paddle1.y <= 0) paddle1.y = 0;
        if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;

        if (paddle2.y <= 0) paddle2.y = 0;
        if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }


    private class AL extends KeyAdapter {


        @Override
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }

}
