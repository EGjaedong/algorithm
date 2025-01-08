package org.example.algorithm.exercise.graph;

public class MatrixArrGraphDFS {

    public static void main(String[] args) {
        int[][] graph = new int[][]{
                {0, 1, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 1, 0}
        };

        boolean[][] visited = new boolean[graph.length][graph[0].length];
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                dfs(graph, visited, i, j);
            }
        }
    }

    // 二维数组实现图的广度优先遍历，递归实现
    public static void dfs(int[][] matrix, boolean[][] visited, int i, int j) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || visited[i][j]) {
            return;
        }
        int[][] direction = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        visited[i][j] = true;
        System.out.println("(" + i + ", " + j + ") -> " + " value: " + matrix[i][j]);
        // 遍历所有方向
        for (int[] dir : direction) {
            int nextI = i + dir[0];
            int nextJ = j + dir[1];
            dfs(matrix, visited, nextI, nextJ);
        }
    }
}
