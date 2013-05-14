package com.meancat.study.datastructures;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LinkedListNodeTest {

    @Test
    public void removeNodeIter() {
        LinkedListNode<String> head = new LinkedListNode<String>("foo");
        head.addAtTail("bar");
        head.addAtTail("baz");

        head = head.removeIter("bar");
        head = head.removeIter("asldkfjasl");
        assertEquals("foo", head.getData());
        assertEquals("baz", head.getNext().getData());
        assertNull(head.getNext().getNext());

        head = head.removeIter("foo");
        head = head.removeIter("asldkfjasl");
        assertEquals("baz", head.getData());
        assertNull(head.getNext());

        head = head.removeIter("baz");
        assertNull(head);

    }

    @Test
    public void removeNode() {
        LinkedListNode<String> head = new LinkedListNode<String>("foo");
        head.addAtTail("bar");
        head.addAtTail("baz");

        head = head.remove("bar");
        head = head.remove("asldkfjasl");
        assertEquals("foo", head.getData());
        assertEquals("baz", head.getNext().getData());
        assertNull(head.getNext().getNext());

        head = head.remove("foo");
        head = head.remove("asldkfjasl");
        assertEquals("baz", head.getData());
        assertNull(head.getNext());

        head = head.remove("baz");
        assertNull(head);

    }
    @Test
    public void addNodeToHead() {
        LinkedListNode<String> head = new LinkedListNode<String>("foo");
        head = head.addAtHead("bar");
        head = head.addAtHead("baz");
        assertEquals("baz", head.getData());
        assertEquals("bar", head.getNext().getData());
        assertEquals("foo", head.getNext().getNext().getData());
    }

    @Test
    public void addNodeToTail() {
        LinkedListNode<String> head = new LinkedListNode<String>("foo");
        head.addAtTail("bar");
        head.addAtTail("baz");
        assertEquals("foo", head.getData());
        assertEquals("bar", head.getNext().getData());
        assertEquals("baz", head.getNext().getNext().getData());
    }
}
