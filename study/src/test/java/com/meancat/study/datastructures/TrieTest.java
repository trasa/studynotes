package com.meancat.study.datastructures;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class TrieTest {

    @Test
    public void buildTries() {

        Trie root = Trie.buildTrie(new String[] {"foo", "for", "faz"});
        System.out.println(root.toString());
    }

    @Test
    public void findWords() {
        Trie root = Trie.buildTrie(new String[] {"foo", "for", "faz", "forte"});

        assertTrue(root.findWord("foo"));
        assertTrue(root.findWord("faz"));
        assertFalse(root.findWord("forp"));
        assertFalse(root.findWord("nop"));
        assertTrue(root.findWord("fort"));
        assertTrue(root.findWord("forte"));
    }
}
