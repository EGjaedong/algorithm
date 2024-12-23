package org.example.algorithm.class02;

public class Code04_NetherlandsFlagSimplifiedVersion {

    // 给定一个数组arr，和一个数num，请把小于等于num的数放在数 组的左边，大于num的
    // 数放在数组的右边。要求额外空间复杂度O(1)，时间复杂度O(N)
    // 解题思路：less指针指向小于等于num的区域，current指针指向当前遍历的位置
    // 比较当前数和目标数的大小，
    // 1、arr[current] <= num，交换arr[less+1]和arr[current]，less++，current++
    // 2、arr[current] > num，current右移
    // 问题的实质，就是less左侧是当前确定的小于等于区域，less右侧和current之间的区域是大于区域，current右侧区域是待定区域，
    // 当待定区域没有东西的时候，整个问题也就解决了。
    public static int[] partition(int[] arr, int num) {
        if (arr == null || arr.length < 2) {
            return arr;
        }

        int less = -1;
        int current = 0;
        while (current <= arr.length - 1) {
            if (arr[current] <= num) {
                swap(arr, ++less, current++);
            } else {
                current++;
            }
        }
        return arr;
    }

    private static void swap(int[] array, int i, int j) {
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
    }
}
