package org.example.algorithm.exercise.recursion_and_dp;

public class JudgePoint24 {

    public static void main(String[] args) {
        double[] arr = new double[]{4, 1, 8, 7};
        System.out.println(process(arr));
    }

    private static boolean process(double[] arr) {
        int n = arr.length;
        if (n == 1) {
            return Math.abs(arr[0] - 24) < 1e-6;
        }
        // 两层循环，每次取两个数，计算结果，将结果放入数组中，递归调用
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    double num1 = arr[i];
                    double num2 = arr[j];
                    double[] next = new double[n - 1];
                    // 去掉已经选择的两个数
                    for (int k = 0, index = 0; k < n; k++) {
                        if (k != i && k != j) {
                            next[index++] = arr[k];
                        }
                    }
                    if (process(add(num1, num2, next)) ||
                            process(add(num1, -num2, next)) ||
                            process(add(-num1, num2, next)) ||
                            process(multiply(num1, num2, next)) ||
                            (num2 > 1e-6 && process(divide(num1, num2, next))) ||
                            (num1 > 1e-6 && process(divide(num2, num1, next)))) {
                        return true; // 如果任何一种操作结果为真，返回true
                    }
                }
            }
        }
        return false;
    }

    private static double[] add(double num1, double num2, double[] next) {
        next[next.length - 1] = num1 + num2;
        return next;
    }

    private static double[] multiply(double num1, double num2, double[] next) {
        next[next.length - 1] = num1 * num2;
        return next;
    }

    private static double[] divide(double denominator, double molecular, double[] next) {
        next[next.length - 1] = denominator / molecular;
        return next;
    }
}
