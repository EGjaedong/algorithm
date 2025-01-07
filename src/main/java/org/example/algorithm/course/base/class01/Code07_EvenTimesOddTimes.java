package org.example.algorithm.course.base.class01;

public class Code07_EvenTimesOddTimes {

    public static void printOddTimesNum1(int[] arr) {
        int eor = 0;
        for (int cur : arr) {
            eor ^= cur;
        }
        System.out.println(eor);
    }

    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        int onlyOne = 0;
        for (int cur : arr) {
            eor ^= cur;
        }
        // eor = a ^ b
        // eor != 0
        // eor 必定有一位不为0
        int rightOne = eor & (~eor + 1); // 提取出最右侧的1
        for (int cur : arr) {
            if ((cur & rightOne) == 1) {
                onlyOne ^= cur;
            }
        }
        System.out.println(onlyOne + " " + (eor ^ onlyOne));
    }
}
