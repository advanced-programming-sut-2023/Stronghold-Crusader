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

    public static int translateVector2DToInt(Vector2D v2, int size) {
        return v2.x * size + v2.y;
    }

    public static Vector2D translateIntToVector2D(int location, int size) {
        return new Vector2D(location / size, location % size);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Vector2D)
            return ((Vector2D) obj).x == this.x && ((Vector2D) obj).y == this.y;
        return false;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
