package org.example.algorithm.exercise.recursion_and_dp;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class TrainOutStationOrder {
    // 火车进站问题
    // 火车站一共有n 辆火车需要入站，每辆火车有一个编号，编号为1到n
    // 同时，也有火车需要出站，由于火车站进出共享一个轨道，所以后入站的火车需要先出站。换句话说，对于某一辆火车，只有在它之后入站的火车都出站了，它才能出站。
    // 现在，已经知道了火车的入站顺序，你需要计算，一共有多少种不同的出站顺序。按照字典序从小到大依次输出全部的出站顺序。

    public static List<String> resList = new ArrayList<>();

    public static Deque<Integer> stack = new ArrayDeque<>();

    public static void trainOutOrder(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        process(arr, 0, 0, "");
        Collections.sort(resList);
        for (String res : resList) {
            System.out.println(res);
        }
    }

    // 递归函数，pushedNumb表示进站的数量，popNum表示出站的数量
    // res表示当前的出站顺序
    // 这个递归其实是一个dfs，考察pushedNum和它后面的元素，以及stack里的元素，每一次递归调用都可以做一个动作，
    // 要么火车进站，要么火车出站
    private static void process(int[] arr, int pushedNum, int popNum, String res) {
        int n = arr.length;
        // base case，所有火车都出站了
        if (popNum == n) {
            resList.add(res);
        }
        // 所有火车都入栈，入栈后恢复原样
        if (pushedNum < n) {
            // 进去一个，已经在栈里的数量多了一个，出来的没变，出来的结果没变
            stack.push(arr[pushedNum]);
            process(arr, pushedNum + 1, popNum, res);
            // 复原，进入下一个递归
            stack.pop();
        }
        // 出站一个，进站的数量不变，出站的数量加1，递归
        if (!stack.isEmpty()) {
            int pop = stack.pop();
            process(arr, pushedNum, popNum + 1, res + pop + " ");
            // 复原
            stack.push(pop);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1, 2, 3};
        trainOutOrder(arr);
    }
}
