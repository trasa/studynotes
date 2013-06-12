package com.meancat.kata;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SingleLinkedListTest {

    @Test
    public void addToHead() {
        SingleLinkedList<Integer> list = new SingleLinkedList<Integer>();
        list.addToHead(0);
        list.addToHead(1);
        list.addToHead(2);
        System.out.println(list.toString());
        assertEquals("2 -> 1 -> 0", list.toString());
    }

    @Test
    public void addToTail() {
        SingleLinkedList<Integer> list = new SingleLinkedList<Integer>();
        list.addToTail(0);
        list.addToTail(1);
        list.addToTail(2);
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
        System.out.println(list.toString());
        assertEquals("0 -> 1 -> 2", list.toString());
    }
}