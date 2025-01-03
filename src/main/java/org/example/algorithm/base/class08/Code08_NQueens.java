package org.example.algorithm.base.class08;

public class Code08_NQueens {
    // N皇后问题
    // 1. 任何两个皇后都不能处于同一行
    // 2. 任何两个皇后都不能处于同一列
    // 3. 任何两个皇后都不能处于同一斜线上（斜率为1或-1）

    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }
        // 用来记录某一行的皇后放在了哪一列，从0到n-1，记录了从第1行到第n行的皇后放在了哪一列
        int[] queuesRecord = new int[n];
        return process1(0, queuesRecord, n);
    }

    // 还是递归的套路，认为line-1行之前都已经放好了皇后，而且皇后的位置记录在了queuesRecord里
    // 本次递归只解决当前行皇后能有多少种放法。
    private static int process1(int line, int[] queuesRecord, int n) {
        // base case，line越界了，说明已经放完了，把这一种方式返回
        if (line == n) {
            return 1;
        }
        int res = 0;
        // 当前在处理第i行，尝试第i行的每一列是否能放皇后，j就是列数
        for (int column = 0; column < n; column++) {
            if (isValid(queuesRecord, line, column)) {
                // 如果当前位置可以放置皇后，记录位置
                queuesRecord[line] = column;
                res += process1(line + 1, queuesRecord, n);
            }
        }
        return res;
    }

    // 判断当前的j列能否放皇后，规则如上
    // 由于递归设计成了逐行放置皇后，所以只需要判断列和斜线即可
    public static boolean isValid(int[] queuesRecord, int i, int j) {
        // k用来遍历之前放过的所有的皇后，看是否和当前的皇后冲突
        for (int k = 0; k < i; k++) {
            // 如果之前的某个皇后和当前皇后在同一列，返回false
            if (j == queuesRecord[k] || Math.abs(queuesRecord[k] - j) == Math.abs(k - i)) {
                return false;
            }
        }
        return true;
    }

    public static int num2(int n) {
        // 1~32皇后问题的答案，如果要解决更大范围的N皇后问题，不能用int类型，需要long，甚至更大
        if (n < 1 || n > 32) {
            return 0;
        }
        // upperLim是一个全1的数，如果n=4，upperLim=1111，n=5，upperLim=11111，用来表示当前行的皇后可以放的位置
        // 1表示可以放，0表示不能放
        // 如果是32皇后问题，upperLim=-1，也就是全1
        // 实际上就是靠位来做标记。这个数在整个算法执行过程中不会变
        int upperLim = n == 32 ? -1 : (1 << n) - 1;
        return process2(upperLim, 0, 0, 0);
    }

    /**
     * 使用位运算优化N皇后问题求解
     * @param upperLim 用来表示当前行的皇后可以放的位置，1表示可以放，0表示不能放
     * @param columnLim 表示递归到当前行位置，之前有哪些列上放置了皇后，1表示放置了，0表示没有放置
     * @param leftDiaLim 表示递归到当前行位置，因收到已放置的所有皇后的左下方斜线的影响，导致当前位置不能放置皇后，1表示不能放置，0表示可以放置
     * @param rightDiaLim 表示递归到当前行位置，因收到已放置的所有皇后的右下方斜线的影响，导致当前位置不能放置皇后，1表示不能放置，0表示可以放置
     * @return
     */
    private static int process2(int upperLim, int columnLim, int leftDiaLim, int rightDiaLim) {
        // 所有列上都是1，表示放满了，返回1
        if (columnLim == upperLim) {
            return 1;
        }
        // pos是当前行可以放皇后的位置，1表示可以放，0表示不能放
        int pos = 0;
        // 最右边的1，表示当前行可以放皇后的最右边的位置
        int mostRightOne = 0;
        // pos是当前行可以放皇后的位置，1表示可以放，0表示不能放
        pos = upperLim & (~(columnLim | leftDiaLim | rightDiaLim));
        int res = 0;
        // 当前还有能继续放置皇后的位置
        while (pos != 0) {
            // 取出pos最右边的1，表示这一次要将皇后放在这个位置上
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            // 递归调用，继续放下一行的皇后
            res += process2(upperLim,
                    columnLim | mostRightOne, // 不能放皇后的列
                    (leftDiaLim | mostRightOne) << 1, // 不能放置皇后的左下方斜线
                    (rightDiaLim | mostRightOne) >>> 1 // 不能放置皇后的右下方斜线，无符号右移
            );
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 14;

        long start = System.currentTimeMillis();
        System.out.println(num2(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(num1(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");
    }
}
