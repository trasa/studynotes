package com.meancat.study.datastructures;

import java.util.HashMap;
import java.util.Map;

public class Trie {

    private Character c;
    private Map<Character, Trie> children = new HashMap<Character, Trie>();

    public Trie() {
    }

    public Trie(char c) {
        this.c = c;
    }

    public Character getCharacter() {
        return c;
    }

    public boolean findWord(String word) {
        return findWord(word, 0);
    }

    private boolean findWord(String word, int charPos) {
        if (charPos == word.length()) {
            return true; // found the end.
        }
        Character c = word.charAt(charPos);
        if (children.containsKey(c)) {
            return children.get(c).findWord(word, ++charPos);
        } else {
            return false;
        }
    }


    public static Trie buildTrie(String[] words) {
        Trie root = new Trie();
        for(String word : words) {
            Trie current = root;
            for(char c : word.toCharArray()) {
                current = current.findOrCreate(c);
            }
            // need to set end? current.setEnd();
        }
        return root;
    }

    private Trie findOrCreate(char c) {
        Trie leaf;
        if (!children.containsKey(c)) {
            leaf = new Trie(c);
            children.put(c, leaf);
        } else {
            leaf = children.get(c);
        }
        return leaf;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(c).append("' : { ");
        for(Trie t : children.values()) {
            sb.append(t.toString());
        }
        sb.append("}");
        return sb.toString();
    }
}

