package nl.hva.ict.se.ads;

import nl.hva.ict.se.ads.utils.ArcherComparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Place all your own tests for ChampionSelector in this class. Tests in any other class will be ignored!
 */
public class ExtendedChampionSelectorTest extends ChampionSelectorTest {

    @Test
    public void testingAllSortingMethods() {
        List<Archer> archers = Archer.generateArchers(100);

        List<Archer> quickSorted = new ArrayList<>(archers);
        List<Archer> insertionSorted = new ArrayList<>(archers);
        List<Archer> collectionSorted = new ArrayList<>(archers);;


        ChampionSelector.collectionSort(collectionSorted, new ArcherComparator());

        ChampionSelector.quickSort(quickSorted, new ArcherComparator());

        ChampionSelector.selInsSort(insertionSorted, new ArcherComparator());


        Collections.sort(archers, new ArcherComparator()); //Sorting archers
        Assertions.assertEquals(archers, insertionSorted);
        Assertions.assertEquals(archers, quickSorted);
    }
}
