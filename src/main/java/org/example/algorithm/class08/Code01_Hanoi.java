package org.example.algorithm.class08;

public class Code01_Hanoi {

    // 汉诺塔问题
    // 用暴力递归解决，思路：将大问题分解为小问题的集合
    // 如果我要将第i个圆盘从from移动到to上，那么我必须先将1到i-1的圆盘移动到other上，
    // 然后移动第i个圆盘到to上，
    // 最后把i-1个圆盘从other上移动到to上。
    // 递归的出口是只有一个圆盘时，直接移动到to上。
    // 第i次递归只需要保证它的行为是符合规则的，至于1到i-1的圆盘如何移动，交给下一次递归处理。
    // 只需要每一层递归都保证自己是合法的，那么整个递归过程就是合法的。
    public static void hanoi(int i) {
        if (i > 0) {
            move(i, "left", "mid", "right");
        }
    }

    public static void move(int i, String from, String to, String other) {
        if (i == 1) {
            System.out.println("Move 1 from " + from + " to " + to); // base case，只有一个圆盘时直接移动到to上
        } else {
            // 将1到i-1的圆盘从from移动到other上
            move(i - 1, from, other, to);
            // 将第i个圆盘从from移动到to上
            System.out.println("Move " + i + " from " + from + " to " + to);
            // 将1到i-1移动到to上
            move(i - 1, other, to, from);
        }
    }

    public static void main(String[] args) {
        hanoi(3);
    }
}
