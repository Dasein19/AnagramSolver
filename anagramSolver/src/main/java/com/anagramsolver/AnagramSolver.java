package com.anagramsolver;

import java.io.PrintStream;
import java.util.List;
import java.util.function.Predicate;

import com.anagramsolver.utils.FormatUtilities;
import com.anagramsolver.utils.TrieManager;
import com.baeldung.trie.Trie;

public class AnagramSolver {

	private static final String dictionaryPath = "wordlist";
	private static final String sourceAnagram = "poultry outwits ants";
	private static final String formattedAnagram = FormatUtilities.prepareAnagram(sourceAnagram);
	private static final byte[][] targetDigests = { FormatUtilities.fromHexToByte("e4820b45d2277f3844eac66c903e84be"),
			FormatUtilities.fromHexToByte("23170acc097c24edb98fc5488ab033fe"),
			FormatUtilities.fromHexToByte("665e5bcb0c20062fe8abaaf4628bb154") };
	private static final List<Predicate<List<Integer>>> anagramPatternsfilterRules = AnagramPatternsfilterRules
			.getAnagramPatternsfilterRules();
	private static final String outputFilePath = "Found anagrams matches.txt";

	public static void main(String[] args) {
		System.out.println("Program starts at: " + FormatUtilities.getCurrentDate());
		long startTime = System.currentTimeMillis();
		PrintStream outputFile = null;
		try {
			outputFile = new PrintStream(outputFilePath);
			Trie dictionaryTrie = TrieManager.populateTrieFromFile(dictionaryPath);
			TrieManager trieManager = new TrieManager(dictionaryTrie);
			AnagramCalculator anagramCalculator = new AnagramCalculatorBuilder().sourceAnagram(formattedAnagram)
					.trieManager(trieManager).targetDigests(targetDigests)
					.anagramPatternsfilterRules(anagramPatternsfilterRules)
					.outputFilePath(outputFile).buildAnagramCalculator();
			boolean executionStatus = anagramCalculator.computeAnagrams();

			if (!executionStatus) {
				System.err.println("Timeout Reached");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		outputFile.close();
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		String formattedElapsedTime = FormatUtilities.timeFormatUtility(elapsedTime);
		System.out.println("Program ran in " + formattedElapsedTime);
		System.out.println("Program ends at " + FormatUtilities.getCurrentDate());
	}

}
