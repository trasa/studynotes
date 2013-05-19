package com.meancat.study.sorting;

import com.meancat.study.util.ListPrinter;

import java.util.List;

/**
 * User: trasa
 * Created: 5/19/13 2:30 PM
 */
public class InsertionSorter {
    public static <T extends Comparable<T>> void sort(List<T> values) {
        for (int j=1; j < values.size(); j++) {
            int i = j - 1;
            T key = values.get(j);
            while (i >= 0 && values.get(i).compareTo(key) > 0) {
                ListPrinter.print(values);
                values.set(i + 1, values.get(i));
                i--;
            }
            values.set(i+1, key);
            ListPrinter.print(values);
        }
    }
}
