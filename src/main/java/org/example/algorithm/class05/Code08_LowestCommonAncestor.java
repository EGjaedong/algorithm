package org.example.algorithm.class05;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Code08_LowestCommonAncestor {
    // 最近公共祖先

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    // 先遍历一遍，用一个Map构建所有节点和父节点的关系
    // 然后从o1节点开始，一直往上找，把所有的父节点都放到一个set里
    // 然后遍历o2节点，一直往上找，如果发现有节点在set里，那么这个节点就是最近公共祖先
    // 前提是，o1和o2都在head为头的树上
    public static Node lowestAncestor(Node head, Node o1, Node o2) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> fatherMap = new HashMap<>();
        fatherMap.put(head, head);
        traversalTree(head, fatherMap);
        Set<Node> set = new HashSet<>();
        Node cur = o1;
        while (cur != fatherMap.get(cur)) {
            set.add(cur);
            cur = fatherMap.get(cur);
        }
        cur = o2;
        while (cur != fatherMap.get(cur)) {
            if (set.contains(cur)) {
                return cur;
            } else {
                cur = fatherMap.get(cur);
            }
        }
        return head;
    }

    public static void traversalTree(Node head, Map<Node, Node> fatherMap) {
        if (head == null) {
            return;
        }
        if (head.left != null) {
            fatherMap.put(head.left, head);
        }
        if (head.right != null) {
            fatherMap.put(head.right, head);
        }
        traversalTree(head.left, fatherMap);
        traversalTree(head.right, fatherMap);
    }

    // 递归方式找两个节点的最近公共祖先
    // 有两种情况：
    // 1、o1在o2为头的子树上，或者o2在o1为头的子树上，那么他们最近公共祖先就是o1或者o2
    // 2、o1和o2不在彼此的子树上，那他们必然有一个公共的祖先，从这个节点向下递归，一定能找到o1和o2，找到后向上汇报。
    // 用递归套路来思考这个问题：一个节点问他下面的子节点要这两个节点的最近公共祖先，如果o1和o2在这个节点的子树上，
    // 那么子树就能找到，并返回o1和o2节点，让他们的公共祖先自己判断是不是返回自己；
    // 如果o1和o2不再某一个子树上，那么这个子树直接告诉上级节点，跟我没关系，我返回null。
    public static Node lowestAncestor2(Node head, Node o1, Node o2) {
        // 递归到了空节点，或者递归找到了o1或者o2，那么不继续往下递归了。
        // 这里涵盖了上述的两种情况，如果是情况1，那么o1或者o2就是最近公共祖先；
        // 如果是情况2，那么o1和o2不在同一个子树上，也没必要继续在这个子树上向下找了
        if (head == null || head == o1 || head == o2) {
            return head;
        }
        // 下面继续处理情况2.
        // 问左右子树，它有没有找到o1或者o2，如果找到了，那么就把o1或者o2返回回来，让当前节点判断它自身是不是公共祖先
        Node left = lowestAncestor2(head.left, o1, o2);
        Node right = lowestAncestor2(head.right, o1, o2);
        // 如果左右都返回了，那么说明o1和o2分别在左右子树上，那么当前节点就是最近公共祖先
        if (left != null && right != null) {
            return head;
        }
        // 上面都不满足，说明到这个节点上，要么找到了一个o1或者o2，要么没有找到，那么就返回找到的那个节点。
        // 也有可能都没找到，命中了：跟我没关系，我返回null。
        return left != null ? left : right;
    }
}
