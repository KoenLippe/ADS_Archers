package nl.hva.ict.se.ads;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        int[] points = new int[]{1, 1, 1};
        int[] points2 = new int[]{2, 2, 2};

        for (int i = 0; i < Archer.MAX_ROUNDS; i++) {
            archer1.registerScoreForRound(i, points);
            archer2.registerScoreForRound(i, points2);
        }

    }

    @Test
    public void testgetWeightedScore() {
        final int TEST_VALUE = 8;

        List<Archer> list = Archer.generateArchers(2);
        int[] points = new int[]{TEST_VALUE, TEST_VALUE + 1, TEST_VALUE};

        for (int i = 0; i < Archer.MAX_ROUNDS; i++) {
            list.get(0).registerScoreForRound(i, points);
        }

        int expected = (TEST_VALUE + 1 + TEST_VALUE + 2 + TEST_VALUE + 1) * Archer.MAX_ROUNDS;

        Assertions.assertEquals(list.get(0).getWeightedScore(), expected);

    }

}
