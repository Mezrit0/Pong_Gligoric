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
}
