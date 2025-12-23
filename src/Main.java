import geometry.shapes.Parabola;
import geometry.base.Point;

public class Main {
    public static void main(String[] args) {
        Parabola p = new Parabola(new Point(0, 0), 1, "horizontal");
        p.addPointOnParabola(new Point(4, 4));
        p.addPointOnParabola(new Point(1, 2));
        System.out.println(p.checkPositionOfPoint(new Point(1, 2)));
        System.out.println(p.equationOfTangent(2));
    }
}
