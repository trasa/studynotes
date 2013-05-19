package com.meancat.study.questions;

/**
 * User: trasa
 * Created: 5/19/13 2:55 PM
 */
public class Reverser {

    public static void reverseWordsInPlace(char[] phrase) {
        // swap the entire string
        swapPhrase(phrase, 0, phrase.length - 1);
        // find the individual words and swap them too.
        int wordStart = 0;
        for (int i=0; i < phrase.length; i++) {
            if (phrase[i] == ' ') {
                swapPhrase(phrase, wordStart, i - 1);
                wordStart = i + 1;
            }
        }
        // don't forget the last word
        swapPhrase(phrase, wordStart, phrase.length - 1);
    }

    private static void swapPhrase(char[] phrase, int startIndex, int endIndex) {
        int mid = startIndex + 1 + (endIndex - startIndex) / 2;
        for (int i=startIndex; i < mid; i++) {
            swap(phrase, i, startIndex + endIndex - i );
        }
        System.out.println("swapPhrase: " + new String(phrase));
    }

    private static void swap(char[] phrase, int x, int y) {
        /* overly simplistic:
        char c = phrase[x];
        phrase[x] = phrase[y];
        phrase[y] = c;
        */

        // completely in place in swap
        if (x == y) {
            return; // otherwise we zero that spot out
        }
        phrase[x] = (char)((int)phrase[x] ^ (int)phrase[y]);
        phrase[y] = (char)((int)phrase[x] ^ (int)phrase[y]);
        phrase[x] = (char)((int)phrase[x] ^ (int)phrase[y]);
    }
}
