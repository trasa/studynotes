package com.meancat.study.datastructures;

import java.util.LinkedList;
import java.util.Queue;

public class Bfs {

    public static boolean search(int value, Node node) {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(node);
        node.visited = true;
        while(!queue.isEmpty()) {
            Node t = queue.remove();
            if (t.value == value) {
                return true; // found it!
            }
            for(Node vertex : t.vertices) {
                if (!vertex.visited) {
                    vertex.visited = true;
                    queue.add(vertex);
                }
            }
        }
        return false;
    }
}
