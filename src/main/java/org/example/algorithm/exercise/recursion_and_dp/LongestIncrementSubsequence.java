package org.example.algorithm.exercise.recursion_and_dp;

import java.util.Arrays;

public class LongestIncrementSubsequence {

    // 找一个数组的最长递增子序列

    // 方法一：暴力递归
    public static int lengthLIS1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return process(nums, 0, -1);
    }

    // 递归函数：求以index下标为结尾的最长递增子序列的长度
    private static int process(int[] nums, int index, int prevIndex) {
        // base case，递归结束条件，返回0
        // 必须让index越界，才能结束递归，否则会遗漏最后一个元素的状态计算
        if (index == nums.length) {
            return 0;
        }
        // 当前最长递增子序列的长度，不包含当前元素
        // 为什么要跳过，因为最长子序列不一定包含当前元素，对于当前元素n，必须尝试两种情况，包含和不包含
        // 1、不包含当前元素，直接跳过，继续求n+1下标的最长递增子序列
        int res = process(nums, index + 1, prevIndex);
        // 2、包含当前元素，如果当前元素大于前一个元素，可以选择当前元素
        // 如果当前元素大于前一个元素，可以选择当前元素
        // 注意边界条件：prevIndex == -1，表示当前元素是第一个元素，直接选择当前元素
        if (prevIndex == -1 || nums[index] > nums[prevIndex]) {
            // 当前最长递增子序列的长度，包含当前元素
            res = Math.max(res, 1 + process(nums, index + 1, index));
        }
        return res;
    }

    // 方法二：动态规划，跳过记忆化搜索傻缓存的方式
    public static int lengthLIS2(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        // 记录当前位置的最长递增子序列的长度
        int[] dp = new int[n];
        // 初始化dp表，每个位置的自己就是一个递增子序列
        Arrays.fill(dp, 1);

        // 最长递增子序列的长度
        int max = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // 如果当前元素大于前一个元素，则可以接在以j为结尾的最长递增子序列后面
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }


    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("using violent recursion solve:");
        System.out.println(lengthLIS1(nums));
        System.out.println("using dynamic programing solve:");
        System.out.println(lengthLIS2(nums));
    }
}
