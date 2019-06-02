package com.anagramsolver.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.baeldung.trie.Trie;

public class TrieManager {

	private Trie dictionary;

	public TrieManager(Trie dictionary) {
		this.dictionary = dictionary;
	}

	public static Trie populateTrieFromFile(String filePath) throws IOException {
		File file = new File(filePath);
		Trie trieRoot = new Trie();
		FileInputStream fis = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		String line;
		while ((line = br.readLine()) != null) {
			trieRoot.insert(line);
		}
		br.close();
		return trieRoot;
	}

	public int isValidWord(String prefix, boolean fullWord) {
		if (prefix.equals("")) {
			return -1;
		}

		int validPrefix;
		if (fullWord) {
			validPrefix = dictionary.containsNode(prefix);
		} else {
			validPrefix = dictionary.isValidPrefix(prefix);
		}
		return validPrefix;
	}

	public int isValidWord(char[] letters, int start, int end) {
		if (letters.length == 0) {
			return -1;
		}

		int validPrefix = dictionary.containsNode(letters, start, end);
		return validPrefix;
	}

}
