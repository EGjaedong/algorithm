package org.example.algorithm.class05;

import java.util.LinkedList;
import java.util.Queue;

public class Code03_TreeMaxWidth {

    public static class Node {

        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 宽度优先遍历
    // 准备一个队列，先将头节点加入队列，弹出时打印，然后先左再右近队列。
    // 循环弹出队列，直到队列为空
    public static void bfs(Node head) {
        System.out.print("BFS: ");
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.print(cur.value + " ");
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        System.out.println();
    }

    // 利用宽度优先遍历求二叉树的最大宽度
    // 利用Map，记录当前Node的层级是什么
    // 统计每一个层级的节点数，取最大值
    public static void getMaxWidth1(Node head) {

    }

}
