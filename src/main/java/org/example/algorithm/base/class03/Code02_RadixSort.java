package org.example.algorithm.base.class03;

public class Code02_RadixSort {
    // 基数排序，桶排序的另一种方式。
    // 分析当前序列进制，当前序列的数是多少进制，就准备多少个桶，十进制就准备十个桶。
    // 找到序列中最多的位数，将其他位数不足的数高位补0，然后从低位到高位，依次对每一位进行桶排序。
    // 例如：[013, 017, 035, 028, 072, 101]
    // 第一次按照个位排序，将数字放入桶中，如下：
    // ====
    // 数字：空  101  072  013  空  035  空   017  028  空
    // 桶号：0    1    2    3   4   5    6    7    8   9
    // ====
    // 然后倒出来，得到一个个位数排好序的序列: [101, 072, 013, 035, 017, 028]
    // 接下来以十位数为基准，开始放入桶中，然后倒出，每个桶都是队列先进先出，得到结果：[101, 013, 017, 028, 035, 072]，
    // 显然，十位数也排好序了，并且，相同的十位数数字，个位数也是有序的，因为上一轮中个位已经排序完成
    // 接下来以百位数为基准，开始放入桶中，然后倒出，得到结果：[013, 017, 028, 035, 072, 101]

    // only for no-negative value
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }

    public static int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int j : arr) {
            max = Math.max(j, max);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }

    // 最后一个参数是位数
    public static void radixSort(int[] arr, int begin, int end, int digit) {
        final int radix = 10;
        int i;
        int j;
        // 有多少个数准备多少个辅助空间
        int[] bucket = new int[end - begin + 1];
        // 遍历所有位数
        for (int d = 1; d <= digit; d++) {
            // 统计每一位上每个数字的词频
            int[] count = new int[radix]; // 开辟十个空间，用来统计词频
            // count[0] 当前位（d位）是0的数字有多少个
            // count[1] 当前位（d位）是0和1的数字有多少个
            // count[2] 当前位（d位）是0，1和2的数字有多少个
            // count[i] 当前位（d位）是0～i的数字有多少个
            for (i = begin; i <= end; i++) {
                // j是count的索引，用来表示当前统计的是哪个数字
                j = getDigit(arr[i], d);
                count[j]++;
            }
            // 把词频统计转换成原序列中当前位数数字笑语等于当前下标的个数
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }
            // 从右往左遍历原序列，根据当前位数的数字，把原序列中的数字放到help数组中
            for (i = end; i >= begin; i--) {
                j = getDigit(arr[i], d);
                // j - 1位置就是当前数字在help数组中的位置
                bucket[count[j] - 1] = arr[i];
                count[j]--;
            }
            // 把当前桶里的数字倒回原序列，相当于维持了本轮排序
            for (i = begin, j = 0; i <= end; i++, j++) {
                arr[i] = bucket[j];
            }
        }
    }

    public static int getDigit(int x, int d) {
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }
}
