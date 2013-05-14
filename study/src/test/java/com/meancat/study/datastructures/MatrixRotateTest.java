package com.meancat.study.datastructures;

import org.junit.Test;

public class MatrixRotateTest {

    @Test
    public void matrix4x4() {
        int[][] matrix = new int[][]{
                {0, 1, 2, 3},
                {4, 5, 6, 7},
                {8, 9, 10, 11},
                {12, 13, 14, 15}
        };
        System.out.println("before:");
        printMatrix(matrix);

        MatrixRotate.rotate(matrix);
        System.out.println("\nafter:");
        printMatrix(matrix);
    }

    private void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int i : row) {
                System.out.print(String.format("%3d", i));
            }
            System.out.println();
        }
    }

}
