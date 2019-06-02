package com.anagramsolver;

import java.io.PrintStream;
import java.util.List;
import java.util.function.Predicate;

import com.anagramsolver.utils.TrieManager;

public class AnagramCalculatorBuilder {

	private String sourceAnagram = "";
	private TrieManager trieManager = null;
	private byte[][] targetDigests;
	private List<Predicate<List<Integer>>> anagramPatternsfilterRules;
	private PrintStream outputFile;

	public AnagramCalculatorBuilder() {
	}

	public AnagramCalculator buildAnagramCalculator() {
		return new AnagramCalculator(sourceAnagram, trieManager, targetDigests, anagramPatternsfilterRules, outputFile);
	}

	public AnagramCalculatorBuilder sourceAnagram(String sourceAnagram) {
		this.sourceAnagram = sourceAnagram;
		return this;
	}

	public AnagramCalculatorBuilder trieManager(TrieManager trieManager) {
		this.trieManager = trieManager;
		return this;
	}

	public AnagramCalculatorBuilder targetDigests(byte[][] targetDigests) {
		this.targetDigests = targetDigests;
		return this;
	}
	
	public AnagramCalculatorBuilder anagramPatternsfilterRules(List<Predicate<List<Integer>>> anagramPatternsfilterRules) {
		this.anagramPatternsfilterRules = anagramPatternsfilterRules;
		return this;
	}
	
	public AnagramCalculatorBuilder outputFilePath(PrintStream outputFile) {
		this.outputFile = outputFile;
		return this;
	}

}
