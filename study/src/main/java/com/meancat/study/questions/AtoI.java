package com.meancat.study.questions;

/**
 * Convert a String (representing an integer) to a number.
 *
 * User: trasa
 * Date: 5/19/13 12:29 PM
 */
public class AtoI {
    /**
     * Convert a String to a number.
     * @param s the string
     * @return the number
     */
    public static int convert(String s) {
        int result = 0;
        char[] chars = s.toCharArray();
        Boolean isNegative = chars[0] == '-';
        int startIdx = isNegative? 1 : 0;
        for (int i=startIdx; i < chars.length; i++) {
            result = (result * 10) + Character.digit(chars[i], 10);
        }
        if (isNegative) {
            result = 0 - result;
        }
        return result;
    }
}
