package org.example.algorithm.exercise.recursion_and_dp;

public class HouseRobber {
    // 力扣（LeetCode）198. 打家劫舍
    // https://leetcode-cn.com/problems/house-robber/

    // 暴力递归解法
    public static int rob1(int[] nums) {
        return process(nums, 0);
    }

    private static int process(int[] nums, int i) {
        // base case
        if (i >= nums.length) {
            return 0;
        }
        // 对于第i家有两种选择，选择偷与不偷，
        // 如果偷，那么下一次只能偷i+2及之后的房子，
        // 如果不偷，那么下一次可以偷i+1及之后的房子
        return Math.max(
                nums[i] + process(nums, i + 2),
                process(nums, i + 1));
    }

    // 动态规划
    private static int rob2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // dp[i]表示偷到第i家时的最大金额
        int[] dp = new int[nums.length + 2];
        // 边界条件
        dp[nums.length] = 0;
        dp[nums.length + 1] = 0;
        // 从后往前遍历，填表
        for (int i = nums.length - 1; i >= 0; i--) {
            dp[i] = Math.max(nums[i] + dp[i + 2], dp[i + 1]);
        }
        return dp[0];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 7, 9, 3, 1};
        System.out.println("using violent recursion: ");
        System.out.println(rob1(arr));
        System.out.println("using dynamic programming: ");
        System.out.println(rob2(arr));
    }
}
