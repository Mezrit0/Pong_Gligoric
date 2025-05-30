import javax.swing.*;
import java.awt.*;

public class ChooseModeMenu extends JPanel {

    public ChooseModeMenu(GamePanel gamePanel, Runnable onGameStart) {
        setLayout(null);
        setBounds(0, 0, 800, 600);
        setBackground(Color.BLACK);

        JLabel label = new JLabel("Choose Mode:");
        label.setBounds(330, 120, 200, 30);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 20));

        JButton singleplayerButton = new JButton("SINGLEPLAYER");
        JButton aiButton = new JButton("VS BOT");

        singleplayerButton.setBounds(300, 200, 200, 50);
        aiButton.setBounds(300, 270, 200, 50);

        add(label);
        add(singleplayerButton);
        add(aiButton);

        singleplayerButton.addActionListener(e -> {
            gamePanel.setAiMode(false);
            onGameStart.run();
        });

        aiButton.addActionListener(e -> {
            gamePanel.setAiMode(true);
            onGameStart.run();
        });
    }
}
