package org.example.algorithm.base.class03;

public class Code01_CountSort {

    // 计数排序，桶排序思想的一种
    // 目的是把数通过计数的方式，放到有序的位置上
    // 开辟一个空间，这个空间的大小和原数组中最大值+1相同，并且初始值都为0，这个数组每一个索引的值，
    // 代表了原数组中每一个数字出现的次数计数。
    // 例如：[0, 1, 1, 3, 5, 6, 9]，在计数桶中的值为[1, 2, 0, 1, 0, 1, 1, 0, 0, 1]，
    // 即0出现过一次，1出现过2次，3，5，6，9分别出现过一次。
    // 遍历数组，将原数组中出现的数字放到计数桶中，然后遍历计数桶，将计数桶中的数字按照计数的次数放到原数组中，
    // 排序完成。
    // 计数排序强依赖于数据的范围，如果数据范围过大，计数桶会非常大，不适合使用计数排序。

    public static void countSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int max = Integer.MIN_VALUE;
        for (int j : arr) {
            max = Math.max(j, max);
        }
        int[] bucket = new int[max + 1];
        for (int j : arr) {
            bucket[j]++;
        }
        int i = 0;
        for (int j = 0; j < bucket.length; j++) {
            while (bucket[j]-- > 0) {
                arr[i++] = j;
            }
        }
    }
}
