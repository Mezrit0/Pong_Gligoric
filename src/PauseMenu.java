import javax.swing.*;
import java.awt.*;

public class PauseMenu extends JPanel {

    public PauseMenu() {
        setBounds(300, 200, 200, 150);
        setLayout(null);
        JButton resumeButton = new JButton("Resume");
        JButton quitButton = new JButton("Quit");
    }
}
