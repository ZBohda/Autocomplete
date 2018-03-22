package com.autocomplete;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RWayTrie implements Trie {

    private List<Node> roots = new ArrayList<>();
    private int size;

    @Override
    public void add(Tuple tuple) {

    }

    @Override
    public boolean contains(String word) {
        return false;
    }

    @Override
    public boolean delete(String word) {
        return false;
    }

    @Override
    public Iterable<String> words() {
        return null;
    }

    @Override
    public Iterable<String> wordsWithPrefix(String pref) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    public void createTriesWithEnglishAlphabet(){
        final char[] englishAlphabet  = IntStream.rangeClosed('a', 'z')
                .mapToObj(c -> "" + (char) c).collect(Collectors.joining()).toCharArray();
        for(char c : englishAlphabet){
            Node node = new Node(c, false, null);
            roots.add(node);
        }
    }

    public List<Node> getRoots() {
        return roots;
    }

    public void setRoots(List<Node> roots) {
        this.roots = roots;
    }

    private class Node {
        private char character;
        private boolean isWord;
        private List<Node> nextNodes;

        public Node(char character, boolean isWord, List<Node> nextNodes) {
            this.character = character;
            this.isWord = isWord;
            this.nextNodes = nextNodes;
        }

        public char getCharacter() {
            return character;
        }

        public void setCharacter(char character) {
            this.character = character;
        }

        public boolean isWord() {
            return isWord;
        }

        public void setWord(boolean word) {
            isWord = word;
        }

        public List<Node> getNextNodes() {
            return nextNodes;
        }

        public void setNextNodes(List<Node> nextNodes) {
            this.nextNodes = nextNodes;
        }
    }
}
