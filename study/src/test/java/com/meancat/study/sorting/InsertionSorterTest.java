package com.meancat.study.sorting;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: trasa
 * Created: 5/19/13 2:31 PM
 */
public class InsertionSorterTest extends SorterTest {
    @Test
    public void simpleSort() {
        List<Integer> values = Arrays.asList(32, 1, 2, 18, 5, 100, 64, 0);
        InsertionSorter.sort(values);
        assertSorted(values);
    }

    @Test
    public void randomSort() {
        List<Integer> values = getRandomIntegers(100);
        InsertionSorter.sort(values);
        assertSorted(values);
    }

    @Test
    public void boringSort() {
        List<Integer> values = Arrays.asList(1, 2, 3, 4, 5);
        InsertionSorter.sort(values);
        assertSorted(values);
    }

    @Test
    public void backwardsSort() {
        List<Integer> values = Arrays.asList(5, 4, 3, 2, 1);
        InsertionSorter.sort(values);
        assertSorted(values);
    }

    @Test
    public void sameSort() {
        List<Integer> values = Arrays.asList(1, 1, 1, 1);
        InsertionSorter.sort(values);
        assertSorted(values);
    }

    @Test
    public void nullSort() {
        List<Integer> values = new ArrayList<Integer>();
        InsertionSorter.sort(values);
        assertSorted(values);
    }
}
