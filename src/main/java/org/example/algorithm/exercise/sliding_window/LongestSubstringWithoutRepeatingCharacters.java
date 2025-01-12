package org.example.algorithm.exercise.sliding_window;

public class LongestSubstringWithoutRepeatingCharacters {
    // 力扣（LeetCode）第 3 题，无重复字符的最长子串
    // 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

    // 滑动窗口
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        // 记录字符上一次出现的位置
        int[] last = new int[256];
        for (int i = 0; i < last.length; i++) {
            last[i] = -1;
        }
        int left = 0;
        int right = 0;
        int res = 0;
        for (; right < chars.length; right++) {
            // 更新窗口左边界的位置。
            left = Math.max(left, last[chars[right]] + 1);
            res = Math.max(res, right - left + 1);
            last[chars[right]] = right;
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "pwwkew";
        System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring(s));
    }
}
