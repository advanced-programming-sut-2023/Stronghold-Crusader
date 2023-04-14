package utils;

import java.io.Serializable;

public class Pair implements Serializable {
    public String x, y;

    public Pair(String x, String y) {
        this.x = x;
        this.y = y;
    }
}