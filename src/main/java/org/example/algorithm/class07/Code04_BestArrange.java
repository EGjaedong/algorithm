package org.example.algorithm.class07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Code04_BestArrange {
    // 会议室安排问题
    // 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
    // 给你每一个项目开始的时间和结束的时间(给你一个数 组，里面是一个个具体
    // 的项目)，你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。
    // 返回这个最多的宣讲场次。
    // 贪心思路：选择最早结束的项目，然后剔除掉与这个项目时间冲突的项目，再选择下一个最早结束的项目，就是结果。

    // 1，实现一个不依靠贪心策略的解法X，可以用最暴力的尝试
    // 2，脑补出贪心策略A、贪心策略B、贪心策略C...
    // 3，用解法X和对数器，去验证每一个贪心策略，用实验的方式得知哪个贪心策略正确
    // 4，不要去纠结贪心策略的证明


    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class ProgramComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    public static List<Program> bestArrange(Program[] programs, int start) {
        Arrays.sort(programs, new ProgramComparator());
        List<Program> validPrograms = new ArrayList<>();
        for (Program program : programs) {
            if (start <= program.start) {
                start = program.end;
                validPrograms.add(program);
            }
        }
        return validPrograms;
    }


    public static List<Program> maxAttendances(Program[] programs) {
        int n = programs.length;
        int maxCount = 0;
        Set<Integer> bestSubset = new HashSet<>(); // 用于保存最大有效子集的索引
        // 生成所有可能的子集 (共 2^n 个子集)
        for (int subsetMask = 0; subsetMask < (1 << n); subsetMask++) {
            Set<Integer> subset = new HashSet<>();
            for (int i = 0; i < n; i++) {
                if ((subsetMask & (1 << i)) != 0) {
                    subset.add(i);
                }
            }

            // 检查子集是否合法
            if (isValidSubset(Arrays.asList(programs), subset)) {
                if (subset.size() > maxCount) {
                    maxCount = subset.size();  // 更新最大场次
                    bestSubset = new HashSet<>(subset);  // 更新最大有效子集
                }
            }
        }
        List<Program> result = new ArrayList<>();
        for (int i : bestSubset) {
            result.add(programs[i]);
        }
        return result;
    }

    // 方法：检查一个子集中的所有项目是否没有时间冲突
    private static boolean isValidSubset(List<Program> projects, Set<Integer> subset) {
        // 遍历子集中的项目
        List<Program> selectedProjects = new ArrayList<>();
        for (int index : subset) {
            selectedProjects.add(projects.get(index));
        }

        // 按照结束时间排序，检查是否有重叠
        selectedProjects.sort(Comparator.comparingInt(a -> a.end)); // 按结束时间排序

        // 检查是否有重叠
        for (int i = 1; i < selectedProjects.size(); i++) {
            Program prev = selectedProjects.get(i - 1);
            Program curr = selectedProjects.get(i);
            if (curr.start < prev.end) { // 如果当前项目的开始时间小于上一个项目的结束时间，说明有重叠
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Program[] programs = new Program[] {
                new Program(1, 3),
                new Program(2, 4),
                new Program(3, 6),
                new Program(5, 7),
                new Program(6, 8),
                new Program(7, 9),
                new Program(8, 10),
                new Program(9, 11)
        };
        System.out.println("use greedy strategy:");
        System.out.println(bestArrange(programs, 1).size());
        System.out.println("detail of best arrange:");
        for (Program program : bestArrange(programs, 1)) {
            System.out.print(program.start + " " + program.end + ",");
        }
        System.out.println();
        System.out.println("use brute force strategy:");
        System.out.println(maxAttendances(programs).size());
        System.out.println("detail of best arrange:");
        for (Program program : maxAttendances(programs)) {
            System.out.print(program.start + " " + program.end + ",");
        }
    }
}
