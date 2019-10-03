package nl.hva.ict.se.ads.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A class to hold and display all time it takes to sort the array.
 *
 * @author koenlippe
 */
public class EfficiencyTimeHolder {

    Map<Integer, ArrayList<Long>> map;

    public EfficiencyTimeHolder() {
        map = new HashMap<>();
    }

    public void put(int index, long value) {
        //If no arraylist is present yet. Make one
        map.computeIfAbsent(index, k -> new ArrayList<Long>());
        //And eventually add value to the arraylist at the index.
        map.get(index).add(value);

    }

    public void print() {
        //For each numberOfArchers generate a string that is
        for (Map.Entry<Integer, ArrayList<Long>> entry : map.entrySet()) {
            ArrayList timeList = entry.getValue();

            //Print numberOfArchers
            System.out.print(entry.getKey() + ";");

            long total = 0;
            for (Object tijd: timeList) {
                //Add the time
                System.out.print(tijd + ";");
                total += (Long)tijd;
            }

            System.out.println();
            //Print average for archer.
            System.out.println(String.format("Archers: %d, Average Time: %d", entry.getKey(), total/timeList.size()));
            System.out.println();
        }

    }







}
