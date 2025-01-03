package org.example.algorithm.base.class02;

public class Code05_NetherlandsFlag {

    // 荷兰国旗问题：
    // 给定一个数组arr，和一个数num，请把小于num的数放在数组的左边，等于num的数放在数组的中间，大于num的数放在数组的右边。
    // 要求额外空间复杂度O(1)，时间复杂度O(N)
    // 解题思路：less指针指向小于num的区域，more指针指向大于num的区域，current指针指向当前遍历的位置
    // 比较当前数和目标数的大小：
    // 1、小于，则当前数与less+1位置交换，less++，current++
    // 2、大于，则当前数与more-1位置交换，more--，current不变。（要重新处理换过来的数，所以current不能动）
    // 3、等于，current++
    // 当current和more相遇时，整个问题解决
    public static int[] partition(int[] arr, int l, int r, int num) {
        if (arr == null || arr.length < 2) {
            return arr;
        }

        // l本质就是current指针
        int less = l - 1;
        int more = r + 1;
        while (l < more) {
            if (arr[l] < num) {
                swap(arr, ++less, l++);
            } else if (arr[l] > num) {
                swap(arr, --more, l);
            } else {
                l++;
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
