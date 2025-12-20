package pnc.base;

public class Combination {
    int totalobjectsN, waystoselectC;
    long combination;
    boolean hasConstantC = false;

    public Combination(int n, int c) {
        this.totalobjectsN = n;
        this.waystoselectC = c;
        this.combination = calculateCombination(n, c);
        this.hasConstantC = true;
    }

    public Combination(int n) { this.totalobjectsN = n; }

    private long calculateCombination(int n, int r) {
        if (r < 0 || r > n) {
            return 0;
        }
        if (r == 0 || r == n) {
            return 1;
        }
        if (r > n / 2) {
            r = n - r;
        }

        long result = 1;
        for (int i = 1; i <= r; i++) {
            result = result * (n - i + 1) / i;
        }
        return result;
    }

    public long combineWithVariableValue(int value) {
        if (!this.hasConstantC) {
            this.combination = calculateCombination(totalobjectsN, value);
            return this.combination;
        } else return -1; // Indicating that combination was already calculated
    }

    public long getCombination() {
        if (this.hasConstantC) return this.combination;
        else return -1; // Indicating that combination was not calculated with constant C
    }
}
