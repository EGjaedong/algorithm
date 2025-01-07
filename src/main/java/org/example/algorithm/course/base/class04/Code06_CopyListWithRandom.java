package org.example.algorithm.course.base.class04;

import java.util.HashMap;
import java.util.Map;

public class Code06_CopyListWithRandom {
    // 给一个带有随机指针的链表，复制这个链表
    public static class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int value) {
            this.value = value;
        }
    }

    // 利用hashMap搞
    public static Node copyListWithRand1(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        // key放老Node，value放复制出来的Node
        Map<Node, Node> copy = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            copy.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            copy.get(cur).next = copy.get(cur.next);
            copy.get(cur).rand = copy.get(cur.rand);
            cur = cur.next;
        }
        return copy.get(head);
    }

    // 先开始拷贝每一个节点，然后把拷贝出来的节点挂在老节点后边
    public static Node copyListWithRand2(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node cur = head;
        Node next;
        // 复制节点挂在老节点后边
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        Node curCopy;
        // 处理rand指针
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }
        // 断开链表
        Node res = head.next;
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = curCopy.next;
            curCopy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }

    public static void printRandLinkedList(Node head) {
        Node cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = null;
        Node res1;
        Node res2;
        printRandLinkedList(head);
        res1 = copyListWithRand1(head);
        printRandLinkedList(res1);
        res2 = copyListWithRand2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

        printRandLinkedList(head);
        res1 = copyListWithRand1(head);
        printRandLinkedList(res1);
        res2 = copyListWithRand2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

    }
}
