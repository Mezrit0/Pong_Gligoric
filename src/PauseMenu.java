import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseMenu extends JPanel {

    public PauseMenu(GamePanel gamePanel) {
        setBounds(200, 150, 400, 300);
        setLayout(null);
        JButton resumeButton = new JButton("Resume");
        resumeButton.setBounds(125, 80, 150, 40);
        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(125, 140, 150, 40);
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
