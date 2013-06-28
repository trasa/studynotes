package com.meancat.kata.lists;

import com.meancat.kata.DoubleNode;
import com.meancat.kata.lists.DoubleLinkedList;
import org.junit.Test;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class DoubleLinkedListTest {

    private void assertListEmpty(DoubleLinkedList<Integer> list) {
        assertNull(list.head);
    }

    private void assertListSizeOne(DoubleLinkedList<Integer> list) {
        assertNull(list.head.next);
        assertNull(list.head.prev);
    }

    private void assertDoubleList(DoubleLinkedList<Integer> list) {
        DoubleNode<Integer> cur = list.head;
        assertNull(list.head.prev);
        while(cur != null) {
            if (cur.prev != null) {
                assertSame(cur, cur.prev.next);
            }
            if (cur.next != null) {
                assertSame(cur, cur.next.prev);
            }
            cur = cur.next;
        }
    }

    @Test
    public void addToHead() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
        assertListEmpty(list);
        list.addToHead(0);
        assertEquals(0, list.head.value.intValue());
        assertListSizeOne(list);
        list.addToHead(1);
        assertEquals(1, list.head.value.intValue());
        assertDoubleList(list);
        list.addToHead(2);
        assertEquals(2, list.head.value.intValue());
        assertDoubleList(list);
        System.out.println(list.toString());
        assertEquals("2 -> 1 -> 0", list.toString());
    }



    @Test
    public void addToTail() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
        assertListEmpty(list);
        list.addToTail(0);
        assertListSizeOne(list);
        assertEquals(0, list.head.value.intValue());

        list.addToTail(1);
        assertEquals(0, list.head.value.intValue());
        assertDoubleList(list);

        list.addToTail(2);
        assertEquals(0, list.head.value.intValue());
        assertDoubleList(list);

        System.out.println(list.toString());
        assertEquals("0 -> 1 -> 2", list.toString());
    }

    @Test
    public void removeHead() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
        list.addToTail(0);
        list.addToTail(1);
        list.addToTail(2);

        list.remove(0);
        assertEquals(1, list.head.value.intValue());
        assertDoubleList(list);

        System.out.println(list.toString());
        assertEquals("1 -> 2", list.toString());
    }

    @Test
    public void removeMiddle() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
        list.addToTail(0);
        list.addToTail(1);
        list.addToTail(2);

        list.remove(1);
        assertEquals(0, list.head.value.intValue());
        assertDoubleList(list);

        System.out.println(list.toString());
        assertEquals("0 -> 2", list.toString());
    }

    @Test
    public void removeTail() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
        list.addToTail(0);
        list.addToTail(1);
        list.addToTail(2);

        list.remove(2);
        assertEquals(0, list.head.value.intValue());
        assertDoubleList(list);

        System.out.println(list.toString());
        assertEquals("0 -> 1", list.toString());
    }

    @Test
    public void removeNotFound() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
        list.addToTail(0);
        list.addToTail(1);
        list.addToTail(2);

        list.remove(3);
        assertEquals(0, list.head.value.intValue());
        assertDoubleList(list);

        System.out.println(list.toString());
        assertEquals("0 -> 1 -> 2", list.toString());
    }

    @Test
    public void removeToEmptyList() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
        list.addToTail(0);
        list.addToTail(1);
        list.addToTail(2);

        list.remove(1);
        assertEquals(0, list.head.value.intValue());
        assertDoubleList(list);

        list.remove(2);
        assertEquals(0, list.head.value.intValue());
        assertDoubleList(list);

        list.remove(0);
        assertListEmpty(list);
        System.out.println(list.toString());
        assertEquals("(empty list)", list.toString());
    }
}
