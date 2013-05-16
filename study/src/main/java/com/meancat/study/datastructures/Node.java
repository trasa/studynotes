package com.meancat.study.datastructures;

import java.util.HashSet;
import java.util.Set;

public class Node {

    public int value;
    public Set<Node> vertices = new HashSet<Node>();
    public boolean visited;


    public Node(int i) {
        this.value = i;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("|").append(value).append("|");
        if (!vertices.isEmpty()) {
            sb.append("{");
            for(Node n : vertices) {
                sb.append(n.toString());
            }
            sb.append("}");
        }
        return sb.toString();
    }
}
