package com.meancat.study.datastructures;

import java.util.List;

/**
 * User: trasa
 * Created: 5/19/13 2:03 PM
 */
public class BinarySearcher<T extends Comparable<T>> {
    private List<T> values;

    public BinarySearcher(List<T> values) {
        this.values = values;
    }

    public int findIndex(T value) {
        return search(value, 0, values.size() - 1);
    }

    private int search(T value, int low, int high) {
        if (high < low) {
            System.out.println("Not found...");
            return -1;
        }
        int pos = low + ((high - low) / 2);
        System.out.println(String.format("Searching for %s from range %d to %d as pos %d",
                value, low, high, pos));
        int c = value.compareTo(values.get(pos));
        if (c == 0) {
            System.out.println("found it!");
            return pos;
        }
        if (c < 0) {
            return search(value, low, pos - 1); // search low half
        }
        // search top half
        return search(value, pos + 1, high);
    }
}
