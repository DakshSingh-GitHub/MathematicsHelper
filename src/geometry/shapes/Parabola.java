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

    public Parabola (Point vertex, double a, String type) {
        this.vertex = vertex;
        this.a = a;
        this.latus_ractum = 4 * a;
        this.type = type;
        this.equation = generateEquation();
        this.isParaboalMade = true;
    }

    public Parabola(Point vertex) {
        this.vertex = vertex;
        this.a = 0;
        this.latus_ractum = 0;
        this.equation = "N/A";
        this.isParaboalMade = false;
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
    }

    public String getEquation() {
        return this.equation;
    }

    public ArrayList<Point> getUsedPointsOnParabola() {
        return this.pointsOnParabola;
    }
}