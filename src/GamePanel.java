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
    private Menu menu;
    private String gameState = "MENU";
    /**
     * player stats
     */
    Player player = new Player(760, 300, 5);

    /**
     * ball stats
     */

    Ball ball = new Ball(300, 200, 20, 2, 2, width, height);

    Score score = new Score();



    GamePanel(){

        /**
         * Switching screens between game and menu
         * Source: ChatGPT
         */
        menu = new Menu(() -> {
            gameState = "GAME";
            menu.setVisible(false);
            this.requestFocusInWindow();
        });
        menu.setBounds(0, 0, width, height);
        this.setLayout(null);
        this.add(menu);

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

        if (ball.getBallX() <= 0) {
            score.increase();
            ball.speedIncrease();
        }
        if (ball.getBallX() > width) {
            ball.resetBall();
            score.reset();
            gameState = "MENU";
            menu.setVisible(true);
            this.requestFocusInWindow();
        }
        ball.checkCollisionWithPaddle(player.playerPaddle);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        if (gameState.equals("MENU")) {

            /**
             * first attempt to make menu without buttons
             */
//            g2.setColor(Color.WHITE);
//            g2.setFont(new Font("Arial", Font.BOLD, 48));
//            g2.drawString("PONG", getWidth() / 2 - 80, 150);
//
//            g2.setFont(new Font("Arial", Font.PLAIN, 32));
//            g2.drawString("Press ENTER to Start", getWidth() / 2 - 160, 250);
        } else if (gameState.equals("GAME")) {
            player.draw(g2);
            ball.draw(g2);
            score.draw(g2, width);
        }
        
        g2.dispose();
    }

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }
}
