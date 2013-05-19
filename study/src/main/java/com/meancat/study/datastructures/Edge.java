package com.meancat.study.datastructures;


/**
 * User: trasa
 * Created: 5/19/13 2:07 PM
 */
public class Edge {
    public final Vertex target;
    public final double weight;

    public Edge(Vertex target, double weight) {
        this.target = target;
        this.weight = weight;
    }
}
