package org.example.algorithm.exercise.array;

public class Problem189_RotateArray {
    // 力扣（LeetCode）189. 旋转数组
    // 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
    // 示例 1:
    // 输入: nums = [1,2,3,4,5,6,7], k = 3
    // 输出：[5,6,7,1,2,3,4]
    // 解释:
    // 向右旋转 1 步: [7,1,2,3,4,5,6]
    // 向右轮转 2 步: [6,7,1,2,3,4,5]
    // 向右轮转 3 步: [5,6,7,1,2,3,4]

    // 示例 2:
    // 输入：nums = [-1,-100,3,99], k = 2
    // 输出：[3,99,-1,-100]
    // 解释:
    // 向右轮转 1 步: [99,-1,-100,3]
    // 向右轮转 2 步: [3,99,-1,-100]

    public void rotate3(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    // 用辅助数组做，但是空间复杂度是O(N)
    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        int round = k % n;
        int left = n - round;
        int index = 0;
        int[] res = new int[n];
        for (int i = left; i < n; i++) {
            res[index++] = nums[i];
        }
        for (int i = 0; i < left; i++) {
            res[index++] = nums[i];
        }
        System.arraycopy(res, 0, nums, 0, n);
    }

    // 这种方式超时了，看起来O(N^2)的时间复杂度会超时。
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int round = k % n;
        // 需要冒泡的子数组的左边界
        int left = n - round;
        int right = n - 1;
        // 把left到right的子数组冒泡到0位置上就可以了。
        bubble(nums, left, right);
    }

    private void bubble(int[] nums, int left, int right) {
        if (left == 0) {
            return;
        }

        while (left > 0) {
            int prev = left - 1;
            while (prev < right) {
                swap(nums, prev, prev + 1);
                prev++;
            }
            left--;
            right--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }

    public static void main(String[] args) {
        Problem189_RotateArray problem189RotateArray = new Problem189_RotateArray();
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        problem189RotateArray.rotate3(nums, 3);
        for (int num : nums) {
            System.out.print(num + " ");
        }

        System.out.println();

        int[] nums2 = new int[]{-1, -100, 3, 99};
        problem189RotateArray.rotate3(nums2, 2);
        for (int num : nums2) {
            System.out.print(num + " ");
        }
    }
}
