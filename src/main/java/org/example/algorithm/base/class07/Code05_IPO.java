package org.example.algorithm.base.class07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Code05_IPO {
    // 输入：
    // 正数数组costs，正数数组profits，正数k，正数m
    // 含义：
    // costs[i]表示i号项目的花费
    // profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
    // k表示你只能串行的最多做k个项目
    // m表示你初始的资金
    // 说明：
    // 你每做完一个项目，马上获得的收益，可以支持你去做下一个项目。
    // 输出：
    // 你最后获得的最大钱数。

    // 项目类
    public static class Node {
        public int p; // 利润
        public int c; // 花费

        public Node(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public static class MinCostComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.c - o2.c;
        }
    }

    public static class MaxProfitComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.p - o2.p;
        }
    }

    public static int findMaximizedCapital(int k, int m, int[] profits, int[] costs) {
        Node[] nodes = new Node[profits.length];
        // 构建项目对象数组
        for (int i = 0; i < profits.length; i++) {
            nodes[i] = new Node(profits[i], costs[i]);
        }
        // 构建花费小根堆
        PriorityQueue<Node> minCostHeap = new PriorityQueue<>(new MinCostComparator());
        // 构建利润大根堆
        PriorityQueue<Node> maxProfitHeap = new PriorityQueue<>(new MaxProfitComparator());
        // 将所有项目加入花费小根堆
        minCostHeap.addAll(Arrays.asList(nodes));
        // 串行做项目
        for (int i = 0; i < k; i++) {
            while (!minCostHeap.isEmpty() && minCostHeap.peek().c <= m) {
                maxProfitHeap.add(minCostHeap.poll());
            }
            if (maxProfitHeap.isEmpty()) {
                // 无法做项目，返回初始资金
                return m;
            }
            // 手上的资金大于所有项目的启动资金，做利润最大的项目
            m += maxProfitHeap.poll().p;
        }
        return m;
    }
}
