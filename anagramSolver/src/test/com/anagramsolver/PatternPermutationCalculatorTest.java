package com.anagramsolver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.anagramsolver.utils.FormatUtilities;
import com.anagramsolver.utils.TrieManager;
import com.baeldung.trie.Trie;

public class PatternPermutationCalculatorTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@After
	public void restoreStreams() {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}

	@Test
	public void twoWordsTest() {
		setUpStreams();
		String sourceAnagram = "hi world";	//7 letters
		try {
			Trie dictionaryTrie = TrieManager.populateTrieFromFile("wordList");
			TrieManager trieManager = new TrieManager(dictionaryTrie);
			int[] patternArray = { 2, 5 };
			byte[][] targetDigests = { FormatUtilities.fromHexToByte("941223D904F006C4D998598272D43D94") };
			String srcAnagramNoSpaces = FormatUtilities.prepareAnagram(sourceAnagram);
			String eol = System.getProperty("line.separator");
			String trgSentence = "FOUND A MATCH: " + sourceAnagram + eol;
			PatternPermutationCalculator.searchAnagramsForSentencePattern(patternArray, srcAnagramNoSpaces, trieManager,
					targetDigests);
			assertEquals(trgSentence, outContent.toString());
		} catch (Exception e) {
			System.err.println(e);
			fail();
		}
		restoreStreams();
	}

	@Test
	public void threeWordsTest() {
		setUpStreams();
		String sourceAnagram = "winter is coming";	//14 letters	
		try {
			Trie dictionaryTrie = TrieManager.populateTrieFromFile("wordList");
			TrieManager trieManager = new TrieManager(dictionaryTrie);
			int[] patternArray = { 6, 2, 6 };
			byte[][] targetDigests = { FormatUtilities.fromHexToByte("A3C7DAC57DA5898C6C68F0942FA62BFC") };
			String srcAnagramNoSpaces = FormatUtilities.prepareAnagram(sourceAnagram);
			String eol = System.getProperty("line.separator");
			String trgSentence = "FOUND A MATCH: " + sourceAnagram + eol;
			PatternPermutationCalculator.searchAnagramsForSentencePattern(patternArray, srcAnagramNoSpaces, trieManager,
					targetDigests);
			assertEquals(trgSentence, outContent.toString());
		} catch (Exception e) {
			System.err.println(e);
			fail();
		}
		restoreStreams();
	}

	@Test
	public void fourWordsTest() {
		setUpStreams();
		String sourceAnagram = "nothing endures but change";	//23 letters
		try {
			Trie dictionaryTrie = TrieManager.populateTrieFromFile("wordList");
			TrieManager trieManager = new TrieManager(dictionaryTrie);
			int[] patternArray = { 7, 7, 3, 6 };
			byte[][] targetDigests = { FormatUtilities.fromHexToByte("299BCFFF92BDA37FA6A748C84C9D4432") };
			String srcAnagramNoSpaces = FormatUtilities.prepareAnagram(sourceAnagram);
			String eol = System.getProperty("line.separator");
			String trgSentence = "FOUND A MATCH: " + sourceAnagram + eol;
			PatternPermutationCalculator.searchAnagramsForSentencePattern(patternArray, srcAnagramNoSpaces, trieManager,
					targetDigests);
			assertEquals(trgSentence, outContent.toString());
		} catch (Exception e) {
			System.err.println(e);
			fail();
		}
		restoreStreams();
	}

	@Test
	public void fiveWordsTest() {
		setUpStreams();
		String sourceAnagram = "I think therefore I am";	//18 letters
		try {
			Trie dictionaryTrie = TrieManager.populateTrieFromFile("wordList");
			TrieManager trieManager = new TrieManager(dictionaryTrie);
			int[] patternArray = { 1, 5, 9, 1, 2 };
			byte[][] targetDigests = { FormatUtilities.fromHexToByte("CD21B5763F5C6D3E8181549519D31641") };
			String srcAnagramNoSpaces = FormatUtilities.prepareAnagram(sourceAnagram);
			String eol = System.getProperty("line.separator");
			String trgSentence = "FOUND A MATCH: " + sourceAnagram.toLowerCase() + eol;
			PatternPermutationCalculator.searchAnagramsForSentencePattern(patternArray, srcAnagramNoSpaces, trieManager,
					targetDigests);
			Assert.assertTrue(outContent.toString().contains(trgSentence));
		} catch (Exception e) {
			System.err.println(e);
			fail();
		}
		restoreStreams();
	}

}
