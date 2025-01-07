package org.example.algorithm.course.base.class06;

// 把别的结构的图转换成我自己的图表示
// 按照左神的说法，图算法是固定的，图相关的题难点在于表示图的方式有很多种，浪费时间的点在于每种表示方式都要实现一遍算法
// 遇到不熟悉的表示方式，算法写起来很费劲
public class GraphGenerator {

    // matrix 矩阵表示所有的边
    // N*3 的矩阵，每一行表示一个边
    // [weight, from节点上的值, to节点上的值]
    // 例如：[[3, 0, 7], [2, 1, 7], [1, 2, 7], [12, 3, 7]]
    public static Graph generateGraph(Integer[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            Integer weight = matrix[i][0];
            Integer from = matrix[i][1];
            Integer to = matrix[i][2];
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge newEdge = new Edge(weight, fromNode, toNode);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(newEdge);
            graph.edges.add(newEdge);
        }
        return graph;
    }
}
