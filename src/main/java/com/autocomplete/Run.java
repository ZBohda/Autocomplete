package com.autocomplete;

import com.autocomplete.trie.PrefixMatches;

import java.util.Iterator;

import static com.autocomplete.utils.DictionaryDataLoader.getData;

public class Run {

    public static void main(String... args) {

        PrefixMatches prefixMatches = new PrefixMatches();
        String[] strings = getData("resources/words.txt");
        prefixMatches.add(strings);
        int i=0;
        Iterator<String> iterator = prefixMatches.wordsWithPrefix("", 3).iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
            i++;
        }
        System.out.println(prefixMatches.size());
        System.out.println(i);
    }
}
