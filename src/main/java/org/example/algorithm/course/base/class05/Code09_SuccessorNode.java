package org.example.algorithm.course.base.class05;

public class Code09_SuccessorNode {

    // 在二叉树中找到一个节点的后继节点
    //【题目】 现在有一种新的二叉树节点类型如下:
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;
        public Node(int val) {
            value = val;
        }
    }
    //该结构比普通二叉树节点结构多了一个指向父节点的parent指针。
    //假设有一棵Node类型的节点组成的二叉树，树中每个节点的parent指针都正确地指向自己的父节点，头节
    //点的parent指向null。
    //只给一个在二叉树中的某个节点node，请实现返回node的后继节点的函数。
    //在二叉树的中序遍历的序列中， node的下一个节点叫作node的后继节点。

    // 有两种情况：
    // 1、当前节点node有右子树，node的后记节点是右子树的最左节点
    // 2、node没有右子树，node的后继节点就需要往上找，找到它的一个父节点是左孩子，
    // 那么node的后继节点就是这个父节点的父节点
    public static Node getSuccessorNode(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            return getLeftMost(node.right);
        } else {
            Node parent = node.parent;
            // 如果parent没到头节点，或者是node还是parent的右孩子
            while (parent != null && parent.left != node) {
                node = parent;
                parent = node.parent;
            }
            // 没进入循环，要么parent到了头节点，要么node是parent的左孩子
            return parent;
        }
    }

    public static Node getLeftMost(Node node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
