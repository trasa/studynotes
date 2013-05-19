package com.meancat.study.sorting;

import java.util.*;

import static junit.framework.Assert.assertEquals;

/**
 * User: trasa
 * Created: 5/19/13 2:22 PM
 */
public abstract class SorterTest {

    protected void assertSorted(List<Integer> values) {
        String mySort = valuesToString(values);
        Collections.sort(values);
        String theirSort = valuesToString(values);
        System.out.println(mySort);
        System.out.println(theirSort);
        assertEquals(theirSort, mySort);
    }

    protected String valuesToString(List<Integer> values) {
        if (values == null || values.isEmpty()) {
            return "";
        }
        Iterator<Integer> iter = values.iterator();
        StringBuilder sb = new StringBuilder(iter.next().toString());
        while(iter.hasNext()) {
            sb.append(",").append(iter.next().toString());
        }
        return sb.toString();
    }

    protected List<Integer> getRandomIntegers(int size) {
        List<Integer> result = new ArrayList<Integer>(size);
        Random generator = new Random();
        for(int i=0; i < size; i++) {
            result.add(generator.nextInt());
        }
        return result;
    }
}
