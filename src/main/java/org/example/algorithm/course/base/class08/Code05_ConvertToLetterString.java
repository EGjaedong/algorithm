package org.example.algorithm.course.base.class08;

public class Code05_ConvertToLetterString {
    // 规定1和A对应、2和B对应、3和C对应...
    // 那么一个数字字符串比如"111"，就可以转化为"AAA"、"KA"和"AK"。
    // 给定一个只有数字字符组成的字符串str，返回有多少种转化结果。

    public static int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    // str[0...i-1]已经转化完了，不用操心了
    // str[i...]请返回有多少种转化的结果
    // 因为题目只要求转化方式，而没有让求出具体的转化结果，所以处理函数中并不需要收集转化结果。
    // 如果需要收集转化结果，可以传入一个List，然后在递归过程中，把每一种转化方式都加入到List中。
    public static int process(char[] str, int i) {
        // base cases
        if (i == str.length) {
            return 1;
        }
        // 当前这个字符是0，但是0不能单独转化成字符，也不能和后面的字符结合转化成字符，
        // 前序选择的转化决定导致后面走不下去了，所以返回0
        if (str[i] == '0') {
            return 0;
        }
        // 剩下三种情况
        // str[i] 的值是 3 ～ 9，则 str[i] 只能单独转化成 'C' 到 'I'，不能和后面的一个字符结合转化
        // str[i] 的值是 1，则 str[i] 可以单独转化成 'A'，也可以和后面的一个字符结合转化成 'J' 到 'S'
        // str[i] 的值是 2，则 str[i] 可以单独转化成 'B'，也可以和后面的一个字符结合转化成 'T' 到 'Z'，
        // 但是后面的字符如果大于6，就不能结合。
        // str[i] == 1，两种结合方式
        if (str[i] == '1') {
            // 当前仅选取str[i]转化成字符，不与后面字符结合，问后面的转化方式有多少种
            int res = process(str, i + 1);
            if (i + 1 < str.length) {
                // 当前选取str[i]和str[i+1]转化成字符，问后面的转化方式有多少种
                res += process(str, i + 2);
            }
            return res;
        }
        // str[i] == 1，两种结合方式
        if (str[i] == '2') {
            int res = process(str, i + 1);
            if (i + 1 < str.length && str[i + 1] <= 6) {
                res += process(str, i + 2);
            }
            return res;
        }
        // str[i] == 3 ~ 9，只有一种转化方式
        return process(str, i + 1);
    }

    public static void main(String[] args) {
        System.out.println(number("11111"));
    }

}
