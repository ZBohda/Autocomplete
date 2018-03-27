package com.autocomplete;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RWayTrie implements Trie {

    private List<Node> roots = new ArrayList<>();
    private int size;

    @Override
    public void add(Tuple tuple) {
        char[] characters;
        String word = tuple.getWord();
        char firstChar = getFirstChar(word);
        characters = getArrayOfCharsWithoutFirstChar(word);
        Node root = findRootNodeByChar(firstChar);
        int counter = 0;
        for (char c : characters) {
            if (root.nextNodes.isEmpty()) {
                root = insertNode(c, root);
                counter++;
                if (characters.length == counter) {
                    root.isWord = true;
                    size++;
                }
            } else {
                Node sheet = findSheetByChar(root, c);
                if (sheet != null) {
                    root = sheet;
                    counter++;
                    if (characters.length == counter) {
                        root.isWord = true;
                        size++;
                    }
                } else {
                    root = insertNode(c, root);
                    counter++;
                    if (characters.length == counter) {
                        root.isWord = true;
                        size++;
                    }
                }
            }
        }
    }

    private char getFirstChar(String s) {
        return s.substring(0, 1).charAt(0);
    }

    private char[] getArrayOfCharsWithoutFirstChar(String s) {
        return s.substring(1).toCharArray();
    }

    private Node insertNode(char c, Node root) {
        Node node = new Node(c, root);
        node.prevNode = root;
        root.nextNodes.add(node);
        return node;
    }

    @Override
    public boolean contains(String word) {
        char[] characters;
        char firstChar = getFirstChar(word);
        characters = getArrayOfCharsWithoutFirstChar(word);
        Node root = findRootNodeByChar(firstChar);
        return findNodeWord(characters, root) != null;
    }

    private Node findNodeWord(char[] characters, Node root) {
        int counter = 0;
        for (char c : characters) {
            Node node;
            if ((node = findSheetByChar(root, c)) != null) {
                counter++;
                if (counter == characters.length && node.isWord) {
                    return node;
                } else root = node;
            }
        }
        return null;
    }

    private Node findRootNodeByChar(char c) {
        for (Node root : roots) {
            if (c == root.character) {
                return root;
            }
        }
        return null;
    }

    private Node findSheetByChar(Node root, char c) {
        List<Node> nodes = root.nextNodes;
        for (Node node : nodes) {
            if (node.character == c) {
                return node;
            }
        }
        return null;
    }

    @Override
    public boolean delete(String word) {
        char[] characters;
        char firstChar = getFirstChar(word);
        characters = getArrayOfCharsWithoutFirstChar(word);
        Node root = findRootNodeByChar(firstChar);
        Node node;
        if ((node = findNodeWord(characters, root)) != null) {
            node.isWord = false;
            return true;
        } else return false;
    }

    @Override
    public Iterable<String> words() {
        return null;
    }

    @Override
    public Iterable<String> wordsWithPrefix(String pref) {
        return null;
    }

    private Iterator<String> getIterator() {
        return new Iterator<String>() {

            private String word;
            private Queue<Node> nodes = new ArrayDeque<>();

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public String next() {
                return null;
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
            Node node = new Node(c, false);
            roots.add(node);
        }
    }

    private char[] createAlphabet() {
        return IntStream.rangeClosed('a', 'z')
                .mapToObj(c -> "" + (char) c).collect(Collectors.joining()).toCharArray();
    }

    public List<Node> getRoots() {
        return roots;
    }

    public void setRoots(List<Node> roots) {
        this.roots = roots;
    }

    public class Node {
        private char character;
        private boolean isWord;
        private List<Node> nextNodes = new ArrayList<>();
        private Node prevNode;

        public Node(char character, boolean isWord) {
            this.character = character;
            this.isWord = isWord;
        }

        public Node(char character, Node prefNode) {
            this.character = character;
            this.prevNode = prefNode;
        }
    }

}
