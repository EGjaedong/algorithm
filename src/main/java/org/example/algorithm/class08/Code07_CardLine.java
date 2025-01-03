package org.example.algorithm.class08;

public class Code07_CardLine {
    // 给定一个整型数组arr，代表数值不同的纸牌排成一条线。玩家A和玩家B依次拿走每张纸
    // 牌，规定玩家A先拿，玩家B后拿，但是每个玩家每次只能拿走最左或最右的纸牌，玩家A
    // 和玩家B都绝顶聪明。请返回最后获胜者的分数。
    //【举例】
    // arr=[1,2,100,4]。
    // 开始时，玩家A只能拿走1或4。如果开始时玩家A拿走1，则排列变为[2,100,4]，接下来
    // 玩家 B可以拿走2或4，然后继续轮到玩家A...
    // 如果开始时玩家A拿走4，则排列变为[1,2,100]，接下来玩家B可以拿走1或100，然后继
    // 续轮到玩家A...
    // 玩家A作为绝顶聪明的人不会先拿4，因为拿4之后，玩家B将拿走100。所以玩家A会先拿1，
    // 让排列变为[2,100,4]，接下来玩家B不管怎么选，100都会被玩家 A拿走。玩家A会获胜，
    // 分数为101。所以返回101。
    // arr=[1,100,2]。
    // 开始时，玩家A不管拿1还是2，玩家B作为绝顶聪明的人，都会把100拿走。玩家B会获胜，
    // 分数为100。所以返回100。

    // 递归思路：互相套娃
    // 1. 如果arr[i...j]只剩一张牌，那么当前玩家只能拿走这张牌，返回arr[i]，这也是base case。
    // 2. 如果arr[i...j]上不止一张牌，那么当前玩家会选择拿走最左边，和他后手选择的从arr[i+1...j]中拿走的分数最大的那个
    // 或者他拿走arr[j]，和他后手选择的从arr[i...j-1]中拿走的分数最大的那个，求一个最大值就是结果。
    // 3. 如果是arr[i...j]上后手，也就是在second函数中，这个玩家是先手拿走arr[i...j]上的牌，但是要求最小值。
    // 为什么是最小值，因为当前玩家在i...j范围上是后手，那么先手的人会选择让当前玩家拿到最小的分数。

    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(first(arr, 0, arr.length - 1), second(arr, 0, arr.length - 1));
    }

    private static int first(int[] arr, int l, int r) {
        // base case
        if (l == r) {
            return arr[l];
        }
        return Math.max(arr[l] + second(arr, l + 1, r), arr[r] + second(arr, l, r - 1));
    }

    // 进入second函数时，l或者r的牌已经被选择了
    public static int second(int[] arr, int l, int r) {
        // base case
        if (l == r) {
            return 0;
        }
        return Math.min(first(arr, l + 1, r), first(arr, l, r - 1));
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 100, 4};
        System.out.println(win1(arr));
        int[] arr2 = {1, 100, 2};
        System.out.println(win1(arr2));
    }
}
