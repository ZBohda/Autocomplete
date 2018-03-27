package com.autocomplete;

final class Tuple {

    private String word;
    private int weight;


    public Tuple(String word){
        this.word = word;
        this.weight = word.length();
    }

    public String getWord() {
        return word;
    }

    public int getWeight() {
        return weight;
    }
}
