package org.example.algorithm.exercise.recursion_and_dp;

public class LongestPalindromeSubstring {

    // 求一个字符串的最长回文子串，力扣第5题

    // 1、先从暴力递归开始尝试，时间复杂度O(n^3)，外层两个循环遍历所有子串O(n^2)，内层循环判断子串是否是回文串O(n)
    // 暴力递归解法
    public static String longestPalindrome1(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        // 用来记录最长回文子串的左右边界
        int left = 0;
        int right = 0;
        // 双层循环，暴力遍历左右子串
        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length() - 1; j > i; j--) {
                // 判断i到j的子串是否为回文子串，如果是，判断当前子串是否比之前的最长回文子串更长
                if (isPalindrome1(s, i, j) &&
                        ((j - i) > (right - left))) {
                    left = i;
                    right = j;
                }
            }
        }
        return s.substring(left, right + 1); // 注意substring的右边界是开区间
    }

    // 判断当前子串是否为回文子串
    public static boolean isPalindrome1(String s, int left, int right) {
        // 只有一个字符，肯定是回文子串
        if (left == right) {
            return true;
        }
        // 左右两个字符不相等，肯定不是回文子串
        if (s.charAt(left) != s.charAt(right)) {
            return false;
        }
        // 下面是左右两个字符相等
        // 如果左右两个字符相邻，肯定是回文子串
        if (left + 1 == right) {
            return true;
        }
        // 递归判断左右两个字符之间的子串是否为回文子串
        return isPalindrome1(s, left + 1, right - 1);
    }

    // 2、记忆化搜索解决方案
    // 递归解法的问题在于，有很多重复计算，比如判断i到j的子串是否为回文子串，可以记忆化搜索，将结果保存起来
    public static String longestPalindrome2(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int left = 0;
        int right = 0;
        // 傻缓存，用来记录i到j的子串是否为回文子串
        // 行表示左边界，列表示右边界，值为1表示是回文子串，0表示不是回文子串，-1表示未命中缓存
        int[][] dp = new int[s.length()][s.length()];
        // 初始化缓存，-1表示未命中缓存
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length() - 1; j > i; j--) {
                // 判断i到j的子串是否为回文子串，如果是，判断当前子串是否比之前的最长回文子串更长
                if (isPalindrome2(s, i, j, dp) &&
                        ((j - i) > (right - left))) {
                    left = i;
                    right = j;
                }
            }
        }
        return s.substring(left, right + 1); // 注意substring的右边界是开区间
    }

    public static boolean isPalindrome2(String s, int left, int right, int[][] dp) {
        // 是否命中缓存，当前在有效区域内
        if (dp[left][right] != -1) {
            return dp[left][right] == 1;
        }
        if (left == right) {
            dp[left][right] = 1;
        } else if (s.charAt(left) != s.charAt(right)) {
            dp[left][right] = 0;
        } else {
            if (left + 1 == right) {
                dp[left][right] = 1;
                return dp[left][right] == 1;
            }
            // 递归判断左右两个字符之间的子串是否为回文子串
            dp[left][right] = isPalindrome2(s, left + 1, right - 1, dp) ? 1 : 0;
        }
        return dp[left][right] == 1;
    }

    // 动态规划版本
    public static String longestPalindrome3(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int left = 0;
        int right = 0;
        // dp表，行表示左边界，列表示有边界，值为1表示是回文子串，0表示不是回文子串
        boolean[][] dp = new boolean[s.length()][s.length()];
        // 右边界一定要大于等于左边界，所以只需要填表上半部分，下半部分直接初始化为-1
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                dp[i][j] = false;
            }
        }
        // 初始化dp表，单个字符一定是回文子串
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }
        // i + 1 == j时，判断i和j是否相等，如果相等，是回文子串
        for (int i = 0; i < s.length() - 1; i++) {
            dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
            if (dp[i][i + 1]) {
                left = i;
                right = i + 1;
            }
        }
        // 从下往上，从左往右填表
        for (int i = s.length() - 3; i >= 0; i--) {
            for (int j = i + 2; j < s.length(); j++) {
                dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                if (dp[i][j] && (j - i) > (right - left)) {
                    left = i;
                    right = j;
                }
            }
        }
        return s.substring(left, right + 1);
    }

    // 动态规划版本-优化
    public static String longestPalindrome4(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int n = s.length();
        int left = 0;
        int right = 0;
        // dp表，行表示左边界，列表示有边界，值为1表示是回文子串，0表示不是回文子串
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = true; // 单个字符一定是回文子串
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i == 1 || dp[i + 1][j - 1]);
                if (dp[i][j] && (j - i) > (right - left)) {
                    left = i;
                    right = j;
                }
            }
        }
        return s.substring(left, right + 1);
    }

    public static void main(String[] args) {
        String s = "babad";
        System.out.println("use violent recursion to solve:");
        System.out.println(longestPalindrome1(s));
        System.out.println("use memorization search to solve:");
        System.out.println(longestPalindrome2(s));
        System.out.println("use dynamic programming to solve:");
        System.out.println(longestPalindrome3(s));
        System.out.println("use dynamic programming to solve improve:");
        System.out.println(longestPalindrome4(s));
    }
}
