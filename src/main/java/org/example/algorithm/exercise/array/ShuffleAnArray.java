package org.example.algorithm.exercise.array;

import java.util.ArrayList;
import java.util.List;

public class ShuffleAnArray {
    // 力扣384，打乱数组
    // 给定一个整数数组nums，设计算法来打乱一个没有重复元素的数组。打乱后，数组的所有排列应该是 等可能 的。

    private int[] originalArr;

    public ShuffleAnArray(int[] nums) {
        originalArr = nums;
    }

    public int[] reset() {
        return originalArr;
    }

    // 另外一种，从list里随机取一个值，放到数组里，然后把这个下标删掉
    public int[] shuffle2() {
        if (originalArr == null || originalArr.length == 0) {
            return new int[0];
        }

        List<Integer> index = new ArrayList<>();
        for (int i = 0; i < originalArr.length; i++) {
            index.add(i);
        }
        int[] arr = new int[originalArr.length];
        for (int i = 0; i < originalArr.length; i++) {
            int randomIndex = (int) (Math.random() * index.size());
            arr[i] = originalArr[index.get(randomIndex)];
            index.remove(randomIndex);
        }
        return arr;
    }

    // 暴力解法，列出所有的排列组合，然后随机返回一个
    public int[] shuffle() {
        if (originalArr == null || originalArr.length == 0) {
            return new int[0];
        }

        List<int[]> permutations = new ArrayList<>();
        for (int i = 0; i < originalArr.length; i++) {
            process(originalArr, i, permutations);
        }
        return permutations.get((int) (Math.random() * permutations.size()));
    }

    private void process(int[] originalArr, int index, List<int[]> permutations) {
        // base case
        if (index == originalArr.length) {
            int[] copy = originalArr.clone();
            permutations.add(copy);
            return;
        }

        // 循环递归，将index位置的元素与后边的元素交换
        for (int i = index; i < originalArr.length; i++) {
            swap(originalArr, index, i);
            process(originalArr, index + 1, permutations);
            // 复原
            swap(originalArr, index, i);
        }
    }

    private void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        ShuffleAnArray shuffleAnArray = new ShuffleAnArray(nums);
        shuffleAnArray.printArray(shuffleAnArray.shuffle());
        shuffleAnArray.printArray(shuffleAnArray.reset());
        System.out.println("==================");
        shuffleAnArray.printArray(shuffleAnArray.shuffle());
        shuffleAnArray.printArray(shuffleAnArray.reset());
    }

    private void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
