package org.example.algorithm.course.base.class06;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Code04_Kruskal {
    // 最小生成树Kruskal算法
    // 对于一个无向图，使用Kruskal算法求最小生成树
    // 起始时令每一个节点都是一个单独的集合，
    // 遍历所有的边，选择权重最小的边，如果这两条边不在一个集合中，则合并这两个集合；如果在一个集合中，则跳过这条边（在一个集合中，说明集合中生成了环）
    // 需要使用到并查集，并查集后面将，先放在这里当黑盒用
    public static class UnionFind {
        private HashMap<Node, Node> fatherMap;
        private HashMap<Node, Integer> rankMap;

        public UnionFind() {
            fatherMap = new HashMap<>();
            rankMap = new HashMap<>();
        }

        private Node findFather(Node n) {
            Node father = fatherMap.get(n);
            if (father != n) {
                father = findFather(father);
            }
            fatherMap.put(n, father);
            return father;
        }

        public void makeSets(Collection<Node> nodes) {
            fatherMap.clear();
            rankMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                rankMap.put(node, 1);
            }
        }

        public boolean isSameSet(Node a, Node b) {
            return findFather(a) == findFather(b);
        }

        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            Node aFather = findFather(a);
            Node bFather = findFather(b);
            if (aFather != bFather) {
                int aFrank = rankMap.get(aFather);
                int bFrank = rankMap.get(bFather);
                if (aFrank <= bFrank) {
                    fatherMap.put(aFather, bFather);
                    rankMap.put(bFather, aFrank + bFrank);
                } else {
                    fatherMap.put(bFather, aFather);
                    rankMap.put(aFather, aFrank + bFrank);
                }
            }
        }
    }

    public static Set<Edge> kruskalMST(Graph graph) {
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());
        // 使用小根堆，从小到达排序所有的边
        // 需要自己实现比较器
        PriorityQueue<Edge> sortedEdges = new PriorityQueue<>(
                Comparator.comparingInt(o -> o.weight));
        sortedEdges.addAll(graph.edges);
        Set<Edge> result = new HashSet<>();
        for (Edge edge : sortedEdges) {
            if (!unionFind.isSameSet(edge.from, edge.to)) {
                result.add(edge);
                unionFind.union(edge.from, edge.to);
            }
        }
        return result;
    }
}
