import pnc.base.*;
import pnc.solver.PncSolver;

public class Main {
    public static void main(String[] args) {
        Combination c1 = new Combination(28, 3);
        Combination com = new Combination(7, 3);
        System.out.println(c1.getCombination() - 8*com.getCombination());

    }
}
