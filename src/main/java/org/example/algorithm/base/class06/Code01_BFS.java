package org.example.algorithm.base.class06;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Code01_BFS {

    // 给定一个图的节点Node，实现宽度优先遍历
    // 利用队列实现
    public static void graphBfs(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        Set<Node> nodeSet = new HashSet<>(); // 用来记录已经遍历过的节点
        queue.add(node);
        nodeSet.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            // 出队列时处理数据
            System.out.println(cur.value);
            for (Node next : cur.nexts) {
                if (!nodeSet.contains(next)) {
                    queue.add(node);
                    nodeSet.add(next);
                }
            }
        }
    }

}
