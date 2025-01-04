package org.example.algorithm.promote.class07;

public class Code02_CoinsMin {
    // 换钱的最少货币数
    //【题目】
    // 给定数组 arr，arr 中所有的值都为正数且不重复。每个值代表一种面值的货币，每种面值
    // 的货币可以使用任意张，再给定一个整数 aim，代表要找的钱数，求组成 aim 的最少货币
    // 数。
    //【举例】
    // arr=[5,2,3]，aim=20。4 张 5 元可以组成 20 元，其他的找钱方案都要使用更多张的货币，所以返回 4。
    // arr=[5,2,3]，aim=0。不用任何货币就可以组成 0 元，返回 0。
    // arr=[3,5]，aim=2根本无法组成 2 元，钱不能找开的情况下默认返回-1。

    // 这个题还有一个变种，是每种货币只能使用一次，求最少货币数

    // 首先暴力递归尝试，尝试模型为从左到右尝试
    public static int minCoins1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return -1;
        }
        return process1(arr, 0, aim);
    }

    // 当前考虑的是index位置的货币，rest是剩余需要的钱数
    // 如果返回-1说明自由使用arr[i..N-1]面值的情况下，无论如何也无法找零rest
    // 如果返回不是-1，代表自由使用arr[i..N-1]面值的情况下，找零rest需要的最少张数
    private static int process1(int[] arr, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        // rest >= 0
        // 已经没有面值能够考虑了
        // 如果此时剩余的钱为0，返回0张
        // 如果此时剩余的钱不是0，返回-1
        if (index == arr.length) {
            return rest == 0 ? 0 : -1;
        }
        // rest >= 0 && index < arr.length
        // 最少张数，初始时为-1，因为还没找到有效解
        int res = -1;
        // 从当前开始尝试
        // 依次尝试使用当前面值(arr[i])0张、1张、k张，但不能超过rest
        for (int k = 0; k * arr[index] <= rest; k++) {
            // 使用了k张arr[i]，剩下的钱为rest - k * arr[i]
            // 交给剩下的面值去搞定(arr[i+1..N-1])
            int next = process1(arr, index + 1, rest - k * arr[index]);
            // 说明后面的返回有效
            if (next != -1) {
                res = res == -1 ? next + k : Math.min(res, next + k);
            }
        }
        return res;
    }

    // 记忆化搜索
    public static int minCoins2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return -1;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        // 因为-1代表无效解，所以初始化为-2
        for (int i = 0; i <= arr.length; i++) {
            for (int j = 0; j <= aim; j++) {
                dp[i][j] = -2;
            }
        }
        return process2(arr, 0, aim, dp);
    }

    private static int process2(int[] arr, int index, int rest, int[][] dp) {
        // 前两个if条件共同组成了命中缓存的情况
        // 缓存表中rest<0的情况，都认为是-1
        if (rest < 0) {
            return -1;
        }
        if (dp[index][rest] != -2) {
            return dp[index][rest];
        }
        // 没有命中缓存
        if (index == arr.length) {
            dp[index][rest] = rest == 0 ? 0 : -1;
        } else {
            dp[index][rest] = -1;
            for (int k = 0; k * arr[index] <= rest; k++) {
                // 使用了k张arr[i]，剩下的钱为rest - k * arr[i]
                // 交给剩下的面值去搞定(arr[i+1..N-1])
                int next = process2(arr, index + 1, rest - k * arr[index], dp);
                // 说明后面的返回有效
                if (next != -1) {
                    dp[index][rest] = dp[index][rest] == -1 ?
                            next + k : Math.min(dp[index][rest], next + k);
                }
            }
        }
        return dp[index][rest];
    }

    // 动态规划版本
    public static int minCoins3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return -1;
        }
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        // 初始化最后一行，除了0位置为0，其他位置为-1
        dp[n][0] = 0;
        for (int col = 1; col <= aim; col++) {
            dp[n][col] = -1;
        }
        // 这个看懂了个大概，没完全懂
        for (int i = n - 1; i >= 0; i--) { // 从下向上，逐行填充数据
            for (int rest = 0; rest <= aim; rest++) { // 每一行都从左往右
                dp[i][rest] = -1;
                if (dp[i + 1][rest] != -1) { // 下面的值有效
                    dp[i][rest] = dp[i + 1][rest]; // 当前行的值先设置成下面的值
                }
                // 左边的值不越界并且有效
                // rest - arr[i] >= 0这个条件，对应暴力递归中的rest < 0
                if (rest - arr[i] >= 0 && dp[i][rest - arr[i]] != -1) {
                    if (dp[i][rest] == - 1) { // 如果之前下面的值无效
                        dp[i][rest] = dp[i][rest - arr[i]] + 1;
                    } else { // 说明下面和左边的值都有效，去最小的
                        dp[i][rest] = Math.min(dp[i][rest],
                                dp[i][rest - arr[i]] + 1);
                    }
                }
            }
        }
        return dp[0][aim];
    }

    // for test
    public static int[] generateRandomArray(int len, int max) {
        int[] arr = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        int len = 10;
        int max = 10;
        int testTime = 10000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(len, max);
            int aim = (int) (Math.random() * 3 * max) + max;
            // 如果两个不相等，则输出错误
            if (minCoins1(arr, aim) != minCoins2(arr, aim) || minCoins1(arr, aim) != minCoins3(arr,
                    aim)) {
                System.out.println("ooops!");
                break;
            }
        }
    }
}
