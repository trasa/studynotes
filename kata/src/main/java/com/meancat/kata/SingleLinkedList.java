package com.meancat.kata;

import com.google.common.base.Joiner;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class SingleLinkedList<T> {

    private Node<T> head;

    public void addToHead(T value) {
        Node<T> newNode = new Node<T>(value);
        newNode.next = head;
        head = newNode;
    }

    public void addToTail(T value) {
        Node<T> newNode = new Node<T>(value);

        if (head == null) {
            head = newNode;
        } else {
            Node<T> cur = head;
            while(cur.next != null) {
                cur = cur.next;
            }
            cur.next = newNode;
        }
    }

    public void remove(T value) {
        Node<T> prev;
        Node<T> cur;

        if (head == null) {
            return;
        }
        if (head.value.equals(value)) {
            head = head.next;
            return;
        }
        prev = head;
        cur = head.next;
        while (cur != null) {
            if (cur.value.equals(value)) {
                prev.next = cur.next;
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
