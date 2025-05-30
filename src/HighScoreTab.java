import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighScoreTab {
    private final String fileName = "highscores.txt";
    private final int maxScores = 10;
    private List<Integer> scores;

    public HighScoreTab() {
        scores = new ArrayList<>();
        loadScores();
    }

    private void loadScores() {
        scores.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Collections.sort(scores, Collections.reverseOrder());
    }

    public void addScore(int score) {
        scores.add(score);
        Collections.sort(scores, Collections.reverseOrder());

        if (scores.size() > maxScores) {
            scores = scores.subList(0, maxScores);
        }

        saveScores();
    }

    private void saveScores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int score : scores) {
                writer.write(score + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> getTopScores() {
        return scores;
    }

    public void drawHighScores(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 36));
        g2.drawString("High Scores", 330, 80);

        g2.setFont(new Font("Arial", Font.PLAIN, 24));
        List<Integer> topScores = getTopScores();

        for (int i = 0; i < topScores.size(); i++) {
            String scoreLine = (i + 1) + ". " + topScores.get(i);
            g2.drawString(scoreLine, 330, 140 + i * 30);
        }

        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        g2.drawString("Press ESC to return", 330, 500);
    }
}
