package model.Game;

import Settings.GovernanceSettings;
import model.MapAsset.Building.Building;
import model.MapAsset.Building.SymbolicBuilding;
import model.MapAsset.MobileUnit.MobileUnit;
import model.User.Player;
import model.enums.AssetType.MapAssetType;
import model.enums.AssetType.Material;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Governance {
    private final ArrayList<Building> buildings;
    private final ArrayList<MobileUnit> people;
    private int totalPopulation;
    private int peasantPopulation;
    private int populationCapacity;
    private int foodRate;
    private int foodPopularity;
    private int taxPopularity;
    private int taxRate;
    private int religionRate;
    private int fearRate;
    private double gold;

    public Governance(Player player) {
        buildings = new ArrayList<>();
        people = new ArrayList<>();
        totalPopulation = 50;
        peasantPopulation = 50;
        foodRate = -8;
        taxRate = 0;
        religionRate = 0;
        fearRate = 0;
        gold = 1000;
        foodPopularity = 0;
    }

    public int getPopulation() {
        return totalPopulation;
    }

    public void applyTax() {
        adjustFoodRateForDistribution();
        double taxToBePayed = GovernanceSettings.taxRates.get(taxRate)[0] * totalPopulation;
        gold += taxToBePayed;
        taxPopularity += GovernanceSettings.taxRates.get(taxRate)[1];
    }

    public void adjustTaxRateForPayment() {
        while (taxRate < 0) {
            double taxToBePayed = GovernanceSettings.taxRates.get(taxRate)[0] * totalPopulation;
            if (taxToBePayed + gold > 0)
                break;
            taxRate++;
        }
    }

    public void distributeFoods() {
        adjustFoodRateForDistribution();
        updateFoodPopularity();
        int foodToBeGiven = (int) (GovernanceSettings.foodDistributionRates.get(foodRate) * totalPopulation);
        for (Material mainFood : Material.getMainFoods()) {
            if (foodToBeGiven == 0)
                break;
            if (getStorageStock(mainFood) == 0)
                continue;
            changeStorageStock(mainFood, -1);
            foodToBeGiven--;
        }
    }

    public HashMap<Material, Integer> getFoodList() {
        HashMap<Material, Integer> list = new HashMap<>();
        for (Material mainFood : Material.getMainFoods())
            list.put(mainFood, getStorageStock(mainFood));
        return list;
    }

    private void adjustFoodRateForDistribution() {
        int totalFoodAmount = getTotalFoodAmount();
        while (foodRate >= -2) {
            double foodToBeGiven = GovernanceSettings.foodDistributionRates.get(foodRate) * totalPopulation;
            if (foodToBeGiven <= totalFoodAmount)
                break;
            foodRate--;
        }
    }

    public int getTotalFoodAmount() {
        HashMap<Material, Integer> foodList = getFoodList();
        int totalAmount = 0;
        for (Integer amount : foodList.values())
            totalAmount += amount;
        return totalAmount;
    }

    public void updateFoodPopularity() {
        int change = -1;
        HashMap<Material, Integer> foodList = getFoodList();
        for (Integer foodAmount : foodList.values()) {
            if (!foodAmount.equals(-1))
                change++;
        }
        change += 4 * foodRate;
        foodPopularity += change;
    }

    public int getPopularity() {
        return getFoodPopularity() + getFearPopularity() + getReligionPopularity() + getTaxPopularity() +
                getInnPopularity();
    }

    public int getFoodPopularity() {
        return foodPopularity;
    }

    public int getTaxPopularity() {
        return taxPopularity;
    }

    public int getReligionPopularity() {
        int sum = 0;
        for (Building building : buildings) {
            if (building.getType() == MapAssetType.CATHEDRAL || building.getType() == MapAssetType.CHURCH)
                sum += 2;
        }
        return sum;
    }

    public int getFearPopularity() {
        return -1 * fearRate;
    }

    public int getInnPopularity() {
        return 0;
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

    public int getFearRate() {
        return fearRate;
    }

    public void setFearRate(int fearRate) {
        this.fearRate = fearRate;
    }

    public double getGold() {
        return gold;
    }

    //TODO HANDLE SET GOLD PROBLEM...
    public void changeGold(double gold) {
        this.gold += gold;
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

    public boolean hasEnoughInStock(Material material, int amount){
        return amount <= getStorageStock(material);
    }

}
