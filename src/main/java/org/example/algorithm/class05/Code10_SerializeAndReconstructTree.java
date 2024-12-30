package org.example.algorithm.class05;

import java.util.LinkedList;
import java.util.Queue;

public class Code10_SerializeAndReconstructTree {
    // 序列化和反序列化一颗树
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 先序序列化
    public static String serializeByPre(Node head) {
        if (head == null) {
            return "#_";
        }
        String res = head.value + "_";
        res += serializeByPre(head.left);
        res += serializeByPre(head.right);
        return res;
    }

    // 先序反序列化
    public static Node recordByPreString(String preStr) {
        String[] values = preStr.split("_");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < values.length; i++) {
            queue.offer(values[i]);
        }
        return recordPreOrder(queue);
    }

    private static Node recordPreOrder(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("#")) {
            return null;
        }
        Node head = new Node(Integer.parseInt(value));
        head.left = recordPreOrder(queue);
        head.right = recordPreOrder(queue);
        return head;
    }

    // 比较笨的办法，先序遍历序列化，然后判断是否是子树
    public static boolean isSubTree(Node root, Node subRoot) {
        String[] rootStr = serializeByPre(root).split("_");
        String[] subStr = serializeByPre(subRoot).split("_");
        if (rootStr.length < subStr.length) {
            return false;
        }
        for (int i = 0; i < rootStr.length; i++) {
            if (rootStr[i].equals(subStr[0])) {
                int j = 0;
                for (; j < subStr.length; j++) {
                    if (!rootStr[i + j].equals(subStr[j])) {
                        break;
                    }
                }
                if (j == subStr.length) {
                    return true;
                }
            }
        }
        return false;
    }

    // 同步遍历方式
    public static boolean isSubTree2(Node root, Node subRoot) {
        return dfs(root, subRoot);
    }

    public static boolean dfs(Node root, Node subRoot) {
        if (root == null) {
            return false;
        }
        return check(root, subRoot) ||
                dfs(root.left, subRoot) ||
                dfs(root.right, subRoot);
    }

    private static boolean check(Node root, Node subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root == null || subRoot == null) {
            return false;
        }
        if (root.value == subRoot.value) {
            return check(root.left, subRoot.left) && check(root.right, subRoot.right);
        }
        return false;
    }

    public static void main(String[] args) {

        Node root = new Node(3);
        root.left = new Node(4);
        root.left.left = new Node(1);
        root.left.right = new Node(2);
        root.right = new Node(5);
        Node subRoot = new Node(4);
        subRoot.left = new Node(1);
        subRoot.right = new Node(2);
        long start1 = System.currentTimeMillis();
        System.out.println(isSubTree(root, subRoot));
        long end1 = System.currentTimeMillis();
        System.out.println("cost time: " + (end1 - start1) + "ms");
        long start2 = System.currentTimeMillis();
        System.out.println(isSubTree2(root, subRoot));
        long end2 = System.currentTimeMillis();
        System.out.println("cost time: " + (end2 - start2) + "ms");
    }
}
