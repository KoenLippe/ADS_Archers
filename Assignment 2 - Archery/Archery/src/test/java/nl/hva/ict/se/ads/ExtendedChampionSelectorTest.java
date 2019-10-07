package nl.hva.ict.se.ads;

import nl.hva.ict.se.ads.utils.ArcherComparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Place all your own tests for ChampionSelector in this class. Tests in any other class will be ignored!
 */
public class ExtendedChampionSelectorTest extends ChampionSelectorTest {

    @Test
    public void testQuickSort() {
        List<Archer> archers = Archer.generateArchers(10);
        List<Archer> quickSorted = new ArrayList<>(archers);
        List<Archer> insertionSorted = new ArrayList<>(archers);
        List<Archer> collectionSorted = new ArrayList<>(archers);;
        ChampionSelector.collectionSort(collectionSorted, new ArcherComparator());




        System.out.println("Using Java's Selection sort");
        for (Archer archer : collectionSorted) {
            System.out.println(archer);
        }

        System.out.println();
        System.out.println();
        System.out.println();


        ChampionSelector.quickSort(quickSorted, new ArcherComparator());
        System.out.println("Using Own quicksort");
        for (Archer archer : archers) {
            System.out.println(archer);
        }

        System.out.println();
        System.out.println();
        System.out.println();


        ChampionSelector.quickSort(insertionSorted, new ArcherComparator());
        System.out.println("Using Insertion sort");
        for (Archer archer : insertionSorted) {
            System.out.println(archer);
        }


        Assertions.assertEquals(archers, collectionSorted);
        Assertions.assertEquals(archers, insertionSorted);
        Assertions.assertEquals(archers, quickSorted);
    }
}
