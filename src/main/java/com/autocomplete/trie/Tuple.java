package com.autocomplete.trie;

public final class Tuple {

    private String word;
    private int weight;

    public Tuple(String word, int weight) {
        this.word = word;
        this.weight = weight;
    }

    public Tuple(String word) {
        this(word, word.length());
    }

    public String getWord() {
        return word;
    }

    public int getWeight() {
        return weight;
    }
}
