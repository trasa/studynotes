package com.meancat.study.datastructures;

public class LinkedListNode<T> {
    private LinkedListNode<T> next;
    private T data;


    public LinkedListNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public LinkedListNode<T> getNext() {
        return next;
    }

    public LinkedListNode<T> addAtHead(T data) {
        LinkedListNode<T> newHead = new LinkedListNode<T>(data);
        newHead.next = this;
        return newHead;
    }

    public LinkedListNode<T> remove(T data) {
        if (this.data.equals(data)) {
            return next;
        }
        if (this.next != null) {
            this.next = this.next.remove(data);
        }
        return this;
    }

    public LinkedListNode<T> removeIter(T data) {

        LinkedListNode<T> prev = null;
        for(LinkedListNode<T> cur = this; cur != null; prev = cur, cur = cur.next) {
            if (cur.data.equals(data)) {
                // found it
                if (prev == null) {
                    // at head
                    return cur.next;
                } else {
                    prev.next = cur.next;
                }
                return this;
            }
        }
        // not found
        return this;
    }

    public void addAtTail(T data) {
        // first find the last node.
        LinkedListNode<T> last = findLastNoRecursion();
        // then add a new node to the last node.
        last.next = new LinkedListNode<T>(data);
    }

    private LinkedListNode<T> findLast() {
        if (next == null) {
            return this;
        }
        return next.findLast();
    }

    private LinkedListNode<T> findLastNoRecursion() {
        LinkedListNode<T> cur = this;
        while(true) {
            if (cur.next == null) {
                return cur;
            } else {
                cur = cur.next;
            }
        }
    }
}
