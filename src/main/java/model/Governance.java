package model;

import model.MapAsset.Building.Building;

import java.util.ArrayList;

public class Governance {
    private final ArrayList<Building> buildings;
    //private final units
    private int population;
    private int popularity;
    private int foodRate;
    private int taxRate;
    private int religionRate;
    private int fearRate;
    private int Gold;
    private Storage storage;

    Governance() {
        buildings = new ArrayList<>();
    }

    public int getPopulation() {
        return population;
    }

    public int getPopularity() {
        return popularity;
    }

    public int getFoodRate() {
        return foodRate;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public int getReligionRate() {
        return religionRate;
    }

    public int getFearRate() {
        return fearRate;
    }

    public int getGold() {
        return Gold;
    }

}
