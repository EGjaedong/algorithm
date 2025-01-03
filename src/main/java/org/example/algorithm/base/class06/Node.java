package org.example.algorithm.base.class06;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public int value; // value根据实际情况而定，可以是int，char，String等
    public int in; // 入度，即有多少条边指向这个节点
    public int out; // 出度，即有多少条边从这个节点指出去
    public List<Node> nexts; // 该节点指向的节点
    public List<Edge> edges; // 属于这个节点的边

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }

}
