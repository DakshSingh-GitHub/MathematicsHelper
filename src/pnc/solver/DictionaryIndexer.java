package pnc.solver;

import pnc.base.Factorial;
import java.util.Map;
import java.util.TreeMap;

public class DictionaryIndexer {
    String word = ""; // WORD - DORW
    int index = 0;

    public DictionaryIndexer(String word) { this.word = word; }
    public DictionaryIndexer(String word, int index) { this.word = word; this.index = index; }
    public String getWord() { return this.word; }
    public int getIndex() { return this.index; }

    public int checkPositionAmongstWord() {
        return 0;
    }

    public long rankWord(String word) {
        long rank = 1;
        int n = word.length();
        Factorial fact = new Factorial(n);

        Map<Character, Integer> freq = new TreeMap<>();
        for (char c : word.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < n; i++) {
            for (char smallerChar : freq.keySet()) {
                if (smallerChar < word.charAt(i)) {
                    freq.put(smallerChar, freq.get(smallerChar) - 1);
                    rank += permutations(n - 1 - i, freq, fact);
                    freq.put(smallerChar, freq.get(smallerChar) + 1);
                }
            }
            freq.put(word.charAt(i), freq.get(word.charAt(i)) - 1);
            if (freq.get(word.charAt(i)) == 0) {
                freq.remove(word.charAt(i));
            }
        }
        return rank;
    }
    private long permutations(int length, Map<Character, Integer> freq, Factorial fact) {
        long denominator = 1;
        for (int count : freq.values()) {
            if (count > 1) {
                denominator *= fact.getFactorial(count);
            }
        }
        if (denominator == 0) return fact.getFactorial(length); // Avoid division by zero
        return fact.getFactorial(length) / denominator;
    }
}

