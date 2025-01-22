package org.example.od;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Code03 {

    public static void main(String[] args) {
        String input = "()abcdefgAC(a)(Ab)(C)";
        String res = simplify2(input);
        if (res == null || res.length() == 0) {
            System.out.println("0");
        } else {
            System.out.println(res);
        }
    }

    public static String simplify2(String str) {
        // 搞一个set，保存括号里的字符
        Set<Character> set = new HashSet<>();
        // 保存括号外的字符，跟之前的方案里边的remainingStr是一样
        StringBuilder builder = new StringBuilder();
        // 这个用来保存括号里边的字符，临时的
        LinkedList<Character> queue = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            // 如果当前是右括号
            if (str.charAt(i) == ')') {
                // 把之前的数据搞到set里
                // 如果里面有数据，有数据指的是括号里的字符数大于1
                if (queue.size() - 1 > 1) {
                    // 把括号里的数据搞到set里
                    while (!queue.isEmpty()) {
                        Character tempChar = queue.pollLast();
                        if (Character.isLetter(tempChar)) {
                            if (Character.isUpperCase(tempChar)) {
                                set.add(Character.toLowerCase(tempChar));
                            }
                            set.add(tempChar);
                        }
                    }
                }
                queue.clear();
                continue;
            }
            // 处理当前的数据
            if (str.charAt(i) == '(' || !queue.isEmpty()) {
                queue.addLast(str.charAt(i));
                continue;
            }
            // 数据加进去
            builder.append(str.charAt(i));
        }
        if (builder.length() == 0) {
            return "0";
        }
        // 如果没有等效字符，直接返回
        if (set.isEmpty()) {
            return builder.toString();
        }
        // 替换
        return replace(builder, set);
    }

    private static String replace(StringBuilder builder, Set<Character> set) {
        ArrayList<Character> commonChars = new ArrayList<>(set);
        commonChars.sort(Comparator.comparingInt(o -> o)); // 排个序，后面取第一个
        char[] charArray = builder.toString().toCharArray();
        StringBuilder res = new StringBuilder();
        for (char current : charArray) {
            if (commonChars.contains(current)) {
                res.append(commonChars.get(0));
            } else {
                res.append(current);
            }
        }
        return res.toString();
    }




    // 这个方式通过率不高，29.73%
    public static String simplify(String str) {
        // 先遍历一遍，把所有的等效集合找出来
        List<Set<Character>> setList = new ArrayList<>();
        char[] charArray = str.toCharArray();
        // 记录括号的位置，后面需要把这个括号和括号内部的数据删除
        char[] remainingStr = findCommonChars(charArray, setList);
        Set<String> stringSet = combineCommonChars(setList);
        return replaceString(remainingStr, stringSet);
    }

    private static String replaceString(char[] remainingStr, Set<String> commonSet) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < remainingStr.length; i++) {
            char current = remainingStr[i];
            if (commonSet.size() > 0) {
                for (String s : commonSet) {
                    if (s.contains(String.valueOf(current))) {
                        builder.append(s.charAt(0));
                    } else {
                        builder.append(current);
                    }
                }
            } else {
                builder.append(current);
            }
        }
        return builder.toString();
    }

    private static Set<String> combineCommonChars(List<Set<Character>> setList) {
        for (int i = 0; i < setList.size(); i++) {
            Set<Character> currentSet = setList.get(i);
            for (int j = i + 1; j < setList.size(); j++) {
                Set<Character> compareSet = setList.get(j);
                if (hasCommon(currentSet, compareSet)) {
                    currentSet.addAll(compareSet);
                    setList.remove(j);
                }
            }
        }
        Set<String> stringSet = new HashSet<>();
        for (Set<Character> set : setList) {
            Set<Character> tempSet = set.stream().sorted().collect(
                    Collectors.toCollection(LinkedHashSet::new));
            StringBuilder stringBuilder = new StringBuilder();
            for (Character c : tempSet) {
                stringBuilder.append(c);
            }
            stringSet.add(stringBuilder.toString());
        }
        return stringSet;
    }

    private static boolean hasCommon(Set<Character> currentSet, Set<Character> compareSet) {
        for (Character c : currentSet) {
            if (compareSet.contains(c)) {
                return true;
            }
        }
        return false;
    }

    private static char[] findCommonChars(char[] charArray, List<Set<Character>> setList) {
        int left = 0;
        int right;
        // 用来记录当前是否在括号内部
        boolean isIn = false;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < charArray.length; i++) {
            char current = charArray[i];
            if (current == '(') {
                left = i;
                // 后面的字符都在括号内部
                isIn = true;
            } else if (current == ')') {
                right = i;
                // 后面的字符重新审视是否在括号内部
                isIn = false;
                // 如果builder的数据不为空，则加入到集合中
                if (builder.length() > 1) {
                    char[] inChar = builder.toString().toCharArray();
                    Set<Character> set = new TreeSet<>();
                    for (char c : inChar) {
                        set.add(c);
                        if (Character.isUpperCase(c)) {
                            set.add(Character.toLowerCase(c));
                        }
                    }
                    setList.add(set);
                }
                // 考察left和right的差值，如果差值大于1(括号内多于一个字符)，说明括号内部有数据
                for (int k = left; k <= right; k++) {
                    charArray[k] = '*';
                }
                // 重置builder
                builder = new StringBuilder();
            } else {
                // 考察普通字符，如果在括号内部，需要把这个字符加入到括号内部的集合中
                if (isIn) {
                    builder.append(current);
                }
            }
        }
        StringBuilder res = new StringBuilder();
        for (char c : charArray) {
            if (c != '*') {
                res.append(c);
            }
        }
        return res.toString().toCharArray();
    }
}
