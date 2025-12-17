package sequences.progression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Represents a geometric progression (GP) of double-precision floating-point numbers.
 * <p>
 * A geometric progression is a sequence of non-zero numbers where each term after the first
 * is found by multiplying the previous one by a fixed, non-zero number called the common ratio.
 *
 * <h3>Instance Formation Rules:</h3>
 * An instance of this class can be in one of three states:
 * <ul>
 *     <li><b>Empty:</b> Created with the no-argument constructor. It has no terms.</li>
 *     <li><b>Uninitialized:</b> Created with one term. The common ratio is not yet known.
 *         A second term must be inserted to initialize the progression.</li>
 *     <li><b>Initialized:</b> Created with two terms, or by adding a second term to an
 *         uninitialized progression. The common ratio is known, and the sequence
 *         can be extended.</li>
 * </ul>
 * Note: A first term of zero is not supported for calculating a common ratio.
 *
 * <h3>Method Overview:</h3>
 * <ul>
 *     <li><b>Constructors:</b>
 *         <ul>
 *             <li>{@code GeometricProgression()}: Creates an empty GP.</li>
 *             <li>{@code GeometricProgression(double first_term)}: Creates a GP with one term (uninitialized).</li>
 *             <li>{@code GeometricProgression(double first_term, double second_term)}: Creates a fully initialized GP.</li>
 *         </ul>
 *     </li>
 *     <li><b>Modification Methods:</b>
 *         <ul>
 *             <li>{@code insertTerm(double n)}: Inserts a term, initializing the GP if needed or validating against the existing ratio.</li>
 *             <li>{@code insertTillNthTerm(int n)}: Extends the sequence to a total of 'n' terms.</li>
 *             <li>{@code safeInsertNextTerm()}: Calculates and adds the next term automatically.</li>
 *             <li>{@code removeLastTerm()}: Removes and returns the last term from the sequence.</li>
 *             <li>{@code removeLastNTerms(int n)}: Removes the last 'n' terms.</li>
 *             <li>{@code clearGP()}: Empties the sequence, saving the old one to a history heap.</li>
 *             <li>{@code updateGP(double n_second_term)}: Changes the second term and recalculates the entire sequence.</li>
 *             <li>{@code resetGP(double n_first_term, double n_second_term)}: Replaces the sequence with a new one.</li>
 *         </ul>
 *     </li>
 *     <li><b>Calculation and Prediction:</b>
 *         <ul>
 *             <li>{@code predictTerm()}: Returns the value of the next term without adding it.</li>
 *             <li>{@code predictTerm(int n)}: Calculates the value of the nth term in the series.</li>
 *             <li>{@code sumOfGP()}: Returns the sum of all terms currently in the sequence.</li>
 *             <li>{@code projectedSumOfGP(int n)}: Calculates the sum of the first 'n' terms of the series.</li>
 *         </ul>
 *     </li>
 *     <li><b>State and Validation:</b>
 *         <ul>
 *             <li>{@code getCurrentSequence()}: Returns a copy of the current sequence.</li>
 *             <li>{@code getRemovedHeap()}: Returns a history of all cleared sequences.</li>
 *             <li>{@code clearRemovedHeap()}: Clears the history of removed sequences.</li>
 *             <li>{@code getGPCurrentValidityTestResult()}: Returns a map with the current state (empty, initialized, valid).</li>
 *             <li>{@code isValidGP(ArrayList<Double> sequence)}: A static method to check if any list of doubles is a valid GP.</li>
 *         </ul>
 *     </li>
 * </ul>
 */

public class GeometricProgression {
    private double first_term, second_term;
    private double common_ratio;
    private boolean isEmpty;
    private boolean isInitialized;
    private ArrayList<Double> sequence;
    private ArrayList<ArrayList<Double>> removedHeap = new ArrayList<>();

    public GeometricProgression() {
        this.isEmpty = true;
        this.isInitialized = false;
        this.sequence = new ArrayList<Double>();
    }

    public GeometricProgression(double first_term) {
        this.first_term = first_term;
        this.isEmpty = false;
        this.isInitialized = false;
        this.sequence = new ArrayList<Double>();
        this.sequence.add(this.first_term);
    }

    public GeometricProgression(double first_term, double second_term) {
        this.first_term = first_term;
        this.second_term = second_term;
        this.common_ratio = this.second_term / this.first_term;
        this.isEmpty = false;
        this.isInitialized = true;
        this.sequence = new ArrayList<Double>();
        this.sequence.add(this.first_term);
        this.sequence.add(this.second_term);
    }

    public void insertTerm(double n) {
        if (this.isEmpty==true && this.isInitialized==false) {
            this.first_term = n;
            this.sequence.add(n);
            this.isEmpty = false;
            this.isInitialized = false;
        } else if (this.isEmpty==false && !this.isInitialized==true) { 
            this.second_term = n;
            this.common_ratio = this.second_term / this.first_term;
            this.sequence.add(n);
            this.isInitialized = true;
        } else if (this.isEmpty==false && this.isInitialized==true) {
            if (this.sequence.get(this.sequence.size()-1)*this.common_ratio == n) {
                this.sequence.add(n);
            } else { System.out.println("The number " + n + " doesn't belong to the series"); }
        }
    }

    public void insertTillNthTerm(int n) {
        if ((this.isEmpty == true && this.isInitialized == false) || (this.isEmpty == false && this.isInitialized == false)) {
            System.out.println("Can't add on a non-initialized AP");
        } else {
            int sizeOfSequence = this.sequence.size();
            if (sizeOfSequence < n) {
                double common_ratio = this.common_ratio;
                ArrayList<Double> numbersOfInsertion = new ArrayList<>();
                for ( int i = sizeOfSequence; i < n; i++ ) {
                    double nextnumber = this.first_term*(Math.pow(common_ratio, i));
                    numbersOfInsertion.add(nextnumber);
                }
                numbersOfInsertion.forEach(e -> { this.sequence.add(e); });
            } 
            else { System.out.println("The new insertion range should be greater than last: " + sizeOfSequence); }
        }
    }

    // This is a safe insert function, it doesn't show any error because the next insertion term is automated
    public void safeInsertNextTerm() {
        if (this.isEmpty == true && this.isInitialized == false) System.out.println("Can't addd on a non-initialized GP");
        else {
            double currentLastTerm = this.sequence.get(this.sequence.size()-1);
            double nextTerm = currentLastTerm * this.common_ratio;
            this.sequence.add(nextTerm);
        }
    }

    // Suggest better ways
    public void clearGP() {
        ArrayList<Double> copy = new ArrayList<Double>();
        this.sequence.forEach(e -> { copy.add(e); });
        this.removedHeap.add(copy);
        this.sequence.clear();
        this.isEmpty = true;
        this.isInitialized = false;
    }

    public void updateGP(double n_second_term) {
        this.second_term = n_second_term;
        this.common_ratio = this.second_term / this.first_term;
        double abs_f_term = this.sequence.get(0);
        ArrayList<Double> copy = new ArrayList<Double>();
        this.sequence.forEach(e -> { copy.add(e); });
        this.removedHeap.add(copy);
        this.sequence.clear();
        this.sequence.add(abs_f_term);
        this.sequence.add(this.second_term);
    }

    public void resetGP(double n_first_term, double n_second_term) {
        this.first_term = n_first_term;
        this.second_term = n_second_term;
        this.common_ratio = this.second_term / this.first_term;
        ArrayList<Double> copy = new ArrayList<Double>();
        this.sequence.forEach(e -> { copy.add(e); });
        this.removedHeap.add(copy);
        this.sequence.clear();
        this.sequence.add(this.first_term);
        this.sequence.add(this.second_term);
        this.isEmpty = false;
        this.isInitialized = true;
    }

    public double predictTerm() {
        double nextTerm = this.sequence.get(this.sequence.size()-1) * this.common_ratio;
        return nextTerm;
    }

    public double predictTerm( int n ) {
        double nextTerm = this.first_term * Math.pow(this.common_ratio, n-1);
        return nextTerm;
    }

    public double removeLastTerm() {
        if( this.isEmpty == false ) { return 0; } 
        else { return this.sequence.remove(this.sequence.size()-1); }
    }

    public void removeLastNTerms( int n ) { for ( int i = 0; i < n; i++ ) removeLastTerm(); }

    public double sumOfGP() {
        double sum = 0.0;
        if (this.isEmpty == true) { System.out.println("Sum can't be initialized on an empty GP"); }
        else {
            double n = this.sequence.size();
            double a = this.sequence.get(0);
            double r = this.common_ratio;
            if (r < 1) sum = a*(1-Math.pow(r, n))/(1-r);
            else sum = a*(Math.pow(r, n)-1)/(r-1);
        }
        return sum;
    }

    // Returns sum of the geometric progression till any specified number/place
    public double projectedSumOfGP(int n) {
        double sum = 0.0;
        if (this.isEmpty == true) { System.out.println("Sum can't be initialized on an empty GP"); }
        else {
            double a = this.sequence.get(0);
            double r = this.common_ratio;
            if (r < 1) sum = a*(1-Math.pow(r, n))/(1-r);
            else sum = a*(Math.pow(r, n)-1)/(r-1);
        }
        return sum;
    }

    public static boolean isValidGP(ArrayList<Double> sequence) {
        if (sequence.size() == 0 || sequence.size() == 1 || sequence.size() == 2) { return false; }
        else {
            // ArrayList<Double> seq = sequence;
            int size = sequence.size();
            double f_term = sequence.get(0);
            double s_term = sequence.get(1);
            double common_ratio = s_term / f_term;
            ArrayList<Double> checkGP = new ArrayList<>() {{ add(f_term);add(s_term); }};
            double ct = 2;
            for ( int i = 0; i < size - 2; i++ ) { checkGP.add(f_term * Math.pow(common_ratio, ct)); ct++; }
            for ( int i = 0; i < size; i++ ) { if (sequence.get(i).doubleValue() != checkGP.get(i).doubleValue()) {return false;} }
        }
        return true;
    }

    public Map<String, Boolean> getGPCurrentValidityTestResult() { 
        Map<String, Boolean> res = new HashMap<>();
        res.put("is_gp_empty", this.isEmpty);
        res.put("is_gp_initialized", this.isInitialized);
        res.put("is_gp_valid", GeometricProgression.isValidGP(this.sequence));
        return res;
    }

    public void clearRemovedHeap() { this.removedHeap.clear(); }
    public ArrayList<ArrayList<Double>> getRemovedHeap() { return this.removedHeap; }
    public ArrayList<Double> getCurrentSequence() { return this.sequence; }
}
