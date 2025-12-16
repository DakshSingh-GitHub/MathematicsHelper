package geometry.base;

import java.util.ArrayList;

public class Point {
    public double x, y, z;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
        this.z = 0.0;
    }

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static double distance(Point p1, Point p2) {
        double dx = p2.x - p1.x;
        double dy = p2.y - p1.y;
        double dz = p2.z - p1.z;
        return Math.sqrt(dx*dx + dy*dy + dz*dz);
    }

    public ArrayList<Double> getCoordinates2D() {
        ArrayList<Double> res = new ArrayList<>();
        res.add(this.x);
        res.add(this.y);
        return res;
    }
}
