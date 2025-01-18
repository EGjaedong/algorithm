package org.example.algorithm.exercise.tree;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    // 力扣（LeetCode）105. 从前序与中序遍历序列构造二叉树
    // 根据一棵树的前序遍历与中序遍历构造二叉树。
    // 示例1：preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
    // 返回如下的二叉树：
    //     3
    //    / \
    //   9  20
    //     /  \
    //    15   7

    // 示例2：preorder = [-1], inorder = [-1]
    // 返回如下的二叉树：
    //   -1


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

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> inOrderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inOrderIndexMap.put(inorder[i], i);
        }
        return buildTreeHelper(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1,
                inOrderIndexMap);
    }

    private TreeNode buildTreeHelper(int[] preorder, int[] inorder, int preStart, int preEnd,
            int inStart, int inEnd, Map<Integer, Integer> inOrderIndexMap) {
        // base case
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        // root 节点对应的值就是前序遍历数组的第一个元素
        int rootVal = preorder[preStart];
        // rootVal 在中序遍历数组中的索引
        int rootIndex = inOrderIndexMap.get(rootVal);
        // 构建根节点
        TreeNode root = new TreeNode(rootVal);
        // 得到左子树中的节点数目
        int leftSize = rootIndex - inStart;
        // 构建左子树
        root.left = buildTreeHelper(preorder, inorder, preStart + 1, preStart + leftSize, inStart, inEnd - 1, inOrderIndexMap);
        // 构建右子树
        root.right = buildTreeHelper(preorder, inorder, preStart + leftSize + 1, preEnd, rootIndex + 1, inEnd, inOrderIndexMap);
        return root;
    }

    public static void main(String[] args) {
        ConstructBinaryTreeFromPreorderAndInorderTraversal constructBinaryTreeFromPreorderAndInorderTraversal = new ConstructBinaryTreeFromPreorderAndInorderTraversal();
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        TreeNode root = constructBinaryTreeFromPreorderAndInorderTraversal.buildTree(preorder, inorder);
        System.out.println(root);
    }
}
