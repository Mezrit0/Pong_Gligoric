package Main;

import java.awt.*;

public class PVPMode {
    private int paddleX = 20;
    private int paddleY = 300;
    private int paddleWidth = 25;
    private int paddleHeight = 150;
    private int speed = 5;

    public Rectangle getPaddle() {
        return new Rectangle(paddleX, paddleY, paddleWidth, paddleHeight);
    }


    public void update(KeyListener keyListener) {
        if (keyListener.upPressed) {
            paddleY -= speed;
        }
        if (keyListener.downPressed) {
            paddleY += speed;
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillRect(paddleX, paddleY, paddleWidth, paddleHeight);
    }
}
