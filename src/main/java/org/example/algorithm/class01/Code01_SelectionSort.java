package org.example.algorithm.class01;

public class Code01_SelectionSort {

    public static void selectionSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        for (int i = 0; i < array.length - 1; i++) { // 从第i个数开始，直到倒数第二个数，往后找到最小的数，找到之后，把最小的放在i位置上
            int minIndex = i; // 记录最小数的下标
            for (int j = i + 1; j < array.length; j++) { // 从i+1开始，往后找所有数最小的，记录下标
                minIndex = array[minIndex] > array[j] ? j : minIndex;
            }
            swap(array, i, minIndex); // 把最小的数放在i位置上
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = {7, 2, 8, 4, 6, 3, 5, 1};
        selectionSort(array);
        for (int i : array) {
            System.out.print(i);
            System.out.print(" ");
        }
    }
}
