package nl.hva.ict.se.ads.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

    /**
     * Used to store an efficiency time value at a certain key
     *
     * @param index The number of archers with the value
     * @param value The time it took the sort the number of archer
     */
    public void put(int index, long value) {
        //If no arraylist is present yet. Make one
        map.computeIfAbsent(index, k -> new ArrayList<Long>());
        //And eventually add value to the arraylist at the index.
        map.get(index).add(value);

    }

    /**
     * Prints stored efficiency times to screen.
     *
     * @author koenlippe
     */
    public void print() {
        //For each numberOfArchers generate a string that is
        for (Map.Entry<Integer, ArrayList<Long>> entry : map.entrySet()) {
            ArrayList timeList = entry.getValue();

            //Print numberOfArchers
            System.out.print(entry.getKey() + ";");

            //For every time in list output "tijd + ';'" to get value that can be used in Excel

            for (Object tijd: timeList) {
                //Add the time
                System.out.print(tijd + ";");
            }

            System.out.println();

        }

    }

    /**
     * Saves efficiency times to file using fiven bufferedWriter
     *
     * @param bufferedWriter Writer to use
     * @author koenlippe
     */
    public void save(BufferedWriter bufferedWriter) {
        try {
            for (Map.Entry<Integer, ArrayList<Long>> entry : map.entrySet()) {
                ArrayList timeList = entry.getValue();

                //Print numberOfArchers
                bufferedWriter.write(entry.getKey() + ";");

                //For every time in list output "tijd + ';'" to get value that can be used in Excel
                for (Object tijd: timeList) {
                    //Add the time
                    bufferedWriter.write(tijd + ";");
                }


                bufferedWriter.write("\n");

            }

        }catch (IOException e) {
            e.printStackTrace();
        }


    }







}
