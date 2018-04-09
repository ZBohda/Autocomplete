package com.autocomplete;

import com.autocomplete.trie.PrefixMatches;
import com.autocomplete.trie.Trie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class PrefixMatchesTest {

    @Mock
    private Trie trie;

    @InjectMocks
    private PrefixMatches instance;

    private static final String INVALID_WORD = "ab";
    private static final String VALID_WORD = "abc";

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
        when(trie.contains(VALID_WORD)).thenReturn(true);
        assertTrue(instance.contains(VALID_WORD));
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
        when(trie.delete(VALID_WORD)).thenReturn(true);
        assertTrue(instance.delete(VALID_WORD));
    }

    @Test
    public void shouldReturnZeroIfTrieIsEmpty() {
        when(trie.size()).thenReturn(0);
        assertEquals(0, instance.size());
    }
}
