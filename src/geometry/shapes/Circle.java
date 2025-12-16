package geometry.shapes;

import java.util.ArrayList;

import geometry.base.Point;

public class Circle {
    Point center;
    Point anyPoint;
    boolean isRadiusGiven;
    double radius;
    private String equationOfCircle;
    private ArrayList<Point> pointsOnCircle = new ArrayList<>();

    Circle(Point center) {
        this.center = center;
        this.radius = 0;
        this.equationOfCircle = "";
        this.isRadiusGiven = false;
    }

    Circle(Point center, Point anyPoint) {
        this.center = center;
        this.anyPoint = anyPoint;
        this.radius = Point.distance(center, anyPoint);
        this.isRadiusGiven = true;
        this.equationOfCircle = "(x-(" + center.x + "))^2 + (y-(" + center.y + "))^2 = " + (this.radius * this.radius);
        this.pointsOnCircle.add(anyPoint);
    }

    public void updateRadiusWithNewPoint(Point secondPoint) {
        this.anyPoint = secondPoint;
        this.radius = Point.distance(this.center, secondPoint);
        this.isRadiusGiven = true;
        this.equationOfCircle = "(x-(" + center.x + "))^2 + (y-(" + center.y + "))^2 = " + (this.radius * this.radius);
        this.pointsOnCircle.clear();
        this.pointsOnCircle.add(secondPoint);
    }

    public void addPointonCircle(Point p) {
        if (this.isRadiusGiven) {
            double rad_check = Point.distance(p, this.center);
            if (rad_check == this.radius) {
                this.pointsOnCircle.add(p);
            } else { IO.println("This point (" + p.x + ", " + p.y + ") doesn't lie on the circle of radius " + this.radius); }
        } else { IO.println("Radius is not given for the circle"); }
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
            IO.println("Radius is not given for the circle");
            return (double) -1;
        }
    }

    public double area() {
        if (this.isRadiusGiven) {
            return Math.PI * this.radius * this.radius;
        } else {
            IO.println("Radius is not given for the circle");
            return (double) -1;
        }
    }

    public ArrayList<Point> getUsedPointsOnCircle() {
        return this.pointsOnCircle;
    }

    public String getEquationOfCircle() { 
        String res = "";
        if (this.isRadiusGiven) { res = this.equationOfCircle; }
        else { res = "Radius is not given for the circle"; }
        return res;
    }

}
