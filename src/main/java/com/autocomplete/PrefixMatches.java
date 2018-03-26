package com.autocomplete;

import java.util.Iterator;

public class PrefixMatches {

    private Trie trie = new RWayTrie();

    public PrefixMatches() {
    }

    public int add(String... strings) {
        int counter = 0;
        for (String s : strings) {
            if (s.length() > 2) {
                Tuple tuple = new Tuple(s);
                trie.add(tuple);
                counter++;
            }
        }
        return counter;
    }

    public boolean contains(String word) {
        return false;
    }

    public boolean delete(String word) {
        return false;
    }

    public int size() {
        return trie.size();
    }

    public Iterator<String> wordsWithPrefix(String pref, int k) {
        return null;
    }

    public Iterator<String> wordsWithPrefix(String pref) {
        return null;
    }

}
