
## How to Use:
1. I'll Add the library *.jar as a Pre-Release version on `versions`
2. Go to your IDE and import this *.jar file as external library refrences
3. To create a jar package, go to the bin folder and run `jar cf mathematics.jar .`

## Project Classes

> sequences.progression

This project provides classes for handling mathematical progressions.

### `ArithematicProgression`

Represents an arithmetic progression (AP) of `double` values. An arithmetic progression is a sequence of numbers where the difference between consecutive terms is constant.

#### Instance States:
*   **Empty:** Created with `ArithematicProgression()`. Has no terms.
*   **Uninitialized:** Created with `ArithematicProgression(firstTerm)`. The common difference is unknown until a second term is added.
*   **Initialized:** Created with `ArithematicProgression(firstTerm, secondTerm)`. The common difference is known, and the sequence can be extended.

#### Key Methods:
*   `insertTerm(double number)`: Adds a new term, initializing or validating the sequence.
*   `insertTillNthTerm(int n)`: Extends the sequence to a total of 'n' terms.
*   `safeInsertNextTerm()`: Automatically calculates and adds the next correct term.
*   `sumOfAP()`: Calculates the sum of the current terms.
*   `projectedSumOfAP(int n)`: Calculates the sum of the first 'n' terms.
*   `predictTerm()`: Predicts the next term without adding it to the sequence.
*   `removeLastTerm()`: Removes the last term from the sequence.
*   `clearAP()` / `resetAP()`: Manages the sequence's lifecycle.

### `GeometricProgression`

Represents a geometric progression (GP) of `double` values. A geometric progression is a sequence where each term after the first is found by multiplying the previous one by a fixed, non-zero number (the common ratio).

#### Instance States:
*   **Empty:** Created with `GeometricProgression()`. Has no terms.
*   **Uninitialized:** Created with `GeometricProgression(firstTerm)`. The common ratio is unknown.
*   **Initialized:** Created with `GeometricProgression(firstTerm, secondTerm)`. The common ratio is known.

#### Method Overview:
*   **Modification:**
    *   `insertTerm(double n)`: Inserts a term, initializing or validating the GP.
    *   `insertTillNthTerm(int n)`: Extends the sequence to 'n' terms.
    *   `safeInsertNextTerm()`: Automatically calculates and adds the next term.
    *   `removeLastTerm()`: Removes the last term.
    *   `clearGP()` / `resetGP()` / `updateGP()`: Manages the sequence's lifecycle.
*   **Calculation & Prediction:**
    *   `predictTerm()`: Returns the value of the next term without adding it.
    *   `predictTerm(int n)`: Calculates the value of the nth term.
    *   `sumOfGP()`: Returns the sum of all terms currently in the sequence.
    *   `projectedSumOfGP(int n)`: Calculates the sum of the first 'n' terms.
*   **State & Validation:**
    *   `getCurrentSequence()`: Returns a copy of the current sequence.
    *   `getRemovedHeap()`: Returns a history of all cleared sequences.
    *   `isValidGP(ArrayList<Double> sequence)`: A static method to check if any list of doubles is a valid GP.
