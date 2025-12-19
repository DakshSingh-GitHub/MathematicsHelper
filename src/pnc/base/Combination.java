package pnc.base;

public class Combination {
    int totalobjectsN, waystoselectC;
    long combination;
    boolean hasConstantC = false;

    public Combination(int n, int c) {
        this.totalobjectsN = n;
        this.waystoselectC = c;
        int factorialN = (new Factorial(n)).getFactorial();
        int factorialC = (new Factorial(c)).getFactorial();
        int factorialNC = (new Factorial(n - c)).getFactorial();
        this.combination = factorialN / (factorialC * factorialNC);
        this.hasConstantC = true;
    }
    public Combination(int n) { this.totalobjectsN = n; }

    public long combineWithVariableValue(int value) {
        if (!this.hasConstantC) {
            int factorialN = (new Factorial(totalobjectsN)).getFactorial();
            int factorialC = (new Factorial(value)).getFactorial();
            int factorialNC = (new Factorial(totalobjectsN - value)).getFactorial();
            this.combination = factorialN / (factorialC * factorialNC);
            return this.combination;
        } else return -1; // Indicating that combination was already calculated
    }

    public long getCombination() {
        if (this.hasConstantC) return this.combination;
        else return -1; // Indicating that combination was not calculated with constant C
    }
}
