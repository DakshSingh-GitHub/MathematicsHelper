import pnc.base.*;

public class Main {
    public static void main(String[] args) {
        Combination perm = new Combination(5);
        System.out.println("Number of combinations of C(5,2): " + perm.combineWithVariableValue(2));
        System.out.println("Number of combinations of C(5,3): " + perm.combineWithVariableValue(3));
        System.out.println("Number of combinations of C(5,2): " + perm.getCombination());
    }
}
