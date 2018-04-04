package com.autocomplete.trie;

final class Tuple {

    private String word;
    private int weight;

    public Tuple(String word){
        this.word = word;
        this.weight = word.length();
    }

    public Tuple(String word, int weight){
        this.word = word;
        this.weight = weight;
    }

    public String getWord() {
        return word;
    }

    public int getWeight() {
        return weight;
    }
}
