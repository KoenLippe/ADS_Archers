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

    //Sorts the archers using quick sort
    private static void sort(List<Archer> archers, int low, int high, Comparator<Archer> comparator) {

        //Skips sorting if the list is made of one item of if low and high aren't right
        if (low < high) {

            // The partition method returns the pivot index.
            int pivot = partition(archers, low, high, comparator);

            //Then we proceed to sort the both partitions
            //Sort lower partition
            sort(archers, low, pivot - 1, comparator);

            //Sort higher partition
            sort(archers, pivot + 1, high, comparator);
        }


    }

    /**
     * This method assigns a pivot and divides the list into two partitions,
     * items less than the pivot, and items greater than the pivot
     *
     * @param archers List of archers to be sorted
     * @param low Lowerst index of the list
     * @param high Higherst index of the list
     * @param comparator Archer comparator
     * @return Pivot index
     */
    private static int partition(List<Archer> archers, int low, int high, Comparator<Archer> comparator) {


        //Pivot starts as the highest number
        int pivotIndex = high;

        // The variable "i" is going to be the tail of the partition of items greater than the pivot.
        int i = low - 1;


        // The variable "j" is going to scan the items ahead of "i" and determine where they should be placed
        for (int j = low; j < high; j++) {

            // If the archer at "j" has better scores than archer at the pivot, swap J with i and move i up one
            // The best archers are supposed to come first, so we throw them at the beginning of the list.
            if (comparator.compare(archers.get(j), archers.get(pivotIndex)) <= 0) {
                i++;
                swap(archers, i, j);

                // At the end of this if statement j also moves up one
            }

        }

        // i is at the tail of the section of items greater than the pivot. So we switch i+1 with the pivot
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
