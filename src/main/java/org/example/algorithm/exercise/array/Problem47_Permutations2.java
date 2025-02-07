package org.example.algorithm.exercise.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Problem47_Permutations2 {
    // 力扣（LeetCode）47. 全排列 II
    // https://leetcode-cn.com/problems/permutations-ii/
    // 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。

    private boolean[] visited;

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }

        List<List<Integer>> result = new ArrayList<>();
        visited = new boolean[nums.length];
        Arrays.sort(nums);
        progress(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void progress(int[] nums, int index, List<Integer> currentPermute,
            List<List<Integer>> result) {
        if (index == nums.length) {
            result.add(new ArrayList<>(currentPermute));
            return;
        }

        for (int i = 0; i < nums.length; ++i) {
            if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) {
                continue;
            }
            currentPermute.add(nums[i]);
            visited[i] = true;
            progress(nums, index + 1, currentPermute, result);
            visited[i] = false;
            currentPermute.remove(index);
        }
    }


    public static void main(String[] args) {
        int[] nums = new int[]{2, 2, 1, 1};
        List<List<Integer>> result = new Problem47_Permutations2().permute(nums);
        for (List<Integer> permute : result) {
            System.out.println(permute);
        }
    }
}
