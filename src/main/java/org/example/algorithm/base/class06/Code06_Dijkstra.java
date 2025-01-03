package org.example.algorithm.base.class06;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Code06_Dijkstra {
    // Dijkstra算法，解决有向图（其实无向图是一种特殊的有向图）中单源最短路径问题。
    // 从一个点出发，求到达图中所有的点的最短路径。
    // 要求没有权值为负数的边。
    // 具体的过程：
    // 1、生成一个源点到各个点的距离表，初始时只有源点的距离为0，其他点的距离为正无穷（可以用在表中不存在的方式来表达正无穷）。
    // 2、考察源点到各个节点的距离，如果找到比距离表上小的距离，则更新距离表。更新完成后，源点锁死，以后不再更新这个点的距离值。
    // 3、在距离表中找到距离值最小的点，这个点就是下一个考察的点，以这个点为中心，查看这个点到所有的其他节点的距离，
    // 如果有比距离表上小的距离，更新距离表。更新完成后，这个点锁死，以后不再更新这个点的距离值。
    // 4、重复步骤3，直到距离表中所有的点都被锁死。
    // 锁死就是标记为已经考察过。

    public static Map<Node, Integer> dijkstra1(Node head) {
        // 从head出发到所有点的最小距离
        // key: 从from出发到达key
        // value：从from出发到key的最短距离
        // 如果在表中没有某一个节点N的记录，那么认为从head到N的距离为正无穷。
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        // 把head加入到距离表中，到自己的距离为0
        distanceMap.put(head, 0);
        // 已经考察过的点，放在set集合中，以后不会再继续考察
        Set<Node> selectedNodes = new HashSet<>();
        // 此时，得到的minNode其实就是head节点。
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        // 只要当前存在没有考察到的节点，就继续循环
        while (minNode != null) {
            int distance = distanceMap.get(minNode);
            // 遍历minNode的所有边
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to;
                // 如果toNode不在distanceMap中，说明这个节点是第一次出现，则直接注册到distanceMap中
                if (!distanceMap.containsKey(toNode)) {
                    distanceMap.put(toNode, distance + edge.weight);
                } else {
                    // 如果toNode在distanceMap中，则需要比较查看当前的距离是不是比原来的距离小，如果是则更新
                    distanceMap.put(toNode, Math.min(distanceMap.get(toNode), distance + edge.weight));
                }
            }
            // minNode已经考察过，放入selectedNodes中，之后不再继续考察该点。
            selectedNodes.add(minNode);
            // 选取下一个要考察的节点
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    // 从distanceMap中找到一个不在selectedNodes中的，且是距离最小的节点返回。
    // 即选取当前距离表中，没有被考察过的而且到起始点距离最小的点。
    private static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap,
            Set<Node> selectedNodes) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Entry<Node, Integer> distanceEntry : distanceMap.entrySet()) {
            Node node = distanceEntry.getKey();
            int distance = distanceEntry.getValue();
            if (!selectedNodes.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distanceMap.get(node);
            }
        }
        return minNode;
    }
}
