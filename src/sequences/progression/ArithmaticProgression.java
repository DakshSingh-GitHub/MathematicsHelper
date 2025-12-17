package sequences.progression;

import java.util.*;

/**
 * Represents an arithmetic progression (AP) of double-precision floating-point numbers.
 * <p>
 * An arithmetic progression is a sequence of numbers such that the difference between
 * consecutive terms is constant. This constant is called the common difference.
 *
 * <h3>Instance Formation Rules:</h3>
 * An instance of this class can be in one of three states:
 * <ul>
 *     <li><b>Empty:</b> Created with the no-argument constructor. It has no terms.</li>
 *     <li><b>Uninitialized:</b> Created with one term. The common difference is not yet known.
 *         A second term must be inserted to initialize the progression.</li>
 *     <li><b>Initialized:</b> Created with two terms, or by adding a second term to an
 *         uninitialized progression. The common difference is known, and the sequence
 *         can be extended.</li>
 * </ul>
 *
 * This class provides methods to insert terms, calculate sums, and manage the sequence.
 */


public class ArithmaticProgression {
    private double firstNumber;
    private double secondNumber;
    private double difference;
    private boolean isEmpty;
    private boolean isInitialized;
    private ArrayList<Double> sequence;
    private ArrayList<ArrayList<Double>> removedHeap = new ArrayList<>();


    public ArithmaticProgression (double firstNumber, double secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.difference = this.secondNumber - this.firstNumber;
        this.sequence = new ArrayList<>();
        sequence.add(this.firstNumber);
        sequence.add(this.secondNumber);
        this.isEmpty = false;
        this.isInitialized = true;
    }

    public ArithmaticProgression (double firstNumber) {
        this.firstNumber = firstNumber;
        this.sequence = new ArrayList<>();
        sequence.add(this.firstNumber);
        this.isEmpty = false;
        this.isInitialized = false;
    }

    public ArithmaticProgression () {
        this.isEmpty = true;
        this.isInitialized = false;
        this.sequence = new ArrayList<>();
    }

    // renamed from insertNumber to insertTerm
    public void insertTerm(double number) {
        if (this.isEmpty == true && this.isInitialized == false) {
            this.firstNumber = number;
            this.sequence.add(number);
            this.isEmpty = false;
            this.isInitialized = false;
        } else if (this.isEmpty == false && this.isInitialized == false) {
            double sec_no = number;
            this.secondNumber = sec_no;
            this.difference = this.secondNumber - this.firstNumber;
            this.sequence.add(sec_no);
            this.isEmpty = false;
            this.isInitialized = true;
        } else {
            double lastnumber = this.sequence.get(this.sequence.size() - 1);
            if (number - lastnumber == this.difference) { this.sequence.add(number); }
            else { System.out.println("The number " + number + " doesn't match the AP"); }
        }
    }

    // Inserts terms to the nth term without error because user only has to specify of what lenth the array should be, and other stuff is handled within the code. Inserts till the size of the sequence gets "N"
    public void insertTillNthTerm(int n) {
        if ((this.isEmpty == true && this.isInitialized == false) || (this.isEmpty == false && this.isInitialized == false)) {
            System.out.println("Can't add on a non-initialized AP");
        } else {
            int sizeOfSequence = this.sequence.size();
            if (sizeOfSequence < n) {
                double difference = this.difference;
                ArrayList<Double> numbersOfInsertion = new ArrayList<>();
                for ( int i = sizeOfSequence; i < n; i++ ) {
                    double nextnumber = this.firstNumber + (i)*difference;
                    numbersOfInsertion.add(nextnumber);
                }
                numbersOfInsertion.forEach(e -> { this.sequence.add(e); });
            }
            else { System.out.println("The new insertion range should be greater than current size: " + sizeOfSequence); }
        }
    }

    public void clearAP() {
        ArrayList<Double> copy = new ArrayList<>();
        this.sequence.forEach(e -> { copy.add(e); });
        this.removedHeap.add(copy);
        this.sequence.clear();
        this.isEmpty = true;
        this.isInitialized = false;
    }

    public void resetAP(double n_first_term, double n_second_term) {
        ArrayList<Double> copy = new ArrayList<>();
        this.sequence.forEach( e -> { copy.add(e); });
        this.removedHeap.add(copy);
        this.sequence.clear();
        this.firstNumber = n_first_term;
        this.secondNumber = n_second_term;
        this.difference = this.secondNumber - this.firstNumber;
        this.isEmpty = false;
        this.isInitialized = true;
        this.sequence.add(this.firstNumber);
        this.sequence.add(this.secondNumber);
    }

    public void updateAP(double n_second_term) {
        this.secondNumber = n_second_term;
        this.difference = this.secondNumber - this.firstNumber;
        double abs_f_term = this.sequence.get(0);
        ArrayList<Double> copy = new ArrayList<>();
        this.sequence.forEach(e -> { copy.add(e); });
        this.removedHeap.add(copy);
        this.sequence.clear();
        this.sequence.add(abs_f_term);
        this.sequence.add(this.secondNumber);
        this.isEmpty = false;
    }
    
    // Returns no error while inserting as it predicts next term, properly and inserts it, without the user having to define the term so prevents error
    public void safeInsertNextTerm() {
        if (this.isEmpty) { System.out.println("No series has been specified"); }
        else {
            double lastnumber = this.sequence.get(this.sequence.size() - 1);
            double nextnumber = lastnumber + this.difference;
            this.sequence.add(nextnumber);
        }
    }

    // Renamed from predictNextNumber to predictTerm
    public void predictTerm() {
        if (this.isEmpty) { System.out.println("No series has been specified"); }
        else {
            double lastnumber = this.sequence.get(this.sequence.size() - 1);
            double nextnumber = lastnumber + this.difference;
            System.out.println("The next number in the AP is (Not added to AP): " + nextnumber);
        }
    }

    // Added a method predictTerm(int n)
    public void predictTerm(int n) {
        if (this.isEmpty) { System.out.println("No series has been specified"); }
        else {
            double first_number = this.firstNumber;
            double difference = this.difference;
            double nextnumber = first_number + (n-1)*difference;
            System.out.println("The next number in the AP is (Not added to AP): " + nextnumber);
        }
    }

    // Renamed from removeNextNumber to removeLastTerm.
    public double removeLastTerm() {
        if (this.isEmpty) { return 0; }
        else {  return this.sequence.remove(this.sequence.size() - 1); }
    }

    // Removes the last N terms given by user
    public void removeLastNTerms( int n ) { for ( int i = 0; i < n; i++ ) removeLastTerm(); }

    public double sumOfAP() {
        double sum =  0;
        if (this.isEmpty) { System.out.println("Sum can't be initialized on an empty AP"); }
        else {
            double n = this.sequence.size();
            sum = (n/2) * (2*this.firstNumber + (n-1)*this.difference);  
        }
        return sum;
    }

    public double projectedSumOfAP(int n) {
        double sum =  0;
        if (this.isEmpty) { System.out.println("Sum can't be initialized on an empty AP"); }
        else {
            sum = (n/2) * (2*this.firstNumber + (n-1)*this.difference);  
        }
        return sum;
    }

    public static boolean isValidAPDouble(ArrayList<Double> sequence) {
        if (sequence.size() == 0 || sequence.size() == 1 || sequence.size() == 2) { return false; }
        else if (sequence.size() < 2) { return false; }
        else {
            double f_number = sequence.get(0);
            double s_number = sequence.get(1);
            ArrayList<Double> checkAp = new ArrayList<>() {{ add(f_number);add(s_number); }};
            double diff = s_number - f_number;
            double ct = 2;
            int size = sequence.size();
            for ( int i = 0; i < size-2; i++ ) { checkAp.add(sequence.get(0) + ct*diff); ct++; }
            for ( int i = 0; i < size; i++ ) { if (sequence.get(i).doubleValue() != checkAp.get(i).doubleValue()) { return false; } }
            return true;
        }
    }

    public static boolean isValidGPInteger(ArrayList<Integer> sequence) {
        if (sequence.size() == 0 || sequence.size() == 1 || sequence.size() == 2) { return false; }
        else if (sequence.size() < 2) { return false; }
        else {
            int f_number = sequence.get(0);
            int s_number = sequence.get(1);
            ArrayList<Integer> checkAp = new ArrayList<>() {{ add(f_number);add(s_number); }};
            int diff = s_number - f_number;
            int ct = 2;
            int size = sequence.size();
            for ( int i = 0; i < size-2; i++ ) { checkAp.add(sequence.get(0) + ct*diff); ct++; }
            for ( int i = 0; i < size; i++ ) { if (sequence.get(i).intValue() != checkAp.get(i).intValue()) { return false; } }
            return true;
        }
    }

    public Map<String, Boolean> getAPCurrentValidityTestResult() { 
        Map<String, Boolean> res = new HashMap<>();
        res.put("is_ap_empty", this.isEmpty);
        res.put("is_ap_initialized", this.isInitialized);
        res.put("is_ap_valid", ArithmaticProgression.isValidAPDouble(this.sequence));
        return res;
    }

    public void clearRemovedHeap() { this.removedHeap.clear(); }
    public ArrayList<Double> getCurrentSequence() { return this.sequence; }
    public ArrayList<ArrayList<Double>> getRemovedHeap() { return this.removedHeap; }
}
