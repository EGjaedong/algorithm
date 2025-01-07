package org.example.algorithm.course.base.class06;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Code05_Prim {
    // Prim算法，要求图必须是无向图
    // Prim算法是从节点出发，生成最小生成树的算法
    // 起始随便选一个节点，添加到已经考察的集合中，将这个点的所有的边加入到小根堆中，
    // 每次从小根堆中弹出一个边，如果这个边的另一个端点不在集合中，则将这个边的另一个节点加入到集合中，并且将这个点的边加入到小根堆中，这条边加入到结果中
    // 继续弹出小根堆中的边，直到小根堆为空

    public static Set<Edge> primMST(Graph graph) {
        PriorityQueue<Edge> sortedEdges = new PriorityQueue<>(
                Comparator.comparingInt(e -> e.weight));
        Set<Node> nodeSet = new HashSet<>();
        Set<Edge> result = new HashSet<>(); // 结果
        // 这个for循环用来处理森林的情况，即这个图中有多个不互相连通的部分
        for (Node node : graph.nodes.values()) {
            if (!nodeSet.contains(node)) { // 如果当前节点还没有处理过，即不在集合中
                nodeSet.add(node); // 当前节点已经处理过
                sortedEdges.addAll(node.edges); // 当前节点所有的边放入小根堆
                while (!sortedEdges.isEmpty()) { // 遍历小根堆中所有解锁了，但是没有消费的边
                    Edge edge = sortedEdges.poll(); // 选取一个权重最小的边
                    if (!nodeSet.contains(edge.to)) { // 如果当前边的另一个端点没有处理过
                        result.add(edge);
                        nodeSet.add(edge.to); // 当前边的另一个端点已经处理过
                        sortedEdges.addAll(edge.to.edges); // 另一个端点的所有边放入小根堆
                    }
                }
            }
        }
        return result;
    }

}
