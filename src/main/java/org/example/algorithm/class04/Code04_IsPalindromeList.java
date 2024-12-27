package org.example.algorithm.class04;

import java.util.ArrayDeque;
import java.util.Deque;

public class Code04_IsPalindromeList {

    // 判断一个链表是不是回文链表
    // 1 -> 2 -> 3 -> 2 -> 1 -> null
    // 1 -> 20 -> 3 -> 20 -> 1 -> null

    // 第一种解法，判断回文常用解法，笔试的时候用就行，额外空间复杂度是O(N)
    public static boolean isPalindrome1(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node cur = head;
        Deque<Integer> helpStack = new ArrayDeque<>();
        while (head != null) {
            helpStack.push(head.value);
            head = head.next;
        }
        while (cur != null) {
            if (cur.value != helpStack.pop()) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    // 快慢指针的方式，这样栈就可以只用一半的空间
    public static boolean isPalindrome2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node right = head.next; // 慢指针
        Node cur = head; // 快指针
        while (cur.next != null && cur.next.next != null) {
            right = right.next;
            cur = cur.next.next;
        }
        Deque<Integer> helpStack = new ArrayDeque<>();
        while (right != null) {
            helpStack.push(right.value);
            right = right.next;
        }
        while (!helpStack.isEmpty()) {
            if (head.value != helpStack.poll()) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    // 第三种解法，不用栈，额外空间复杂度O(1)
    // 依旧是快慢指针，当慢指针走到中间的时候，把右半部分反转，然后比较
    // 比较完成之后，右半部分需要还原
    public static boolean isPalindrome3(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        // 现阶段，n1是慢指针，n2是快指针
        Node n1 = head;
        Node n2 = head;
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }
        // 此时，n1为中间节点，偶数个时，为中间节点左边的一个节点（中间的概念是虚拟的）
        // 将n2设置为n1的下一个节点，也就是右半部分的第一个节点
        n2 = n1.next;
        // 需要将中间节点的next置为null，否则会形成环
        n1.next = null;
        // 反转右半部分
        Node n3;
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        // 反转完成后，n1位最右侧节点，保存最右侧节点
        n3 = n1;
        n2 = head;
        // 从两头开始往中间走，判断是不是回文
        boolean res = true;
        while (n1 != null && n2 != null) {
            if (n1.value != n2.value) {
                res = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        // 把右边链表转回去
        n1 = n3.next;
        n3.next = null;
        while (n1 != null) {
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Node head = null;
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");
    }

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }
}
