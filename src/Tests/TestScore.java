package Tests;

import Main.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestScore {
    private Score score;

    @BeforeEach
    public void setUp() {
        score = new Score();
    }

    @Test
    public void testInitialScoreIsZero() {
        assertEquals(0, score.getPlayerScore());
    }

    @Test
    public void testIncreaseIncrementsScore() {
        score.increase();
        assertEquals(1, score.getPlayerScore());
    }

    @Test
    public void testMultipleIncrements() {
        score.increase();
        score.increase();
        score.increase();
        assertEquals(3, score.getPlayerScore());
    }

    @Test
    public void testResetSetsScoreToZero() {
        score.increase();
        score.reset();
        assertEquals(0, score.getPlayerScore());
    }

    @Test
    public void testScoreAfterResetAndIncrement() {
        score.increase();
        score.reset();
        score.increase();
        assertEquals(1, score.getPlayerScore());
    }
}
