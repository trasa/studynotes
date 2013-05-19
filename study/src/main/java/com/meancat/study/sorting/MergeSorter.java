package com.meancat.study.sorting;


import java.util.ArrayList;
import java.util.List;

/**
 * User: trasa
 * Created: 5/19/13 2:38 PM
 */
public class MergeSorter<T extends Comparable<T>> {

    public List<T> sort(List<T> values) {
        if (values.size() <= 1) {
            return values;
        }

        int middle = values.size() / 2;
        List<T> left = new ArrayList<T>();
        List<T> right = new ArrayList<T>();

        for (int i=0; i < middle; i++) {
            left.add(values.get(i));
        }
        for (int i=middle; i < values.size(); i++) {
            right.add(values.get(i));
        }
        left = sort(left);
        right = sort(right);
        if (last(left).compareTo(last(right)) > 0) {
            return merge(left, right);
        }
        return merge(right, left);
    }

    private T last(List<T> values) {
        return values.get(values.size() - 1);
    }

    private T first(List<T> values) {
        return values.get(0);
    }

    private List<T> merge(List<T> left, List<T> right) {
        List<T> result = new ArrayList<T>();
        while(left.size() > 0 && right.size() > 0) {
            if (first(left).compareTo(first(right)) <= 0) {
                result.add(first(left));
                left.remove(0);
            } else {
                result.add(first(right));
                right.remove(0);
            }
        }
        if (left.size() > 0) {
            result.addAll(left);
        } else {
            result.addAll(right);
        }
        return result;
    }
}
