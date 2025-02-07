package org.example.algorithm.exercise.array;

import org.example.algorithm.exercise.array.Problem54_SpiralMatrix.Direction;

public class Problem59_SpiralMatrix2 {
    // 59. 螺旋矩阵 II
    // https://leetcode-cn.com/problems/spiral-matrix-ii/
    // 给你一个正整数 n ，生成一个包含 1 到 n^2 所有元素，且元素按顺时针螺旋顺序排列的 n x n 正方形矩阵 matrix 。
    // 示例 1：
    // 输入：n = 3
    // 输出：[[1,2,3],[8,9,4],[7,6,5]]

    public enum Direction {
        RIGHT, DOWN, LEFT, UP;

        public Direction next() {
            return switch (this) {
                case RIGHT -> DOWN;
                case DOWN -> LEFT;
                case LEFT -> UP;
                default -> RIGHT;
            };
        }
    }


    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int topEdge = 0;
        int bottomEdge = n - 1;
        int leftEdge = 0;
        int rightEdge = n - 1;
        int total = n * n;
        int cur = 1;
        Direction direction = Direction.RIGHT;
        while (cur <= total) {
            switch (direction) {
                case RIGHT:
                    for (int i = leftEdge; i <= rightEdge; i++) {
                        matrix[topEdge][i] = cur;
                        cur++;
                    }
                    topEdge++;
                    direction = direction.next();
                    break;
                case DOWN:
                    for (int i = topEdge; i <= bottomEdge; i++) {
                        matrix[i][rightEdge] = cur;
                        cur++;
                    }
                    rightEdge--;
                    direction = direction.next();
                    break;
                case LEFT:
                    for (int i = rightEdge; i >= leftEdge; i--) {
                        matrix[bottomEdge][i] = cur;
                        cur++;
                    }
                    bottomEdge--;
                    direction = direction.next();
                    break;
                case UP:
                    for (int i = bottomEdge; i >= topEdge; i--) {
                        matrix[i][leftEdge] = cur;
                        cur++;
                    }
                    leftEdge++;
                    direction = direction.next();
                    break;
                default:
                    break;
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] result = new Problem59_SpiralMatrix2().generateMatrix(n);
        for (int[] row : result) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

}
