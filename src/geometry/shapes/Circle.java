package geometry.shapes;

import java.util.ArrayList;

import geometry.base.Line2D;
import geometry.base.Point;

public class Circle {
    Point center;
    Point anyPoint;
    boolean isRadiusGiven;
    double radius;
    private String equationOfCircle;
    private ArrayList<Point> pointsOnCircle = new ArrayList<>();
    private ArrayList<ArrayList<Point>> removedHeap = new ArrayList<>();

    public Circle(Point center) {
        this.center = center;
        this.radius = 0;
        this.equationOfCircle = "";
        this.isRadiusGiven = false;
    }

    public Circle(Point center, Point anyPoint) {
        this.center = center;
        this.anyPoint = anyPoint;
        this.radius = Point.distance(center, anyPoint);
        this.isRadiusGiven = true;
        if (this.center.x == 0 && this.center.y == 0) {
            this.equationOfCircle = "x^2 + y^2 = " + (this.radius * this.radius);
        } else if (this.center.x == 0) {
            this.equationOfCircle = "x^2 + (y-(" + this.center.y + "))^2 = " + (this.radius * this.radius);
        } else if (this.center.y == 0) {
            this.equationOfCircle = "(x-(" + this.center.x + "))^2 + y^2 = " + (this.radius * this.radius);
        } else {
            this.equationOfCircle = "(x-(" + this.center.x + "))^2 + (y-(" + this.center.y + "))^2 = " + (this.radius * this.radius);
        }
        this.pointsOnCircle.add(anyPoint);
    }

    public void updateRadiusWithNewPoint(Point secondPoint) {
        this.anyPoint = secondPoint;
        this.radius = Point.distance(this.center, secondPoint);
        this.isRadiusGiven = true;
        if (this.center.x == 0 && this.center.y == 0) {
            this.equationOfCircle = "x^2 + y^2 = " + (this.radius * this.radius);
        } else if (this.center.x == 0) {
            this.equationOfCircle = "x^2 + (y-(" + this.center.y + "))^2 = " + (this.radius * this.radius);
        } else if (this.center.y == 0) {
            this.equationOfCircle = "(x-(" + this.center.x + "))^2 + y^2 = " + (this.radius * this.radius);
        } else {
            this.equationOfCircle = "(x-(" + this.center.x + "))^2 + (y-(" + this.center.y + "))^2 = " + (this.radius * this.radius);
        }
        ArrayList<Point> remh = new ArrayList<>();
        for (Point p : this.pointsOnCircle) remh.add(p);
        this.removedHeap.add(remh);
        this.pointsOnCircle.clear();
        this.pointsOnCircle.add(secondPoint);
    }

    public void clearPointsOnCircle() {
        ArrayList<Point> remh = new ArrayList<>();
        for (Point p : this.pointsOnCircle) remh.add(p);
        this.removedHeap.add(remh);
        this.pointsOnCircle.clear();
    }

    public void addPointonCircle(Point p) {
        if (this.isRadiusGiven) {
            double rad_check = Point.distance(p, this.center);
            if (rad_check == this.radius) {
                this.pointsOnCircle.add(p);
            } else { System.out.println("This point (" + p.x + ", " + p.y + ") doesn't lie on the circle of radius " + this.radius); }
        } else { System.out.println("Radius is not given for the circle"); }
    }

    public boolean checkPointOnCircle(Point p) {
        if (this.isRadiusGiven) {
            double rad_check = Point.distance(p, this.center);
            return rad_check == this.radius;
        } else { return false; }
    }

    public double circumference() {
        if (this.isRadiusGiven) {
            return 2 * Math.PI * this.radius;
        } else {
            System.out.println("Radius is not given for the circle");
            return (double) -1;
        }
    }

    public double area() {
        if (this.isRadiusGiven) {
            return Math.PI * this.radius * this.radius;
        } else {
            System.out.println("Radius is not given for the circle");
            return (double) -1;
        }
    }

    public ArrayList<Point> getUsedPointsOnCircle() { return this.pointsOnCircle; }
    public ArrayList<ArrayList<Point>> getRemovedHeap() { return this.removedHeap; }
    public String getEquationOfCircle() { 
        String res = "";
        if (this.isRadiusGiven) {
            res = this.equationOfCircle;
        }
        else { res = "Radius is not given for the circle"; }
        return res;
    }

    public int checkPositionOfPoint(Point p) {
        /** 
         * Returns 0 if point is outside the circle, 1 if inside, 2 if on the circle, -1 if radius is not given.
         */
        if (this.isRadiusGiven) {
            double rad_check = Point.distance(p, this.center);
            if (rad_check > this.radius) return 0;
            else if (rad_check < this.radius) return 1;
            else return 2;
        } else { return -1; }
    }

    public int checkPositionOfLine(Line2D line) {
        if (this.isRadiusGiven) {
            double distance = line.distanceFromPoint(this.center);
            if (distance > this.radius) return 0;
            else if (distance < this.radius) return 1;
            else return 2;
        } else return -1;
    }

    public ArrayList<Point> getIntersectionPointsWithLine(Line2D line) {
        if (this.isRadiusGiven) {
            ArrayList<Point> intersectionPoints = new ArrayList<>();
            double d = line.distanceFromPoint(this.center);
            if (d > this.radius) {
                return intersectionPoints;
            } else if (d == this.radius) {
                double m = line.getSlope();
                double x0 = this.center.x;
                double y0 = this.center.y;
                double x_intersect = (m*(y0 - line.getY_intercept()) + x0) / (m*m + 1);
                double y_intersect = (m*m*y0 + m*x0 + line.getY_intercept()) / (m*m + 1);
                intersectionPoints.add(new Point(x_intersect, y_intersect));
                return intersectionPoints;
            } else {
                double m = line.getSlope();
                double x0 = this.center.x;
                double y0 = this.center.y;
                double A = 1 + m*m;
                double B = -2*x0 + 2*m*(line.getY_intercept() - y0);
                double C = x0*x0 + (line.getY_intercept() - y0)*(line.getY_intercept() - y0) - this.radius*this.radius;
                double discriminant = B*B - 4*A*C;
                double sqrt_disc = Math.sqrt(discriminant);
                double x1 = (-B + sqrt_disc) / (2*A);
                double y1 = m*x1 + line.getY_intercept();
                double x2 = (-B - sqrt_disc) / (2*A);
                double y2 = m*x2 + line.getY_intercept();
                intersectionPoints.add(new Point(x1, y1));
                intersectionPoints.add(new Point(x2, y2));
                return intersectionPoints;
            }
        } else {
            System.out.println("Radius is not given for the circle");
            return null;
        }
    }

    public ArrayList<String> equationOfTangent(double slope) {
        if (this.isRadiusGiven) {
            ArrayList<String> res = new ArrayList<>();
            double c = this.radius * Math.sqrt(1 + slope*slope);
            res.add("y = " + slope + "x + " + c);
            res.add("y = " + slope + "x - " + c);
            return res;
        } else return new ArrayList<String>();
    }

    public ArrayList<String> equationOfTangent(Point p) {
        if (this.isRadiusGiven && checkPositionOfPoint(p) == 2) {
            ArrayList<String> res = new ArrayList<>();
            double slope = -(p.x - this.center.x) / (p.y - this.center.y);
            double y_intercept = p.y - slope*p.x;
            res.add("y = " + slope + "x + " + y_intercept);
            return res;
        } else {
            System.out.println("The Point (" + p.x + ", " + p.y + ") does not lie on the circle or radius is not given");
            return new ArrayList<String>();
        }
    }
}
