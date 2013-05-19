package com.meancat.study.util;

import java.util.List;

/**
 * User: trasa
 * Created: 5/19/13 2:34 PM
 */
public class ListPrinter {

    public static void print(List<?> values) {
        System.out.print("[");
        for(Object t : values) {
            System.out.print(t + " ");
        }
        System.out.println("]");
    }
}
