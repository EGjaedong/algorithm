package org.example.algorithm.course.base.class04;

public class Code07_FindFirstIntersectNode {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    // 给定两个可能有环也可能无环的单链表，头节点head1和head2。请实
    // 现一个函数，如果两个链表相交，请返回相交的 第一个节点。如果不相交，返
    // 回null。
    // 如果两个链表的长度之和为N，时间复杂度请达到O(N)，额外空间复杂度请达到O(1)。
    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        // 两个链表都无环
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        // 两个都有环
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        // 一个有环一个无环，一定不相交
        return null;
    }

    // 如何判断一个链表是否有环，并找到第一个入环节点
    // 第一种方法，使用哈希表，遍历链表，如果遇到某个节点，哈希表中已经存在，说明有环，返回这个节点。过于简单，不实现了。
    // 第二种方法，使用快慢指针，快指针一次走两步，慢指针一次走一步，如果快指针走到null，说明没有环；
    // 如果快指针和慢指针相遇，快指针回到head，快慢指针一次走一步，再次相遇的节点就是第一个入环节点。
    // 第二种方法的证明：设环外长度为a，相遇时的位置距离环的入口为b，环剩余部分的长度为c，
    // 因为快指针是慢指针速度的两倍，易推倒出相遇时一定是慢指针在环中走了小于等于两圈。
    // 首先假设慢指针在第一圈与快指针相遇，那么此时快指针走过的距离 S1 = a + (n) * (b + c) + b
    // 慢指针走过的距离 S2 = a + b，由于S1 = 2 * S2，所以 a + (n) * (b + c) + b = 2 * (a + b)
    // 可以得到 a = (n - 1) * (b + c) + c，因此，当快指针回到head，快慢指针一次走一步，走a步之后，到达入环节点，
    // 慢指针一定也在入环节点。
    // 其次假设慢指针在第二圈与快指针相遇，那么此时快指针走过的距离 S1 = a + (n) * (b + c) + b
    // 慢指针走过的距离 S2 = a + b + c + b，由于S1 = 2 * S2，所以 a + (n) * (b + c) + b = 2 * (a + b + c + b)
    // 可以得出 a = (n - 3) * (b + c) + c，因此，当快指针回到head，快慢指针一次走一步，走a步之后，仍旧在入环节点。
    // 快慢指针方式：时间复杂度O(N)，额外空间复杂度O(1)
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        // 快慢指针。其实这里的head.next是为了让快指针先走一步，否则一开始他们就相遇了。
        Node fast = head.next.next;
        Node slow = head.next;
        while (fast != slow) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = head;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    // 如果两个链表都无环，返回第一个相交节点，如果不相交，则返回null
    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        // 长度差值
        int difference = 0;
        while (cur1.next != null) {
            difference++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            difference--;
            cur2 = cur2.next;
        }
        // 如果最后一个节点不相等，说明不相交
        if (cur1 != cur2) {
            return null;
        }
        // cur1指向长链表的头节点，cur2指向短链表的头节点
        cur1 = difference > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        difference = Math.abs(difference);
        while (difference != 0) {
            difference--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    // 两个链表都有环的情况
    // 有三种情况，分别是两个链表不相交，两个链表在环外相交，两个链表在环内相交
    // 两个链表不相交，只要其中一个转一圈，遇不到另一个loop，就说明不相交。
    // 两个链表环外相交，等同于两个无环链表相交的情况，把loop节点当成链表末尾处理即可。
    // 两个链表环内相交，一个往下转，一个不动，如果相遇返回即可。
    private static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1;
        Node cur2;
        // 两个链表入环节点相同，即环外相交
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            int difference = 0;
            while (cur1.next != loop1) {
                difference++;
                cur1 = cur1.next;
            }
            while (cur2.next != loop2) {
                difference--;
                cur2 = cur2.next;
            }
            cur1 = difference > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            difference = Math.abs(difference);
            while (difference != 0) {
                difference--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            // 两个链表入环节点不同，即环内相交
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return loop1; // 返回任意一个loop即可
                }
                cur1 = cur1.next;
            }
            // 不相交
            return null;
        }
    }
}
