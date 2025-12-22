import geometry.shapes.Parabola;
import geometry.base.Point;

public class Main {
    public static void main(String[] args) {
        Parabola p = new Parabola(new Point(0, 1), 1, "horizontal");
        p.addPointOnParabola(new Point(1, 3));
        p.addPointOnParabola(new Point(4, 5));
        p.updateLatusRectum(2);
        p.getRemovedHeap().forEach(e -> { e.forEach(t -> System.out.println("(" + t.x + ", " + t.y + ")")); });
    }
}
