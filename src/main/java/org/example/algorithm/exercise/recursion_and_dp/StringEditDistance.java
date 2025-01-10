package org.example.algorithm.exercise.recursion_and_dp;

public class StringEditDistance {

    // 字符串编辑距离问题
    // 给定两个字符串str1和str2，编辑距离是两个字符串之间从一个字符串转换成另一个字符串所需的最少操作次数。
    // 返回将str1编辑成str2的最小代价。
    // 只允许三种编辑操作：插入，删除，替换

    // 暴力递归求解
    public static int editDistanceRecursion(String str1, String str2) {
        if (str1 == null || str1.isEmpty()) {
            return str2 == null ? 0 : str2.length();
        }
        if (str2 == null || str2.isEmpty()) {
            return str1.length();
        }
        return process1(str1, str2, str1.length() - 1, str2.length() - 1);
    }

    private static int process1(String str1, String str2, int i, int j) {
        // 边界条件
        boolean charEquals = str1.charAt(i) == str2.charAt(j);
        if (i == 0 && j == 0) {
            return charEquals ? 0 : 1;
        }
        if (i == 0) {
            return charEquals ? j : j + 1;
        }
        if (j == 0) {
            return charEquals ? i : i + 1;
        }
        // 普遍情况
        if (charEquals) {
            return process1(str1, str2, i - 1, j - 1);
        } else {
            // 1是当前操作，当前操作可以是三种情况，分别是删除，插入和替换，不同的情况会对下一阶段的子过程的索引产生影响
            // 删除：str1删除i位置的字符，str1的索引i-1，str2的索引j不变
            // 插入：str1在i位置插入str2的j位置的字符，str1的索引i不变，str2的索引j-1
            // 替换：str1的i位置替换成str2的j位置的字符，str1的索引i-1，str2的索引j-1
            return 1 + Math.min(
                    process1(str1, str2, i - 1, j),
                    Math.min(process1(str1, str2, i, j - 1),
                            process1(str1, str2, i - 1, j - 1)));
        }
    }

    // 动态规划求解
    public static int editDistanceDP(String str1, String str2) {
        if (str1 == null || str1.isEmpty()) {
            return str2 == null ? 0 : str2.length();
        }
        if (str2 == null || str2.isEmpty()) {
            return str1.length();
        }
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m][n];
        // 边界条件
        dp[0][0] = str1.charAt(0) == str2.charAt(0) ? 0 : 1;
        for (int i = 1; i < m; i++) {
            dp[i][0] = str1.charAt(i) == str2.charAt(0) ? i : dp[i - 1][0] + 1; // 累计删除操作
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = str1.charAt(0) == str2.charAt(j) ? j : dp[0][j - 1] + 1; // 累计插入操作
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(
                            dp[i - 1][j],
                            Math.min(dp[i][j - 1], dp[i - 1][j - 1])
                    );
                }
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        String str1 = "abcdefg";
        String str2 = "abcdef";
        System.out.println(editDistanceRecursion(str1, str2));
        System.out.println(editDistanceDP(str1, str2));
    }
}
