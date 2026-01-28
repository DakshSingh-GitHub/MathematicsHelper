package sequences.series;

import java.util.ArrayList;

public class SquareSeries {
    @SuppressWarnings("unused")
    private double firstTerm;
    // @SuppressWarnings("unused")
    // private Integer n;
    private Boolean isInitialized;
    private ArrayList<Double> skeletonSeries = new ArrayList<>();
    private ArrayList<Double> series = new ArrayList<>();

    public ArrayList<Double> getSeries() { return this.series; }
    public ArrayList<Double> getSkeletonSeries() { return this.skeletonSeries; }
    public void clearSeries() { this.series.clear(); this.skeletonSeries.clear(); this.isInitialized=false; this.firstTerm=0; }

    public SquareSeries(double a, int n) {
        this.firstTerm = a;
        // this.n = n;
        for (int i = 1; i <= n; i++) {
            series.add(Math.pow(a, 2));
            skeletonSeries.add(a);
            a += 1;
        }
        this.isInitialized = true;
    }

    public SquareSeries(double a) {
        this.firstTerm = a;
        this.isInitialized = true;
        this.skeletonSeries.add(a);
        this.series.add(Math.pow(a, 2));
    }

    public SquareSeries() { isInitialized = false; }

    public void addNumberToSeries(double number) {
        // Earlier Bug -> Adding of squares to the above series
        double sqrt = Math.sqrt(number);
        if (sqrt != Math.floor(sqrt)) {
            System.out.println("Doesn't belong to series");
            return;
        }

        if (!this.isInitialized) {
            this.firstTerm = sqrt;
            this.isInitialized = true;
            this.series.add(number);
            this.skeletonSeries.add(sqrt);
        } else {
            // Checking if the number is one more than the last skeletal number, if yes then add otherwise throw exception
            double lastNumberOfSkeletal = this.skeletonSeries.get(this.skeletonSeries.size() - 1);
            if (Math.pow(lastNumberOfSkeletal+1, 2) == number) {
                this.series.add(number);
                this.skeletonSeries.add(lastNumberOfSkeletal+1);
            } else System.out.println("Doesn't belong to series");
        }
    }

    public void insertNextTerm() {
        if (this.isInitialized) {
            double lastNumberOfSkeletal = this.skeletonSeries.get(this.skeletonSeries.size() - 1);
            this.series.add(Math.pow(lastNumberOfSkeletal+1, 2));
            this.skeletonSeries.add(lastNumberOfSkeletal+1);
        } else System.out.println("Series has not been initialized with a number. Use addNumberToSeries(n)");
    }

    public void insertNextTerm(int n) {
        if (this.isInitialized) {
            for (int i = 0; i < n; i++) {
                this.insertNextTerm();
            }
        } else System.out.println("Series has not been initialized with a number. Use addNumberToSeries(n)");
    }

    public double getNthTerm(int n) {
        try {
            if (this.isInitialized) {
                return Math.pow(this.skeletonSeries.get(n-1), 2);
            } else System.out.println("This number might not be in the series");; return 0;
        } catch (IndexOutOfBoundsException e) { System.out.println("This number might not be in the series"); return 0; }
    }

    public double getSum() {
        if (this.isInitialized) {
            double sum = 0;
            for (double e : this.series) {
                sum += e;
            }
            return sum;
        }
        return 0;
    }
}
