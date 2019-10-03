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
    public static List<Archer> quickSort(List<Archer> archers, Comparator<Archer> scoringScheme) {
        if(archers.size()>1){
            int start = 0;
            int end = archers.size() - 1;
            int pivot = start;
            while (scoringScheme.compare(archers.get(start), archers.get(pivot)) < 0){
                //While start is smaller than pivot
                //*Make start bigger
                start++;
            }
            while (end > end){
                //while end is bigger than pivot
                //*Make end smaller//
                end--;
            }
            if(start==end){
                //If they cross
                //*Swap both
            }

        }


        return archers;
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
