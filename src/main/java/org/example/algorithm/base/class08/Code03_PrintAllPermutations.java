package org.example.algorithm.base.class08;

import java.util.ArrayList;
import java.util.List;

public class Code03_PrintAllPermutations {
    // 打印一个字符串的全部排列
    // 打印一个字符串的全部排列，要求不要出现重复的排列
    // 从左往右，每一个位置都做一个决定

    public static List<String> permutations(String str) {
        List<String> res = new ArrayList<>();
        if (str == null || str.isEmpty()) {
            return res;
        }
        char[] charArray = str.toCharArray();
        process(charArray, 0, res);
        return res;
    }

    // str[i...]范围上，所有的字符，都可以在i位置上，后续都去尝试
    // str[0 ... i-1]范围上，都是之前做好的选择
    // 也就是进这个方法前，0～i-1范围上的数据是已经做好选择的，i～n-1范围上的数据是待做选择的，
    // 要的是把所有i后面的数据都换到i位置上做一遍选择。
    // 把排列放到res中
    private static void process(char[] str, int i, List<String> res) {
        if (i == str.length) {
            res.add(String.valueOf(str));
        }
        // 分支限界，提前杀死不符合条件的分支
        boolean[] visited = new boolean[26]; // 标记为，当前的26个字符是否都出现过，如果是，则对应的位置为true
        for (int j = i; j < str.length; j++) {
            if (!visited[str[j] - 'a']) {
                visited[str[i] - 'a'] = true;
                swap(str, i, j);
                process(str, i + 1, res);
                swap(str, i, j);
            }
        }
    }

    public static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(permutations("abc"));
    }
}
