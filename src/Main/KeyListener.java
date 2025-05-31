package Main;

import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener {
    public boolean upPressed;
    public boolean downPressed;
    public boolean wPressed;
    public boolean sPressed;
    private GamePanel gamePanel;

    public KeyListener(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_UP:
                upPressed = true;
                break;
            case KeyEvent.VK_DOWN:
                downPressed = true;
                break;
            case KeyEvent.VK_W:
                wPressed = true;
                break;
            case KeyEvent.VK_S:
                sPressed = true;
        }

        /**
         *  if mid game player needs to pause the game this changes the game state
         */

        if (code == KeyEvent.VK_ESCAPE) {
            if (gamePanel.getGameState().equals("GAME")) {
                gamePanel.setGameState("PAUSE");
                gamePanel.pauseMenu.setVisible(true);
            } else if (gamePanel.getGameState().equals("PAUSE")) {
                gamePanel.setGameState("GAME");
                gamePanel.pauseMenu.setVisible(false);
                gamePanel.requestFocusInWindow();
            }else if (gamePanel.getGameState().equals("HIGHSCORE")) {
                gamePanel.setGameState("MENU");
                gamePanel.getMenu().setVisible(true);
            }
        }

    }
    @Override
    public void keyReleased (KeyEvent e){
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_UP:
                upPressed = false;
                break;
            case KeyEvent.VK_DOWN:
                downPressed = false;
                break;
            case KeyEvent.VK_W:
                wPressed = false;
                break;
            case KeyEvent.VK_S:
                sPressed = false;
                break;
        }    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean iswPressed() {
        return wPressed;
    }

    public boolean issPressed() {
        return sPressed;
    }
}

