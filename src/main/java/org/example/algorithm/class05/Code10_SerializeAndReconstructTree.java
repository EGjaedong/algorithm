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

}
