package geometry.shapes;

import java.util.ArrayList;
import geometry.base.Point;

public class Parabola {
    double a;
    String type;
    double latus_ractum;
    String equation;
    Point vertex;
    boolean isParaboalMade = false;
    ArrayList<Point> pointsOnParabola = new ArrayList<>();
    ArrayList<ArrayList<Point>> removedHeap = new ArrayList<>();

    public Parabola (Point vertex, double a, String type) {
        this.vertex = vertex;
        this.a = a;
        this.latus_ractum = 4 * a;
        this.type = type;
        this.equation = generateEquation();
        this.isParaboalMade = true;
        this.pointsOnParabola.add(vertex);
    }

    public Parabola(Point vertex) {
        this.vertex = vertex;
        this.a = 0;
        this.latus_ractum = 0;
        this.equation = "N/A";
        this.isParaboalMade = false;
        this.pointsOnParabola.add(vertex);
    }

    private String generateEquation() {
        if (type == null) {
            return "N/A";
        }

        String eqn;
        if ("vertical".equalsIgnoreCase(type)) {
            String xPart = (vertex.x == 0) ? "x^2" : "(x - " + vertex.x + ")^2";
            String yPart;
            if (vertex.y == 0) {
                yPart = latus_ractum + "y";
            } else {
                yPart = latus_ractum + "(y - " + vertex.y + ")";
            }
            eqn = xPart + " = " + yPart;
        } else if ("horizontal".equalsIgnoreCase(type)) {
            String yPart = (vertex.y == 0) ? "y^2" : "(y - " + vertex.y + ")^2";
            String xPart;
            if (vertex.x == 0) {
                xPart = latus_ractum + "x";
            } else {
                xPart = latus_ractum + "(x - " + vertex.x + ")";
            }
            eqn = yPart + " = " + xPart;
        } else {
            eqn = "N/A";
        }
        return eqn;
    }

    public void updateLatusRectum(double a) {
        this.a = a;
        this.latus_ractum = 4 * a;
        this.equation = generateEquation();
        this.isParaboalMade = true;
        ArrayList<Point> remh = new ArrayList<>();
        for (Point p : this.pointsOnParabola) remh.add(p);
        this.removedHeap.add(remh);
        Point temp_vert = new Point(this.vertex.x, this.vertex.y);
        this.pointsOnParabola.clear();
        this.pointsOnParabola.add(temp_vert);
    }

    public void clearPointsOnParabola() {
        ArrayList<Point> remh = new ArrayList<>();
        for (Point p : this.pointsOnParabola) remh.add(p);
        this.removedHeap.add(remh);
        Point temp_vert = new Point(this.vertex.x, this.vertex.y);
        this.pointsOnParabola.clear();
        this.pointsOnParabola.add(temp_vert);
    }

    public void addPointOnParabola(Point point) {
        if (this.isParaboalMade) {
            double x = point.x - this.vertex.x;
            double y = point.y - this.vertex.y;
            if (this.type.toLowerCase() == "horizontal") {
                if ((y*y - this.latus_ractum*x) == 0) this.pointsOnParabola.add(point);
                else System.err.println("This Point (" + x + ", " + y + ") cannot be added, doesn't lie on parabola");
            } else if (this.type.toLowerCase() == "vertical") {
                if ((x*x - this.latus_ractum*y) == 0) this.pointsOnParabola.add(point);
                else System.err.println("This Point (" + x + ", " + y + ") cannot be added, doesn't lie on parabola");
            }
        } else System.err.println("Parabola has not been initiated");
    }

    public int checkPositionOfPoint(Point p) {
        /** 
         * Returns 0 if point is outside the parabola, 1 if inside, 2 if on the parabola, -1 if paprabola isn't made
         */
        if (this.isParaboalMade) {
            double x = p.x - this.vertex.x;
            double y = p.y - this.vertex.y;
            if (this.type.toLowerCase() == "horizontal") {
                double s = y*y - this.latus_ractum*x;
                if (s > 0) return 0;
                else if (s < 0) return 1;
                else if (s == 0) return 2;
            } else if (this.type.toLowerCase() == "vertical") {
                double s = x*x - this.latus_ractum*y;
                if (s > 0) return 0;
                else if (s < 0) return 1;
                else if (s == 0) return 2;
            } else return -1;
        } 
        return -1;
    }

    public ArrayList<Point> getUsedPointsOnParabola() { return this.pointsOnParabola; }
    public ArrayList<ArrayList<Point>> getRemovedHeap() { return this.removedHeap; }
    public String getEquation() { return this.equation; }
}
