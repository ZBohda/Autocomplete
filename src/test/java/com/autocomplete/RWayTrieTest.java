package com.autocomplete;

import com.autocomplete.trie.RWayTrie;
import com.autocomplete.trie.Trie;
import com.autocomplete.trie.Tuple;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)

public class RWayTrieTest {

    private Trie instance = new RWayTrie();
    private Iterator<String> trieIterator;


    private static final String FIRST_VALID_WORD = "abc";
    private static final String SECOND_VALID_WORD = "abcd";
    private static final String THIRD_VALID_WORD = "abcde";
    private static final String INVALID_WORD = "ab";
    private static final String INVALID_PREFIX = "a";
    private static final String VALID_PREFIX = "ab";
    private static final String EMPTY_STRING = "";
    private static final Tuple FIRST_VALID_TUPLE = new Tuple(FIRST_VALID_WORD);
    private static final Tuple SECOND_VALID_TUPLE = new Tuple(SECOND_VALID_WORD);
    private static final Tuple THIRD_VALID_TUPLE = new Tuple(THIRD_VALID_WORD);

    @Test
    public void shouldReturnFalseWhenTrieDoesNotContainWord() {
        assertFalse(instance.contains(FIRST_VALID_WORD));
    }

    @Test
    public void shouldReturnTrueWhenTrieContainsParticularWord() {
        instance.add(FIRST_VALID_TUPLE);
        assertTrue(instance.contains(FIRST_VALID_WORD));
    }

    @Test
    public void shouldReturnFalseWhenTrieContainsInvalidWord() {
        assertFalse(instance.contains(INVALID_WORD));
    }

    @Test
    public void shouldReturnFalseWhenDeleteWordWhichDoesNotExist() {
        assertFalse(instance.delete(FIRST_VALID_WORD));
    }

    @Test
    public void shouldReturnFalseWhenDeleteInvalidWord() {
        assertFalse(instance.delete(INVALID_WORD));
    }

    @Test
    public void shouldReturnTrueWhenDeleteValidWordWhichExists() {
        instance.add(FIRST_VALID_TUPLE);
        assertTrue(instance.delete(FIRST_VALID_WORD));
    }

    @Test
    public void shouldReturnFalseWhenDeleteValidWordWhichDoesNotExist() {
        instance.add(FIRST_VALID_TUPLE);
        assertFalse(instance.delete(SECOND_VALID_WORD));
    }

    @Test
    public void shouldReturnZeroWhenTrieHasNoWords() {
        assertEquals(0, instance.size());
    }

    @Test
    public void shouldReturnThreeWhenTrieHasThreeWords() {
        instance.add(FIRST_VALID_TUPLE);
        instance.add(SECOND_VALID_TUPLE);
        instance.add(THIRD_VALID_TUPLE);
        assertEquals(3, instance.size());
    }

    @Test
    public void shouldReturnTwoWhenTrieHasThreeWordsAndOneWordWhichWasAddedWasDeleted() {
        instance.add(FIRST_VALID_TUPLE);
        instance.add(SECOND_VALID_TUPLE);
        instance.add(THIRD_VALID_TUPLE);
        instance.delete("abcde");
        assertEquals(2, instance.size());
    }

    @Test
    public void shouldReturnTwoWhenTrieHasOneWordAndOneWordWasAdded() {
        instance.add(FIRST_VALID_TUPLE);
        assertEquals(1, instance.size());
        instance.add(SECOND_VALID_TUPLE);
        assertEquals(2, instance.size());
    }

    @Test
    public void shouldReturnZeroWhenTrieHasNoWordsAndOneWordWasDeleted() {
        assertEquals(0, instance.size());
        instance.delete(FIRST_VALID_WORD);
        assertEquals(0, instance.size());
    }

    @Test
    public void shouldReturnFalseWhenInvalidPrefixUsedForSearch(){
        assertFalse(instance.wordsWithPrefix(INVALID_PREFIX).iterator().hasNext());
    }

    @Test
    public void shouldReturnFalseWhenNullPrefixUsedForSearch(){
        assertFalse(instance.wordsWithPrefix(null).iterator().hasNext());
    }

    @Test
    public void shouldReturnAllWordsWhenPrefixIsEmpty(){
        instance.add(FIRST_VALID_TUPLE);
        instance.add(SECOND_VALID_TUPLE);
        instance.add(THIRD_VALID_TUPLE);
        trieIterator = instance.wordsWithPrefix(EMPTY_STRING).iterator();
        assertTrue(trieIterator.hasNext());
        assertEquals(FIRST_VALID_WORD, trieIterator.next());
        assertTrue(trieIterator.hasNext());
        assertEquals(SECOND_VALID_WORD, trieIterator.next());
        assertTrue(trieIterator.hasNext());
        assertEquals(THIRD_VALID_WORD, trieIterator.next());
        assertFalse(trieIterator.hasNext());
    }

    @Test
    public void shouldReturnOneWordWhenPrefixIsValidAndTrieContainsParticularWord(){
        instance.add(FIRST_VALID_TUPLE);
        trieIterator = instance.wordsWithPrefix(VALID_PREFIX).iterator();
        assertTrue(trieIterator.hasNext());
        assertEquals(FIRST_VALID_WORD, trieIterator.next());
        assertFalse(trieIterator.hasNext());
    }

    @Test
    public void shouldReturnAnEmptyDequeWhenPrefixIsValidAndTrieDoesNotContainAnyWords(){
        trieIterator = instance.wordsWithPrefix(VALID_PREFIX).iterator();
        assertFalse(trieIterator.hasNext());
    }

    @Test
    public void shouldReturnAnEmptyDequeWhenInvalidPrefixUsedForSearchAndTrieContainsAtLeastOneWord(){
        instance.add(FIRST_VALID_TUPLE);
        instance.add(SECOND_VALID_TUPLE);
        instance.add(THIRD_VALID_TUPLE);
        trieIterator = instance.wordsWithPrefix(INVALID_PREFIX).iterator();
        assertFalse(trieIterator.hasNext());
    }

    @Test (expected = NoSuchElementException.class)
    public void shouldThrowExceptionWhenNextCalledAndDequeHasNoMoreElements(){
        instance.add(FIRST_VALID_TUPLE);
        trieIterator = instance.wordsWithPrefix(VALID_PREFIX).iterator();
        assertTrue(trieIterator.hasNext());
        assertEquals(FIRST_VALID_WORD, trieIterator.next());
        assertFalse(trieIterator.hasNext());
        trieIterator.next();
    }
}
