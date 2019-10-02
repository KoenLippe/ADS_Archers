package nl.hva.ict.se.ads;

import nl.hva.ict.se.ads.utils.sortingScheme;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class ChampionSelectorTest extends testSetup {
    protected Comparator<Archer> comparator;

    @BeforeEach
    public void createComparator() {
        comparator = new sortingScheme();
        // Instantiate your own comparator here...
        // comparator = new .....();
    }

    @Test
    public void selInsSortAndCollectionSortResultInSameOrder() {
        List<Archer> unsortedArchersForSelIns = Archer.generateArchers(4); //was 23
        List<Archer> unsortedArchersForCollection = new ArrayList<>(unsortedArchersForSelIns);
        List<Archer> sortedArchersSelIns = ChampionSelector.selInsSort(unsortedArchersForSelIns, comparator);


        for(Archer archer : unsortedArchersForCollection) {
            System.out.println(archer);
        }



        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        List<Archer> sortedArchersCollection = ChampionSelector.collectionSort(unsortedArchersForCollection, comparator);


        for (Archer archer : sortedArchersCollection) {
            System.out.println(archer);
        }


        Assertions.assertEquals(sortedArchersCollection, sortedArchersSelIns);
    }

}