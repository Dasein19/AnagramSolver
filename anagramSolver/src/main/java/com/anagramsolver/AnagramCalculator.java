package com.anagramsolver;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import com.anagramsolver.utils.TrieManager;
import com.geeksforgeeks.org.Partitions;

public class AnagramCalculator {

	private String sourceAnagram = "";
	private TrieManager trieManager = null;
	private byte[][] targetDigests = null;
	private List<Predicate<List<Integer>>> anagramPatternsfilterRules;
	private PrintStream anagramResultsFile;

	public AnagramCalculator(String sourceAnagram, TrieManager trieManager, byte[][] targetDigests,
			List<Predicate<List<Integer>>> anagramPatternsfilterRules, PrintStream anagramResultsFile) {
		this.sourceAnagram = sourceAnagram;
		this.trieManager = trieManager;
		this.targetDigests = targetDigests;
		this.anagramPatternsfilterRules = anagramPatternsfilterRules;
		this.anagramResultsFile = anagramResultsFile;
	}

	public String getSourceAnagram() {
		return sourceAnagram;
	}

	public void setSourceAnagram(String sourceAnagram) {
		this.sourceAnagram = sourceAnagram;
	}

	public TrieManager getTrieManager() {
		return trieManager;
	}

	public void setTrieManager(TrieManager trieManager) {
		this.trieManager = trieManager;
	}

	public byte[][] getTargetDigests() {
		return targetDigests;
	}

	public void setTargetDigests(byte[][] targetDigests) {
		this.targetDigests = targetDigests;
	}

	private void filterPatternList(List<List<Integer>> wordsPerSentenceList) {
		for (Predicate<List<Integer>> anagramPatternsfilterRule : anagramPatternsfilterRules) {
			wordsPerSentenceList.removeIf(anagramPatternsfilterRule);
		}
	}

	public boolean computeAnagrams()
			throws FileNotFoundException, UnsupportedEncodingException, NoSuchAlgorithmException {
		List<List<Integer>> wordsPerSentenceList = Partitions.getPartitionList(this.sourceAnagram.length());

		filterPatternList(wordsPerSentenceList);

		ExecutorService executorService = Executors.newFixedThreadPool(10); // number of threads

		for (List<Integer> wordsPerSentence : wordsPerSentenceList) {
			int[] wordsPerSentenceArray = wordsPerSentence.stream().mapToInt(i -> i).toArray();
			Arrays.sort(wordsPerSentenceArray, 0, wordsPerSentenceArray.length);
			// parallelize computation
			executorService.submit(new SentencePatternSearchTask(wordsPerSentenceArray, this, anagramResultsFile));
		}

		executorService.shutdown();
		boolean hasTimeout = false;
		try {
			hasTimeout = executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {

		}

		return hasTimeout;
	}

}
