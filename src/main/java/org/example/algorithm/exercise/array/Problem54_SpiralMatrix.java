package org.example.algorithm.exercise.array;

import java.util.ArrayList;
import java.util.List;

public class Problem54_SpiralMatrix {
    // 54. 螺旋矩阵
    // https://leetcode-cn.com/problems/spiral-matrix/
    // 给你一个 m 行 n 列的矩阵 matrix ，请按照顺时针螺旋顺序，返回矩阵中的所有元素

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

    public List<Integer> spiralOrder(int[][] matrix) {
        Direction direction = Direction.RIGHT;
        int topEdge = 0;
        int bottomEdge = matrix.length - 1;
        int leftEdge = 0;
        int rightEdge = matrix[0].length - 1;
        int total = matrix.length * matrix[0].length;
        int cur = 0;
        List<Integer> result = new ArrayList<>();
        while (cur < total) {
            switch (direction) {
                case RIGHT:
                    for (int i = leftEdge; i <= rightEdge; i++) {
                        result.add(matrix[topEdge][i]);
                        cur++;
                    }
                    topEdge++;
                    direction = direction.next();
                    break;
                case DOWN:
                    for (int i = topEdge; i <= bottomEdge; i++) {
                        result.add(matrix[i][rightEdge]);
                        cur++;
                    }
                    rightEdge--;
                    direction = direction.next();
                    break;
                case LEFT:
                    for (int i = rightEdge; i >= leftEdge; i--) {
                        result.add(matrix[bottomEdge][i]);
                        cur++;
                    }
                    bottomEdge--;
                    direction = direction.next();
                    break;
                case UP:
                    for (int i = bottomEdge; i >= topEdge; i--) {
                        result.add(matrix[i][leftEdge]);
                        cur++;
                    }
                    leftEdge++;
                    direction = direction.next();
                    break;
                default:
                    break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> result = new Problem54_SpiralMatrix().spiralOrder(
                new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        System.out.println(result);
    }
}
