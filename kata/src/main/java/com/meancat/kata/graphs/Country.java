package com.meancat.kata.graphs;

import java.util.HashSet;
import java.util.Set;

public class Country {

    private String name;
    private Set<Country> neighbors = new HashSet<Country>();

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<Country> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(Country neighbor) {
        neighbors.add(neighbor);
        neighbor.neighbors.add(this);
    }

    @Override
    public String toString() {
        return "Country{" + "'" + name + '\'' +
                '}';
    }
}
