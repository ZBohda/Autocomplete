package com.autocomplete;

import java.util.Iterator;

public class PrefixMatches {

    private final static String DIVIDER = " ";
    private final static int MINIMAL_WORD_LENGTH = 2;

    private Trie trie = new RWayTrie();

    public PrefixMatches() {
    }

    public int add(String... strings) {
        int counter = 0;
        if (strings != null && strings.length > 0) {
            for (String s : strings) {
                counter = counter + readString(s);
            }
        }
        return counter;
    }

    private int readString(String s) {
        int words = 0;
        if (s != null) {
            words = words + addWord(s);
        }
        return words;
    }

    private int addWord(String s) {
        int words = 0;
        String[] strings = s.split(DIVIDER);
        for (String string : strings) {
            if (string.length() > MINIMAL_WORD_LENGTH) {
                Tuple tuple = new Tuple(string);
                trie.add(tuple);
                words++;
            }
        }
        return words;
    }

    public boolean contains(String word) {
        return validateWord(word) && trie.contains(word);
    }

    private boolean validateWord(String word) {
        return word != null && word.length() > MINIMAL_WORD_LENGTH;
    }

    public boolean delete(String word) {
        return validateWord(word) && trie.delete(word);
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
