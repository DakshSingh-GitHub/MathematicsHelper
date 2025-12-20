import pnc.base.*;
import pnc.solver.PncSolver;

public class Main {
    public static void main(String[] args) {
        Combination com = new Combination(15, 11);
        System.out.println(com.getCombination());

        PncSolver solver = new PncSolver(5, "combination");
        long permResult = solver.calculateCombination(3);
        System.out.println(permResult);
    }
}
