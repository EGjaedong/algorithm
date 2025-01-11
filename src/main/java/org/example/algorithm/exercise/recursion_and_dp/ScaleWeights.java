package org.example.algorithm.exercise.recursion_and_dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ScaleWeights {
    // 给定n中砝码，第一行输入砝码的重量，第二行输入对应重量砝码的个数，求一共能称出多少种重量
    // 例如：
    // 例如：weight = [1, 2]，nums = [2, 1]，表示两个重量为1的砝码，1个重量为2的砝码
    // 输出：5
    // 有两种解法，一种是把数组拍平，然后全排列

    // 拍平全排列就是，求所有子序列的和，子序列中包含0个元素
    public static void scaleWeights1(int[] weights, int[] num) {
        List<Integer> list = new ArrayList<>();
        // 拍平
        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num[i]; j++) {
                list.add(weights[i]);
            }
        }
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        Set<Integer> res = new HashSet<>();
        // 先求出所有子序列，然后求和，放入set中
        process(arr, 0, 0, res);
        System.out.println(res.size());
    }

    private static void process(int[] arr, int i, int alreadyWeight, Set<Integer> res) {
        // base case
        if (i == arr.length) {
            res.add(alreadyWeight);
            return;
        }
        // 普遍条件，要当前元素，不要当前元素
        // 1、要当前元素
        alreadyWeight += arr[i];
        process(arr, i + 1, alreadyWeight, res);
        // 2、不要当前元素
        alreadyWeight -= arr[i];
        process(arr, i + 1, alreadyWeight, res);
    }

    // 另一种是动态规划，dp[i]表示当前重量索引的下标i，能够称出的重量
    // set本身就是dp数组
    public static void scaleWeight2(int[] weights, int[] nums) {
        Set<Integer> res = new HashSet<>();
        res.add(0);
        for (int i = 0; i < weights.length; i++) {
            List<Integer> list = new ArrayList<>(res);
            for (int j = 1; j <= nums[i]; j++) {
                for (int k = 0; k < list.size(); k++) {
                    res.add(list.get(k) + weights[i] * j);
                }
            }
        }
        System.out.println(res.size());
    }
}
