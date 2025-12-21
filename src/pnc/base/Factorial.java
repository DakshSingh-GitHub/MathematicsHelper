package pnc.base;

public class Factorial {
    long fact;

    public Factorial(int num) {
        int fact = 1;
        for (int i = 1; i <= num; i++) fact *= i;
        this.fact = fact;
    }

    public long getFactorial() { return this.fact; }

    public long getFactorial (int num) {
        int fact = 1;
        for (int i = 1; i <= num; i++) fact *= i;
        return fact;
    }
}