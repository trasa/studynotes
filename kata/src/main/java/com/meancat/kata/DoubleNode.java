package com.meancat.kata;

public class DoubleNode<T> {

    public T value;
    public DoubleNode<T> next;
    public DoubleNode<T> prev;

    public DoubleNode(T value) {
        this.value = value;
    }
}
