package nl.hva.ict.se.ads;

import nl.hva.ict.se.ads.setup.TestSetup;
import nl.hva.ict.se.ads.utils.ArcherComparator;
import nl.hva.ict.se.ads.utils.EfficiencyTimeHolder;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EfficiencyTest extends TestSetup {

    private final int NUMBER_OF_TESTS = 10;

    private final int STARTING_ARCHERS = 100;
    private final int MAX_AMOUNT_OF_ARCHERS = 5_000_000;
    private final int MAX_AMOUNT_SECONDS = 20000;



    @Test
    public void efficiencyTestInsertionSort() {
        LocalDateTime testStart = LocalDateTime.now();
        EfficiencyTimeHolder timeHolder = new EfficiencyTimeHolder();

        //Testing Efficiency test 10 times.
        for (int i = 1; i < NUMBER_OF_TESTS + 1; i++) {
            System.out.println("Running test #" + i);


            long duration = 0;
            //Testing efficiency itself
            for (int amountOfArchers = STARTING_ARCHERS; amountOfArchers < MAX_AMOUNT_OF_ARCHERS && duration < MAX_AMOUNT_SECONDS; amountOfArchers *= 2) {

                List<Archer> archers = Archer.generateArchers(amountOfArchers);

                //Sorting
                long start = System.currentTimeMillis();
                ChampionSelector.selInsSort(archers, new ArcherComparator());
                long end = System.currentTimeMillis();

                //Calculating duration and putting it in holder object
                duration = end - start;
                timeHolder.put(amountOfArchers, duration);

                System.out.println(String.format("%d;%d", amountOfArchers, duration));
            }
            System.out.println();
        }

        //Printing test results
        timeHolder.print();

        LocalDateTime testEnd = LocalDateTime.now();

        System.out.println("All tests took: " + Duration.between(testStart, testEnd).toSeconds() + " seconds");


    }

}
