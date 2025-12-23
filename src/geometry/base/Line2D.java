package geometry.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Line2D {
    private Point p1;
    Point p2;
    private double slope;
    private double x_intercept;
    private double y_intercept; 
    public ArrayList<Point> pointsOnLine = new ArrayList<>();
    private String equationOfLine;
    private String generalEquationOfLine;

    public Line2D(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.slope = (p2.y - p1.y) / (p2.x - p1.x);
        this.y_intercept = p1.y - slope*p1.x;
        this.x_intercept = -this.y_intercept / slope;

        if (this.y_intercept > 0) {
            equationOfLine = "y = " + slope + "x + " + this.y_intercept;
            generalEquationOfLine = "x/" + this.x_intercept + " + y/" + this.y_intercept + " = 1";
        } else if (this.y_intercept < 0) {
            equationOfLine = "y = " + slope + "x - " + Math.abs(this.y_intercept);
            generalEquationOfLine = "x/" + this.x_intercept + " - y/" + Math.abs(this.y_intercept) + " = 1";
        } else if (this.y_intercept == 0) { 
            equationOfLine = "y = " + slope + "x";
            generalEquationOfLine = "x/" + this.x_intercept + " = 1";
        }

        
        pointsOnLine.add(p1);
        pointsOnLine.add(p2);
    }

    public Line2D(Point p1, double slope) {
        this.p1 = p1;
        this.slope = slope;
        this.y_intercept = p1.y - slope*p1.x;
        this.x_intercept = -this.y_intercept / slope;

        if (this.y_intercept > 0) {
            equationOfLine = "y = " + slope + "x + " + this.y_intercept;
            generalEquationOfLine = "x/" + this.x_intercept + " + y/" + this.y_intercept + " = 1";
        } else if (this.y_intercept < 0) {
            equationOfLine = "y = " + slope + "x - " + Math.abs(this.y_intercept);
            generalEquationOfLine = "x/" + this.x_intercept + " - y/" + Math.abs(this.y_intercept) + " = 1";
        } else if (this.y_intercept == 0) { 
            equationOfLine = "y = " + slope + "x";
            generalEquationOfLine = "x/" + this.x_intercept + " = 1";
        }

        pointsOnLine.add(p1);
    }

    public void updateSlope(double slope) {
        pointsOnLine.clear();
        pointsOnLine.add(p1);
        this.slope = slope;
        this.y_intercept = p1.y - slope*p1.x;
        this.x_intercept = -this.y_intercept / slope;
        if (this.y_intercept > 0) {
            equationOfLine = "y = " + slope + "x + " + this.y_intercept;
            generalEquationOfLine = "x/" + this.x_intercept + " + y/" + this.y_intercept + " = 1";
        } else if (this.y_intercept < 0) {
            equationOfLine = "y = " + slope + "x - " + Math.abs(this.y_intercept);
            generalEquationOfLine = "x/" + this.x_intercept + " - y/" + Math.abs(this.y_intercept) + " = 1";
        } else if (this.y_intercept == 0) { 
            equationOfLine = "y = " + slope + "x";
            generalEquationOfLine = "x/" + this.x_intercept + " = 1";
        }
    }

    public void addPointOnLine(Point p) {
        if (p.y == this.slope*p.x + this.y_intercept) { pointsOnLine.add(p); }
        else { System.out.println("This Point (" + p.x + ", " + p.y + ") doesn't lie on the line"); }
    }

    public Map<String, String> LineEquations() { 
        Map<String, String> res = new HashMap<>();
        res.put("General Equation", this.generalEquationOfLine);
        res.put("Equation", this.equationOfLine);
        res.put("slope", Double.toString(this.slope));
        res.put("y_intercept", Double.toString(this.y_intercept));
        res.put("x_intercept", Double.toString(this.x_intercept));
        return res;
    }

    public double distanceFromPoint(Point p) {
        double numerator = Math.abs(this.slope*p.x - p.y + this.y_intercept);
        double denominator = Math.sqrt(this.slope*this.slope + 1);
        return numerator / denominator;
    }

    public ArrayList<Point> getUsedPointsOnLine() { return this.pointsOnLine; }
    public double getSlope() { return this.slope; }
    public double getX_intercept() { return this.x_intercept; }
    public double getY_intercept() { return this.y_intercept; }
}
