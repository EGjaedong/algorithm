package org.example.algorithm.course.base.class05;

public class Code07_IsFullTree {
    // 满二叉树（full binary tree）
    // 满二叉树定义：在满二叉树中，除最后一层无任何子节点外，每一层上的所有结点都有两个子结点。
    // 满二叉树性质：高度为h的满二叉树，节点数为2^h - 1

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class ReturnData {
        public int height;
        public int nodes;

        public ReturnData(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    public static boolean isFullTree(Node node) {
        ReturnData data = process(node);
        return data.nodes == (1 << data.height) - 1;
    }

    public static ReturnData process(Node node) {
        if (node == null) {
            return new ReturnData(0, 0);
        }
        ReturnData leftData = process(node.left);
        ReturnData rightData = process(node.right);
        int height = Math.max(leftData.height, rightData.height) + 1;
        int nodes = leftData.nodes + rightData.nodes + 1;
        return new ReturnData(height, nodes);
    }
}
