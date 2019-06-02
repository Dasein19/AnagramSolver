package com.anagramsolver;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import com.anagramsolver.utils.Permutations;

public class SentencePatternSearchTask implements Runnable {
	private int[] sentencePattern;
	private AnagramCalculator anagramCalculator;
	private PrintStream anagramResultsFile;

	public SentencePatternSearchTask(int[] sentencePattern, AnagramCalculator anagramCalculator,
			PrintStream anagramResultsFile) {
		this.sentencePattern = sentencePattern;
		this.anagramCalculator = anagramCalculator;
		this.anagramResultsFile = anagramResultsFile;
	}

	private synchronized void printInFile(List<String> lines) {
		for (String line : lines) {
			anagramResultsFile.println(line);
		}
	}

	@Override
	public void run() {
		// search for every lexical permutation, anagrams that have match in vocabulary
		// and fit to the source sentence pattern
		do {
			try {
				List<String> anagramMatches = PatternPermutationCalculator.searchAnagramsForSentencePattern(
						sentencePattern, anagramCalculator.getSourceAnagram(), anagramCalculator.getTrieManager(),
						anagramCalculator.getTargetDigests());
				printInFile(anagramMatches);
			} catch (FileNotFoundException | UnsupportedEncodingException | NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		} while (Permutations.nextPermutation(sentencePattern));
		System.out.println("All patterns (permutations included) with " + Arrays.toString(sentencePattern) + " words have been searched!");
	}

}
