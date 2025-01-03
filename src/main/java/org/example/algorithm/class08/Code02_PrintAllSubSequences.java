package org.example.algorithm.class08;

import java.util.ArrayList;
import java.util.List;

public class Code02_PrintAllSubSequences {

    // 打印一个字符串的全部子序列，包括空字符串
    public static void printAllSubSequences2(String str) {
        char[] charArray = str.toCharArray();
        process(charArray, 0);
    }

    // 节省空间的做法
    public static void process(char[] charArray, int i) {
        if (i == charArray.length) {
            System.out.println(String.valueOf(charArray));
            return;
        }
        // 两种情况，一种要当前字符，一种不要当前字符
        process(charArray, i + 1);
        char temp = charArray[i];
        charArray[i] = 0; // 让当前位置等于空字符
        process(charArray, i + 1);
        charArray[i] = temp; // 恢复原状
    }

    public static void printAllSubSequences(String str) {
        char[] charArray = str.toCharArray();
        process(charArray, 0, new ArrayList<>());
    }

    private static void process(char[] charArray, int i, List<Character> characters) {
        if (i == charArray.length) {
            printList(characters);
            return;
        }
        // 两种情况，要当前字符，不要当前字符
        List<Character> resKeep = copyList(characters);
        resKeep.add(charArray[i]);
        process(charArray, i + 1, resKeep);
        List<Character> resNoInclude = copyList(characters);
        process(charArray, i + 1, resNoInclude);
    }

    private static List<Character> copyList(List<Character> characters) {
        return new ArrayList<>(characters);
    }

    private static void printList(List<Character> characters) {
        System.out.println(characters);
    }

    public static void main(String[] args) {
        printAllSubSequences("abc");
        System.out.println("======");
        printAllSubSequences2("abc");
    }
}
