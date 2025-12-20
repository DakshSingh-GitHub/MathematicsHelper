package pnc.solver;

import pnc.base.Combination;
import pnc.base.Permutation;

public class PncSolver {
    int totalobjectsN;
    String method;

    public PncSolver(int totalobjectsN, String method, String title) {
        this.totalobjectsN = totalobjectsN;
        if (method.toLowerCase() == "permutation") this.method = "permutation";
        else if (method.toLowerCase() == "combination") this.method = "combination";
        else this.method = "unknown";
    }

    public PncSolver(int totalobjectsN, String method) {
        this.totalobjectsN = totalobjectsN;
        if (method.toLowerCase() == "permutation") this.method = "permutation";
        else if (method.toLowerCase() == "combination") this.method = "combination";
        else this.method = "unknown";
    }

    public int getTotalObjectsN() { return totalobjectsN; }
    public String getMethod() { return method; }

    public static void getHelp() {
        System.out.println("Available methods:\n" +
                "You can provide title (that's completely optional, just for better understanding) at the last parameter of constructor.\n" +
                "1. permutation - Calculate permutations of N objects.\n" +
                "2. combination - Calculate combinations of N objects.\n" +
                "Usage:\n" +
                "Create an instance of PncSolver with totalobjectsN and method.\n" +
                "Then call the appropriate calculation method based on the chosen method.");
    }

    public static void getHelp(String method) {
        if (method.toLowerCase() == "permutation") {
            System.out.println("Permutation Method Help:\n" +
                    "Calculate permutations of N objects.\n" +
                    "Usage:\n" +
                    "Create an instance of PncSolver with totalobjectsN and 'permutation' as method.\n" +
                    "Then call calculatePermutation() to get the result.");
        } else if (method.toLowerCase() == "combination") {
            System.out.println("Combination Method Help:\n" +
                    "Calculate combinations of N objects.\n" +
                    "Usage:\n" +
                    "Create an instance of PncSolver with totalobjectsN and 'combination' as method.\n" +
                    "Then call calculateCombination(waystoselectC) to get the result.");
        } else {
            System.out.println("Unknown method. Available methods are 'permutation' and 'combination'.");
        }
    }

    public static void getExamplesOfMethods() {
        // Real life examples can be added here. Like, selecting team members, arranging books.
        System.out.println("Examples of Methods:\n" +
                "1. Permutation: Arranging 5 books on a shelf.\n" +
                "2. Combination: Selecting 3 team members from a group of 5.");
    }

    public long calculatePermutation(int waystorearrangeC) {
        if (this.method == "permutation") {
            Permutation permutationCalculator = new Permutation(this.totalobjectsN, waystorearrangeC);
            return permutationCalculator.getPermutations();
        } else return -1;
    }

    public long calculateCombination(int waystoselectC) {
        if (this.method == "combination") {
            Combination combinationCalculator = new Combination(this.totalobjectsN, waystoselectC);
            return combinationCalculator.getCombination();
        } else return -1;
    }
}
