package geometry.shapes;

import geometry.base.Point;

public class Triangle {
    Point p1, p2, p3;
    boolean checkValid;

    Triangle(Point p1, Point p2, Point p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        double d_p1_p2 = Point.distance(p1, p2);
        double d_p2_p3 = Point.distance(p2, p3);
        double d_p3_p1 = Point.distance(p3, p1);
        if (d_p1_p2 + d_p2_p3 > d_p3_p1 &&
            d_p2_p3 + d_p3_p1 > d_p1_p2 &&
            d_p3_p1 + d_p1_p2 > d_p2_p3) {
            checkValid = true;
        } else {
            checkValid = false;
        }

        if (!checkValid) { 
            throw new IllegalArgumentException("The given points do not form a valid triangle.");
        }
    }

    public double area() {
        double a = Point.distance(p1, p2);
        double b = Point.distance(p2, p3);
        double c = Point.distance(p3, p1);
        double s = (a + b + c) / 2.0;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    public Point centroid() {
        double cx = (p1.x + p2.x + p3.x) / 3.0;
        double cy = (p1.y + p2.y + p3.y) / 3.0;
        double cz = (p1.z + p2.z + p3.z) / 3.0;
        return new Point(cx, cy, cz);
    }

    public Point orthocenter3D() {
        double ax = p1.x, ay = p1.y, az = p1.z;
        double bx = p2.x, by = p2.y, bz = p2.z;
        double cx = p3.x, cy = p3.y, cz = p3.z;

        double d = 2 * (ax * (by - cy) + bx * (cy - ay) + cx * (ay - by));
        double ux = ((ax * ax + ay * ay + az * az) * (by - cy) +
                     (bx * bx + by * by + bz * bz) * (cy - ay) +
                     (cx * cx + cy * cy + cz * cz) * (ay - by)) / d;
        double uy = ((ax * ax + ay * ay + az * az) * (cx - bx) +
                     (bx * bx + by * by + bz * bz) * (ax - cx) +
                     (cx * cx + cy * cy + cz * cz) * (bx - ax)) / d;
        double uz = ((ax * ax + ay * ay + az * az) * (bx * cy - cx * by) +
                     (bx * bx + by * by + bz * bz) * (cx * ay - ax * cy) +
                     (cx * cx + cy * cy + cz * cz) * (ax * by - bx * ay)) / d;

        return new Point(ux, uy, uz);
    }
}
