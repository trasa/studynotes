package com.meancat.study.datastructures;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class BfsTest {

    Node root;
    Node[] nodes;

    @Test
    public void findSuccessful() {
        assertTrue(Bfs.search(15, root));
    }

    @Test
    public void successfulWithLoop() {
        nodes[3].vertices.add(root);
        assertTrue(Bfs.search(14, root));
    }

    @Test
    public void notSuccessful() {
        assertFalse(Bfs.search(17, root));
    }

    @Test
    public void notSuccessfulWithLoop() {
        nodes[3].vertices.add(root);
        assertFalse(Bfs.search(17, root));
    }

    @Before
    public void setUp() {
        nodes = new Node[16];
        for(int i=0; i < nodes.length; i++) {
            nodes[i] = new Node(i);
        }
        root = nodes[0];
        root.vertices.add(nodes[1]);
        root.vertices.add(nodes[2]);
        root.vertices.add(nodes[3]);
        root.vertices.add(nodes[4]);
        root.vertices.add(nodes[5]);

        nodes[1].vertices.add(nodes[6]);
        nodes[1].vertices.add(nodes[7]);

        nodes[2].vertices.add(nodes[8]);
        nodes[2].vertices.add(nodes[9]);

        nodes[3].vertices.add(nodes[10]);
        nodes[3].vertices.add(nodes[11]);

        nodes[4].vertices.add(nodes[12]);
        nodes[4].vertices.add(nodes[13]);

        nodes[5].vertices.add(nodes[14]);
        nodes[5].vertices.add(nodes[15]);

        System.out.println(root.toString());
    }
}
