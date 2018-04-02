package com.autocomplete;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RWayTrie implements Trie {

    private static final int ASCII_CODE_OF_FIRST_LETTER = 97;
    private static final int ASCII_CODE_OF_LAST_LETTER = 122;
    private static final int NUMBER_OF_LETTERS = 26;
    private static final String EMPTY_PREFIX = "";


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
        return node != null && node.isWord;
    }

    @Override
    public boolean delete(String word) {
        int index = 0;
        Node node = get(root, word, index);
        if (node == null) return false;
        else if (node.isWord) {
            size--;
            node.isWord = false;
            return true;
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
        return wordsWithPrefix(EMPTY_PREFIX);
    }

    @Override
    public Iterable<String> wordsWithPrefix(String pref) {
        if (IsPrefValid(pref)) {
            int index = 0;
            Node rootNodeForPref = get(root, pref, index);
            if (rootNodeForPref == null) {
                return new ArrayDeque<>();
            }
            return collectByPref(rootNodeForPref);
        } else return new ArrayDeque<>();
    }

    private boolean IsPrefValid(String pref) {
        return pref != null && pref.length() >= 2;
    }

    private Iterable<String> collectByPref(Node rootNodeForPref) {
        return () -> new Iterator<String>() {

            private Queue<Node> nodesQueue = new ArrayDeque<>();

            {
                nodesQueue.add(rootNodeForPref);
            }

            public boolean hasNext() {
                return !nodesQueue.isEmpty();
            }

            public String next() {
                if (hasNext()) {
                    return getNextWordNode();
                } else throw new NoSuchElementException();
            }

            public String getNextWordNode() {
                Node node = nodesQueue.poll();
                addNextNodesToQueue(node);
                if (node.isWord) {
                    return getWord(node);
                } else return next();
            }

            private void addNextNodesToQueue(Node node) {
                for (char c = ASCII_CODE_OF_FIRST_LETTER; c <= ASCII_CODE_OF_LAST_LETTER; c++) {
                    if (node.nextNodes[getPosition(c)] != null) {
                        nodesQueue.add(node.nextNodes[getPosition(c)]);
                    }
                }
            }

            private String getWord(Node node) {
                StringBuilder word = new StringBuilder();
                word.append(node.character);
                while (node.prevNode != null) {
                    word.append(node.prevNode.character);
                    node = node.prevNode;
                }
                return word.reverse().toString();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
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
        return c - ASCII_CODE_OF_FIRST_LETTER;
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
