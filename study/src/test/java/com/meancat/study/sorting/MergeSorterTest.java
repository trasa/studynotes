package com.meancat.study.sorting;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * User: trasa
 * Created: 5/19/13 2:43 PM
 */
public class MergeSorterTest extends SorterTest {
    @Test
    public void basicSort() {
        List<Integer> values = Arrays.asList(32, 1, 2, 18, 5, 100, 64, 0);
        values = new MergeSorter<Integer>().sort(values);
        assertSorted(values);
    }

    @Test
    public void randomSort() {
        List<Integer> values = getRandomIntegers(100);
        values = new MergeSorter<Integer>().sort(values);
        assertSorted(values);
    }
}
