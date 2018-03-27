package com.autocomplete;

import java.util.ArrayList;
import java.util.List;

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
