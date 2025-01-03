package org.example.algorithm.class08;

public class Code06_Knapsack {

    // 给定两个长度都为N的数组weights和values，weights[i]和values[i]分别代表
    // i号物品的重量和价值。给定一个正数bag，表示一个载重bag的袋子，你装的物
    // 品不能超过这个重量。返回你能装下最多的价值是多少？

    // 递归尝试的原则：可变参数尽量少，形式尽量简单（能有变量就不用列表）。

    public static int maxValue1(int[] weights, int[] values, int bag) {
        return process1(weights, values, 0, 0, bag);
    }

    private static int process1(int[] weights, int[] values, int i, int alreadyWeight, int bag) {
        // 当前超重了，这一次尝试不能获取任何价值
        // 这里不能返回0，而是返回一个很小的负数，因为在进入递归之前，values[i] + process(...)已经把values[i]加上了
        // 所以这里判断重量加上当前物品超重了，说明进入递归前的values[i]就不合法，所以返回一个很小的负数，
        // 代表本次递归不合适，需要舍弃。或者需要返回-values[i-1]，回溯上面的结果。
        // 两种方式都可以。
        if (alreadyWeight > bag) {
//            return -values[i - 1];
            return Integer.MIN_VALUE;
        }
        // 当前已经没有物品可以选择了，这一次尝试不能获取任何价值
        if (i == weights.length) {
            return 0;
        }
        // 到当前i位置，前序已经确定，不再考虑，仅考虑本次要i还是不要i，然后递归后面的数据。
        return Math.max(
                process1(weights, values, i + 1, alreadyWeight, bag),
                values[i] + process1(weights, values, i + 1, alreadyWeight + weights[i], bag)
        );
    }

    public static int maxValue2(int[] weights, int[] values, int bag) {
        return process2(weights, values, 0, 0, 0, bag);
    }


    // 带着value进行递归尝试
    private static int process2(int[] weights, int[] values, int i, int alreadyWeight, int alreadyValue, int bag) {
        if (alreadyWeight > bag) {
            return 0;
        }
        // 结束了，返回结果
        if (i == weights.length) {
            return alreadyValue;
        }
        return Math.max(
                process2(weights, values, i + 1, alreadyWeight, alreadyValue, bag),
                process2(weights, values, i + 1, alreadyWeight + weights[i], alreadyValue + values[i], bag)
        );
    }

    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7 };
        int[] values = { 5, 6, 3, 19 };
        int bag = 11;
        System.out.println(maxValue1(weights, values, bag));
        System.out.println(maxValue2(weights, values, bag));
    }
}
