import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Menu extends JPanel {

    private JButton startButton;

    public Menu(Runnable startGame) {
        setLayout(null);
        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());
        startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.BOLD, 32));

        startButton.addActionListener(e -> startGame.run());

        add(startButton);
    }

    /**
     * drawing stuff on menu
     * @param g the <code>Graphics</code> object to protect
     */

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.drawString("PONG GLIGORIC", 230, 100);
    }
}


