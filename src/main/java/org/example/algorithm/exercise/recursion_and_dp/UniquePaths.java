package org.example.algorithm.exercise.recursion_and_dp;

public class UniquePaths {

    // 力扣62题，不同路径
    // 描述：一个机器人位于一个 m x n 网格的左上角（起始点在下图中标记为“Start” ）。
    // 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
    // 问总共有多少条不同的路径？

    // 先从暴力递归开始尝试，时间复杂度O(2^(m+n))，每一步有两种选择，m+n步
    public static int uniquePaths1(int m, int n) {
        return process1(m, n, 1, 1);
    }

    private static int process1(int height, int width, int row, int column) {
        if (row == height || column == width) {
            return 1;
        }
        return process1(height, width, row + 1, column) + process1(height, width, row, column + 1);
    }

    // 改动态规划，熟练了以后直接跳过记忆化搜索
    public static int uniquePaths2(int m, int n) {
        // 递归中有两个可变参数，所以dp数组是一个二维数组
        int[][] dp = new int[m][n];
        // 根据递归，画出边界条件
        for (int i = 0; i < n; i++) {
            dp[m - 1][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            dp[i][n - 1] = 1;
        }
        // 从下往上，从右往左填表，也就是递归逆向填表
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        System.out.println("using violent recursion to solve:");
        System.out.println(uniquePaths1(7, 3));
        System.out.println("using dynamic programming to solve:");
        System.out.println(uniquePaths2(7, 3));
    }
}
