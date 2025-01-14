package org.example.old_dog;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Code01 {
    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = "efghicabiii";

        int length1 = str1.length();
        int length2 = str2.length();

        int res = 0;

        for (int i = 0; i <= length2 - length1; i++) {
            String string2Substring = str2.substring(i, i + length1);
            if (isChildStr(str1, string2Substring)) {
                res = i;
                break;
            }
        }
        System.out.println(res);
    }

    public static boolean isChildStr(String string1, String string2) {
        List<Character> chs = new ArrayList<>();
        char[] str2Chars = string2.toCharArray();
        for (Character c : str2Chars) {
            chs.add(c);
        }
        int count = 0;
        for (Character currentChar : string1.toCharArray()) {
            for (int j = 0; j < chs.size(); j++) {
                if (Objects.equals(currentChar, chs.get(j))) {
                    count++;
                    chs.remove(j);
                    break;
                }
            }
        }
        return count == string1.length();
    }
}
