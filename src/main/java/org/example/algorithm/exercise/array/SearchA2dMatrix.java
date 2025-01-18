package org.example.algorithm.exercise.array;

public class SearchA2dMatrix {
    // 力扣（LeetCode）74. 搜索二维矩阵
    // 编写一个高效的算法来判断m x n矩阵中，是否存在一个目标值。该矩阵具有如下特性：
    // 每行中的整数从左到右按升序排列。
    // 每行的第一个整数大于前一行的最后一个整数。

    // 思路：先找到目标所在的行，然后在那一行里二分查找

    public boolean searchMatrix(int[][] matrix, int target) {
        for (int[] line : matrix) {
            if (target >= line[0] && target < line[line.length - 1]) {
                return searchLine(line, target);
            }
        }
        return false;
    }

    private boolean searchLine(int[] line, int target) {
        int left = 0;
        int right = line.length - 1;
        int mid = left + ((right - left) >> 1);
        while (left <= right) {
            if (line[mid] == target) {
                return true;
            }
            if (line[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
            mid = left + ((right - left) >> 1);
        }
        return false;
    }

    public static void main(String[] args) {
        SearchA2dMatrix searchA2dMatrix = new SearchA2dMatrix();
        int[][] matrix = new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        System.out.println(searchA2dMatrix.searchMatrix(matrix, 3));
        System.out.println(searchA2dMatrix.searchMatrix(matrix, 13));
    }

}
