import java.awt.*;

public class Ball {
    int ballX;
    int ballY;
    int ballSize;
    int ballSpeedX;
    int ballSpeedY;
    int panelWidth;
    int panelHeight;

    public Ball(int ballX, int ballY, int ballSize, int ballSpeedX, int ballSpeedY, int panelWidth, int panelHeight) {
        this.ballX = ballX;
        this.ballY = ballY;
        this.ballSize = ballSize;
        this.ballSpeedX = ballSpeedX;
        this.ballSpeedY = ballSpeedY;
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
    }

    public void update() {
        /**
         * bounce from the top and bottom wall
         * Source: ChatGPT
         */
        ballX += ballSpeedX;
        ballY += ballSpeedY;

        if (ballY <= 0) {
            ballY = 0;
            ballSpeedY = -ballSpeedY;
        }

        if (ballY + ballSize >= panelHeight) {
            ballY = panelHeight - ballSize;
            ballSpeedY = -ballSpeedY;
        }

        /**
         * bounce from left wall
         */
        if (ballX <= 0) {
            ballX = 0;
            ballSpeedX = -ballSpeedX;
        }

        if (checkCollisionWithPaddle(ne)){
            resetBall();
        }


    }


    /**
     * brings back the ball to middle
     */
    public void resetBall() {
        ballX = panelWidth / 2;
        ballY = panelHeight / 2;
        ballSpeedX = 4;
        ballSpeedY = 4;
    }



    public void draw(Graphics2D g2) {
        g2.setColor(Color.YELLOW);
        g2.fillOval(ballX, ballY, ballSize, ballSize);
    }

    /**
     * Checks collisions with paddle
     * Source: ChatGPT
     * @param paddle
     */
    public void checkCollisionWithPaddle(Rectangle paddle) {
        Rectangle ballRect = new Rectangle(ballX, ballY, ballSize, ballSize);
        if (ballRect.intersects(paddle)) {
            ballSpeedX = -ballSpeedX;
        }
    }
}
