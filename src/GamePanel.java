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
    Player player = new Player(760, 300, 5);

    /**
     * ball stats
     */

    Ball ball = new Ball(300, 200, 20, 4, 4, width, height);

    Score score = new Score();



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
            player.playerY += player.playerSpeed;
        }
        else if (keyListener.upPressed){
            player.playerY -= player.playerSpeed;
        }
        player.playerPaddle.y = player.playerY;

        ball.update();

        if (ball.ballSize + ball.ballSize >= width) {
            ball.ballSpeedX = -ball.ballSpeedY;
            score.increase();
        }
        ball.checkCollisionWithPaddle(player.playerPaddle);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        player.draw(g2);
        ball.draw(g2);
        score.draw(g2, width);
        
        g2.dispose();
    }

}
