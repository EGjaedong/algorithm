package org.example.algorithm.exercise.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem46_Permutations {
    // 力扣（LeetCode）46. 全排列
    // https://leetcode-cn.com/problems/permutations/
    // 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。

    public static List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }

        List<List<Integer>> result = new ArrayList<>();
        progress(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static void progress(int[] nums, int index, List<Integer> currentPermute,
            List<List<Integer>> result) {
        if (index == nums.length) {
            result.add(currentPermute);
            return;
        }

        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);
            ArrayList<Integer> newPermute = new ArrayList<>(currentPermute);
            newPermute.add(nums[index]);
            progress(nums, index + 1, newPermute, result);
            swap(nums, index, i);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> result = permute(nums);
        for (List<Integer> permute : result) {
            System.out.println(permute);
        }
    }
}
