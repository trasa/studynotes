package com.meancat.study.questions;

import org.junit.Test;

import java.util.Stack;

import static junit.framework.Assert.assertEquals;

/**
 * User: trasa
 * Created: 5/19/13 3:07 PM
 */
public class StackInverterTest {

    private void pushElements(Stack<Integer> stack, int elementCount) {
        for (int i = 0; i < elementCount; i++) {
            stack.push(i);
        }
    }

    private void assertStackOrder(Stack<Integer> stack, int elementCount) {
        for (int i = 0; i < elementCount; i++) {
            int e = stack.pop();
            assertEquals(i, e);
        }
        assert (stack.isEmpty());
    }

    private void runTest(int elementCount) {
        Stack<Integer> stack = new Stack<Integer>();
        pushElements(stack, elementCount);
        System.out.println(stack);
        StackInverter.invert(stack);
        System.out.println(stack);
        assertStackOrder(stack, elementCount);
    }

    @Test
    public void oneElement() {
        runTest(1);
    }

    @Test
    public void twoElements() {
        runTest(2);
    }

    @Test
    public void threeElements() {
        runTest(3);
    }

    @Test
    public void fourElements() {
        runTest(4);
    }

    @Test
    public void fiveElements() {
        runTest(5);
    }
}
