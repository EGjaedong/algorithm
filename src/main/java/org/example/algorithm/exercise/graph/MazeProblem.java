package org.example.algorithm.exercise.graph;

import java.util.ArrayList;
import java.util.List;

public class MazeProblem {
    // 迷宫问题
    // 一个二维数组，0表示可以走，1表示不可以走，从左上角走到右下角，只能走上下左右四个方向。
    // 输入保证起点和终点一定为空方格，你始终可以找到且能唯一找到一条从起点出发到达终点的可行路径。
    // 求解思路：
    // DFS递归求解，每到一个节点，如果当前节点合法，并且没到底，把这个节点放进path和visited数组中，
    // visited数组用来标记当前哪些节点走过了，防止走回头路。
    // 如果当前路能走，就保留在路径列表里，如果不能走，一直往上退，退的过程中，需要把当前节点从path和visited中移除。

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {0, 1, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 1, 0}
        };
        getPath(matrix);
    }

    private static void getPath(int[][] matrix) {
        List<Node> path = new ArrayList<>();
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        if (!dfs(matrix, visited, 0, 0, path)) {
            System.out.println("No path found.");
        }
        for (Node node : path) {
            System.out.println("(" + node.row + "," + node.column + ")");
        }
    }

    private static boolean dfs(int[][] matrix, boolean[][] visited, int i, int j, List<Node> path) {
        // base case：数组越界或者已经访问过
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || visited[i][j]
                || matrix[i][j] == 1) {
            return false;
        }
        path.add(new Node(i, j));
        visited[i][j] = true;
        // 到达终点
        if (i == matrix.length - 1 && j == matrix[0].length - 1) {
            return true;
        }
        int[][] direction = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : direction) {
            int nextI = i + dir[0];
            int nextJ = j + dir[1];
            // 只有走到最后一个节点，才返回true
            if (dfs(matrix, visited, nextI, nextJ, path)) {
                return true;
            }
        }
        // 其他情况都是false，往回退，退的时候把当前节点从path和visited中移除
        path.remove(path.size() - 1);
        visited[i][j] = false;
        return false;
    }

    public static class Node {

        public int row;
        public int column;

        public Node(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }
}
