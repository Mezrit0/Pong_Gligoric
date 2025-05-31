package Tests;

import Main.Player;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestPlayer {
    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player(100, 10, 5);
    }

    @Test
    public void testInitialPosition() {
        assertEquals(100, player.getPlayerPaddle().getX());
    }


    @Test
    public void testGetPlayerPaddle() {
        Rectangle paddle = player.getPlayerPaddle();
        assertNotNull(paddle);
        assertEquals(100, paddle.y);
    }
}
