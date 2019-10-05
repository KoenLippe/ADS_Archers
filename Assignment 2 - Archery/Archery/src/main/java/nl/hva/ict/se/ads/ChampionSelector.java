package nl.hva.ict.se.ads;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Given a list of Archer's this class can be used to sort the list using one of three sorting algorithms.
 */
public class ChampionSelector {
    /**
     * This method uses insertion sort for sorting the archers.
     */
    public static List<Archer> selInsSort(List<Archer> archers, Comparator<Archer> scoringScheme) {
        //Insertion Sort

        for (int i = 1; i < archers.size(); i++) {
            Archer comparingTo = archers.get(i);

            int j = i - 1;

            while (j >= 0 && scoringScheme.compare(archers.get(j), comparingTo) > 0) {
                //comparingTo is smaller than the current Archer at index j.
                //comparingTo is is memory so j + 1 can be overridden.
                archers.set(j + 1, archers.get(j)); //Moving last sorted Archer up one.
                j -= 1;
            }
            //Finally setting comparingTo at the correct place
            archers.set(j + 1, comparingTo);

        }
        return archers;
    }

    /**
     * This method uses quick sort for sorting the archers.
     */
    public static List<Archer> quickSort(List<Archer> archers, Comparator<Archer> scoringScheme) {


        sort(archers, 0, archers.size() - 1, scoringScheme);
        return archers;
    }

    private static void sort(List<Archer> archers, int low, int high, Comparator<Archer> comparator) {
        if (low < high) {
            int j = partition(archers, low, high, comparator);
            sort(archers, low, j - 1, comparator);
            sort(archers, j + 1, high, comparator);
        }


    }

    private static int partition(List<Archer> archers, int low, int high, Comparator<Archer> comparator) {


        //Pivot is the beginning
        int pivotIndex = high;

        int i = low - 1; //Smalller index


        for (int j = low; j < high; j++) { //J = higher index

            // If the archer at J has better scores than archer at the pivot, swap J with i and move i up one
            if (comparator.compare(archers.get(j), archers.get(pivotIndex)) <= 0) {
                i++;
                swap(archers, i, j);

                // At the end of this if statement j also moves up one
            }

        }

        // i is at the tail of the section of items less than the pivot. So we switch i+1 with the pivot
        swap(archers, i + 1, high);

        return i + 1; //Correctly placed pivot position

    }


    private static void swap(List<Archer> archers, int archer1, int archer2) {
        Archer savedArcher = archers.get(archer1);  //Save variable from being overridden
        archers.set(archer1, archers.get(archer2)); //2 times archer2
        archers.set(archer2, savedArcher);          //Swapped

    }


    /**
     * This method uses the Java collections sort algorithm for sorting the archers.
     */
    public static List<Archer> collectionSort(List<Archer> archers, Comparator<Archer> scoringScheme) {
        Collections.sort(archers, scoringScheme);
        return archers;
    }

    /**
     * This method uses quick sort for sorting the archers in such a way that it is able to cope with an Iterator.
     *
     * <b>THIS METHOD IS OPTIONAL</b>
     */
    public static Iterator<Archer> quickSort(Iterator<Archer> archers, Comparator<Archer> scoringScheme) {
        return null;
    }

}
