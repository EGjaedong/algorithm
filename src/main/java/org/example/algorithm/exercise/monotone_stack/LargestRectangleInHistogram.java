package org.example.algorithm.exercise.monotone_stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleInHistogram {
    // 力扣84. 柱状图中最大的矩形
    // 三种解法：
    // 1. 暴力解法：枚举所有的矩形，找到最大的矩形
    // 2. 优化暴力解法（预处理左右边界）
    // 3. 单调栈解法：找到每个柱子的左右边界，然后计算矩形面积
    // 单调栈解法是最优的，思路：
    // 1. 维护一个单调递增栈，栈中存放的是柱子的下标
    // 2. 遍历每个柱子，如果当前柱子的高度大于栈顶柱子的高度，则入栈
    // 3. 如果当前柱子的高度小于栈顶柱子的高度，则出栈，计算出栈柱子的面积
    // 4. 当前柱子索引入栈
    // 5. 遍历结束后，栈中可能还有柱子，需要继续出栈计算面积，模拟一个高度为0的柱子，重复3的操作，直到栈为空

    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        if (heights.length == 1) {
            return heights[0];
        }
        int n = heights.length;
        int maxArea = 0;
        Deque<Integer> monoStack = new ArrayDeque<>();
        for (int i = 0; i <= n; i++) {
            int currentHeight = i == n ? 0 : heights[i];
            while (!monoStack.isEmpty() && currentHeight < heights[monoStack.peek()]) {
                int height = heights[monoStack.pop()];
                int width = monoStack.isEmpty() ? i : i - 1 - monoStack.peek();
                maxArea = Math.max(maxArea, height * width);
            }
            monoStack.push(i);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(heights));
    }
}
