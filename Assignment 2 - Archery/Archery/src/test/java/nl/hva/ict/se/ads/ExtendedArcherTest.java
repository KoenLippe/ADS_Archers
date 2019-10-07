package nl.hva.ict.se.ads;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Place all your own tests for Archer in this class. Tests in any other class will be ignored!
 */
public class ExtendedArcherTest extends ArcherTest {

    /**
     * We used this test method to print out all the points and add them by hand using method
     * described in the assignment. Than comparing it to the calculated WeightedScore by the class.
     */

    Archer archer1, archer2;

    public void setup() {
        archer1 = new Archer("Koen", "Lippe");
        archer2 = new Archer("Yan", "Alexandre");
        int[] points = new int[]{1, 0, 1};
        int[] points2 = new int[]{2, 2, 2};

        for (int i = 0; i < Archer.MAX_ROUNDS; i++) {
            archer1.registerScoreForRound(i, points);
            archer2.registerScoreForRound(i, points2);
        }
    }

    @Test
    public void testgetWeightedScore() {
        setup();
        int expected;

        expected = ((1 + 1) + (1 + 1) - (1 * 7)) * Archer.MAX_ROUNDS;
        Assertions.assertEquals(expected, archer1.getWeightedScore());

        //Using seconds point array in setup()
        expected = ((2 + 1) + (2 + 1) + (2 + 1)) * Archer.MAX_ROUNDS;
        Assertions.assertEquals(expected, archer2.getWeightedScore());

    }

    @Test
    public void testGetTotalScore() {
        setup();
        //2,2,2 = 3 points per rounds
        int expected = Archer.MAX_ROUNDS * (3 * 2);
        Assertions.assertEquals(expected, archer2.getTotalScore());
    }

}
