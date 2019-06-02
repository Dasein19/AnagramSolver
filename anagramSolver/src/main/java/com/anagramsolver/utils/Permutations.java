package com.anagramsolver.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.common.primitives.Chars;


public class Permutations {

	// ******************************************************
	// SOURCE: https://www.nayuki.io/page/next-lexicographical-permutation-algorithm
	public static boolean nextPermutation(char[] array) {
		// Find longest non-increasing suffix
		int i = array.length - 1;
		while (i > 0 && array[i - 1] >= array[i])
			i--;
		// Now i is the head index of the suffix

		// Are we at the last permutation already?
		if (i <= 0)
			return false;

		// Let array[i - 1] be the pivot
		// Find rightmost element that exceeds the pivot
		int j = array.length - 1;
		while (array[j] <= array[i - 1])
			j--;
		// Now the value array[j] will become the new pivot
		// Assertion: j >= i

		// Swap the pivot with j
		char temp = array[i - 1];
		array[i - 1] = array[j];
		array[j] = temp;

		// Reverse the suffix
		j = array.length - 1;
		while (i < j) {
			temp = array[i];
			array[i] = array[j];
			array[j] = temp;
			i++;
			j--;
		}

		// Successfully computed the next permutation
		return true;
	}

	public static boolean nextPermutation(int[] array) {
		// Find longest non-increasing suffix
		int i = array.length - 1;
		while (i > 0 && array[i - 1] >= array[i])
			i--;
		// Now i is the head index of the suffix

		// Are we at the last permutation already?
		if (i <= 0)
			return false;

		// Let array[i - 1] be the pivot
		// Find rightmost element that exceeds the pivot
		int j = array.length - 1;
		while (array[j] <= array[i - 1])
			j--;
		// Now the value array[j] will become the new pivot
		// Assertion: j >= i

		// Swap the pivot with j
		int temp = array[i - 1];
		array[i - 1] = array[j];
		array[j] = temp;

		// Reverse the suffix
		j = array.length - 1;
		while (i < j) {
			temp = array[i];
			array[i] = array[j];
			array[j] = temp;
			i++;
			j--;
		}

		// Successfully computed the next permutation
		return true;
	}

	// ******************************************************

	// find next letter in lexical order from the given pivot and swap it with the
	// pivot. Then order the array to start from a new permutation iteration 
	public static boolean resetPermutationFromPosition(char[] srcChars, int charToChangeIndex) {
		if (charToChangeIndex + 1 > srcChars.length) {
			return false;
		}

		// search the next minimum element greater than the pivot in the right part
		int nextLexicalCandidate = -1;
		for (int j = charToChangeIndex + 1; j < srcChars.length; j++) {
			if (nextLexicalCandidate == -1) {
				if (srcChars[charToChangeIndex] < srcChars[j]) {
					nextLexicalCandidate = j;
				}
			} else {
				if (srcChars[charToChangeIndex] < srcChars[j] && srcChars[j] < srcChars[nextLexicalCandidate]) {
					nextLexicalCandidate = j;
				}
			}
		}

		// given the pivot, if no more lexically greater characters are on the right
		// part it means that this subpart has no next permutation from the pivot char.
		// Restart then the permutation from the next step sorting in reverse order
		if (nextLexicalCandidate == -1) {
			//use Guava, wraps the primitive
			List<Character> charObjectWrapperList = Chars.asList(srcChars);
			Collections.sort(charObjectWrapperList.subList(charToChangeIndex, charObjectWrapperList.size()), Collections.reverseOrder());
			return false;
		}

		swap(srcChars, charToChangeIndex, nextLexicalCandidate);

		Arrays.sort(srcChars, charToChangeIndex + 1, srcChars.length);
		return true;
	}

	private static void swap(char[] a, int i, int j) {
		char temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

}