package org.example.algorithm.class04;

public class Code05_SmallerEqualBigger {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    // 将单链表氛围三个部分，小于num的放左边，等于num的放中间，大于num的放右边

    public static Node listPartition1(Node head, int pivot) {
        if (head == null || head.next == null) {
            return head;
        }
        // 先遍历一遍，得到链表的长度，用来准备数组
        Node cur = head;
        int i = 0;
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        Node[] nodes = new Node[i];
        i = 0;
        // 把链表中的节点放到数组中
        cur = head;
        for (; i < nodes.length; i++) {
            nodes[i] = cur;
            cur = cur.next;
        }
        // 用荷兰国旗问题的解法，把数组中的节点按照pivot分成三部分
        arrPartition(nodes, pivot);
        // 把数组中的节点重新连成链表
        for (i = 1; i < nodes.length; i++) {
            nodes[i-1].next = nodes[i];
        }
        nodes[nodes.length -1].next = null;
        return nodes[0];
    }

    private static void arrPartition(Node[] nodes, int pivot) {
        int small = -1;
        int big = nodes.length;
        int current = 0;
        while (current != big) {
            if (nodes[current].value < pivot) {
                swap(nodes, ++small, current++);
            } else if (nodes[current].value > pivot) {
                swap(nodes, --big, current);
            } else {
                current++;
            }
        }
    }

    public static void swap(Node[] nodes, int i, int j) {
        Node help = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = help;
    }

    // 准备六个变量，分别是小于部分的头和尾，等于部分的头和尾，大于部分的头和尾
    // 判断完之后，把这些连一起。
    // 但是，要注意，需要判断边界条件
    public static Node listPartition2(Node head, int pivot) {
        if (head == null || head.next == null) {
            return head;
        }
        Node smallHead = null;
        Node smallTail = null;
        Node equalHead = null;
        Node equalTail = null;
        Node bigHead = null;
        Node bigTail = null;
        Node next;
        // every node distributed to three lists
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < pivot) {
                if (smallHead == null) {
                    smallHead = head;
                    smallTail = head;
                } else {
                    smallTail.next = head;
                    smallTail = head;
                }
            }
            if (head.value == pivot) {
                if (equalHead == null) {
                    equalHead = head;
                    equalTail = head;
                } else {
                    equalTail.next = head;
                    equalTail = head;
                }
            }
            if (head.value > pivot) {
                if (bigHead == null) {
                    bigHead = head;
                    bigTail = head;
                } else {
                    bigTail.next = head;
                    bigTail = head;
                }
            }
            head = next;
        }
        // small and equal first linked
        if (smallTail != null) {
            smallTail.next = equalHead;
            equalTail = equalTail == null ? smallTail : equalTail;
        }
        // equal and big first linked
        if (equalTail != null) {
            equalTail.next = bigHead;
        }
        if (smallHead != null) {
            return smallHead;
        }
        return equalHead != null ? equalHead : bigHead;
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
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
//         head1 = listPartition1(head1, 5);
        head1 = listPartition2(head1, 5);
        printLinkedList(head1);

    }

}
