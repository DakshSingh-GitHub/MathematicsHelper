import sequences.series.SquareSeries;

public class Main {
    public static void main(String[] args) {
        SquareSeries sq = new SquareSeries(1, 5);
        sq.insertNextTerm(3);
        System.out.println(sq.getSeries());
        System.out.println(sq.getNthTerm(11));
    }
}
