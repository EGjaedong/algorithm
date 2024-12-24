package org.example.algorithm.class02;

public class Code07_HeapSort {

    // 用一个数组来表示堆，本质上就是一个完全二叉树
    // 根结点是0位置，左孩子是2*i+1，右孩子是2*i+2，父结点是(i-1)/2。
    // 堆的大小是heapSize，堆的有效部分是[0, heapSize)。
    // 另外，优先级队列，本质上就是一个堆，我们认为堆顶是优先级最高的堆。
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 先把数组构建成一个大根堆，则0位置上的数是最大的
        for (int i = 0; i < arr.length; i++) { // O(N)
            headInsert(arr, i); // O(logN)
        }
        // 求出当前堆的大小
        int heapSize = arr.length;
        while (heapSize > 0) { // O(N)
            // 将当前heapSize的数组构建成一个大根堆，第一轮循环时，本身就是一个大根堆，所以第一次heapify本质上不改变数组结构。
            // 但是从第二次开始，0位置上的数就是上一个循环中交换过来的堆中的最后一个数，
            // 不确定当前这个堆还是不是大根堆，因此需要重新进行heapify操作，构建一次大根堆，
            heapify(arr, 0, heapSize); // O(logN)
            // 交换0位置和heapSize-1位置上的数，此时heapSize—1上的数就是当前这个堆中最大的数。
            // 也就是这个数已经排好序了，不用再参与排序了。
            // 换句话说就是最大的那个数从堆中去掉了，--size也是为了更新heapSize。
            swap(arr, 0, --heapSize); // O(1)
        }
    }

    public static void headInsert(int[] arr, int index) {
        // 维持一个大根堆，保证父节点始终大于子节点
        while (arr[index] > arr[index - 1] / 2) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index + 2 + 1;
        // 当有左孩子时，比较大小
        while (left < heapSize) {
            // 找到左孩子和右孩子中较大的一个的下标，如果没有右孩子，则取左孩子下标
            int latest = left + 1 < heapSize ? (arr[left] > arr[left + 1] ? left : left + 1) : left;
            // 父节点和子节点比较，较大的值下标给latest
            latest = arr[latest] > arr[index] ? latest : index;
            // 如果当前比较（父节点，左右两个孩子）中，最大的那个是父节点，则
            if (arr[index] == arr[latest]) {
                break;
            } else {
                // 否则交换父节点和较大孩子的值
                swap(arr, index, latest);
                // 此时，index继续向下判断，指向刚才交换完的位置
                index = latest;
                // 更新当前index的左孩子的位置
                left = index * 2 + 1;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
