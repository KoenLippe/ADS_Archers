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

        for (int i = 1; i < archers.size() ; i++) {
           Archer comparingTo = archers.get(i);

           int j = i - 1;

           while(j >= 0 && scoringScheme.compare(archers.get(j), comparingTo) > 0) {
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
    public static List<Archer> quickSort(List<Archer> archers, Comparator<Archer> scoringScheme, int left, int right) {

        if (left < right){

            /**
             * The final pivot position returned by the partition() method.
             * It is in the middle of the left and right partitions
             **/
            int finalPivotPosition = partition(archers, scoringScheme, left, right);

            // Quickort the partitions to the left and the right of the pivot
            quickSort(archers, scoringScheme, left, finalPivotPosition -1);
            quickSort(archers, scoringScheme, finalPivotPosition -1, right);
        }
        // Return the quicksorted list of archers
        return archers;
    }

    public static int partition(List<Archer> archers, Comparator<Archer> scoringScheme, int left, int right){

        //Pivot is the last element
        Archer pivot = archers.get(right);

        int tailLeftPartition = left - 1;

        // Let scanner scan till the end of the partition
        for (int scanner = left; scanner < right ; scanner++) {

            //If scanner is smaller or equal than the pivot
            if(scoringScheme.compare(archers.get(scanner), pivot) < 0){

                //Increase the size of items that are smaller than the pivot
                tailLeftPartition++;

                swap(archers, tailLeftPartition, scanner);
            }

        }
        /**
         * Set the pivot in its correct place, that is,
         * after the section of items that is less than the pivot
         */
        swap(archers,tailLeftPartition + 1, right);

        //Return the pivots position
        return tailLeftPartition + 1;
    }

    /**
     * This method swaps two elements
     *
     * @param archers The archers list
     * @param tailLeftPartition The end of the partition with items less than the pivot
     * @param scanner The parameter that scans the next element
     */
    private static void swap(List<Archer> archers, int tailLeftPartition, int scanner) {

        // Makes copy of tail
        Archer tailLeft = archers.get(tailLeftPartition);

        //Swaps tail element with scanner element
        archers.set(tailLeftPartition, archers.get(scanner));
        archers.set(scanner, tailLeft);
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
