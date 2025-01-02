package org.example.algorithm.class07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Code03_LessMoneySplitGold {

    // 一块金条切成两半，是需要花费和长度数值一样的铜板的。
    // 比如长度为20的金条，不管切成长度多大的两半，都要花费20个铜板。
    // 一群人想整分整块金条，怎么分最省铜板？
    // 例如,给定数组{10,20,30}，代表一共三个人，整块金条长度为60，金条要分成10,20,30三个部分。
    // 如果先把长度60的金条分成10和50，花费60；再把长度50的金条分成20和30，花费50；一共花费110铜板。
    // 但如果先把长度60的金条分成30和30，花费60；再把长度30金条分成10和20，花费30；一共花费90铜板。
    // 输入一个数组，返回分割的最小代价。

    // 暴力解决思路：
    // 1. 从数组中选择两个数，合并为一个数，这个数是两个数的和
    // 2. 从数组中删除这两个数，加入这个数
    // 3. 重复1，2步骤，直到数组中只剩一个数
    // 4. 选择最小的合并代价

    // 贪心解决思路：
    // 哈夫曼编码问题。
    // 1、把所有的数放入小根堆中
    // 2、每次从小根堆中弹出两个数，合并为一个数，再放入小根堆中
    // 3、重复2步骤，直到小根堆中只剩一个数
    public static int lessMoney(int[] arr) {
        if (arr == null) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i : arr) {
            heap.add(i);
        }
        int sum = 0;
        int cur;
        while (heap.size() > 1) {
            cur = heap.poll() + heap.poll();
            sum += cur;
            heap.add(cur);
        }
        return sum;
    }

    // 计算切割花费
    public static int getCost(List<Integer> list) {
        int cost = 0;
        while (list.size() > 1) {
            // 每次取最小的两个元素切割
            int min1 = Integer.MAX_VALUE;
            int min2 = Integer.MAX_VALUE;
            int index1 = -1, index2 = -1;
            // 找到最小的两个元素
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) < min1) {
                    min2 = min1;
                    index2 = index1;
                    min1 = list.get(i);
                    index1 = i;
                } else if (list.get(i) < min2) {
                    min2 = list.get(i);
                    index2 = i;
                }
            }
            // 合并两个最小元素，并计算成本
            int mergeCost = min1 + min2;
            cost += mergeCost;
            // 替换合并后的元素
            if (index1 > index2) { // 先删除较大的索引
                list.remove(index1);
                list.remove(index2);
            } else {
                list.remove(index2);
                list.remove(index1);
            }
            list.add(mergeCost);
        }
        return cost;
    }

    // 暴力递归枚举所有排列
    public static int bruteForce(int[] lengths) {
        List<List<Integer>> permutations = new ArrayList<>();
        generatePermutations(lengths, 0, permutations);

        int minCost = Integer.MAX_VALUE;
        for (List<Integer> perm : permutations) {
            // 对每个排列计算切割成本
            List<Integer> temp = new ArrayList<>(perm);
            minCost = Math.min(minCost, getCost(temp));
        }
        return minCost;
    }

    // 生成所有排列
    public static void generatePermutations(int[] arr, int index, List<List<Integer>> result) {
        if (index == arr.length) {
            List<Integer> permutation = new ArrayList<>();
            for (int num : arr) {
                permutation.add(num);
            }
            result.add(permutation);
            return;
        }

        for (int i = index; i < arr.length; i++) {
            swap(arr, i, index);
            generatePermutations(arr, index + 1, result);
            swap(arr, i, index); // 回溯
        }
    }

    // 交换数组中的两个元素
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) {
        int[] arr = { 6, 7, 8, 9 };
        System.out.println("use greedy strategy:");
        System.out.println(lessMoney(arr));
        System.out.println("====================");
        System.out.println("use brute force strategy:");
        System.out.println(bruteForce(arr));
    }
}
