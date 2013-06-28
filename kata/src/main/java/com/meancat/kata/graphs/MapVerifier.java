package com.meancat.kata.graphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class MapVerifier {

    public boolean verify(Country[] countries) {
        Queue<Country> queue = new LinkedList<Country>();
        HashMap<Country, Boolean> visited = new HashMap<Country, Boolean>();
        queue.add(countries[0]);
        while(!queue.isEmpty()) {
            Country c = queue.poll();
            if (visited.containsKey(c)) {
                continue;
            }
            System.out.println("Visiting " + c.getName());
            visited.put(c, true);
            for(Country neighbor : c.getNeighbors()) {
                if (!visited.containsKey(neighbor)) {
                    queue.add(neighbor);
                }
            }
        }
        System.out.println("Visited " + visited.size() + " out of " + countries.length);
        return visited.size() == countries.length;
    }
}
