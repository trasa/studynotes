package com.meancat.study.sorting;

import com.meancat.study.util.ListPrinter;

import java.util.Collections;
import java.util.List;

/**
 * User: trasa
 * Created: 5/19/13 2:15 PM
 */
public class HeapSorter<T extends Comparable<T>> {
    private List<T> values;
    private int heapSize;

    public void sort(List<T> values) {
        this.values = values;
        heapSize = values.size();
        buildHeap();
        ListPrinter.print(values);
        if (!isHeap()) {
            throw new IllegalStateException("should be a heap here!");
        }
        for (int i= values.size() - 1; i >0; i--) {
            Collections.swap(this.values, 0, i);
            heapSize--;
            heapify(0);
        }
    }

    private void buildHeap() {
        for(int i = heapSize/2; i >=0; i--) {
            heapify(i);
        }
    }

    private void heapify(int i) {
        Integer leftIndex = left(i);
        Integer rightIndex = right(i);
        int largest;
        if (leftIndex != null && values.get(leftIndex).compareTo(values.get(i)) > 0) {
            largest = leftIndex;
        } else {
            largest = i;
        }
        if (rightIndex != null && values.get(rightIndex).compareTo(values.get(largest)) > 0) {
            largest = rightIndex;
        }
        if (largest == i) {
            return;
        }
        Collections.swap(values, i, largest);
        heapify(largest);
    }

    private Integer left(int i) {
        i = 2*i + 1;
        if (i < 0 || i >= heapSize) {
            return null;
        }
        return i;
    }

    private Integer right(int i) {
        i = 2*i + 2;
        if (i < 0 || i >= heapSize) {
            return null;
        }
        return i;
    }

    private Integer parent(int i) {
        return (i - 1) / 2;
    }

    public boolean isHeap() {
        for (int i=0; i < heapSize; i++) {
            if (values.get(parent(i)).compareTo(values.get(i)) < 0) {
                return false;
            }
        }
        return true;
    }
}
