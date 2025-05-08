import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    /**
     * screen settings
     */
    final int width = 800;
    final int height = 600;
    Thread gameThread;
    KeyListener keyListener = new KeyListener();

    /**
     * player stats
     */
    int playerX = 700;
    int playerY = 300;
    int playerSpeed = 5;
    Rectangle playerPaddle = new Rectangle(playerX, playerY, 25, 150);

    /**
     * ball stats
     */
    int ballX = 300;
    int ballY = 200;
    int ballSize = 20;
    int ballSpeedX = 4;
    int ballSpeedY = 4;


    GamePanel(){
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyListener);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public void startGameThreads(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        int fps = 60;
        long timePerFrame = 1000000000 / fps;
        long lastTime = System.nanoTime();
        long now;
        double time = 0;

        while (gameThread != null){
            now = System.nanoTime();
            time += (now - lastTime) / (double) timePerFrame;
            lastTime = now;

            if (time >= 1) {
                update();
                repaint();
                time--;
            }

        }
    }

    public void update(){
        if (keyListener.downPressed){
            playerY += playerSpeed;
        }
        else if (keyListener.upPressed){
            playerY -= playerSpeed;
        }
        playerPaddle.y = playerY;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);
        g2.fill(playerPaddle);

        g2.setColor(Color.yellow);
        g2.fillOval(ballX, ballY, ballSize, ballSize);
        
        g2.dispose();
    }

}
