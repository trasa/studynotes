package com.meancat.study.questions;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * User: trasa
 * Created: 5/19/13 3:03 PM
 */
public class ReverserTest {
    @Test
    public void reverseString() {
        char[] chars = "hello world".toCharArray();
        Reverser.reverseWordsInPlace(chars);
        assertEquals("world hello", new String(chars));
    }

    @Test
    public void reverseStringWithPivot() {
        char[] chars = "hello worl".toCharArray();
        Reverser.reverseWordsInPlace(chars);
        assertEquals("worl hello", new String(chars));
    }

    @Test
    public void trivialReverse() {
        char[] chars = new char[]{'c'};
        Reverser.reverseWordsInPlace(chars);
        assertEquals("c", new String(chars));
    }

    @Test
    public void trivialReverseTwoChar() {
        char[] chars = new char[]{'c', 'e'};
        Reverser.reverseWordsInPlace(chars);
        assertEquals("ce", new String(chars));
    }

    @Test
    public void trivialReverseThreeChar() {
        char[] chars = "cel".toCharArray();
        Reverser.reverseWordsInPlace(chars);
        assertEquals("cel", new String(chars));
    }

    @Test
    public void reverseBigPhrase() {
        char[] chars = "The Quick Brown Fox Jumped Over The Lazy Dog".toCharArray();
        Reverser.reverseWordsInPlace(chars);
        assertEquals("Dog Lazy The Over Jumped Fox Brown Quick The", new String(chars));
    }
}
