package com.meancat.study.datastructures;

import org.junit.Test;

import java.util.List;

/**
 * User: trasa
 * Created: 5/19/13 2:13 PM
 */
public class VertextTest {
    @Test
    public void computePaths() {
        Vertex v0 = new Vertex("Harrisburg");
        Vertex v1 = new Vertex("Baltimore");
        Vertex v2 = new Vertex("Washington");
        Vertex v3 = new Vertex("Philadelphia");
        Vertex v4 = new Vertex("Binghamton");
        Vertex v5 = new Vertex("Allentown");
        Vertex v6 = new Vertex("New York");
        v0.adjacencies = new Edge[]{new Edge(v1, 79.83),
                new Edge(v5, 81.15)};
        v1.adjacencies = new Edge[]{new Edge(v0, 79.75),
                new Edge(v2, 39.42),
                new Edge(v3, 103.00)};
        v2.adjacencies = new Edge[]{new Edge(v1, 38.65)};
        v3.adjacencies = new Edge[]{new Edge(v1, 102.53),
                new Edge(v5, 61.44),
                new Edge(v6, 96.79)};
        v4.adjacencies = new Edge[]{new Edge(v5, 133.04)};
        v5.adjacencies = new Edge[]{new Edge(v0, 81.77),
                new Edge(v3, 62.05),
                new Edge(v4, 134.47),
                new Edge(v6, 91.63)};
        v6.adjacencies = new Edge[]{new Edge(v3, 97.24),
                new Edge(v5, 87.94)};
        Vertex[] vertices = {v0, v1, v2, v3, v4, v5, v6};

        System.out.println("Computing paths...");
        Vertex.computePaths(v0);
        for (Vertex v : vertices) {
            System.out.println("Distance to " + v + ": " + v.minDistance);
            List<Vertex> path = Vertex.getShortestPathTo(v);
            System.out.println("Path: " + path);
        }
    }
}
