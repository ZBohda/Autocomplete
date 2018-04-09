package com.autocomplete.trie;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class PrefixMatches {

    private final static String WORDS_DIVIDER = " ";
    private final static int MINIMAL_WORD_LENGTH = 2;

    private Trie trie = new RWayTrie();

    public PrefixMatches() {
    }

    public int add(String... strings) {
        int numberOfAddedWords = 0;
        if (strings != null && strings.length > 0) {
            for (String s : strings) {
                numberOfAddedWords += addWords(s);
            }
        }
        return numberOfAddedWords;
    }

    private int addWords(String s) {
        int numberOfAddedWords = 0;
        if(s != null){
            String[] strings = s.split(WORDS_DIVIDER);
            for (String string : strings) {
                if (string.length() > MINIMAL_WORD_LENGTH) {
                    Tuple tuple = new Tuple(string);
                    trie.add(tuple);
                    numberOfAddedWords++;
                }
            }
        }
        return numberOfAddedWords;
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

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        if (!IsPrefValid(pref)) {
            return new ArrayDeque<>();
        }
        if (k < 0) {
            throw new IllegalArgumentException();
        }
        return () -> new Iterator<String>() {

            private final int MAX_SEARCH_DEPTH = pref.length() + k;
            private Queue<String> wordsQueue = new ArrayDeque<>();
            private Iterator<String> iterator = trie.wordsWithPrefix(pref).iterator();

            {
                addNewWordToWordsQueue();
            }

            @Override
            public boolean hasNext() {
                return !wordsQueue.isEmpty();
            }

            @Override
            public String next() {
                if (hasNext()) {
                    addNewWordToWordsQueue();
                    return wordsQueue.poll();
                } else throw new NoSuchElementException();
            }

            private void addNewWordToWordsQueue() {
                String newWord;
                if (iterator.hasNext()) {
                    newWord = iterator.next();
                    if (isNewWordValid(newWord)) {
                        wordsQueue.add(newWord);
                    }
                }
            }

            private boolean isNewWordValid(String newWord) {
                return newWord.length() < MAX_SEARCH_DEPTH;
            }
        };
    }

    private boolean IsPrefValid(String pref) {
        return pref != null && pref.length() >= MINIMAL_WORD_LENGTH;
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        return trie.wordsWithPrefix(pref);
    }

}
