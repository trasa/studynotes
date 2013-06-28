package com.meancat.kata.lists;

import com.google.common.base.Joiner;
import com.meancat.kata.Node;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class TailPointerLinkedList<T> {

    protected Node<T> head;
    protected Node<T> tail;

    public void addToHead(T value) {
        Node<T> newNode = new Node<T>(value);
        if (head == null) {
            // empty list
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    public void addToTail(T value) {
        Node<T> newNode = new Node<T>(value);
        if (tail == null) {
            // empty list
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void remove(T value) {
        if (head == null) {
            return;
        }
        if (head.value.equals(value)) {
            if (tail == head) {
                // we will have an empty list because head.next is null
                tail = head.next;
            }
            head = head.next;
            return;
        }
        Node<T> cur = head.next;
        Node<T> prev = head;
        while(cur != null) {
            if (cur.value.equals(value)) {
                prev.next = cur.next;
                if (cur == tail) {
                    tail = prev;
                }
                return;
            }
            prev = cur;
            cur = cur.next;
        }
    }


    @Override
    public String toString() {
        if (head == null) {
            return "(empty list)";
        }
        List<T> values = newArrayList();
        Node<T> n = head;
        while (n != null) {
            values.add(n.value);
            n = n.next;
        }
        return Joiner.on(" -> ").join(values);
    }
}
