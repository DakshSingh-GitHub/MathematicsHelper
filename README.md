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


> pnc.base

### `Factorial`

A simple class to calculate the factorial of a non-negative integer.

#### Methods:
*   `Factorial(int num)`: Constructor that calculates the factorial of `num`.
*   `int getFactorial()`: Returns the calculated factorial.

### `Combination`

This class calculates combinations (nCr), which represents the number of ways to choose 'r' items from a set of 'n' items where order does not matter.

#### Methods:
*   `Combination(int n, int c)`: Calculates nCr upon instantiation.
*   `Combination(int n)`: Initializes with 'n' but defers calculation.
*   `long combineWithVariableValue(int value)`: Calculates nCr with a variable 'r' value.
*   `long getCombination()`: Returns the pre-calculated combination value.

### `Permutation`

This class calculates permutations (nPr), which represents the number of ways to arrange 'r' items from a set of 'n' items where order matters.

#### Methods:
*   `Permutation(int totalobjectsN, int waystorearrangeC)`: Calculates nPr upon instantiation.
*   `Permutation(int totalobjectsN)`: Initializes with 'n' but defers calculation.
*   `long permuteWithVariableValue(int value)`: Calculates nPr with a variable 'r' value.
*   `long getPermutations()`: Returns the pre-calculated permutation value.

> pnc.solver

This package provides a solver for permutation and combination problems.

### `PncSolver`

A class that simplifies solving permutation and combination problems based on user input.

#### Methods:
*   `PncSolver(int totalobjectsN, String method)`: Initializes the solver with the total number of objects and the method ("permutation" or "combination").
*   `PncSolver(int totalobjectsN, String method, String title)`: An overloaded constructor that includes an optional title for the problem.
*   `long calculatePermutation()`: Calculates the factorial of 'n' (n!).
*   `long calculateCombination(int waystoselectC)`: Calculates nCr.
*   `static void getHelp()`: Displays general help information.
*   `static void getHelp(String method)`: Provides help for a specific method.
*   `static void getExamplesOfMethods()`: Shows real-life examples of when to use permutation vs. combination.

> geometry.base

This package provides the basic building blocks for geometric calculations.

### `Point`

Represents a point in 2D or 3D space.

#### Methods:
*   `Point(double x, double y)`: Creates a 2D point.
*   `Point(double x, double y, double z)`: Creates a 3D point.
*   `static double distance(Point p1, Point p2)`: Calculates the distance between two points.
*   `ArrayList<Double> getCoordinates2D()`: Returns the 2D coordinates of the point.

> geometry.shapes

This package provides classes for various geometric shapes.

### `Circle`

Represents a circle in 2D space.

#### Methods:
*   `Circle(Point center)`: Creates a circle with a given center and no defined radius.
*   `Circle(Point center, Point anyPoint)`: Creates a circle with a given center and a point on its circumference.
*   `void updateRadiusWithNewPoint(Point secondPoint)`: Updates the circle's radius using a new point.
*   `void addPointonCircle(Point p)`: Adds a point to the circle's definition if it lies on the circumference.
*   `boolean checkPointOnCircle(Point p)`: Checks if a given point lies on the circle.
*   `double circumference()`: Calculates the circumference of the circle.
*   `double area()`: Calculates the area of the circle.
*   `String getEquationOfCircle()`: Returns the equation of the circle.
*   `int checkPositionOfPoint(Point p)`: Determines if a point is inside, outside, or on the circle.
*   `int checkPositionOfLine(Line2D line)`: Determines if a line is a tangent, secant, or outside the circle.
*   `ArrayList<Point> getIntersectionPointsWithLine(Line2D line)`: Calculates the intersection points of the circle and a line.
*   `ArrayList<String> equationOfTangent(double slope)`: Returns the equations of tangents with a given slope.
*   `ArrayList<String> equationOfTangent(Point p)`: Returns the equation of the tangent at a specific point on the circle.

### `Line2D`

Represents a line in 2D space.

#### Methods:
*   `Line2D(Point p1, Point p2)`: Creates a line defined by two points.
*   `Line2D(Point p1, double slope)`: Creates a line defined by a point and a slope.
*   `void updateSlope(double slope)`: Updates the slope of the line.
*   `void addPointOnLine(Point p)`: Adds a point to the line's definition if it lies on the line.
*   `Map<String, String> LineEquations()`: Returns the general and slope-intercept equations of the line.
*   `double distanceFromPoint(Point p)`: Calculates the perpendicular distance from a point to the line.

### `Triangle`

Represents a triangle in 2D or 3D space.

#### Methods:
*   `Triangle(Point p1, Point p2, Point p3)`: Creates a triangle from three points, with validation.
*   `double area()`: Calculates the area of the triangle.
*   `Point centroid()`: Calculates the centroid of the triangle.
*   `Point orthocenter3D()`: Calculates the orthocenter of the triangle in 3D space.
