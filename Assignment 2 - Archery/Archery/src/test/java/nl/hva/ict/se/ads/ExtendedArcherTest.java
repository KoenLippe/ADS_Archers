package nl.hva.ict.se.ads;

import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Place all your own tests for Archer in this class. Tests in any other class will be ignored!
 */
public class ExtendedArcherTest extends ArcherTest {

    @Test
    public void testgetWeightedScore() {


        List<Archer> list = Archer.generateArchers(2);


        int[][] pointsArray = list.get(0).getRoundPoints();


        for (int ronde = 0; ronde < 10; ronde++) {
            for (int point = 0; point < 3; point++) {
                System.out.print(pointsArray[ronde][point] + " ");
            }
        }

        System.out.println();
        System.out.println(list.get(0).getWeightedScore());


    }
}
