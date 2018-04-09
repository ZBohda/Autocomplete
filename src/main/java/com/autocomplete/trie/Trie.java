package com.autocomplete.trie;

public interface Trie {

    void add(Tuple tuple);

    boolean contains(String word);

    boolean delete(String word);

    Iterable<String> words();

    Iterable<String> wordsWithPrefix(String pref);

    int size();
}
