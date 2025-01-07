package org.example.algorithm.course.base.class04;

public class Code02_ReverseList {

    public static class Node {

        public int value;
        public Node next;

        public Node(int val) {
            value = val;
        }
    }

    public static Node reverseList(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static class DoubleNode {

        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int value) {
            this.value = value;
        }
    }

    public static DoubleNode reverseList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }
}
