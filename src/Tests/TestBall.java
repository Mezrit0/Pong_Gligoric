package Tests;

import Main.Ball;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestBall {

    @Test
    public void testBallMovesCorrectly() {
        Ball ball = new Ball(100, 100, 10, 4, 4, 800, 600);
        ball.update();
        assertEquals(104, ball.getBallX());
        assertEquals(104, ball.getBallY());
    }

    @Test
    public void testBallBouncesOffTopWall() {
        Ball ball = new Ball(100, 0, 10, 4, -4, 800, 600);
        ball.update();
        assertTrue(ball.getBallY() >= 0);
        assertTrue(ball.getBallSpeedY() > 0);
    }

    @Test
    public void testBallResetsToCenter() {
        Ball ball = new Ball(100, 100, 10, 4, 4, 800, 600);
        ball.resetBall();
        assertEquals(400, ball.getBallX()); // 800 / 2
        assertEquals(300, ball.getBallY()); // 600 / 2
    }

    @Test
    public void testBallCollisionWithPaddle() {
        Ball ball = new Ball(100, 100, 10, 4, 0, 800, 600);
        Rectangle paddle = new Rectangle(100, 100, 20, 60);
        boolean collided = ball.checkCollisionWithPaddle(paddle);
        assertTrue(collided);
    }

}
