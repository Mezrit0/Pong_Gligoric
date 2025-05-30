import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Menu extends JPanel {
     private GamePanel gamePanel;

    public Menu(Runnable openChooseMenu, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        setLayout(null);
        setBounds(0, 0, 800, 600);
        setBackground(Color.BLACK);

        JButton startButton = new JButton("Start Game");
        startButton.setBounds(300, 200, 200, 50);
        add(startButton);

        JButton highScoreButton = new JButton("High Scores");
        highScoreButton.setBounds(300, 270, 200, 50);
        add(highScoreButton);
        highScoreButton.addActionListener(e -> {
            gamePanel.setGameState("HIGHSCORE");
            gamePanel.repaint();
        });


        startButton.addActionListener(e -> openChooseMenu.run());



    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
