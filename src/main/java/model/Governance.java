package model;

import model.MapAsset.Building.Building;
import model.MapAsset.MobileUnit.MobileUnit;

import java.util.ArrayList;

public class Governance {
    private final Player player;
    private final ArrayList<Building> buildings;
    private final ArrayList<MobileUnit> people;
    private final ArrayList<Trade> trades;
    private int population;
    private int popularity;
    private int foodRate;
    private int taxRate;
    private int religionRate;
    private int fearRate;
    private int Gold;
    private Storage storage;

    //TODO Plater ? Governance
    Governance(Player player) {
        buildings = new ArrayList<>();
        people = new ArrayList<>();
        trades = new ArrayList<>();
        population = 50;
        popularity = -8;
        foodRate = -8;
        taxRate = 0;
        religionRate = 0;
        fearRate = 0;
        Gold = 1000;
        storage = new Storage();
        this.player = player;
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

    public Player getPlayer() {
        return player;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public void setFoodRate(int foodRate) {
        this.foodRate = foodRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    public void setReligionRate(int religionRate) {
        this.religionRate = religionRate;
    }

    public void setFearRate(int fearRate) {
        this.fearRate = fearRate;
    }

    public void setGold(int gold) {
        Gold = gold;
    }

    public void addPeople(MobileUnit mobileUnit) {
        people.add(mobileUnit);
    }

    public void addBuilding(Building building) {
        buildings.add(building);
    }

}
