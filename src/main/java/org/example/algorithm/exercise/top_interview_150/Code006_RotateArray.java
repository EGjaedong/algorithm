package org.example.algorithm.exercise.top_interview_150;

public class Code006_RotateArray {

    // https://leetcode.cn/problems/rotate-array/description/?envType=study-plan-v2&envId=top-interview-150
    // 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
    // 示例 1:
    // 输入: nums = [1,2,3,4,5,6,7], k = 3
    // 输出: [5,6,7,1,2,3,4]
    // 解释:
    // 向右轮转 1 步: [7,1,2,3,4,5,6]
    // 向右轮转 2 步: [6,7,1,2,3,4,5]
    // 向右轮转 3 步: [5,6,7,1,2,3,4]
    // 示例 2:
    // 输入：nums = [-1,-100,3,99], k = 2
    // 输出：[3,99,-1,-100]
    // 解释:
    // 向右轮转 1 步: [99,-1,-100,3]
    // 向右轮转 2 步: [3,99,-1,-100]

    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while(start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }

    public static void main(String[] args) {

    }
}
