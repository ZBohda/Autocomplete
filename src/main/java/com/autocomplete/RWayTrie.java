package com.autocomplete;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RWayTrie implements Trie {

    private static final int A_ASCII_CODE = 97;
    private static final int NUMBER_OF_LETTERS = 26;

    private Node root = new Node();
    private int size;

    @Override
    public void add(Tuple tuple) {
        String word = tuple.getWord();
        int weight = tuple.getWeight();
        put(root, word, weight);
    }

    private Node put(Node root, String word, int weight) {
        int index = 0;
        while (index < weight) {
            char c = word.charAt(index);
            Node next = root.nextNodes[getPosition(c)];
            if (next == null) {
                next = new Node(c, root);
                root.nextNodes[getPosition(c)] = next;
            }
            root = next;
            index++;
        }
        if (!root.isWord) size++;
        root.isWord = true;
        return root;
    }

    @Override
    public boolean contains(String word) {
        return get(word);
    }

    private boolean get(String word) {
        int index = 0;
        Node node = get(root, word, index);
        if (node == null) return false;
        return node.isWord;
    }

    @Override
    public boolean delete(String word) {
        int index = 0;
        Node node = get(root, word, index);
        if (node == null) return false;
        else {
            if (node.isWord == true) {
                size--;
                node.isWord = false;
                return true;
            }
        }
        return false;
    }

    private Node get(Node root, String word, int index) {
        if (root == null) {
            return null;
        }
        if (index == word.length()) {
            return root;
        }
        char c = word.charAt(index);
        return get(root.nextNodes[getPosition(c)], word, index + 1);
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
        return size;
    }

    public RWayTrie() {
        createTriesWithAlphabet();
    }

    private void createTriesWithAlphabet() {
        char[] alphabet = createAlphabet();
        for (char c : alphabet) {
            Node node = new Node();
            node.character = c;
            root.nextNodes[getPosition(c)] = node;
        }
    }

    private int getPosition(char c) {
        return c - A_ASCII_CODE;
    }

    private char[] createAlphabet() {
        return IntStream.rangeClosed('a', 'z')
                .mapToObj(c -> "" + (char) c).collect(Collectors.joining()).toCharArray();
    }

    private class Node {
        private char character;
        private boolean isWord;
        private Node[] nextNodes = new Node[NUMBER_OF_LETTERS];
        private Node prevNode;

        public Node(char character, Node node) {
            this.character = character;
            this.prevNode = node;
        }

        public Node() {
        }
    }
}
