package org.example.algorithm.exercise.recursion_and_dp;

import java.util.HashMap;
import java.util.Map;

public class CanIWin {
    // 力扣（LeetCode）464，我能赢吗
    // https://leetcode-cn.com/problems/can-i-win/

    Map<Integer, Boolean> memo = new HashMap<>();

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if ((1 + maxChoosableInteger) * (maxChoosableInteger) / 2 < desiredTotal) {
            return false;
        }
        return dfs(maxChoosableInteger, 0, desiredTotal, 0);
    }

    public boolean dfs(int maxChoosableInteger, int usedNumbers, int desiredTotal, int currentTotal) {
        if (!memo.containsKey(usedNumbers)) {
            boolean res = false;
            for (int i = 0; i < maxChoosableInteger; i++) {
                if (((usedNumbers >> i) & 1) == 0) {
                    if (i + 1 + currentTotal >= desiredTotal) {
                        res = true;
                        break;
                    }
                    if (!dfs(maxChoosableInteger, usedNumbers | (1 << i), desiredTotal, currentTotal + i + 1)) {
                        res = true;
                        break;
                    }
                }
            }
            memo.put(usedNumbers, res);
        }
        return memo.get(usedNumbers);
    }

    public static void main(String[] args) {
        int maxChoosableInteger = 10;
        int desiredTotal = 11;
        System.out.println(new CanIWin().canIWin(maxChoosableInteger, desiredTotal));
    }
}
