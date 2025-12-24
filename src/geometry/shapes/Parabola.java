package geometry.shapes;

import java.util.ArrayList;
import geometry.base.Point;
import geometry.base.Line2D;

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

    //Returns 0 if point is outside the parabola, 1 if inside, 2 if on the parabola, -1 if paprabola isn't made
    public int checkPositionOfPoint(Point p) {
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
        } return -1;
    }

    public String equationOfTangent(double slope) {
        String eqn = "";
        if (this.isParaboalMade) {
            if (this.type.toLowerCase() == "horizontal") {
                Point intersect = new Point((this.a)/(slope*slope), (2*this.a)/slope);
                Line2D line = new Line2D(intersect, slope);
                eqn = line.LineEquations().get("Equation");
            } else if (this.type.toLowerCase() == "vertical") {
                Point intersect = new Point((2*this.a)/(slope), (this.a)/(slope*slope));
                Line2D line = new Line2D(intersect, slope);
                eqn = line.LineEquations().get("Equation");
            } else eqn = "N/A";
        } else { eqn = "N/A"; }
        return eqn;
    }

    public String equationOfTangent(Point p) {
        String eqn;
        if (this.isParaboalMade) {
            double x_shifted = p.x - this.vertex.x;
            double y_shifted = p.y - this.vertex.y;
            boolean onParabola = false;
            // A small tolerance for floating point comparisons
            double epsilon = 1e-9;

            if ("horizontal".equalsIgnoreCase(this.type)) {
                if (Math.abs(y_shifted * y_shifted - this.latus_ractum * x_shifted) < epsilon) {
                    onParabola = true;
                }
            } else if ("vertical".equalsIgnoreCase(this.type)) {
                if (Math.abs(x_shifted * x_shifted - this.latus_ractum * y_shifted) < epsilon) {
                    onParabola = true;
                }
            }

            if (!onParabola) {
                return "Point is not on the parabola.";
            }

            double A, B, C;
            if ("horizontal".equalsIgnoreCase(this.type)) {
                // Tangent for (y-k)^2 = 4a(x-h) is (y-k)(y1-k) = 2a((x-h) + (x1-h))
                // Expanding and rearranging to Ax + By + C = 0 form:
                // 2ax - (y1-k)y - 2ax1 + 4ah + k*y1 - k*k = 0
                A = 2 * this.a;
                B = -(p.y - this.vertex.y);
                C = -2 * this.a * p.x + 4 * this.a * this.vertex.x + p.y * this.vertex.y - this.vertex.y * this.vertex.y;
            } else if ("vertical".equalsIgnoreCase(this.type)) {
                // Tangent for (x-h)^2 = 4a(y-k) is (x-h)(x1-h) = 2a((y-k) + (y1-k))
                // Expanding and rearranging to Ax + By + C = 0 form:
                // (x1-h)x - 2ay - x1h + h^2 - 2ay1 + 4ak = 0
                 A = p.x - this.vertex.x;
                 B = -2 * this.a;
                 C = -this.vertex.x * p.x + this.vertex.x * this.vertex.x - 2 * this.a * p.y + 4 * this.a * this.vertex.y;
            } else {
                return "N/A";
            }

            // Build the equation string
            StringBuilder equationBuilder = new StringBuilder();
            if (Math.abs(A) > epsilon) {
                equationBuilder.append(String.format("%.2f", A)).append("x");
            }
            if (Math.abs(B) > epsilon) {
                if (equationBuilder.length() > 0) {
                     if (B > 0)  {
                        equationBuilder.append(" + ");
                     } else {
                        equationBuilder.append(" - ");
                     }
                } else if (B < 0) {
                    equationBuilder.append("-");
                }
                equationBuilder.append(String.format("%.2f", Math.abs(B))).append("y");
            }
            if (Math.abs(C) > epsilon) {
                if (equationBuilder.length() > 0) {
                    if (C > 0) {
                        equationBuilder.append(" + ");
                    } else {
                        equationBuilder.append(" - ");
                    }
                } else if (C < 0) {
                    equationBuilder.append("-");
                }
                equationBuilder.append(String.format("%.2f", Math.abs(C)));
            }
            if (equationBuilder.length() == 0) {
                return "0 = 0";
            }
            equationBuilder.append(" = 0");
            eqn = equationBuilder.toString();

        } else {
            eqn = "N/A";
        }
        return eqn;
    }

    public ArrayList<Point> getUsedPointsOnParabola() { return this.pointsOnParabola; }
    public ArrayList<ArrayList<Point>> getRemovedHeap() { return this.removedHeap; }
    public void clearRemovedHeap() { this.removedHeap.clear(); }
    public String getEquation() { return this.equation; }
}
