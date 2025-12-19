import pnc.base.*;
import pnc.solver.PncSolver;

public class Main {
    public static void main(String[] args) {
        PncSolver.getHelp();
        PncSolver.getExamplesOfMethods();
        PncSolver pnc = new PncSolver(5, "combination");
        long result = pnc.calculateCombination(2);
        System.out.println("Combination Result: " + result);
    }
}
