package org.example.old_dog;

public class Code02 {
    // 给是个数字，左边一堆，右边一堆，让这两堆的和差值尽可能的小
    // 递归，每个数字都有两种选择，要么放左边，要么放右边，求出差值的最小值
    private static int RES = Integer.MAX_VALUE;

    public static void minDiff(int[] arr) {
        int totalScore = 0;
        for (int num : arr) {
            totalScore += num;
        }
        process(arr, totalScore,0, 0, 0);
    }

    // 这个递归过程求的是差值最小值
    // count表示当前这个team选择了几个数
    // index表示轮训到当前这个数，左边这个队伍要还是不要这个数
    public static void process(int[] arr, int totalScore, int index, int count, int alreadySum) {
        // base case，左边已经选择够了所有的数据
        if (count == 5) {
            int otherTeamSum = totalScore - alreadySum;
            RES = Math.min(RES, Math.abs(alreadySum - otherTeamSum));
            return;
        }
        // 没有数据可以选择了，退出当前递归
        if (index == arr.length) {
            return;
        }
        // 普遍位置，两种选择，要当前数，或者不要当前数
        // 要
        process(arr, totalScore, index + 1, count + 1, alreadySum + arr[index]);
        // 不要
        process(arr, totalScore, index + 1, count, alreadySum);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        minDiff(arr);
        System.out.println(RES);
    }

}
