package org.example.algorithm.base.class02;

public class Code03_ReverseNumCount {

    // 逆序对问题：在一个数组中，左边的数如果比右边的数大，则这两个数构成一个逆序对，求一个数组中有多少个逆序对。
    // 同小和问题，小和问题是在归并过程中，找右侧序列有多少个数比左侧序列大；逆序对相反，是找左侧有多少个数比右侧的大
    public static int reverseNumCount(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    public static int merge(int[] arr, int l, int mid, int r) {
        int count = 0;
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
            // 通过下表计算的方式求出来左侧有多少个数比右侧的大
            count += arr[p1] > arr[p2] ? (mid - p1 + 1) : 0;
            // 如果两个数相等，要先拷贝左侧的，这样后续的数才可以通过下标计算得到结果
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return count;
    }
}
