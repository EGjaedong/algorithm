package org.example.algorithm.class06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Code03_TopologySort {
    // 拓扑排序：给定一个有向无环图，按照拓扑顺序打印所有的节点
    // 要求有向图，且有入度为0的节点，且没有环
    // 怎么做？
    // 首先，准备一个map，把所有节点的入度都记录下来
    // 再准备一个队列，这个队列中只能加入入度为0的节点，然后弹出一个节点，把这个节点的所有邻居的入度减1；
    // 过程中有新的入度为0的节点产生，也加入队列。
    // 循环直到队列为空，排序完成。

    public static List<Node> topologySort(Graph graph) {
        Map<Node, Integer> inMap = new HashMap<>();
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        List<Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Node cur = zeroInQueue.poll();
            result.add(cur);
            for (Node newNode : cur.nexts) {
                inMap.put(newNode, inMap.get(newNode) - 1);
                if (inMap.get(newNode) == 0) {
                    zeroInQueue.add(newNode);
                }
            }
        }
        return result;
    }
}
