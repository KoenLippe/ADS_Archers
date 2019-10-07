package nl.hva.ict.se.ads;

import nl.hva.ict.se.ads.utils.ArcherComparator;
import nl.hva.ict.se.ads.utils.EfficiencyTimeHolder;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EfficiencyTest {

    private final int NUMBER_OF_TESTS = 2;

    private final int STARTING_ARCHERS = 100;
    private final int MAX_AMOUNT_OF_ARCHERS = 2000;
    private final int MAX_AMOUNT_SECONDS = 20_000;


    private final int MAX_ARCHERS_INSERTION_XINT = 6400;
    private final int MAX_ARCHERS_QUICK_XINT = 204_800;
    private final int MAX_ARCHERS_COLLECTION_XINT = 819_200;

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
    public void testingAllSortingMethods() throws IOException {


        EfficiencyTimeHolder insertionTimeHolder = new EfficiencyTimeHolder();
        EfficiencyTimeHolder quicksortTimeHolder = new EfficiencyTimeHolder();
        EfficiencyTimeHolder collectionTimeHolder = new EfficiencyTimeHolder();


        for (int i = 0; i < NUMBER_OF_TESTS; i++) {
            long start = 0;
            long end = 0;

            long duration = 0;
            //Testing efficiency itself
            for (int amountOfArchers = STARTING_ARCHERS; amountOfArchers < MAX_AMOUNT_OF_ARCHERS && duration < MAX_AMOUNT_SECONDS; amountOfArchers *= 2) {

                List<Archer> insertionSortArchers = new ArrayList<>(0);
                List<Archer> collectionSortArchers = new ArrayList<>(0);
                List<Archer> quickSortArchers = new ArrayList<>(0);

                System.out.println();
                long[] times = new long[3];

                //Generating single archer list
                List<Archer> archers = Archer.generateArchers(amountOfArchers);


                //Filling arrays in case
                if (amountOfArchers < MAX_ARCHERS_INSERTION_XINT) {
                    //Setting insertionSort Array
                    System.out.println("Setting insertionSort Array");
                    insertionSortArchers = new ArrayList<>(archers);
                }

                if (amountOfArchers < MAX_ARCHERS_QUICK_XINT) {
                    //Setting quicksort Array
                    System.out.println("Setting quicksort Array ");
                    quickSortArchers = new ArrayList<>(archers);
                }

                if (amountOfArchers < MAX_ARCHERS_COLLECTION_XINT) {
                    //Setting collectionSort Array
                    System.out.println("Setting collectionSort Array ");
                    collectionSortArchers = new ArrayList<>(archers);
                }

                System.out.println();

                //Only perfoming test if array has archers
                if (insertionSortArchers.size() != 0) {
                    System.out.println("Sorting with insertion sort: " + amountOfArchers);

                    //InsertionSort
                    start = System.currentTimeMillis();
                    ChampionSelector.selInsSort(insertionSortArchers, new ArcherComparator());
                    end = System.currentTimeMillis();

                    long insertionSortDuration = end - start;
                    times[0] = insertionSortDuration;
                    insertionTimeHolder.put(amountOfArchers, insertionSortDuration);
                }

                //Only perfoming test if array has archers
                if (quickSortArchers.size() != 0) {
                    System.out.println("Sorting with quick sort: " + amountOfArchers);

                    //QuickSort
                    start = System.currentTimeMillis();
                    ChampionSelector.quickSort(quickSortArchers, new ArcherComparator());
                    end = System.currentTimeMillis();

                    long quickSortDuration = end - start;
                    times[1] = quickSortDuration;
                    quicksortTimeHolder.put(amountOfArchers, quickSortDuration);

                }

                //Only perfoming test if array has archers
                if (collectionSortArchers.size() != 0) {
                    System.out.println("Sorting with collection sort: " + amountOfArchers);

                    //CollectionSort
                    start = System.currentTimeMillis();
                    ChampionSelector.collectionSort(collectionSortArchers, new ArcherComparator());
                    end = System.currentTimeMillis();

                    long collectionSortDuration = end - start;
                    times[2] = collectionSortDuration;
                    collectionTimeHolder.put(amountOfArchers, collectionSortDuration);
                }

                long max = times[0];
                if (times[1] > max) {
                    max = times[1];
                } else if (times[2] > max) {
                    max = times[2];
                }

                duration = max;

            }

        }



        BufferedWriter writer = new BufferedWriter(new FileWriter(new Date().toString()));




        System.out.println("Insertion sort");
        writer.write("Insertion sort");
        insertionTimeHolder.save(writer);
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("Quick sort");
        quicksortTimeHolder.print();
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("Collection sort");
        collectionTimeHolder.print();
        System.out.println();
        System.out.println();
        System.out.println();

        writer.close();


    }

    /**
     * This function tests what the max amount of archers are that can be solved in less than 20 seconds
     * This value has manually been put as the final value on top of the file.]
     * That value is used to determine the max amount of archers used to test all 3 methods
     * <p>
     * The function is executed with the -Xint VM option
     *
     * @author koenlippe
     */
    @Test
    public void getMaxArchersForQuickSortInTime() {


        for (int i = 0; i < 4; i++) {
            EfficiencyTimeHolder timeHolder = new EfficiencyTimeHolder();
            long duration = 0;

            //Testing efficiency itself
            for (int amountOfArchers = STARTING_ARCHERS; amountOfArchers < MAX_AMOUNT_OF_ARCHERS && duration < MAX_AMOUNT_SECONDS; amountOfArchers *= 2) {

                List<Archer> archers = Archer.generateArchers(amountOfArchers);

                //Selection Sort
                long start = System.currentTimeMillis();
                ChampionSelector.quickSort(archers, new ArcherComparator());
                long end = System.currentTimeMillis();

                duration = end - start;
                System.out.println(String.format("Sorted %d archers in %d seconds", amountOfArchers, duration));

                timeHolder.put(amountOfArchers, duration);
            }
        }

    }


    /**
     * This function tests what the max amount of archers are that can be solved in less than 20 seconds
     * This value has manually been put as the final value on top of the file.]
     * That value is used to determine the max amount of archers used to test all 3 methods
     * <p>
     * The function is executed with the -Xint VM option
     *
     * @author koenlippe
     */
    @Test
    public void getMaxArchersForInsertionSortInTime() {


        for (int i = 0; i < 4; i++) {
            EfficiencyTimeHolder timeHolder = new EfficiencyTimeHolder();
            long duration = 0;

            //Testing efficiency itself
            for (int amountOfArchers = STARTING_ARCHERS; amountOfArchers < MAX_AMOUNT_OF_ARCHERS && duration < MAX_AMOUNT_SECONDS; amountOfArchers *= 2) {

                List<Archer> archers = Archer.generateArchers(amountOfArchers);

                //Selection Sort
                long start = System.currentTimeMillis();
                ChampionSelector.selInsSort(archers, new ArcherComparator());
                long end = System.currentTimeMillis();

                duration = end - start;
                System.out.println(String.format("Sorted %d archers in %d seconds", amountOfArchers, duration));

                timeHolder.put(amountOfArchers, duration);
            }
        }


    }

    /**
     * This function tests what the max amount of archers are that can be solved in less than 20 seconds
     * This value has manually been put as the final value on top of the file.]
     * That value is used to determine the max amount of archers used to test all 3 methods
     * <p>
     * The function is executed with the -Xint VM option
     *
     * @author koenlippe
     */
    @Test
    public void getMaxArchersForCollectionSortInTime() {


        for (int i = 0; i < 4; i++) {
            EfficiencyTimeHolder timeHolder = new EfficiencyTimeHolder();
            long duration = 0;

            //Testing efficiency itself
            for (int amountOfArchers = STARTING_ARCHERS; amountOfArchers < MAX_AMOUNT_OF_ARCHERS && duration < MAX_AMOUNT_SECONDS; amountOfArchers *= 2) {

                List<Archer> archers = Archer.generateArchers(amountOfArchers);

                //Selection Sort
                long start = System.currentTimeMillis();
                ChampionSelector.collectionSort(archers, new ArcherComparator());
                long end = System.currentTimeMillis();

                duration = end - start;
                System.out.println(String.format("Sorted %d archers in %d seconds", amountOfArchers, duration));

                timeHolder.put(amountOfArchers, duration);
            }
        }


    }

}
