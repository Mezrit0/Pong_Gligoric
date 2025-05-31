package Tests;

import Main.PVPMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static org.junit.jupiter.api.Assertions.*;

public class TestPvpMode {
    private PVPMode pvpMode;
    private KeyListener keyListener;

    @BeforeEach
    public void setUp() {
        pvpMode = new PVPMode();
        keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
    }

    @Test
    public void testInitialPaddlePosition() {
        assertEquals(200, pvpMode.getPaddle().y);
    }

    @Test
    public void testMovePaddleUp() {
//        keyListener.upPressed = true;
        pvpMode.update((Main.KeyListener) keyListener);
        assertEquals(195, pvpMode.getPaddle().y);
    }

    @Test
    public void testMovePaddleDown() {
//        keyListener.downPressed = true;
        pvpMode.update((Main.KeyListener) keyListener);
        assertEquals(205, pvpMode.getPaddle().y);
    }

    @Test
    public void testNoKeyPressed() {
        pvpMode.update((Main.KeyListener) keyListener);
        assertEquals(200, pvpMode.getPaddle().y);
    }

    @Test
    public void testMoveUpThenDown() {
//        keyListener.upPressed = true;
        pvpMode.update((Main.KeyListener) keyListener); // 195
        ((Main.KeyListener) keyListener).upPressed = false;
        ((Main.KeyListener) keyListener).downPressed = true;
        pvpMode.update((Main.KeyListener) keyListener); // 200
        assertEquals(200, pvpMode.getPaddle().y);
    }
}
