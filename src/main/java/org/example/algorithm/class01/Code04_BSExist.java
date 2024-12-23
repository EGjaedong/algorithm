package org.example.algorithm.class01;

public class Code04_BSExist {

    public static boolean exist(int[] sortedArr, int target) {
        if (sortedArr == null || sortedArr.length == 0) {
            return false;
        }
        int L = 0, R = sortedArr.length - 1;
        while (L < R) {
            int mid = L + ((R - L) >> 1);
            if (sortedArr[mid] == target) {
                return true;
            } else if (sortedArr[mid] < target) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return sortedArr[L] == target;
    }
}
