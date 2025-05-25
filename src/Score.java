import java.awt.*;

public class Score {
    int value;
    private int playerScore = 0;


    public Score() {
        value = 0;
    }

    public void increase() {
        value++;
        playerScore++;
    }

    public void reset() {
        value = 0;
        playerScore = 0;
    }

    public void draw(Graphics2D g2, int panelWidth) {
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 36));
        String text = "Score: " + value;

        /**
         * placing it on top middle
         */

        int stringWidth = g2.getFontMetrics().stringWidth(text);
        int x = (panelWidth - stringWidth) / 2;

        g2.drawString(text, x, 50);
    }

    public int getPlayerScore() {
        return playerScore;
    }
}
