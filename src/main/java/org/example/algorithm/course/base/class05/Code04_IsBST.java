package org.example.algorithm.course.base.class05;

import java.util.ArrayDeque;
import java.util.Deque;

public class Code04_IsBST {
    // 判断一个树是否是搜索二叉树(binary search tree)（任意一个节点，左子树所有节点都小于它，右子树所有节点都大于它）
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class ReturnData {
        public boolean isBST;
        public int min;
        public int max;

        public ReturnData(boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }

    public static int preValue = Integer.MIN_VALUE;

    // 递归判断一个二叉树是否为搜索二叉树
    // 中序遍历，判断当前节点左子树是否为BST，并且记录刚才判断的那个value值，和当前节点的value值比较，如果小于等于，返回false
    // 刚才判断的那个value值，就是当前节点的左子树的最大值
    public static boolean isBSTUseRecur(Node head) {
        if (head == null) {
            return true;
        }
        boolean isBstLeft = isBSTUseRecur(head.left);
        if (!isBstLeft) {
            return false;
        } else if (head.value <= preValue) {
            return false;
        } else {
            preValue = head.value;
        }
        return isBSTUseRecur(head.right);
    }

    public static boolean isBSTUseNoRecur(Node head) {
        if (head == null) {
            return true;
        }
        int preValue = Integer.MIN_VALUE;
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(head);
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                if (head.value <= preValue) {
                    return false;
                } else {
                    preValue = head.value;
                }
                head = head.right;
            }
        }
        return true;
    }

    // 递归返回套路判断当前树是否为BST
    // 1、左树是BST
    // 2、右树是BST
    // 3、左树的最大值小于当前节点的值，右树的最小值大于当前节点的值
    public static boolean isBSTUseRecur2(Node node) {
        if (node == null) {
            return true;
        }
        return process(node).isBST;
    }

    public static ReturnData process(Node node) {
        if (node == null) {
            return null;
        }
        int min = node.value;
        int max = node.value;
        ReturnData leftData = process(node.left);
        ReturnData rightData = process(node.right);
        if (leftData != null) {
            min = Math.min(min, leftData.min);
            max = Math.max(max, leftData.max);
        }
        if (rightData != null) {
            min = Math.min(min, rightData.min);
            max = Math.max(max, rightData.max);
        }
        boolean isBST = true;
        if (leftData != null && (!leftData.isBST || leftData.max >= node.value)) {
            isBST = false;
        }
        if (rightData != null && (!rightData.isBST || rightData.min <= node.value)) {
            isBST = false;
        }
        return new ReturnData(isBST, min, max);
    }
}
