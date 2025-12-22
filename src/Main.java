import geometry.shapes.Parabola;
import geometry.base.Point;

public class Main {
    public static void main(String[] args) {
        Parabola p = new Parabola(new Point(0, 0), 1, "horizontal");
        System.out.println(p.getEquation());
    }
}
