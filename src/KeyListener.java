import java.awt.event.KeyEvent;

import static java.awt.SystemColor.menu;

public class KeyListener implements java.awt.event.KeyListener {
    public boolean upPressed;
    public boolean downPressed;
    private GamePanel gamePanel;


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }

        /**
         *  if mid game player needs to pause the game this changes the game state
         */

        if (code == KeyEvent.VK_ESCAPE && gamePanel.getGameState().equals("GAME")) {
            gamePanel.setGameState("PAUSE");
        } else if (gamePanel.getGameState().equals("PAUSE")) {
            if () {
                gamePanel.setGameState("GAME");
            }

        }

    }
    @Override
    public void keyReleased (KeyEvent e){
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
    }
}
