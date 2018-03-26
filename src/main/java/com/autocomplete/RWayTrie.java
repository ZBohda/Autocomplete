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
        char[] characters;
        String word = tuple.getWord();
        char firstChar = getFirstChar(word);
        characters = getArrayOfCharsWithoutFirstChar(word);
        Node root = findRootNodeByChar(firstChar);
        int counter = 0;
        for (char c : characters) {
            if (root.getNextNodes().isEmpty()) {
                root = insertNode(c, root);
                counter++;
                if (characters.length == counter) {
                    root.setWord(true);
                    size++;
                }
            } else {
                Node sheet = findSheetByChar(root, c);
                if (sheet != null) {
                    root = sheet;
                    counter++;
                    if (characters.length == counter) {
                        root.setWord(true);
                        size++;
                    }
                } else {
                    root = insertNode(c, root);
                    counter++;
                    if (characters.length == counter) {
                        root.setWord(true);
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

    private Node findRootNodeByChar(char c) {
        for (Node root : roots) {
            if (c == root.getCharacter()) {
                return root;
            }
        }
        return null;
    }

    private Node findSheetByChar(Node root, char c) {
        List<Node> nodes = root.getNextNodes();
        for (Node node : nodes) {
            if (node.getCharacter() == c) {
                return node;
            }
        }
        return null;
    }

    private Node insertNode(char c, Node root) {
        Node node = new Node(c, root);
        node.setPrefNode(root);
        root.getNextNodes().add(node);
        return node;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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
        return size;
    }

    public List<Node> getRoots() {
        return roots;
    }

    public void setRoots(List<Node> roots) {
        this.roots = roots;
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

    public class Node {
        private char character;
        private boolean isWord;
        private List<Node> nextNodes = new ArrayList<>();
        private Node prefNode;

        public Node(char character, boolean isWord, List<Node> nextNodes, Node prefNode) {
            this.character = character;
            this.isWord = isWord;
            this.nextNodes = nextNodes;
            this.prefNode = prefNode;
        }

        public Node(char character, boolean isWord) {
            this.character = character;
            this.isWord = isWord;
        }

        public Node(char character, Node prefNode) {
            this.character = character;
            this.prefNode = prefNode;
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

        public Node getPrefNode() {
            return prefNode;
        }

        public void setPrefNode(Node prefNode) {
            this.prefNode = prefNode;
        }
    }
}
