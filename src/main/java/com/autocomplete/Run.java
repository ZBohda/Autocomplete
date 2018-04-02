package com.autocomplete;

import java.util.Iterator;

import static com.autocomplete.DictionaryDataLoader.getData;

public class Run {

    public static void main(String... args) {

        PrefixMatches prefixMatches = new PrefixMatches();
        String[] strings = getData("resources/words.txt");
        prefixMatches.add(strings);
        int i=0;
        Iterator<String> iterator = prefixMatches.wordsWithPrefix("wwww", 2).iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
            i++;
        }
        System.out.println(prefixMatches.size());
        System.out.println(i);
    }
}
