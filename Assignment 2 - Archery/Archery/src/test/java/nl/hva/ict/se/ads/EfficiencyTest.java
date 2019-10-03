package nl.hva.ict.se.ads;

import nl.hva.ict.se.ads.setup.TestSetup;
import nl.hva.ict.se.ads.utils.ArcherComparator;
import nl.hva.ict.se.ads.utils.EfficiencyTimeHolder;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class EfficiencyTest extends TestSetup {

    private final int NUMBER_OF_TESTS = 10;

    private final int STARTING_ARCHERS = 100;
    private final int MAX_AMOUNT_OF_ARCHERS = 5_000_000;
    private final int MAX_AMOUNT_SECONDS = 20_000;

    


    private void efficiencyTest(String method) {
        LocalDateTime testStart = LocalDateTime.now();
        EfficiencyTimeHolder timeHolder = new EfficiencyTimeHolder();

        //Testing Efficiency test 10 times.
        for (int i = 1; i < NUMBER_OF_TESTS + 1; i++) {
            System.out.println(String.format("Running %s sort test #%d", method, i));


            long duration = 0;
            //Testing efficiency itself
            for (int amountOfArchers = STARTING_ARCHERS; amountOfArchers < MAX_AMOUNT_OF_ARCHERS && duration < MAX_AMOUNT_SECONDS; amountOfArchers *= 2) {

                List<Archer> archers = Archer.generateArchers(amountOfArchers);

                long start = 0, end = 0;
                //Sorting
                switch (method) {
                    case "insertion":
                        //Selection Sort
                        start = System.currentTimeMillis();
                        ChampionSelector.selInsSort(archers, new ArcherComparator());
                        end = System.currentTimeMillis();
                        break;

                    case "quick":
                        //Quick Sort
                        start = System.currentTimeMillis();
                        ChampionSelector.quickSort(archers, new ArcherComparator());
                        end = System.currentTimeMillis();
                        break;

                    case "collection":
                        //Collection Sort
                        start = System.currentTimeMillis();
                        ChampionSelector.collectionSort(archers, new ArcherComparator());
                        end = System.currentTimeMillis();
                        break;
                }


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


    @Test
    public void efficiencyTestInsertionSort() {
        efficiencyTest("insertion");
    }

    @Test
    public void efficiencyTestQuickSort() {
        efficiencyTest("quick");
    }

    @Test
    public void efficiencyTestCollectionSort() {
        efficiencyTest("collection");
    }


}
