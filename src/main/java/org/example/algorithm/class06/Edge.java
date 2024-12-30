package org.example.algorithm.class06;

public class Edge {
    public int weight; // 边的权重
    public Node from; // 边的起点
    public Node to; // 边的终点

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }

}
