import java.awt.*;

public class AIMode {
    int x;
    int y;
    int width = 25;
    int height = 150;
    int speed = 5;

    Rectangle aiPaddle;

    public AIMode(int x, int y) {
        this.x = x;
        this.y = y;
        aiPaddle = new Rectangle(x, y, width, height);
    }

    public void update(int ballY) {
        if (ballY < y + height / 2) {
            y -= speed;
        } else if (ballY > y + height / 2) {
            y += speed;
        }

        if (y < 0) y = 0;
        if (y + height > 600) y = 600 - height;

        aiPaddle.y = y;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.fillRect(x, y, width, height);
    }

    public Rectangle getPaddle() {
        return aiPaddle;
    }
}
