package org.example.algorithm.exercise.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView {
    // 力扣（LeetCode）199. 二叉树的右视图
    // 给定一个二叉树的根节点root，想象自己站在树的右侧，按照从顶部到底部的顺序，
    // 示例1：root = [1,2,3,null,5,null,4]
    // 输出：[1,3,4]

    // 示例2: root = [1,2,3,4,null,null,null,5]
    // 输出：[1,3,4,5]

    // 示例3: root = [1,null,3]
    // 输出：[1,3]

    // 示例4: root = []
    // 输出：[]

    // 思路：BFS，取每一层最后一个节点，
    // 和层序遍历打印不同，这个BFS要一次从队列中弹出一整层的节点，取最后一个节点，然后再把下一层的节点加入队列，循环直到队列为空

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        if (root.left == null && root.right == null) {
            return List.of(root.val);
        }
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        queue.offer(root);
        List<TreeNode> currentLevelNode = new ArrayList<>();
        while (!queue.isEmpty()) {
            currentLevelNode.clear();
            while (!queue.isEmpty()) {
                currentLevelNode.add(queue.poll());
            }
            if (!currentLevelNode.isEmpty()) {
                result.add(currentLevelNode.getLast().val);
                // 把下一层的节点加入队列
                for (TreeNode node : currentLevelNode) {
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        BinaryTreeRightSideView binaryTreeRightSideView = new BinaryTreeRightSideView();
        TreeNode root = binaryTreeRightSideView.new TreeNode(1);
        TreeNode node2 = binaryTreeRightSideView.new TreeNode(2);
        TreeNode node3 = binaryTreeRightSideView.new TreeNode(3);
        TreeNode node4 = binaryTreeRightSideView.new TreeNode(4);
        TreeNode node5 = binaryTreeRightSideView.new TreeNode(5);
        root.left = node2;
        root.right = node3;
        node2.right = node5;
        node3.right = node4;
        List<Integer> result = binaryTreeRightSideView.rightSideView(root);
        System.out.println(result);
    }

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
