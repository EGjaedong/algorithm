package org.example.algorithm.class05;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Code03_TreeMaxWidth {

    public static class Node {

        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 宽度优先遍历
    // 准备一个队列，先将头节点加入队列，弹出时打印，然后先左再右近队列。
    // 循环弹出队列，直到队列为空
    public static void bfs(Node head) {
        System.out.print("BFS: ");
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.print(cur.value + " ");
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        System.out.println();
    }

    // 利用宽度优先遍历求二叉树的最大宽度
    // 利用Map，记录当前Node的层级是什么
    // 统计每一个层级的节点数，取最大值。
    // 本质就是在BFS过程中，借助Map记录每一个节点所在的层级，然后统计每一个层级的节点数
    public static int getMaxWidth1(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Map<Node, Integer> nodeLevelMap = new HashMap<>();
        nodeLevelMap.put(head, 1);
        int currentLevel = 1; // 当前统计的层级
        int currentLevelNodes = 0; // 当前统计层级中的节点数
        int maxWidth = Integer.MIN_VALUE; // 最大宽度
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curNodeLevel = nodeLevelMap.get(cur);
            if (curNodeLevel == currentLevel) {
                currentLevelNodes++;
            } else {
                // 如果不等于当前正在统计的层级，说明当前已经进入到当前层级的子节点层级，记录当前最大值
                maxWidth = Math.max(maxWidth, currentLevelNodes);
                currentLevel++;
                currentLevelNodes = 0;
            }
            if (cur.left != null) {
                queue.add(cur.left);
                nodeLevelMap.put(cur.left, curNodeLevel + 1);
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nodeLevelMap.put(cur.right, curNodeLevel + 1);
            }
        }
        return maxWidth;
    }

    // 利用宽度优先遍历求二叉树的最大宽度。
    // 借助队列记录当前访问到哪个节点，下一个节点是哪个。
    // 不使用Map，直接在BFS过程中统计每一个层级的节点数。
    // 需要有指针记录当前层级的最右节点，下一层最右侧节点，以及当前层数节点个数。
    // 每次弹出一个节点，左右子节点分别进队列。每次进队列的节点，都将下一层的最右节点指针指向进队列的节点。
    // 如果当前节点是本层的最后一个节点，则更新最大宽度，将下一层最右侧节点设置为null，当前层数节点个数清零。
    // 循环直到队列为空。
    // 不算最左侧和最右侧节点之间的null节点。
    // 如果需要计算null节点，可以在每次进队列的时候，要判断当前null节点是不是最左侧或者最右侧的节点，如果不是，则进队列。
    // 出队列的时候统计数据，需要判断当前是不是null节点，如果是，则不进行左右子节点进队列的操作。
    public static int getMaxWith2(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curLevelEnd = head;
        Node nextLevelEnd = head;
        int currentLevelNodes = 0;
        int maxWidth = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            currentLevelNodes++;
            if (cur.left != null) {
                queue.add(cur.left);
                nextLevelEnd = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextLevelEnd = cur.right;
            }
            if (curLevelEnd == cur) {
                maxWidth = Math.max(maxWidth, currentLevelNodes);
                currentLevelNodes = 0;
                curLevelEnd = nextLevelEnd;
                nextLevelEnd = null;
            }
        }
        return maxWidth;
    }
}
