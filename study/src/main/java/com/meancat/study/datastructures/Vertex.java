package com.meancat.study.datastructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * User: trasa
 * Created: 5/19/13 2:07 PM
 */
public class Vertex implements Comparable<Vertex> {
    public final String name;
    public Edge[] adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex previous;

    public Vertex(String name) {
        this.name = name;
    }

    @Override
    public String toString() { return name; }

    @Override
    public int compareTo(Vertex other) {
        return Double.compare(minDistance, other.minDistance);
    }

    public static void computePaths(Vertex source) {
        source.minDistance = 0.0;
        PriorityQueue<Vertex> q = new PriorityQueue<Vertex>();
        q.add(source);
        while (!q.isEmpty()) {
            Vertex u = q.poll();
            for (Edge e : u.adjacencies) {
                Vertex v = e.target;
                double weight = e.weight;
                // relax edge (u,v)
                double distanceThroughU = u.minDistance + weight;
                if (distanceThroughU < v.minDistance) {
                    // yay found a shorter way
                    q.remove(v);
                    v.minDistance = distanceThroughU;
                    v.previous = u;
                    q.add(v); // re add to queue with new distance
                }
                // done relaxing
            }
        }
    }

    public static List<Vertex> getShortestPathTo(Vertex target){
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous) {
            path.add(vertex);
        }
        Collections.reverse(path);
        return path;
    }
}
