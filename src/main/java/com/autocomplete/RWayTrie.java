package com.autocomplete;

import java.util.List;

public class RWayTrie implements Trie {

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
