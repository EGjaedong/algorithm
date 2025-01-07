package org.example.algorithm.course.base.class02;

import java.util.PriorityQueue;

public class Code08_SortArrayDistanceLessK {

    // 已知一个几乎有序的数组，几乎有序是指，如果把数组排好顺序的话，每个元
    // 素移动的距离可以不超过k，并且k相对于数组来说比较小。请选择一个合适的
    // 排序算法针对这个数据进行排序。
    // 解题思路：利用小根堆，先将前k个数放入小根堆中，然后每次将堆顶元素弹出，然后将后面的元素放入堆中，继续弹出堆顶，依次类推。
    // 直到最后一个元素放入堆中，然后依次弹出堆顶元素，即可得到有序数组。
    // 根据题干，每个元素移动的距离不超过k，所以堆的大小为k+1。
    // 时间复杂度：O(N*logK)

    public static void sortArrayDistanceLessK(int[] arr, int k) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 构建一个小根堆，使用优先级队列，默认是小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        // 当前要加入堆中的数的位置
        int index = 0;
        // 把前k个数加入堆中
        for (; index < Math.min(arr.length, k); index++) {
            heap.add(arr[index]);
        }
        // 当前弹出堆顶元素的位置
        int i = 0;
        for (; index < arr.length; i++, index++) {
            // 弹出堆顶元素
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }
}
