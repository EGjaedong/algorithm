package org.example.algorithm.class02;

public class Code02_SmallSum {

    // 小和问题
    // 在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。求一个数组 的小和。
    // 例子:[1,3,4,2,5] 1左边比1小的数，没有; 3左边比3小的数，1; 4左边比4小的数，1、3;
    // 2左边比2小的数，1; 5左边比5小的数，1、3、4、2;所以小和为1+1+3+1+1+3+4+2=16。
    // 反转一下思路，从右边看有多少个小和，等同于从左边看，右边有多少个数比当前的大，这样就可以用归并排序的思路来解决
    // 上例中，从左往右看，1右边比1大的数，有4个，则产生4个1的小和；3右边比3大的数有2个，则产生2个3的小和；
    // 4右边比4大的数有1个，则产生1个4的小和；2右边比2大的数有1个，则产生1个2的小和；
    // 5右边比5大的数有0个，则产生0个5的小和；所以总共有4+6+4+2+0=16的小和。
    // 求右边有多少个数比当前数大，可以借助归并排序时merge过程比较右侧比左侧大的数的个数。
    // 整体复杂度为O（NlogN），和归并排序一样。
    // 同理，还有逆序对问题，求一个数组中有多少个逆序对，也可以用归并排序的思路来解决。
    // 逆序对问题描述：在一个数组中，左边的数如果比右边的数大，则这两个数构成一个逆序对，求一个数组中有多少个逆序对。
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    // 求小和的过程，每一次merge，都是左侧数列中的每一个数和右侧数列中的所有数做对比来求小和
    public static int merge(int[] arr, int l, int mid, int r) {
        int res = 0;
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
            // 不通过遍历，而是通过下表计算的方式来找有侧当前有多少个数比左侧当前的数要大。
            // 因为每一次merge的过程，是左右两个有序数列的整体合并过程。
            res += arr[p1] > arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            // 当左组的数和有组的数一样大的时候，要先拷贝右组的数，这样能通过下表计算求出有多少个数比左侧当前的数要大
            help[i++] = arr[p1] < arr[p2] ? arr[p2++] : arr[p1++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return res;
    }
}
