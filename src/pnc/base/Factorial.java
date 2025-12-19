package pnc.base;

public class Factorial {
    int fact;

    public Factorial(int num) {
        int fact = 1;
        for (int i = 1; i <= num; i++) fact *= i;
        this.fact = fact;
    }

    public int getFactorial() { return this.fact; }
}
