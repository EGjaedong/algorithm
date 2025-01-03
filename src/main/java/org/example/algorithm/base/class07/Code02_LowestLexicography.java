package org.example.algorithm.base.class07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Code02_LowestLexicography {

    // 字符串数组中拼接起来字典序最小的字符串
    // 贪心算法。
    // 如果有字符串a，b和c，如果能知道 a.b <= b.a（a和b组合）,b.c <= c.b，能够推出 a.c <= c.a (.表示结合)
    // 那么就说明这个比较是具有传递性的，能够从局部最优解推倒出全局最优解，就可以用贪心算法解决。
    // 证明：认为一个字符串可以认为是k进制的数，那么 a.b 等价于 a*k^b.length + b，
    // 简化一下，k^b.length 用符号 m（b）代替，即向左位移b的长度，
    // 则有： a * m(b) + b <= b * m(a) + a 和 b * m(c) + c <= c * m(b) + b
    // 目标是证明 a * m(c) + c <= c * m(a) + a
    // 第一个不等式，等号两边共同减去b，再共同乘c，则不等式变为 a * m(b) *c <= b * m(a) * c + a * c - b * c
    // 第二个不等式，两边共同减b再乘a，则有 b * m(c) * a + a * c - b * c <= c * m(a) * a
    // 则有b * m(c) * a + a * c - b * c <= b * m(a) * c + a * c - b * c
    // 即 b * m(c) * a <= b * m(a) * c，即 a * m(c) <= c * m(a)，即 a.c <= c.a

    public static class StringComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    public static String lowestString(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs, new StringComparator());
        StringBuilder res = new StringBuilder();
        for (String s : strs) {
            res.append(s);
        }
        return res.toString();
    }

    public static String bruteForce(String[] strs) {
        List<String> all = permute(strs);
        String min = all.get(0);
        for (String s : all) {
            if (s.compareTo(min) < 0) {
                min = s;
            }
        }
        return min;
    }

    // 主排列方法
    public static List<String> permute(String[] array) {
        List<String> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), array, new boolean[array.length]);
        return result;
    }

    // 回溯算法生成排列字符串
    private static void backtrack(List<String> result, List<String> tempList, String[] array, boolean[] used) {
        // 判断是否已完成排列
        if (tempList.size() == array.length) {
            // 将结果拼接为字符串并加入结果集
            result.add(String.join("", tempList));
            return;
        }

        // 遍历数组
        for (int i = 0; i < array.length; i++) {
            if (used[i]) continue; // 跳过已使用的元素
            tempList.add(array[i]); // 添加当前元素
            used[i] = true; // 标记为已使用
            backtrack(result, tempList, array, used); // 递归下一层
            used[i] = false; // 回溯
            tempList.remove(tempList.size() - 1); // 移除最后一个元素
        }
    }

    public static boolean isEqual(String a, String b) {
        if (a == null && b != null || a != null && b == null) {
            return false;
        }
        if (a == null && b == null) {
            return true;
        }
        if (a.length() != b.length()) {
            return false;
        }
        return a.equals(b);
    }

    public static void main(String[] args) {
        String[] strs1 = { "jibw", "ji", "jp", "bw", "jibw" };
        System.out.println("use greedy strategy:");
        System.out.println(lowestString(strs1));
        System.out.println("use brute force strategy:");
        System.out.println(bruteForce(strs1));
        System.out.println(isEqual(lowestString(strs1), bruteForce(strs1)));

        String[] strs2 = {"ba", "b"};
        System.out.println("use greedy strategy:");
        System.out.println(lowestString(strs2));
        System.out.println("use brute force strategy:");
        System.out.println(bruteForce(strs2));
        System.out.println(isEqual(lowestString(strs2), bruteForce(strs2)));
    }
}
