import java.awt.*;
import java.awt.event.KeyEvent;

public class PVPMode {
    private int paddleX = 20;
    private int paddleY = 300;
    private int paddleWidth = 20;
    private int paddleHeight = 100;
    private int speed = 5;

    public Rectangle getPaddle() {
        return new Rectangle(paddleX, paddleY, paddleWidth, paddleHeight);
    }

    public void update(boolean wPressed, boolean sPressed) {
        if (wPressed) {
            paddleY -= speed;
        }
        if (sPressed) {
            paddleY += speed;
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillRect(paddleX, paddleY, paddleWidth, paddleHeight);
    }
}
