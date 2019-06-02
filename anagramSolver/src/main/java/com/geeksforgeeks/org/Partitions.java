package com.geeksforgeeks.org;

import java.util.ArrayList;
import java.util.List;

public class Partitions {

	// ****************************************************************************
	// SOURCE:
	// https://www.geeksforgeeks.org/generate-unique-partitions-of-an-integer/

	// Function to print an array p[] of size n
	static void pushInList(int p[], int n, List<List<Integer>> partitionList) {
		if (n > 0) {
			partitionList.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < n; i++) {
			partitionList.get(partitionList.size() - 1).add(p[i]);
		}
	}

	// Function to generate all unique partitions of an integer
	public static List<List<Integer>> getPartitionList(int n) {
		int[] p = new int[n]; // An array to store a partition
		int k = 0; // Index of last element in a partition
		p[k] = n; // Initialize first partition as number itself
		List<List<Integer>> partitionList = new ArrayList<List<Integer>>();

		// This loop first prints current partition, then generates next
		// partition. The loop stops when the current partition has all 1s
		while (true) {
			// print current partition
			pushInList(p, k + 1, partitionList);

			// Generate next partition

			// Find the rightmost non-one value in p[]. Also, update the
			// rem_val so that we know how much value can be accommodated
			int rem_val = 0;
			while (k >= 0 && p[k] == 1) {
				rem_val += p[k];
				k--;
			}

			// if k < 0, all the values are 1 so there are no more partitions
			if (k < 0)
				return partitionList;

			// Decrease the p[k] found above and adjust the rem_val
			p[k]--;
			rem_val++;

			// If rem_val is more, then the sorted order is violeted. Divide
			// rem_val in differnt values of size p[k] and copy these values at
			// different positions after p[k]
			while (rem_val > p[k]) {
				p[k + 1] = p[k];
				rem_val = rem_val - p[k];
				k++;
			}

			// Copy rem_val to next position and increment position
			p[k + 1] = rem_val;
			k++;
		}
	}
	// ****************************************************************************

}
