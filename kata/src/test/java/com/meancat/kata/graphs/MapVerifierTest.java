package com.meancat.kata.graphs;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MapVerifierTest {

    @Test
    public void connected() {
        ArrayList<Country> countries = new ArrayList<Country>();
        Country a = new Country("a");
        Country b = new Country("b");
        Country c =new Country("c");
        Country d =new Country("d");
        Country e =new Country("e");

        countries.add(a);
        countries.add(b);
        countries.add(c);
        countries.add(d);
        countries.add(e);

        a.addNeighbor(b);
        a.addNeighbor(c);
        a.addNeighbor(d);

        b.addNeighbor(c);
        b.addNeighbor(d);

        c.addNeighbor(d);

        d.addNeighbor(e);

        MapVerifier verifier = new MapVerifier();
        assertTrue("should be connected", verifier.verify(countries.toArray(new Country[countries.size()])));
    }

    @Test
    public void notConnected() {
        ArrayList<Country> countries = new ArrayList<Country>();
        Country a = new Country("a");
        Country b = new Country("b");
        Country c =new Country("c");
        Country d =new Country("d");
        Country e =new Country("e");

        countries.add(a);
        countries.add(b);
        countries.add(c);
        countries.add(d);
        countries.add(e);

        a.addNeighbor(b);
        a.addNeighbor(c);
        a.addNeighbor(d);

        b.addNeighbor(c);
        b.addNeighbor(d);

        c.addNeighbor(d);

        // e not connected
//        d.addNeighbor(e);

        MapVerifier verifier = new MapVerifier();
        assertFalse("should not be connected", verifier.verify(countries.toArray(new Country[countries.size()])));
    }


    @Test
    public void partitioned() {
        ArrayList<Country> countries = new ArrayList<Country>();
        Country a = new Country("a");
        Country b = new Country("b");
        Country c =new Country("c");
        Country d =new Country("d");
        Country e =new Country("e");

        countries.add(a);
        countries.add(b);
        countries.add(c);
        countries.add(d);
        countries.add(e);

        // a & b are neighbors
        a.addNeighbor(b);
        // c & d & e are neighbors
        c.addNeighbor(d);
        d.addNeighbor(e);

        MapVerifier verifier = new MapVerifier();
        assertFalse("should not be connected", verifier.verify(countries.toArray(new Country[countries.size()])));
    }
}
