package org.example.algorithm.base.class05;

public class Code06_IsBalancedTree {
    // 平衡二叉树（balanced binary tree）
    // 平衡二叉树定义：对于一颗二叉树，假设其深度为d（d>1）。每一个节点的左右子树的高度差不超过1，这样的二叉树被称为平衡二叉树。
    // 1、左树是平衡的
    // 2、右树是平衡的
    // 3、左树和右树得高度差不超过1

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    // 每个子树需要返回给上级两个信息
    // 有点充血模型的意思，类似于：
    // 两个下级告诉领导自己的工作成果怎么样，然后领导根据两个下级的工作成果结合自己的工作成果判断在他这个部门能不能算完成
    // 然后把他这个部门的工作成果汇报上去。
    public static class ReturnType {
        public boolean isBalance;
        public int height;

        public ReturnType(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }
    }

    public static boolean isBalancedTree(Node head) {
        return process(head).isBalance;
    }

    private static ReturnType process(Node head) {
        if (head == null) {
            return new ReturnType(true, 0);
        }
        ReturnType leftReturn = process(head.left);
        ReturnType rightReturn = process(head.right);
        boolean isBalanced = leftReturn.isBalance && rightReturn.isBalance && Math.abs(leftReturn.height - rightReturn.height) <= 1;
        int height = Math.max(leftReturn.height, rightReturn.height) + 1;
        return new ReturnType(isBalanced, height);
    }
}
