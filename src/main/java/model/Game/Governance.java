package model.Game;

import model.MapAsset.Building.Building;
import model.MapAsset.Building.SymbolicBuilding;
import model.MapAsset.MobileUnit.MobileUnit;
import model.User.Player;
import model.enums.AssetType.Material;

import java.util.ArrayList;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Governance {
    private final ArrayList<Building> buildings;
    private final ArrayList<MobileUnit> people;
    private int population;
    private int populationCapacity;
    private int popularity;
    private int foodRate;
    private int taxRate;
    private int religionRate;
    private int fearRate;
    private int Gold;

    public Governance(Player player) {
        buildings = new ArrayList<>();
        people = new ArrayList<>();
        population = 50;
        popularity = -8;
        foodRate = -8;
        taxRate = 0;
        religionRate = 0;
        fearRate = 0;
        Gold = 1000;
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

    public void setFoodRate(int foodRate) {
        this.foodRate = foodRate;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    public int getReligionRate() {
        return religionRate;
    }

    public void setReligionRate(int religionRate) {
        this.religionRate = religionRate;
    }

    public int getFearRate() {
        return fearRate;
    }

    public void setFearRate(int fearRate) {
        this.fearRate = fearRate;
    }

    public int getGold() {
        return Gold;
    }

    //TODO HANDLE SET GOLD PROBLEM...
    public void changeGold(int gold) {
        Gold += gold;
    }

    public void addPeople(MobileUnit mobileUnit) {
        people.add(mobileUnit);
    }

    public void addBuilding(Building building) {
        buildings.add(building);
        if (building instanceof SymbolicBuilding)
            populationCapacity += ((SymbolicBuilding) building).getPopulationAmount();
    }

    public void removeBuilding(Building building) {
        buildings.remove(building);
        if (building instanceof SymbolicBuilding)
            populationCapacity -= ((SymbolicBuilding) building).getPopulationAmount();
    }

    public int getStorageStock(Material material) {
        int stock = 0;
        for (Building building : buildings) {
            if (!(building instanceof SymbolicBuilding))
                continue;
            SymbolicBuilding symbolicBuilding = (SymbolicBuilding) building;
            if (symbolicBuilding.hasMaterial(material))
                stock += symbolicBuilding.getStock(material);
        }
        return stock;
    }

    public int getStorageCapacity(Material material) {
        int capacitySum = 0;
        for (Building building : buildings) {
            if (!(building instanceof SymbolicBuilding))
                continue;
            SymbolicBuilding symbolicBuilding = (SymbolicBuilding) building;
            if (symbolicBuilding.hasMaterial(material))
                capacitySum += symbolicBuilding.getTotalCapacity();
        }
        return capacitySum;
    }

    public int getStorageRemainingCapacity(Material material) {
        int capacitySum = 0;
        for (Building building : buildings) {
            if (!(building instanceof SymbolicBuilding))
                continue;
            SymbolicBuilding symbolicBuilding = (SymbolicBuilding) building;
            if (symbolicBuilding.hasMaterial(material))
                capacitySum += (symbolicBuilding.getRemainingCapacity());
        }
        return capacitySum;
    }

    public void changeStorageStock(Material material, int offset) {
        for (Building building : buildings) {
            if (offset == 0)
                return;
            if (!(building instanceof SymbolicBuilding))
                continue;
            SymbolicBuilding symbolicBuilding = (SymbolicBuilding) building;
            if (!symbolicBuilding.hasMaterial(material))
                continue;
            int changeAmount;
            if (offset > 0)
                changeAmount = min(symbolicBuilding.getRemainingCapacity(), offset);
            else
                changeAmount = max(-1 * symbolicBuilding.getStock(material), offset);
            offset -= changeAmount;
            symbolicBuilding.changeStock(material, changeAmount);
        }
    }

}
