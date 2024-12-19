package org.example.algorithm.sort;

public class Code06_BSAwesome {
    // 局部最小值问题：
    // 已知有一个数组，任意两个相邻的数都不想等，找到这个数组中的一个局部最小值
    // 求解思路：
    // 先判断两头的数分别是不是局部最小值，如果是，则返回；如果不是，则说明两头分别是向数组内下降趋势的曲线，则内部一定存在局部最小值
    // 持续二分查看曲线方向即可，先查看中间位置是否为局部最小值，如果是则返回，如果不是，则根据曲线方向，根据上面的规则判断向哪个方向继续二分。
    public static int localMinimum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        int l = 0;
        if (arr[l] < arr[l + 1]) {
            return arr[l];
        }
        int r = arr.length - 1;
        if (arr[r] < arr[r - 1]) {
            return arr[r];
        }
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                return arr[mid];
            } else if (arr[mid] > arr[mid - 1]) {
                r = mid - 1;
            } else {
                l = mid - 1;
            }
        }
        return -1;
    }
}
