package utils;

import model.enums.AssetType.Material;

public class Pair {
    public String x, y, z;

    public Pair(String x, String y) {
        this.x = x;
        this.y = y;
    }

    public Pair(String x, int y) {
        this.x = x;
        this.y = String.valueOf(y);
    }

    public Pair(Material material, int y) {
        this.x = material.name();
        this.y = String.valueOf(y);
    }

    public Pair(String x, int y, String z) {
        this.x = x;
        this.y = String.valueOf(y);
        this.z = z;
    }

    @Override
    public String toString() {
        return "x:" + x + "|y:" + y;
    }
}