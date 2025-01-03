package org.example.algorithm.base.class07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Code06_MedianQuick {
    // 给一个数据流，如何随时取得之前所有收到的数据的中位数
    // 解体思路：准备两个堆，一个大根堆，一个小根堆，保证中位数左边的数据在大根堆，右边的数据在小根堆
    // 1.数据先进大根堆
    // 2.再来一个数据，看这个数据和大根堆堆顶的数据谁小，小的进大根堆，大的进小根堆
    // 3.如果大根堆的size和小根堆的size差值大于1，就要调整，把多的堆顶弹出，进入另一个堆

    public static class MedianHolder {
        public PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new MaxHeapComparator());
        public PriorityQueue<Integer> minHeap = new PriorityQueue<>(new MinHeapComparator());

        public void addNumber(Integer number) {
            if (maxHeap.isEmpty() || number <= maxHeap.peek()) {
                maxHeap.add(number);
            } else {
                minHeap.add(number);
            }
            modifyTwoHeapsSize();
        }

        private void modifyTwoHeapsSize() {
            if (maxHeap.size() == minHeap.size() + 2) {
                minHeap.add(maxHeap.poll());
            }
            if (minHeap.size() == maxHeap.size() + 2) {
                maxHeap.add(minHeap.poll());
            }
        }

        public int getMedian() {
            int maxHeapSize = maxHeap.size();
            int minHeapSize = minHeap.size();
            if (maxHeapSize == 0 && minHeapSize == 0) {
                return 0;
            }
            if (maxHeapSize == minHeapSize) {
                return (maxHeap.peek() + minHeap.peek()) / 2;
            }
            if (maxHeapSize > minHeapSize) {
                return maxHeap.peek();
            }
            return minHeap.peek();
        }
    }

    public static class MaxHeapComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 > o2 ? 1 : -1;
        }
    }

    public static class MinHeapComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 < o2 ? 1 : -1;
        }
    }

    // for test
    public static int[] getRandomArray(int maxLen, int maxValue) {
        int[] res = new int[(int) (Math.random() * maxLen) + 1];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue);
        }
        return res;
    }

    // for test, this method is ineffective but absolutely right
    public static int getMedianOfArray(int[] arr) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(newArr);
        int mid = (newArr.length - 1) / 2;
        if ((newArr.length & 1) == 0) {
            return (newArr[mid] + newArr[mid + 1]) / 2;
        } else {
            return newArr[mid];
        }
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        boolean err = false;
        int testTimes = 200000;
        for (int i = 0; i != testTimes; i++) {
            int len = 30;
            int maxValue = 1000;
            int[] arr = getRandomArray(len, maxValue);
            MedianHolder medianHold = new MedianHolder();
            for (int j = 0; j != arr.length; j++) {
                medianHold.addNumber(arr[j]);
            }
            if (medianHold.getMedian() != getMedianOfArray(arr)) {
                err = true;
                printArray(arr);
                break;
            }
        }
        System.out.println(err ? "Oops..what a fuck!" : "today is a beautiful day^_^");

    }
}
