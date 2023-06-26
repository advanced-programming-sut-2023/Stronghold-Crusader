package model.Game;

import Settings.GovernanceSettings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.MapAsset.Building.Building;
import model.MapAsset.Building.StorageBuilding;
import model.MapAsset.MapAsset;
import model.MapAsset.MobileUnit.MobileUnit;
import model.enums.AssetType.MapAssetType;
import model.enums.AssetType.Material;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Governance {
    private final ArrayList<Building> buildings;
    private final ArrayList<MobileUnit> units;
    private int totalPopulation;
    private IntegerProperty peasantPopulation;
    private int populationCapacity;
    private int foodRate;
    private int totalPopularity;
    private int foodPopularity;
    private int taxPopularity;
    private int taxRate;
    private int fearRate;
    private final DoubleProperty gold;

    public Governance() {
        buildings = new ArrayList<>();
        units = new ArrayList<>();
        peasantPopulation = new SimpleIntegerProperty(50);
        foodRate = -2;
        taxRate = 0;
        fearRate = 0;
        gold = new SimpleDoubleProperty(1000);
        foodPopularity = 0;
        taxPopularity = 0;
        totalPopularity = 0;
    }

    public void processPopulation() {
        calculatePopulation();
        calculatePopulationCapacity();
        int growth = getPopulationGrowth();
        peasantPopulation.set(peasantPopulation.get() + growth);
        totalPopulation += growth;
    }

    private int getPopulationGrowth() {
        int remainingHousehold = populationCapacity - totalPopulation;
        int growth = getTotalFoodAmount() / 10;
        if (totalPopularity < -20)
            growth += totalPopularity * 4;
        else
            growth += totalPopularity * 2;
        if (growth < 0)
            return Math.min(Math.max(growth, -1 * peasantPopulation.get()), remainingHousehold);
        return Math.min(growth, remainingHousehold);
    }

    private void calculatePopulation() {
        totalPopulation = peasantPopulation.get();
        for (Building building : buildings)
            totalPopulation += building.getWorkerCount();
        for (MobileUnit unit : units) {
            totalPopulation += unit.getEngineersCount();
            if (MapAssetType.getPeople().contains(unit.getType())) //if is person
                totalPopulation += 1;
        }
    }

    private void calculatePopulationCapacity() {
        populationCapacity = 0;
        for (Building building : buildings)
            populationCapacity += building.getPopulationCapacity();
    }

    public void calculatePopularity() {
        totalPopularity = getFoodPopularity() + getFearPopularity() + getReligionPopularity() +
                getTaxPopularity() + getInnPopularity();
    }

    public void payTax() {
        adjustTaxRateForPayment();
        double taxToBePayed = GovernanceSettings.taxRates.get(taxRate)[0] * totalPopulation;
        gold.set(gold.get() + taxToBePayed);
        taxPopularity += GovernanceSettings.taxRates.get(taxRate)[1];
    }

    private void adjustTaxRateForPayment() {
        while (taxRate < 0) {
            double taxToBePayed = GovernanceSettings.taxRates.get(taxRate)[0] * totalPopulation;
            if (taxToBePayed + gold.get() > 0)
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

    private void adjustFoodRateForDistribution() {
        int totalFoodAmount = getTotalFoodAmount();
        while (foodRate >= -2) {
            double foodToBeGiven = GovernanceSettings.foodDistributionRates.get(foodRate) * totalPopulation;
            if (foodToBeGiven <= totalFoodAmount)
                break;
            foodRate--;
        }
    }

    private int getTotalFoodAmount() {
        HashMap<Material, Integer> foodList = getFoodList();
        int totalAmount = 0;
        for (Integer amount : foodList.values())
            totalAmount += amount;
        return totalAmount;
    }

    private void updateFoodPopularity() {
        int change = -1;
        HashMap<Material, Integer> foodList = getFoodList();
        for (Integer foodAmount : foodList.values()) {
            if (!foodAmount.equals(-1))
                change++;
        }
        change += 4 * foodRate;
        foodPopularity += change;
    }

    public HashMap<Material, Integer> getFoodList() {
        HashMap<Material, Integer> list = new HashMap<>();
        for (Material mainFood : Material.getMainFoods())
            list.put(mainFood, getStorageStock(mainFood));
        return list;
    }

    public int getTotalPopularity() {
        return totalPopularity;
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
        int innConuter = 0;
        for (Building building : buildings) {
            if (building.getType() == MapAssetType.INN)
                innConuter++;
        }
        return innConuter * 5;
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

    public void setFearRate(int fearRate) {
        this.fearRate = fearRate;
    }

    public int getFearRate() {
        return fearRate;
    }

    public void changePeasantPopulation(int offset) {
        peasantPopulation.set(peasantPopulation.get() + offset);
    }

    public IntegerProperty getPeasantPopulation() {
        return peasantPopulation;
    }

    public int getTotalPopulation() {
        return totalPopulation;
    }

    public DoubleProperty getGold() {
        return gold;
    }

    public void changeGold(double gold) {
        this.gold.set(this.gold.get() + gold);
    }

    public void addAsset(MapAsset asset) {
        if (asset instanceof Building)
            addBuilding((Building) asset);
        else if (asset instanceof MobileUnit)
            addMobileUnit((MobileUnit) asset);
    }

    private void addMobileUnit(MobileUnit mobileUnit) {
        units.add(mobileUnit);
    }

    private void addBuilding(Building building) {
        buildings.add(building);
    }

    public void removeAsset(MapAsset asset) {
        if (asset instanceof Building)
            removeBuilding((Building) asset);
        else if (asset instanceof MobileUnit)
            removeMobileUnit((MobileUnit) asset);
    }

    private void removeBuilding(Building building) {
        buildings.remove(building);
    }

    private void removeMobileUnit(MobileUnit mobileUnit) {
        units.remove(mobileUnit);
    }

    public int getStorageStock(Material material) {
        int stock = 0;
        for (Building building : buildings) {
            if (!(building instanceof StorageBuilding))
                continue;
            StorageBuilding storageBuilding = (StorageBuilding) building;
            if (storageBuilding.hasMaterial(material))
                stock += storageBuilding.getStock(material);
        }
        return stock;
    }

    public int getStorageCapacity(Material material) {
        int capacitySum = 0;
        for (Building building : buildings) {
            if (!(building instanceof StorageBuilding))
                continue;
            StorageBuilding storageBuilding = (StorageBuilding) building;
            if (storageBuilding.hasMaterial(material))
                capacitySum += storageBuilding.getTotalCapacity();
        }
        return capacitySum;
    }

    public int getStorageRemainingCapacity(Material material) {
        int capacitySum = 0;
        for (Building building : buildings) {
            if (!(building instanceof StorageBuilding))
                continue;
            StorageBuilding storageBuilding = (StorageBuilding) building;
            if (storageBuilding.hasMaterial(material))
                capacitySum += (storageBuilding.getRemainingCapacity());
        }
        return capacitySum;
    }

    public void changeStorageStock(Material material, int offset) {
        for (Building building : buildings) {
            if (offset == 0)
                return;
            if (!(building instanceof StorageBuilding))
                continue;
            StorageBuilding storageBuilding = (StorageBuilding) building;
            if (!storageBuilding.hasMaterial(material))
                continue;
            int changeAmount;
            if (offset > 0)
                changeAmount = min(storageBuilding.getRemainingCapacity(), offset);
            else
                changeAmount = max(-1 * storageBuilding.getStock(material), offset);
            offset -= changeAmount;
            storageBuilding.changeStock(material, changeAmount);
        }
    }

    public boolean hasEnoughInStock(Material material, int amount) {
        return amount <= getStorageStock(material);
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public ArrayList<MobileUnit> getUnits() {
        return units;
    }

    public int getEngineers() {
        int result = 0;
        for (MapAsset mapAsset : units) {
            if (mapAsset.getType().equals(MapAssetType.ENGINEER)) result++;
        }
        return result;
    }


    @Override
    public String toString() {
        return "Governance{" + "\n" +
                "buildings=" + buildings + "\n" +
                ", number of units=" + units.size() + "\n" +
                ", totalPopulation=" + totalPopulation + "\n" +
                ", gold=" + gold + "\n" +
                ", total popularity" + getTotalPopularity() + "\n" +
                '}';
    }

    public boolean containsType(MapAssetType type) {
        for (MapAsset building : buildings) {
            if (building.getType().equals(type)) return true;
        }
        return false;
    }
}
