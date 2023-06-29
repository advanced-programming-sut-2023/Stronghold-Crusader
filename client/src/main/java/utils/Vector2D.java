package utils;

import java.io.Serializable;

public class Vector2D implements Serializable {
    public int x;
    public int y;

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getDistance(Vector2D v2, boolean isDiagonal) {
        if(isDiagonal) return Math.max(Math.abs(v2.x - x) , Math.abs(v2.y - y));
        return Math.abs(v2.x - x) + Math.abs(v2.y - y);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector2D)
            return ((Vector2D) obj).x == this.x && ((Vector2D) obj).y == this.y;
        return false;
    }

    @Override
    public int hashCode() {
        return (x + y) * (x + y + 1) / 2 + x;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
