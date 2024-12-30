package org.example.algorithm.class06;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class Code02_DFS {

    // 给定一个图的节点Node，实现深度优先遍历
    // 利用栈实现，当然也可以用递归实现
    public static void graphDfs(Node node) {
        if (node == null) {
            return;
        }
        Deque<Node> stack = new ArrayDeque<>();
        // 用来记录已经遍历过的节点
        Set<Node> nodeSet = new HashSet<>();
        stack.push(node);
        nodeSet.add(node);
        // 入栈时处理数据
        System.out.println(node.value);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            // 其实就是抓住一条路走到黑，把这个节点出去的其中一条路径的所有节点都压入栈中。
            // 走到头之后，再从栈中弹出一个节点，继续走下一条路。
            for (Node next : cur.nexts) {
                if (!nodeSet.contains(next)) {
                    // 把当前节点和next节点都压入栈中
                    stack.push(cur);
                    stack.push(next);
                    nodeSet.add(next);
                    System.out.println(node.value);
                    break;
                }
            }
        }
    }

}
