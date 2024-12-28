package org.example.algorithm.class05;

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

    public static int preValue = Integer.MIN_VALUE;

    // 递归判断一个二叉树是否为搜索二叉树
    // 中序遍历，判断当前节点左子树是否为BST，并且记录刚才判断的那个value值，和当前节点的value值比较，如果小于等于，返回false
    // 刚才判断的那个value值，就是当前节点的左子树的最右侧的节点
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
}
