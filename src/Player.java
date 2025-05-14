import java.awt.*;

public class Player {

    int playerX;
    int playerY;
    int playerSpeed = 5;
    Rectangle playerPaddle;

    public Player(int playerX, int playerY, int playerSpeed) {
        this.playerX = playerX;
        this.playerY = playerY;
        this.playerSpeed = playerSpeed;
        this.playerPaddle = new Rectangle(playerX, playerY, 25, 150);
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fill(playerPaddle);
    }
}
