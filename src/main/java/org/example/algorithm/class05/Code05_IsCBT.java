package org.example.algorithm.class05;

import java.util.LinkedList;
import java.util.Queue;

public class Code05_IsCBT {
    // 完全二叉树（complete binary tree）
    // 两个判断条件：
    // 1、如果一个节点有右孩子，但没有左孩子，直接返回false
    // 2、当遇到第一个左，右孩子不双全的节点之后，后续所有的节点都必须是叶子节点

    public static class Node {

        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    // 使用bfs
    public static boolean isCBT(Node head) {
        if (head == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        // 判断此后节点是否都应该是叶子节点
        boolean leaf = false;
        Node left;
        Node right;
        while (!queue.isEmpty()) {
            head = queue.poll();
            left = head.left;
            right = head.right;
            // 两个判断条件：
            // 1、如果一个节点有右孩子，但没有左孩子，直接返回false
            // 2、当遇到第一个没有右孩子的节点之后，后续所有的节点都必须是叶子节点
            if ((leaf && (left != null || right != null)) || (left == null && right != null)) {
                return false;
            }
            if (left != null) {
                queue.add(left);
            }
            if (right != null) {
                queue.add(right);
            } else {
                leaf = true;
            }
        }
        return true;
    }
}
