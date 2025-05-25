import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    /**
     * screen settings
     */
    final int width = 800;
    final int height = 600;
    Thread gameThread;
    KeyListener keyListener = new KeyListener(this);
    private Menu menu;
    private String gameState = "MENU";
    PauseMenu pauseMenu;
    private ChooseModeMenu chooseModeMenu;
    HighScoreTab highScoreTab = new HighScoreTab();
    /**
     * player stats
     */
    Player player = new Player(760, 300, 5);

    /**
     * ball stats
     */

    Ball ball = new Ball(300, 200, 20, 2, 2, width, height);

    /**
     * AI stats
     */
    AIMode ai;
    private boolean aiMode = false;

    Score score = new Score();



    GamePanel(){

        /**
         * Switching screens between game and menu
         * Source: ChatGPT
         */
        menu = new Menu(() -> {
            menu.setVisible(false);
            chooseModeMenu.setVisible(true);
        });

        chooseModeMenu = new ChooseModeMenu(this, () -> {
            gameState = "GAME";
            chooseModeMenu.setVisible(false);
            this.requestFocusInWindow();
        });
        chooseModeMenu.setVisible(false);

        this.add(menu);
        this.add(chooseModeMenu);
        menu.setBounds(0, 0, width, height);
        this.setLayout(null);
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyListener);
        this.setFocusable(true);
        this.requestFocusInWindow();
        pauseMenu = new PauseMenu(this);
        pauseMenu.setVisible(false);
        ai = new AIMode(20, 300);
        this.add(pauseMenu);
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
        /**
         * if game is stopped it won't update
         */
        if(!gameState.equals("GAME")){
            return;
        }
        if (aiMode) {
            ai.update(ball.getBallY());
            ball.checkCollisionWithPaddle(ai.getPaddle());
        }
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
            if (!aiMode) {
                ball.speedIncrease();
            }
        }
        if (ball.getBallX() > width) {
            highScoreTab.addScore(score.getPlayerScore());
            ball.resetBall();
            score.reset();
            gameState = "MENU";
            menu.setVisible(true);
            this.requestFocusInWindow();
        }

        if (aiMode) {
            ball.checkCollisionWithPaddle(ai.getPaddle());
            ball.checkCollisionWithPaddle(player.playerPaddle);
        } else {
            ball.checkCollisionWithPaddle(player.playerPaddle);
        }
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
        if (aiMode) {
            ai.draw(g2);
        }
        g2.dispose();
    }

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String newState) {
        this.gameState = newState;

        if (newState.equals("PAUSE")) {
            pauseMenu.setVisible(true);
        } else {
            pauseMenu.setVisible(false);
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public void setAiMode(boolean enabled) {
        this.aiMode = enabled;
    }

    public boolean isAiMode() {
        return aiMode;
    }
}
