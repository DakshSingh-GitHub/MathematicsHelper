package pnc.base;

public class Permutation {
    int totalobjectsN, waystorearrangeC;
    long permutations;
    boolean hasConstantC = false;

    public Permutation(int totalobjectsN, int waystorearrangeC) {
        this.totalobjectsN = totalobjectsN;
        this.waystorearrangeC = waystorearrangeC;
        int factorialN = (new Factorial(totalobjectsN)).getFactorial();
        int factorialNC = (new Factorial(totalobjectsN - waystorearrangeC)).getFactorial();
        this.permutations = factorialN / factorialNC;
        this.hasConstantC = true;
    }
    public Permutation(int totalobjectsN) { this.totalobjectsN = totalobjectsN; }

    public long permuteWithVariableValue(int value) {
        if (!this.hasConstantC) {
            int factorialN = (new Factorial(totalobjectsN)).getFactorial();
            int factorialNC = (new Factorial(totalobjectsN - value)).getFactorial();
            this.permutations = factorialN / factorialNC;
            return this.permutations;
        } else return -1; // Indicating that permutations were already calculated
    }

    public long getPermutations() {
        if (this.hasConstantC) return this.permutations;
        else  return -1; // Indicating that permutations were not calculated with constant C
    }
}
