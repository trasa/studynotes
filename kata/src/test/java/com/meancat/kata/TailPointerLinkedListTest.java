package com.meancat.kata;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TailPointerLinkedListTest {

    @Test
    public void addToHead() {
        TailPointerLinkedList<Integer> list = new TailPointerLinkedList<Integer>();
        list.addToHead(0);
        assertEquals(0, list.head.value.intValue());
        assertEquals(0, list.tail.value.intValue());
        list.addToHead(1);
        assertEquals(1, list.head.value.intValue());
        assertEquals(0, list.tail.value.intValue());
        list.addToHead(2);
        assertEquals(2, list.head.value.intValue());
        assertEquals(0, list.tail.value.intValue());
        System.out.println(list.toString());
        assertEquals("2 -> 1 -> 0", list.toString());
    }

    @Test
    public void addToTail() {
        TailPointerLinkedList<Integer> list = new TailPointerLinkedList<Integer>();
        list.addToTail(0);
        assertEquals(0, list.head.value.intValue());
        assertEquals(0, list.tail.value.intValue());
        list.addToTail(1);
        assertEquals(0, list.head.value.intValue());
        assertEquals(1, list.tail.value.intValue());
        list.addToTail(2);
        assertEquals(0, list.head.value.intValue());
        assertEquals(2, list.tail.value.intValue());
        System.out.println(list.toString());
        assertEquals("0 -> 1 -> 2", list.toString());
    }

    @Test
    public void removeHead() {
        TailPointerLinkedList<Integer> list = new TailPointerLinkedList<Integer>();
        list.addToTail(0);
        list.addToTail(1);
        list.addToTail(2);

        list.remove(0);
        assertEquals(1, list.head.value.intValue());
        assertEquals(2, list.tail.value.intValue());
        System.out.println(list.toString());
        assertEquals("1 -> 2", list.toString());
    }

    @Test
    public void removeMiddle() {
        TailPointerLinkedList<Integer> list = new TailPointerLinkedList<Integer>();
        list.addToTail(0);
        list.addToTail(1);
        list.addToTail(2);

        list.remove(1);
        assertEquals(0, list.head.value.intValue());
        assertEquals(2, list.tail.value.intValue());
        System.out.println(list.toString());
        assertEquals("0 -> 2", list.toString());
    }

    @Test
    public void removeTail() {
        TailPointerLinkedList<Integer> list = new TailPointerLinkedList<Integer>();
        list.addToTail(0);
        list.addToTail(1);
        list.addToTail(2);

        list.remove(2);
        assertEquals(0, list.head.value.intValue());
        assertEquals(1, list.tail.value.intValue());
        System.out.println(list.toString());
        assertEquals("0 -> 1", list.toString());
    }

    @Test
    public void removeNotFound() {
        TailPointerLinkedList<Integer> list = new TailPointerLinkedList<Integer>();
        list.addToTail(0);
        list.addToTail(1);
        list.addToTail(2);

        list.remove(3);
        assertEquals(0, list.head.value.intValue());
        assertEquals(2, list.tail.value.intValue());
        System.out.println(list.toString());
        assertEquals("0 -> 1 -> 2", list.toString());
    }

    @Test
    public void removeToEmptyList() {
        TailPointerLinkedList<Integer> list = new TailPointerLinkedList<Integer>();
        list.addToTail(0);
        list.addToTail(1);
        list.addToTail(2);

        list.remove(1);
        assertEquals(0, list.head.value.intValue());
        assertEquals(2, list.tail.value.intValue());
        list.remove(2);
        assertEquals(0, list.head.value.intValue());
        assertEquals(0, list.tail.value.intValue());
        list.remove(0);
        assertNull(list.head);
        assertNull(list.tail);
        System.out.println(list.toString());
        assertEquals("(empty list)", list.toString());
    }
}
