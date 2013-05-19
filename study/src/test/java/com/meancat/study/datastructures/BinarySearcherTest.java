package com.meancat.study.datastructures;

import java.util.List;
import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import org.junit.Test;
/**
 * User: trasa
 * Created: 5/19/13 2:05 PM
 */
public class BinarySearcherTest {

    @Test
    public void findFirst() {
        List<Integer> values = buildList(10);
        assertEquals(0, new BinarySearcher<Integer>(values).findIndex(0));
    }

    @Test
    public void findLast() {
        List<Integer> values = buildList(10);
        assertEquals(9, new BinarySearcher<Integer>(values).findIndex(9));
    }


    @Test
    public void findMiddle() {
        List<Integer> values = buildList(10);
        assertEquals(4, new BinarySearcher<Integer>(values).findIndex(4));
        assertEquals(5, new BinarySearcher<Integer>(values).findIndex(5));
    }

    @Test
    public void findAll() {
        for (int max = 1; max < 30; max++){
            System.out.println("building max " + max);
            BinarySearcher<Integer> searcher = new BinarySearcher<Integer>(buildList(max));
            for (int pos = 0; pos < max; pos++){
                assertEquals(pos, searcher.findIndex(pos));
            }
        }
    }

    @Test
    public void wontFind() {
        for (int max = 1; max < 30; max++){
            BinarySearcher<Integer> searcher = new BinarySearcher<Integer>(buildList(max));
            assertEquals(-1, searcher.findIndex(-1));
            assertEquals(-1, searcher.findIndex(max));
        }
    }

    @Test
    public void wontFindFive() {
        List<Integer> values = buildList(12);
        values.remove(new Integer(5));
        assertEquals(-1, new BinarySearcher<Integer>(values).findIndex(5));
    }

    private List<Integer> buildList(int max){
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i=0; i < max; i++)
            result.add(i);
        return result;
    }
}
