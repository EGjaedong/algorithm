package org.example.algorithm.class06;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;

public class Graph {
    public Map<Integer, Node> nodes;
    public Set<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }

}
