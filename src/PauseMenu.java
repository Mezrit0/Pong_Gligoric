import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseMenu extends JPanel {

    public PauseMenu(GamePanel gamePanel) {
        setBounds(0, 0, 800, 600);
        setLayout(null);
        JButton resumeButton = new JButton("Resume");
        resumeButton.setBounds((800 - 150)/2, 200, 150, 40);
        JButton quitButton = new JButton("Quit");
        quitButton.setBounds((800 - 150)/2, 160, 150, 40);
        add(resumeButton);
        add(quitButton);

        setBackground(new Color(0, 0, 0, 180));
        resumeButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            gamePanel.setGameState("GAME");
            setVisible(false);
            gamePanel.requestFocusInWindow();
        }
        });

        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gamePanel.setGameState("MENU");
                setVisible(false);
                gamePanel.getMenu().setVisible(true);
                gamePanel.requestFocusInWindow();
            }
        });
    }
}
