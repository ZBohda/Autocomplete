package com.autocomplete;

import static com.autocomplete.DictionaryDataLoader.getData;

public class Run {

    public static void main(String... args) {
        PrefixMatches prefixMatches = new PrefixMatches();
        String[] strings = getData("resources/words.txt");
        prefixMatches.add(strings);
        System.out.println(prefixMatches.size());
        System.out.println(prefixMatches.contains("www"));
        System.out.println(prefixMatches.delete("www"));
        System.out.println(prefixMatches.size());


    }
}
