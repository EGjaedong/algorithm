package org.example.algorithm.exercise.recursion_and_dp;

public class LongestCommonSubstring {

    // 经典问题，两个字符串的最长公共子串

    // 首先用暴力递归来解决问题
    public static String longestCommonSubstring(String str1, String str2) {
        if (str1 == null || str1.isEmpty() || str2 == null || str2.isEmpty()) {
            return null;
        }
        Range result = new Range(0, -1); // 默认结果为空
        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {
                Range cur = process(str1, str2, i, j);
                if (cur.end - cur.start > result.end - result.start) {
                    result = cur;
                }
            }
        }
        return str1.substring(result.start, result.end + 1);
    }

    // 递归，判断当前以i结尾的str1，和以j结尾的str2的最长公共子串
    public static Range process(String s1, String s2, int i, int j) {
        if (i < 0 || j < 0) {
            return new Range(i + 1, i); // 返回空字符串
        }
        // 相等，继续向前匹配
        if (s1.charAt(i) == s2.charAt(j)) {
            Range range = process(s1, s2, i - 1, j - 1);
            return new Range(range.start, i);
        } else {
            // 不匹配，直接结束递归，由外侧循环继续向后匹配
            return new Range(i + 1, i);
        }
    }

    public static class Range {

        public int start;
        public int end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    // 暴力递归改动态规划
    public static String longestCommonSubstringDP(String str1, String str2) {
        if (str1 == null || str1.isEmpty() || str2 == null || str2.isEmpty()) {
            return "";
        }

        int m = str1.length();
        int n = str2.length();
        Range[][] dp = new Range[m][n]; // dp 表大小为 (m) x (n)
        Range longestRange = new Range(0, -1); // 记录最长公共子串的 Range，初始化为 (0, -1)

        // 填充 DP 表
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 如果字符匹配
                if (str1.charAt(i) == str2.charAt(j)) {
                    if (i > 0 && j > 0) {
                        // 如果前一个字符的最长公共子串有效，那么当前的最长公共子串就是前一个字符的最长公共子串加1
                        if (dp[i - 1][j - 1] != null) {
                            dp[i][j] = new Range(dp[i - 1][j - 1].start, i);
                        } else { // 如果前一个字符的最长公共子串无效，那么当前的最长公共子串就是当前字符
                            dp[i][j] = new Range(i, i);
                        }
                    } else {
                        // 当前处在边界，直接设置为当前字符
                        dp[i][j] = new Range(i, i);
                    }

                    // 更新最长公共子串
                    if (dp[i][j].end - dp[i][j].start > longestRange.end - longestRange.start) {
                        longestRange = dp[i][j];
                    }
                } else {
                    dp[i][j] = null;
                }
            }
        }

        if (longestRange.end == -1) {
            return ""; // 没有公共子串
        }

        return str1.substring(longestRange.start, longestRange.end + 1);
    }

    public static void main(String[] args) {
        String str1 = "ha";
        String str2 = "a";
        System.out.println("using violent recursion solve:");
        System.out.println(longestCommonSubstring(str1, str2));
        System.out.println("using dynamic programing solve:");
        System.out.println(longestCommonSubstringDP(str1, str2));
    }
}
