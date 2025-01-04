package org.example.algorithm.promote.class07;

public class Code01_RobotWalk {
    // 机器人达到指定位置方法数
    //【题目】
    // 假设有排成一行的 N 个位置，记为 1~N，N 一定大于或等于 2。开始时机器人在其中的 M 位
    // 置上(M 一定是 1~N 中的一个)，机器人可以往左走或者往右走，如果机器人来到 1 位置， 那
    // 么下一步只能往右来到 2 位置;如果机器人来到 N 位置，那么下一步只能往左来到 N-1 位置。
    // 规定机器人必须走 K 步，最终能来到 P 位置(P 也一定是 1~N 中的一个)的方法有多少种。给
    // 定四个参数 N、M、K、P，返回方法数。
    //【举例】
    // N=5,M=2,K=3,P=3
    // 上面的参数代表所有位置为 1 2 3 4 5。机器人最开始在 2 位置上，必须经过 3 步，最后到
    // 达 3 位置。走的方法只有如下 3 种:
    // 1)从2到1，从1到2，从2到3
    // 2)从2到3，从3到2，从2到3
    // 3)从2到3，从3到4，从4到3
    // 所以返回方法数 3。
    // N=3,M=1,K=3,P=3
    // 上面的参数代表所有位置为 1 2 3。
    // 机器人最开始在 1 位置上，必须经过 3 步，最后到达3位置。怎么走也不可能，所以返回方法数 0。

    // 先用暴力递归解决问题，时间复杂度O(2^k)
    // N:位置为1~N，固定参数
    // M:当前在M位置，可变参数
    // K:还剩下多少步，可变参数
    // P:最终目标P，固定参数
    public static int ways1(int N, int M, int K, int P) {
        return walk1(N, P, K, M);
    }

    // n:位置为1~N，固定参数
    // target:最终目标P，固定参数
    // remaining:还剩下多少步，可变参数
    // cur:当前在start位置，可变参数
    // 返回当前start位置，走remaining步之后，最终来到target位置的方法数
    private static int walk1(int n, int target, int remaining, int cur) {
        // base case，没有步数了，判断当前在不在target位置，如果在，返回当前这种走法；
        // 如果不在，则返回0，表示当前走法不能到达target位置
        if (remaining == 0) {
            return cur == target ? 1 : 0;
        }
        // 下面处理非base case情况
        // 如果当前在1位置，只能往右走
        if (cur == 1) {
            return walk1(n, target, remaining - 1, cur + 1);
        }
        // 如果当前在N位置，只能往左走
        if (cur == n) {
            return walk1(n, target, remaining - 1, cur - 1);
        }
        // 如果当前在中间位置，可以往左走，也可以往右走
        // 返回向左右和向右走两个方向走法的总和
        return walk1(n, target, remaining - 1, cur + 1) +
                walk1(n, target, remaining - 1, cur - 1);
    }

    // 暴力递归改记忆化搜索，时间复杂度O(N*K)
    public static int ways2(int N, int M, int K, int P) {
        // 只要是暴力递归，都可以改成记忆化搜索。
        // 记忆表长什么样子，取决于暴力递归过程中的可变参数有几个。
        // 只要足够大能放下数据就行，尽量不要浪费空间就好。
        int[][] dp = new int[K + 1][N + 1];
        // 把dp表的所有位置初始化为-1
        for (int i = 0; i <= K; i++) {
            for (int j = 0; j <= N; j++) {
                dp[i][j] = -1;
            }
        }
        // N:位置为1~N，固定参数
        // P:最终目标P，固定参数
        // M:当前在M位置，可变参数
        // K:还剩下多少步，可变参数
        return walk2(N, P, K, M, dp);
    }

    // 使用dp作为缓存
    private static int walk2(int n, int target, int remaining, int cur, int[][] dp) {
        // 取缓存
        if (dp[remaining][cur] != -1) {
            return dp[remaining][cur];
        }
        // 后面放缓存
        if (remaining == 0) {
            dp[remaining][cur] = cur == target ? 1 : 0;
            return dp[remaining][cur];
        }
        if (cur == 1) {
            dp[remaining][cur] = walk2(n, target, remaining - 1, cur + 1, dp);
        } else if (cur == n) {
            dp[remaining][cur] = walk2(n, target, remaining - 1, cur - 1, dp);
        } else {
            dp[remaining][cur] = walk2(n, target, remaining - 1, cur + 1, dp) +
                    walk2(n, target, remaining - 1, cur - 1, dp);
        }
        return dp[remaining][cur];
    }

    // 动态规划，时间复杂度O(n*K)
    // 可以在纸上把记忆化搜索里的dp表画出来，依赖关系就是暴力递归里面的递归调用关系
    public static int ways3(int n, int M, int K, int P) {
        // 参数无效直接返回0
        if (n < 2 || M < 1 || M > n || K < 1 || P < 1 || P > n) {
            return 0;
        }
        // dp表，行代表剩余步数，列代表位置，Java中数组默认初始化为0
        int[][] dp = new int[K + 1][n + 1];
        // 初始化dp表中暴力递归的base case的值
        dp[0][P] = 1;
        // 根据递归函数的递归调用关系，查看依赖关系，填表。
        // 可以看到列为1的位置，依赖于右上角的一格；
        // 列为n的位置，依赖于左上角的一格；
        // 其他位置依赖于左上角和右上角的和
        // 填表。
        // 填表过程中，根本就不需要关系递归含义是什么，只看了base case和递归调用关系(其实也就依赖关系)，就可以填表。
        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= n; j++) {
                if (j == 1) {
                    dp[i][j] = dp[i - 1][2];
                } else if (j == n) {
                    dp[i][j] = dp[i - 1][n - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i + 1][j + 1];
                }
            }
        }
        return dp[K][M];
    }


    public static void main(String[] args) {
        System.out.println(ways1(7, 4, 9, 5));
        System.out.println(ways2(7, 4, 9, 5));
        System.out.println(ways3(7, 4, 9, 5));
    }
}
