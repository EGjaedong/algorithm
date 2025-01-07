package org.example.algorithm.course.base.class05;

import java.util.ArrayDeque;
import java.util.Deque;

public class Code01_PreInPosTraversal {

    // 二叉树的节点
    public static class Node {

        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 递归方式实现二叉树的先序遍历
    // 先序遍历就是二叉树的DFS
    public static void preOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    // 递归方式实现二叉树的中序遍历
    public static void inOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        inOrderRecur(head.left);
        System.out.print(head.value + " ");
        inOrderRecur(head.right);
    }

    // 递归方式实现二叉树的后序遍历
    public static void posOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.print(head.value + " ");
    }

    // 非递归方式实现二叉树的先序遍历
    // 准备一个栈，先把头节点压入栈中，然后弹出栈顶节点打印，再把右孩子压入栈中（如果有），再把左孩子压入栈中（如果有）
    // 重复循环
    public static void preOrderUnRecur(Node head) {
        System.out.print("pre-order: ");
        if (head != null) {
            Deque<Node> stack = new ArrayDeque<>();
            stack.push(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.print(head.value + " ");
                if (head.right != null) {
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }

    // 非递归方式实现二叉树的中序遍历
    // 准备一个栈，按照左中右方式处理。
    // 对于每一颗子树，都把左右的左节点压入栈中，然后依次弹出，弹出时打印，然后对弹出节点的右子树循环这个过程。
    public static void inOrderUnRecur(Node head) {
        System.out.print("in-order: ");
        if (head != null) {
            Deque<Node> stack = new ArrayDeque<>();
            while (!stack.isEmpty() || head != null) {
                // 如果当前节点不是空，那么压栈，然后往左走。
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    // 如果当前节点为空，说明已经到数的叶子节点，开始处理弹出打印子树
                    head = stack.pop();
                    System.out.print(head.value + " ");
                    // 处理右树
                    head = head.right;
                }
            }
        }
        System.out.println();
    }

    // 非递归方式实现二叉树的后序遍历
    // 准备两个栈，一个用来压栈，一个用来收集，先把头节点压入栈中，然后弹出，不打印，扔到收集栈中；然后把左孩子压入栈中（如果有），再把右孩子压入栈中（如果有）
    // 依次循环。结束后，依次弹出收集栈的内容打印。
    public static void posOrderUnRecur1(Node head) {
        System.out.print("pos-order: ");
        if (head != null) {
            Deque<Node> stack = new ArrayDeque<>();
            Deque<Node> collectionStack = new ArrayDeque<>();
            stack.push(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                collectionStack.push(head);
                if (head.left != null) {
                    stack.push(head.left);
                }
                if (head.right != null) {
                    stack.push(head.right);
                }
            }
            while (!collectionStack.isEmpty()) {
                System.out.print(collectionStack.pop().value + " ");
            }
        }
        System.out.println();
    }

    public static void posOrderUnRecur2(Node head) {

    }

    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);
        head.right.left.left = new Node(6);
        head.right.right = new Node(10);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(11);

        // recursive
        System.out.println("==============recursive==============");
        System.out.print("pre-order: ");
        preOrderRecur(head);
        System.out.println();
        System.out.print("in-order: ");
        inOrderRecur(head);
        System.out.println();
        System.out.print("pos-order: ");
        posOrderRecur(head);
        System.out.println();

        // unrecursive
        System.out.println("============unrecursive=============");
        preOrderUnRecur(head);
        inOrderUnRecur(head);
        posOrderUnRecur1(head);
//        posOrderUnRecur2(head);

    }
}
