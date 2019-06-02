package com.anagramsolver;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class AnagramPatternsfilterRules {
	static List<Predicate<List<Integer>>> getAnagramPatternsfilterRules() {
		List<Predicate<List<Integer>>> rules = new ArrayList<Predicate<List<Integer>>>();
		rules.add(new Predicate<List<Integer>>() {
			@Override
			public boolean test(List<Integer> t) {
				if (t.size() > 4) {
					return true;
				} else {
					return false;
				}
			}

		});
		rules.add(new Predicate<List<Integer>>() {
			@Override
			public boolean test(List<Integer> t) {
				int maxSmallWords = 1;
				int countSmallWords = 0;
				for(Integer i : t) {
					if(i <= 2) {
						countSmallWords++;
					}
					if(countSmallWords > maxSmallWords) {
						return true;
					}
				}
				return false;
			}

		});
		return rules;
	}
}
