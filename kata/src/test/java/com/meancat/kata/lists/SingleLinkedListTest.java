package com.meancat.kata.lists;

import com.meancat.kata.lists.SingleLinkedList;
import org.junit.Test;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;

public class SingleLinkedListTest {

    @Test
    public void addToHead() {
        SingleLinkedList<Integer> list = new SingleLinkedList<Integer>();
        assertNull(list.head);
        list.addToHead(0);
        assertEquals(0, list.head.value.intValue());
        list.addToHead(1);
        assertEquals(1, list.head.value.intValue());
        list.addToHead(2);
        assertEquals(2, list.head.value.intValue());
        System.out.println(list.toString());
        assertEquals("2 -> 1 -> 0", list.toString());
    }

    @Test
    public void addToTail() {
        SingleLinkedList<Integer> list = new SingleLinkedList<Integer>();
        assertNull(list.head);
        list.addToTail(0);
        assertEquals(0, list.head.value.intValue());
        list.addToTail(1);
        assertEquals(0, list.head.value.intValue());
        list.addToTail(2);
        assertEquals(0, list.head.value.intValue());
        System.out.println(list.toString());
        assertEquals("0 -> 1 -> 2", list.toString());
    }

    @Test
    public void removeHead() {
        SingleLinkedList<Integer> list = new SingleLinkedList<Integer>();
        list.addToTail(0);
        list.addToTail(1);
        list.addToTail(2);

        list.remove(0);
        assertEquals(1, list.head.value.intValue());
        System.out.println(list.toString());
        assertEquals("1 -> 2", list.toString());
    }

    @Test
    public void removeMiddle() {
        SingleLinkedList<Integer> list = new SingleLinkedList<Integer>();
        list.addToTail(0);
        list.addToTail(1);
        list.addToTail(2);

        list.remove(1);
        assertEquals(0, list.head.value.intValue());
        System.out.println(list.toString());
        assertEquals("0 -> 2", list.toString());
    }

    @Test
    public void removeTail() {
        SingleLinkedList<Integer> list = new SingleLinkedList<Integer>();
        list.addToTail(0);
        list.addToTail(1);
        list.addToTail(2);

        list.remove(2);
        assertEquals(0, list.head.value.intValue());
        System.out.println(list.toString());
        assertEquals("0 -> 1", list.toString());
    }

    @Test
    public void removeNotFound() {
        SingleLinkedList<Integer> list = new SingleLinkedList<Integer>();
        list.addToTail(0);
        list.addToTail(1);
        list.addToTail(2);

        list.remove(3);
        assertEquals(0, list.head.value.intValue());
        System.out.println(list.toString());
        assertEquals("0 -> 1 -> 2", list.toString());
    }

    @Test
    public void removeToEmptyList() {
        SingleLinkedList<Integer> list = new SingleLinkedList<Integer>();
        list.addToTail(0);
        list.addToTail(1);
        list.addToTail(2);

        list.remove(1);
        assertEquals(0, list.head.value.intValue());
        list.remove(2);
        assertEquals(0, list.head.value.intValue());
        list.remove(0);
        assertNull(list.head);
        System.out.println(list.toString());
        assertEquals("(empty list)", list.toString());
    }
}