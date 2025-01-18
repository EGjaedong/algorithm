package org.example.algorithm.exercise.sort;

import java.util.PriorityQueue;

public class SmallestKLcci {
    // 面试题 17.14. 最小K个数
    // 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
    // 示例：arr = [1,3,5,7,2,4,6,8], k = 4
    // 输出：[1,2,3,4]

    // 构建小根堆，持续弹出堆顶元素，直到堆的大小为k

    public int[] smallestK(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k == 0) {
            return new int[0];
        }
        if (arr.length <= k) {
            return arr;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num : arr) {
            heap.offer(num);
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = heap.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        SmallestKLcci smallestKLcci = new SmallestKLcci();
        int[] arr = new int[]{1, 3, 5, 7, 2, 4, 6, 8};
        int k = 4;
        int[] result = smallestKLcci.smallestK(arr, k);
        for (int num : result) {
            System.out.print(num + " ");
        }
    }

}
