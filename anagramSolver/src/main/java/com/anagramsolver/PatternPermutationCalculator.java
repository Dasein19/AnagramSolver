package com.anagramsolver;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.anagramsolver.utils.MD5HashManager;
import com.anagramsolver.utils.Permutations;
import com.anagramsolver.utils.TrieManager;

public class PatternPermutationCalculator {
	private static String prepareString(String srcString, int[] sentencePattern) {
		StringBuilder sb = new StringBuilder(srcString);
		String whitespaceCharacter = " ";
		int currOffset = 0;
		for (int i = 0; i < sentencePattern.length - 1; i++) {
			currOffset += sentencePattern[i];
			sb.insert(currOffset + i, whitespaceCharacter);
		}
		return sb.toString();
	}

	private static boolean hashBytesMatchInTrgList(byte[] srcBytes, byte[][] trgBytes) {
		for (byte[] hashBytes : trgBytes) {
			if (srcBytes.length != hashBytes.length) {
				return false;
			}
			for (int i = 0; i < srcBytes.length; i++) {
				if (srcBytes[i] != hashBytes[i]) {
					break;
				}
				if (i == hashBytes.length - 1) {
					return true;
				}
			}
		}
		return false;
	}

	public static List<String> searchAnagramsForSentencePattern(int[] sentencePattern, String sourceAnagram,
			TrieManager trieManager, byte[][] targetDigests)
			throws FileNotFoundException, UnsupportedEncodingException, NoSuchAlgorithmException {
		List<String> anagramMatches = new ArrayList<String>();
		char[] srcLetters = sourceAnagram.toCharArray();
		// Must start at lowest permutation
		boolean nextPermutation = true;
		do {
			int sentenceOffset = 0;
			for (int i = 0; i < sentencePattern.length; i++) {
				int currElement = sentencePattern[i];

				// search if current words permutations contains valid words of the length
				// specified by the current pattern
				int isValidWord;
				// allow only vocals on single letter words
				if (currElement == 1) {
					isValidWord = "aeiou".indexOf(currElement) < 0 ? -1 : 0;
				} else {
					isValidWord = trieManager.isValidWord(srcLetters, sentenceOffset, sentenceOffset + currElement);
				}
				// if the current pattern word has a match (-1), increase the offset and search
				// for next word. If all the pattern words are correctly found get the MD5 and
				// test it. Otherwise in isValidWord we have the index in the Trie of the
				// longest word found in dictionary. Start then the next lexical permutation
				// from it
				if (isValidWord < 0) {
					if (i == sentencePattern.length - 1) {
						// check sentence with md5
						String sentenceToCheck = prepareString(new String(srcLetters), sentencePattern);
						byte[] md5Digest = MD5HashManager.getMD5Hash(sentenceToCheck);
						if (hashBytesMatchInTrgList(md5Digest, targetDigests)) {
							String foundMatchString = "FOUND A MATCH: " + sentenceToCheck;
							anagramMatches.add(foundMatchString);
							System.out.println(foundMatchString);
						}
						nextPermutation = Permutations.nextPermutation(srcLetters);
					}
					sentenceOffset += currElement;
				} else {
					boolean nextPermResult = Permutations.resetPermutationFromPosition(srcLetters,
							sentenceOffset + isValidWord);
					if (!nextPermResult) {
						if (sentenceOffset + isValidWord == 0) {
							// we were trying to change first char. Search has ended
							return anagramMatches;
						} else {
							nextPermutation = Permutations.nextPermutation(srcLetters);
						}
					}
					break;
				}
			}
		} while (nextPermutation);
		return anagramMatches;
	}
}
