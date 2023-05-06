package utils;

public class Vector2D {
    public int x;
    public int y;

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getDistance(Vector2D v2) {
        return Math.abs(v2.x - x) + Math.abs(v2.y - y);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
