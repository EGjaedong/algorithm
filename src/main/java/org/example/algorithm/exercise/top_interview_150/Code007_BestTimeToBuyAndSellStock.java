package org.example.algorithm.exercise.top_interview_150;

public class Code007_BestTimeToBuyAndSellStock {

    // https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/description/?envType=study-plan-v2&envId=top-interview-150
    // 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
    // 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
    // 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
    // 示例 1：
    // 输入：[7,1,5,3,6,4]
    // 输出：5
    // 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
    // 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
    // 示例 2：
    // 输入：prices = [7,6,4,3,1]
    // 输出：0
    // 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。

    // 1、先暴力递归
    public static int getMax(int[] prices) {
        return process(prices, 0);
    }

    public static int process(int[] prices, int curBuy) {
        // 终止条件，最后一天买入，赚不到钱
        if (curBuy == prices.length - 1) {
            return 0;
        }
        // 不是最后一天买入，有两个选择，当前买入或者不买入
        // 当天不买入
        int notCurBuyIn = process(prices, curBuy + 1);
        // 当天买入，计算最大差价
        int max = 0;
        for (int sale = curBuy + 1; sale < prices.length; sale++) {
            if (prices[sale] > prices[curBuy]) {
                max = Math.max(prices[sale] - prices[curBuy], max);
            }
        }
        return Math.max(max, notCurBuyIn);
    }

    // 2、记忆化搜索
    public static int getMax2(int[] prices) {
        int[] maxArray = new int[prices.length];
        return process2(prices, 0, maxArray);
    }

    public static int process2(int[] prices, int curBuy, int[] maxArray) {
        // 终止条件，最后一天买入，赚不到钱
        if (curBuy == prices.length - 1) {
            return 0;
        }

        int max = 0;
        if (maxArray[curBuy] != 0) {
            max = maxArray[curBuy];
        }
        // 不是最后一天买入，有两个选择，当前买入或者不买入
        // 当天不买入
        int notCurBuyIn = process(prices, curBuy + 1);
        // 当天买入，计算最大差价
        for (int sale = curBuy + 1; sale < prices.length; sale++) {
            if (prices[sale] > prices[curBuy]) {
                max = Math.max(prices[sale] - prices[curBuy], max);
            }
        }
        max = Math.max(max, notCurBuyIn);
        maxArray[curBuy] = max;
        return max;
    }

    // 3、DP
    public static int getMax3(int[] prices) {
        int[] maxArray = new int[prices.length];
        maxArray[prices.length - 1] = 0;
        int maxPrice = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            if (maxPrice > prices[i]) {
                maxArray[i] = Math.max(maxArray[i + 1], maxPrice - prices[i]);
            }
            maxPrice = Math.max(maxPrice, prices[i]);
        }
        int max = 0;
        for (int j : maxArray) {
            max = Math.max(max, j);
        }
        return max;
    }

    // 4、DP2
    public static int getMax4(int[] prices) {
        int maxProfit = 0;
        int maxPrice = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            if (maxPrice > prices[i]) {
                maxProfit = Math.max(maxProfit, maxPrice - prices[i]);
            }
            maxPrice = Math.max(maxPrice, prices[i]);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        int max = getMax(prices);
        System.out.println(max);

        int max2 = getMax2(prices);
        System.out.println(max2);

        int max3 = getMax3(prices);
        System.out.println(max3);

        int max4 = getMax4(prices);
        System.out.println(max4);
    }
}
