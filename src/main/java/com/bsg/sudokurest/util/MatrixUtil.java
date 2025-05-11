package com.bsg.sudokurest.util;

import java.util.Arrays;

public final class MatrixUtil {

    public static int[][] deepCopy(int[][] board, int size) {
        int[][] copy = new int[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, size);
        }
        return copy;
    }

    public static int[] matrixToArray(int[][] matrix) {
        return Arrays.stream(matrix)
                .flatMapToInt(Arrays::stream)
                .toArray();
    }

    public static int[][] arrayToMatrix(int[] solution, int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < solution.length; i++) {
            matrix[i / size][i % size] = solution[i];
        }

        return matrix;
    }
}