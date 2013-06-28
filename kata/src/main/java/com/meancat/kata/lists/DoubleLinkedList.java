package com.meancat.kata.lists;

import com.google.common.base.Joiner;
import com.meancat.kata.DoubleNode;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class DoubleLinkedList<T> {

    protected DoubleNode<T> head;

    public void addToHead(T value) {
        DoubleNode<T> newNode = new DoubleNode<T>(value);
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    public void addToTail(T value) {
        DoubleNode<T> newNode = new DoubleNode<T>(value);

        if (head == null) {
            head = newNode;
        } else {
            DoubleNode<T> cur = head;
            while(cur.next != null) {
                cur = cur.next;
            }
            cur.next = newNode;
            newNode.prev = cur;
        }
    }

    public void remove(T value) {
        if (head == null) {
            return;
        }
        if (head.value.equals(value)) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
            return;
        }
        DoubleNode<T> cur = head;
        while(cur != null) {
            if (cur.value.equals(value)) {
                DoubleNode<T> next = cur.next;
                cur.prev.next = next;
                if (next != null) {
                    next.prev = cur.prev;
                }
                return;
            }
            cur = cur.next;
        }
    }


    @Override
    public String toString() {
        if (head == null) {
            return "(empty list)";
        }
        List<T> values = newArrayList();
        DoubleNode<T> n = head;
        while (n != null) {
            values.add(n.value);
            n = n.next;
        }
        return Joiner.on(" -> ").join(values);
    }
}
