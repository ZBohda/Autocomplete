package com.autocomplete;

import com.autocomplete.trie.Trie;
import com.autocomplete.trie.Tuple;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

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
    public void shouldReturnTrueIfWordIsValidAndExists() {
        when(trie.contains(FIRST_VALID_WORD)).thenReturn(true);
        assertTrue(instance.contains(FIRST_VALID_WORD));
    }

    @Test
    public void shouldReturnFalseIfWordIsValidAndDoesNotExists() {
        when(trie.contains(FIRST_VALID_WORD)).thenReturn(false);
        assertFalse(instance.contains(FIRST_VALID_WORD));
    }

    @Test
    public void shouldReturnFalseIfContainsAnEmptyWord() {
        when(trie.contains(EMPTY_STRING)).thenReturn(false);
        assertFalse(instance.contains(EMPTY_STRING));
    }

    @Test
    public void shouldReturnFalseIfDeleteInvalidWord() {
        when(trie.delete(INVALID_WORD)).thenReturn(false);
        assertFalse(instance.delete(INVALID_WORD));
    }

    @Test
    public void shouldReturnFalseIfWordIsValidAndDeleteValidWord() {
        when(trie.delete(FIRST_VALID_WORD)).thenReturn(false);
        assertFalse(instance.delete(FIRST_VALID_WORD));
    }

    @Test
    public void shouldReturnFalseIfDeleteNull() {
        when(trie.delete(null)).thenReturn(false);
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
        verify(trie, times(numberOfAddedWords)).add(any(Tuple.class));
        verify(trie, never()).add(null);
    }

    @Test
    public void shouldAddOneWordIfAddArrayStringWithValidData() {
        String stringArray[] = {FIRST_VALID_WORD};
        int numberOfAddedWords = 1;
        assertEquals(numberOfAddedWords, instance.add(stringArray));
        verify(trie, times(numberOfAddedWords)).add(any(Tuple.class));
    }

    @Test
    public void shouldNotAddAnyWordsIfAddNullArrayString() {
        String stringArray[] = null;
        int numberOfAddedWords = 0;
        assertEquals(numberOfAddedWords, instance.add(stringArray));
        verify(trie, times(numberOfAddedWords)).add(any(Tuple.class));
        verify(trie, never()).add(any(Tuple.class));
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
    public void shouldReturnOneWordIfPrefixIsValidAndSearchDepthEqualsToTwo() {
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

    @Test
    public void shouldReturnFalseIfTryToGetWordsByInvalidPrefix() {
        Queue<String> queue = new ArrayDeque<>();
        when(trie.wordsWithPrefix(INVALID_PREFIX)).thenReturn(queue);
        matchesIterator = instance.wordsWithPrefix(INVALID_PREFIX).iterator();
        assertFalse(matchesIterator.hasNext());
    }

    @Test
    public void shouldReturnFalseIfTryToGetWordsByNullPrefix() {
        Queue<String> queue = new ArrayDeque<>();
        when(trie.wordsWithPrefix(null)).thenReturn(queue);
        matchesIterator = instance.wordsWithPrefix(null).iterator();
        assertFalse(matchesIterator.hasNext());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfInvalidSearchDepthUsed() {
        int searchDepth = -1;
        instance.wordsWithPrefix(VALID_PREFIX, searchDepth).iterator();
    }
}
