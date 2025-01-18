package org.example.algorithm.exercise.recursion_and_dp;

public class NumberOfIslands {
    // 200. Number of Islands
    // 求岛屿数量
    // 给定一个二位数组，1代表陆地，0代表水，求岛屿的数量
    // DFS来解决，遍历二位数组，对于每一个1，进行DFS，把上下左右相邻的1都变成0，并且数量+1

    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int row = grid.length;
        int column = grid[0].length;
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    progress(grid, i, j);
                }
            }
        }
        return count;
    }

    public static void progress(char[][] grid, int row, int right) {
        if (row < 0 || row >= grid.length ||
            right < 0 || right >= grid[0].length ||
            grid[row][right] == '0') {
            return;
        }
        grid[row][right] = '0';
        progress(grid, row - 1, right);
        progress(grid, row + 1, right);
        progress(grid, row, right - 1);
        progress(grid, row, right + 1);
    }

    public static void main(String[] args) {
        char[][] grid = new char[][] {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };
        System.out.println(numIslands(grid));
    }
}
