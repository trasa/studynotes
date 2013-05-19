package com.meancat.study.questions;

import org.junit.Test;

import java.util.Random;

import static junit.framework.Assert.assertEquals;

/**
 * User: trasa
 * Created: 5/19/13 1:56 PM
 */
public class AtoITest {

    @Test
    public void maxInt() {
        assertEquals(Integer.MAX_VALUE, AtoI.convert(String.valueOf(Integer.MAX_VALUE)));
    }

    @Test
    public void minInt() {
        assertEquals(Integer.MIN_VALUE, AtoI.convert(String.valueOf(Integer.MIN_VALUE)));
    }
    @Test
    public void singleDigit() {
        assertEquals(1, AtoI.convert("1"));
    }

    @Test
    public void singleDigitNegative() {
        assertEquals(-5, AtoI.convert("-5"));
    }


    @Test
    public void twoDigit() {
        assertEquals(26, AtoI.convert("26"));
    }

    @Test
    public void twoDigitNegative() {
        assertEquals(-55, AtoI.convert("-55"));
    }

    @Test
    public void threeDigit() {
        assertEquals(205, AtoI.convert("205"));
    }

    @Test
    public void threeDigitNegative() {
        assertEquals(-555, AtoI.convert("-555"));
    }

    @Test
    public void randomDigits() {
        Random r = new Random();
        for(int i = 0 ; i < 50; i++){
            Integer value = r.nextInt();
            assertEquals((int)value, AtoI.convert(value.toString()));
        }
    }
}
