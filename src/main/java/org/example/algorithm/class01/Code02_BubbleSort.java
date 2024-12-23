package org.example.algorithm.class01;

public class Code02_BubbleSort {

    public static void bubbleSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        for (int i = array.length - 1; i > 0; i--) { //
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    private static void swap(int[] array, int i, int j) {
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
    }

    public static void main(String[] args) {
        int[] array = {7, 2, 8, 4, 6, 3, 5, 1};
        bubbleSort(array);
        for (int i : array) {
            System.out.print(i);
            System.out.print(" ");
        }
    }
}
