package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static org.junit.jupiter.api.Assertions.*;

public class TestKeyListener {
    private KeyListener keyListener;

    @BeforeEach
    public void setUp() {
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
    public void testWKeyPressAndRelease() {
        keyListener.keyPressed(new KeyEvent(new java.awt.Label(), 0, 0, 0, KeyEvent.VK_W, 'W'));
//        assertTrue(keyListener.get);

        keyListener.keyReleased(new KeyEvent(new java.awt.Label(), 0, 0, 0, KeyEvent.VK_W, 'W'));
//        assertFalse(keyListener.wPressed);
    }

    @Test
    public void testSKeyPressAndRelease() {
        keyListener.keyPressed(new KeyEvent(new java.awt.Label(), 0, 0, 0, KeyEvent.VK_S, 'S'));
//        assertTrue(keyListener.sPressed);

        keyListener.keyReleased(new KeyEvent(new java.awt.Label(), 0, 0, 0, KeyEvent.VK_S, 'S'));
//        assertFalse(keyListener.sPressed);
    }

    @Test
    public void testUpKeyPressAndRelease() {
        keyListener.keyPressed(new KeyEvent(new java.awt.Label(), 0, 0, 0, KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED));
//        assertTrue(keyListener.upPressed);

        keyListener.keyReleased(new KeyEvent(new java.awt.Label(), 0, 0, 0, KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED));
//        assertFalse(keyListener.upPressed);
    }

    @Test
    public void testDownKeyPressAndRelease() {
        keyListener.keyPressed(new KeyEvent(new java.awt.Label(), 0, 0, 0, KeyEvent.VK_DOWN, KeyEvent.CHAR_UNDEFINED));
//        assertTrue(keyListener.downPressed);

        keyListener.keyReleased(new KeyEvent(new java.awt.Label(), 0, 0, 0, KeyEvent.VK_DOWN, KeyEvent.CHAR_UNDEFINED));
//        assertFalse(keyListener.downPressed);
    }

    @Test
    public void testMultipleKeys() {
        keyListener.keyPressed(new KeyEvent(new java.awt.Label(), 0, 0, 0, KeyEvent.VK_W, 'W'));
        keyListener.keyPressed(new KeyEvent(new java.awt.Label(), 0, 0, 0, KeyEvent.VK_DOWN, KeyEvent.CHAR_UNDEFINED));

//        assertTrue(keyListener.wPressed);
//        assertTrue(keyListener.downPressed);

        keyListener.keyReleased(new KeyEvent(new java.awt.Label(), 0, 0, 0, KeyEvent.VK_W, 'W'));
        keyListener.keyReleased(new KeyEvent(new java.awt.Label(), 0, 0, 0, KeyEvent.VK_DOWN, KeyEvent.CHAR_UNDEFINED));

//        assertFalse(keyListener.wPressed);
//        assertFalse(keyListener.downPressed);
    }
}
