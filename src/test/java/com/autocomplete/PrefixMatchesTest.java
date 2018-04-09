package com.autocomplete;

import com.autocomplete.trie.PrefixMatches;
import com.autocomplete.trie.Trie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class PrefixMatchesTest {

    @Mock
    private Trie trie;

    @InjectMocks
    private PrefixMatches instance;

    private static final String INVALID_WORD = "ab";
    private static final String FIRST_VALID_WORD = "abc";
    private static final String SECOND_VALID_WORD = "abcd";
    private static final String THIRD_VALID_WORD = "abcde";
    private static final String INVALID_PREFIX = "a";
    private static final String VALID_PREFIX = "ab";
    private static final String WORDS_DIVIDER = " ";
    private static final String EMPTY_STRING = "";

    private Iterator<String> matchesIterator;

    @Test
    public void shouldReturnFalseWhenContainsNull() {
        when(trie.contains(null)).thenReturn(false);
        assertFalse(instance.contains(null));
    }

    @Test
    public void shouldReturnFalseIfWordIsInvalid() {
        when(trie.contains(INVALID_WORD)).thenReturn(false);
        assertFalse(instance.contains(INVALID_WORD));
    }

    @Test
    public void shouldReturnTrueIfWordIsValid() {
        when(trie.contains(FIRST_VALID_WORD)).thenReturn(true);
        assertTrue(instance.contains(FIRST_VALID_WORD));
    }

    @Test
    public void shouldReturnFalseIfDeleteInvalidWord() {
        when(trie.delete(INVALID_WORD)).thenReturn(false);
        assertFalse(instance.delete(INVALID_WORD));
    }

    @Test
    public void shouldReturnFalseIfDeleteNull() {
        when(trie.delete(INVALID_WORD)).thenReturn(false);
        assertFalse(instance.delete(null));
    }

    @Test
    public void shouldReturnTrueIfDeleteValidWord() {
        when(trie.delete(FIRST_VALID_WORD)).thenReturn(true);
        assertTrue(instance.delete(FIRST_VALID_WORD));
    }

    @Test
    public void shouldReturnZeroIfTrieIsEmpty() {
        when(trie.size()).thenReturn(0);
        assertEquals(0, instance.size());
    }

    @Test
    public void shouldReturnFalseIfInvalidPrefixUsedForSearch() {
        when(trie.wordsWithPrefix(INVALID_PREFIX)).thenReturn(new ArrayDeque<>());
        assertFalse(instance.wordsWithPrefix(INVALID_PREFIX).iterator().hasNext());
    }

    @Test
    public void shouldReturnFalseIfNullPrefixUsedForSearch() {
        when(trie.wordsWithPrefix(null)).thenReturn(new ArrayDeque<>());
        assertFalse(instance.wordsWithPrefix(null).iterator().hasNext());
    }

    @Test
    public void shouldAddOneWordIfAddStringWithOneWord() {
        int numberOfAddedWords = 1;
        assertEquals(numberOfAddedWords, instance.add(FIRST_VALID_WORD));
    }

    @Test
    public void shouldAddThreeWordsIfAddArrayStringWithNullAndEmptyStringAndThreeValidWords() {
        String stringArray[] = {FIRST_VALID_WORD + WORDS_DIVIDER + SECOND_VALID_WORD, null, EMPTY_STRING, THIRD_VALID_WORD};
        int numberOfAddedWords = 3;
        assertEquals(numberOfAddedWords, instance.add(stringArray));
    }

    @Test
    public void shouldReturnOneWordIfPrefixIsValidAndWordExists() {
        Queue<String> queue = new ArrayDeque<>();
        queue.add(FIRST_VALID_WORD);
        when(trie.wordsWithPrefix(VALID_PREFIX)).thenReturn(queue);
        matchesIterator = instance.wordsWithPrefix(VALID_PREFIX).iterator();
        assertTrue(matchesIterator.hasNext());
        assertEquals(matchesIterator.next(), FIRST_VALID_WORD);
        assertFalse(matchesIterator.hasNext());
    }

    @Test
    public void shouldReturnOneWordIfPrefixIsValidAndSearchDepthEqualsToTwo(){
        Queue<String> queue = new ArrayDeque<>();
        queue.add(FIRST_VALID_WORD);
        queue.add(SECOND_VALID_WORD);
        queue.add(THIRD_VALID_WORD);
        when(trie.wordsWithPrefix(VALID_PREFIX)).thenReturn(queue);
        matchesIterator = instance.wordsWithPrefix(VALID_PREFIX, 2).iterator();
        assertTrue(matchesIterator.hasNext());
        assertEquals(matchesIterator.next(), FIRST_VALID_WORD);
        assertFalse(matchesIterator.hasNext());
    }
}
