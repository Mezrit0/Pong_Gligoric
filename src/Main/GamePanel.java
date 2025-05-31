package Main;

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
    private HighScoreTab highScoreTab = new HighScoreTab();
    PVPMode pvpMode = new PVPMode();
    private boolean pvpmode = false;
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
        }, this);

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

    /**
     * the cycle of running the game till its closed and implementation of fps
     */

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

    public void update() {
        if (!gameState.equals("GAME")) {
            return;
        }

        ball.update();

        /**
         * if player plays AI mod it will check collisions of both the paddles
         */

        if (aiMode) {
            ai.update(ball.getBallY());

            if (ball.checkCollisionWithPaddle(ai.getPaddle())) {
                ball.speedIncrease();
            }

            if (keyListener.sPressed) {
                player.playerY += player.playerSpeed;
            } else if (keyListener.wPressed) {
                player.playerY -= player.playerSpeed;
            }
            player.playerPaddle.y = player.playerY;

            /**
             * if player plays pvp mode it will check collisions of both the paddles
             */
        } else if (pvpMode != null) {
            if (keyListener.sPressed) {
                player.playerY += player.playerSpeed;
            } else if (keyListener.wPressed) {
                player.playerY -= player.playerSpeed;
            }
            player.playerPaddle.y = player.playerY;

            pvpMode.update(keyListener);
            if (ball.checkCollisionWithPaddle(player.playerPaddle)) {
                ball.speedIncrease();
            }
            if (ball.checkCollisionWithPaddle(pvpMode.getPaddle())) {
                ball.speedIncrease();
            }
//            ball.checkCollisionWithPaddle(player.playerPaddle);
//            ball.checkCollisionWithPaddle(pvpMode.getPaddle());

            /**
             * if player plays singleplayer the left wall wont be able to penetrate with ball
             */
        } else {
            if (keyListener.sPressed) {
                player.playerY += player.playerSpeed;
            } else if (keyListener.wPressed) {
                player.playerY -= player.playerSpeed;
            }
            player.playerPaddle.y = player.playerY;
            ball.checkCollisionWithPaddle(player.playerPaddle);
        }

        if (ball.getBallX() <= 0) {
            if (aiMode) {
                highScoreTab.addScore(score.getPlayerScore());
                ball.resetBall();
                score.reset();
                gameState = "MENU";
                menu.setVisible(true);
                this.requestFocusInWindow();
            } else if (pvpMode != null) {
                ball.resetBall();
                score.reset();
                gameState = "MENU";
                menu.setVisible(true);
                this.requestFocusInWindow();
            } else {
                score.increase();
                ball.speedIncrease();
                ball.ballSpeedX = Math.abs(ball.ballSpeedX);
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

            if (pvpMode != null && !aiMode) {
                pvpMode.draw(g2);
            }
            if (aiMode) {
                ai.draw(g2);
            }
        }else if (gameState.equals("HIGHSCORE")) {
            menu.setVisible(false);
            chooseModeMenu.setVisible(false);
            pauseMenu.setVisible(false);
            highScoreTab.drawHighScores(g2);
        }

        g2.dispose();
    }

    public String getGameState() {
        return gameState;
    }

    /**
     * sets the actual game state so it shows the right window
     * @param newState
     */

    public void setGameState(String newState) {
        this.gameState = newState;

        menu.setVisible(false);
        chooseModeMenu.setVisible(false);
        pauseMenu.setVisible(false);

        switch (newState) {
            case "MENU":
                menu.setVisible(true);
                break;
            case "CHOOSE_MODE":
                chooseModeMenu.setVisible(true);
                break;
            case "PAUSE":
                pauseMenu.setVisible(true);
                break;
            case "HIGHSCORE":
                break;
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

    public void setPvpMode(PVPMode mode) {
        this.pvpMode = mode;
    }
}
