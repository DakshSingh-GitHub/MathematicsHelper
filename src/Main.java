import geometry.shapes.Circle;
import geometry.shapes.Line2D;
import geometry.base.Point;

public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(new Point(0, 0), new Point(0, 5));
        Line2D line = new Line2D(new Point(0, 1), 3);
        System.out.println("Equation of the circle: " + circle.getEquationOfCircle());
        System.out.println("Equation of the line: " + line.LineEquations().get("Equation"));
        try {
            for (Point p : circle.getIntersectionPointsWithLine(line)) {
                System.out.println("Intersection Point: (" + p.x + ", " + p.y + ")");
            }
        } catch (Exception e) {
            System.out.println("Cannot read null intersection points");
        }
        
        System.out.println("Tangent equations to the circle with slope 2:");
        for (String s : circle.equationOfTangent(new Point(-4, 3))){ System.out.println(s); }
    }
}
