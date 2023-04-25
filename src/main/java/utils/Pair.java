package utils;

public class Pair {
    public String x, y;

    public Pair(String x, String y) {
        this.x = x;
        this.y = y;
    }

    public Pair(String x, int y) {
        this.x = x;
        this.y = String.valueOf(y);
    }

    @Override
    public String toString() {
        return "x:" + x + "|y:" + y;
    }
}