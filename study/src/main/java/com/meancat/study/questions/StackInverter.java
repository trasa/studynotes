package com.meancat.study.questions;

import java.util.Stack;

/**
 * User: trasa
 * Created: 5/19/13 3:04 PM
 */
public class StackInverter {


    /**
     * [a, b, c] --> (a, [b,c]) --> (a, (b, [c])) --> (a, (b, (c, []))) --> (return)
     *                                                        [c]            (append c, [])
     *                                    [c, b]            (append b, [c])
     *                [c, b, a]
     * @param stack to be inverted
     * @param <E> stuff in stack
     */
    public static <E> void invert(Stack<E> stack) {
        System.out.println("invert");
        if (stack.isEmpty()) {
            return;
        }
        E o = stack.pop();
        invert(stack); // push everything on to the call stack
        append(stack, o); // pop everything back onto the stack
    }

    private static <E> void append(Stack<E> stack, E o) {
        System.out.println("append " + o);
        if (!stack.isEmpty()) {
            // if there is stuff, pop it all off to the call stack
            // so that 'o' gets to the bottom to be appended
            E obj = stack.pop();
            append(stack, o);
            stack.push(obj);
        } else {
            System.out.println("(end)");
            stack.push(o);
        }
    }
}
