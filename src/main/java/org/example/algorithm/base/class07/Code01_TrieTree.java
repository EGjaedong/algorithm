package org.example.algorithm.base.class07;

public class Code01_TrieTree {
    // 前缀树，也叫字典树，一般用于存储字符串的信息。可以用来判断字符串是否存在，或者前缀是否存在
    // 1、判断一个字符串是否在一个集合中，就可以从root开始，根据字符串的每个字符，找到对应的节点，如果最后的节点的end不为0，说明存在
    // 2、判断一个字符串是否是另一个字符串的前缀，也是从root开始，根据字符串的每个字符，找到对应的节点，如果最后的节点的pass不为0，说明存在
    // 3、根结点的Pass值，表是加入了多少个字符串。End值就表示有多少个空字符串加入到了这个前缀树中。
    public static class TrieNode {
        public int pass; // 当前这个节点有多少次经过
        public int end; // 当前这个节点，有多少个路径是以这个节点为结尾的
        // 也可以用HashMap<Character, Node>的map来表示（如果字符比较多的话，不单纯是字母）。
        public TrieNode[] nexts; // 下级的节点都是哪些，用26个字母表示，某一个位置上有值，说明该位置上有对应的字符。

        public TrieNode() {
            pass = 0;
            end = 0;
            // 26个英文字母
            nexts = new TrieNode[26];
        }
    }

    public static class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        // 插入一个字符串
        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chars = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (char ch : chars) {
                index = ch - 'a'; // 表示当前字符在字母表的哪个位置，决定在nexts中的位置
                if (node.nexts[index] == null) {
                    node.nexts[index] = new TrieNode();
                }
                node = node.nexts[index];
                node.pass++;
            }
            node.end++;
        }

        // 删除一个字符串
        public void delete(String word) {
            if (word == null) {
                return;
            }
            if (search(word) == 0) {
                return;
            }
            char[] chars = word.toCharArray();
            TrieNode node = root;
            // 进来的时候，头节点的pass值减1
            node.pass--;
            int index = 0;
            for (char ch : chars) {
                index = ch - 'a';
                // 减掉下一个节点的pass值，并且判断当前pass是不是0，如果是0，说明后续的节点都不需要维护了，直接置空
                if (--node.nexts[index].pass == 0) {
                    node.nexts[index] = null;
                    return;
                }
                node = node.nexts[index];
            }
            // 最后一个节点end值减1
            node.end--;
        }

        // word这个单词之前加入过几次
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            TrieNode node = root;
            int index;
            for (char ch : chars) {
                index = ch - 'a';
                // 这个条件如果命中，说明这个字符没有在节点的下一个字符表中，说明这个单词没有加入过
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }

        // 判断有多少个字符串是以pre这个字符串作为前缀的
        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chars = pre.toCharArray();
            TrieNode node = root;
            int index;
            for (char ch : chars) {
                index = ch - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pass;
        }
    }

}
