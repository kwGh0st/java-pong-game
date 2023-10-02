package main.java.pong;

import java.awt.*;

public class Score extends Rectangle {
    private static int GAME_WIDTH;
    private static int GAME_HEIGHT;
    private int player1;
    private int player2;

    public Score(int gameWidth, int gameHeight) {
        Score.GAME_WIDTH = gameWidth;
        Score.GAME_HEIGHT = gameHeight;
    }

    public int getPlayer1() {
        return player1;
    }

    public void setPlayer1(int player1) {
        this.player1 = player1;
    }

    public int getPlayer2() {
        return player2;
    }

    public void setPlayer2(int player2) {
        this.player2 = player2;
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN, 60));

        g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT);
        g.drawString(String.valueOf(player1), (GAME_WIDTH / 2) - 85, 50);
        g.drawString(String.valueOf(player2), (GAME_WIDTH / 2) + 50, 50);
    }
}
