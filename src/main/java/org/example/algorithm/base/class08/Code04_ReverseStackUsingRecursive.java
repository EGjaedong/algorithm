package org.example.algorithm.base.class08;

import java.util.Deque;
import java.util.LinkedList;

public class Code04_ReverseStackUsingRecursive {

    // 给你一个栈，请你逆序这个栈，不能申请额外的数据结构，只能使用递归函数。
    // 递归本身就是一个压栈过程，从栈顶递归往下找，如果当前栈不为空，那么递归；刚才从栈里弹出来的元素，就被压倒方法栈中了。
    // 等到了栈底，返回每一层压栈保存起来的数据即可。

    // 每一次调用，都会从栈底里拿出一个元素放到变量i中，然后压入系统栈中
    public static void reverse(Deque<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = func(stack);
        reverse(stack);
        stack.push(i);
    }

    // 这个函数是在弹出栈底元素
    public static int func(Deque<Integer> stack) {
        int result = stack.pop();
        // base case
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = func(stack);
            stack.push(result);
            return last;
        }
    }

    public static void main(String[] args) {
        Deque<Integer> stack = new LinkedList<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack);
        reverse(stack);
        System.out.println(stack);
    }
}
