package com.meancat.study.sorting;

import java.util.Collections;
import java.util.List;

/**
 * User: trasa
 * Created: 5/19/13 2:46 PM
 */
public class QuickSorter<T extends Comparable<T>>{
    private List<T> values;

    public QuickSorter(List<T> values) {
        this.values = values;
    }

    public static <T extends Comparable<T>> void sort(List<T> values) {
        QuickSorter<T> qs = new QuickSorter<T>(values);
        qs.qsort(0, values.size() - 1);
    }

    private void qsort(int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int part = getPartition(startIndex, endIndex);
            qsort(startIndex, part);
            qsort(part + 1, endIndex);
        }
    }

    private int getPartition(int startIndex, int endIndex) {
        T pivot = values.get(startIndex);
        int bottom = startIndex - 1;
        int top = endIndex + 1;
        while(true) {
            // decrement top until we find an element less than pivot,
            // this element doesn't belong in the top section
            do {
                top--;
            } while(values.get(top).compareTo(pivot) > 0);
            // increment bottom until we find an element that is greater than pivot
            // this element doesn't belong in the bottom section
            do {
                bottom++;
            } while(values.get(bottom).compareTo(pivot) < 0);

            // do we need to swap?
            if (bottom < top) {
                Collections.swap(values, bottom, top);
            } else {
                return top;
            }
        }
    }
}
