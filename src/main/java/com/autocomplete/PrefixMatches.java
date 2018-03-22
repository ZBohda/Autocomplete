package com.autocomplete;

import java.util.Iterator;

public class PrefixMatches {

    private Trie trie;
    private RWayTrie rWayTrie = new RWayTrie();

    public int add(String... strings) {

        return 0;
    }

    public boolean contains(String word) {
        return false;
    }

    public boolean delete(String word) {
        return false;
    }

    public int size() {
       return 0;
    }

    public Iterator<String> wordsWithPrefix(String pref, int k) {
        return null;
    }

    public Iterator<String> wordsWithPrefix(String pref) {
        return null;
    }

    public RWayTrie getrWayTrie() {
        return rWayTrie;
    }

    public void setrWayTrie(RWayTrie rWayTrie) {
        this.rWayTrie = rWayTrie;
    }
}
