package Settings;

import java.util.HashMap;

public class GovernanceSettings {
    public static final HashMap<Integer, Double> foodDistributionRates;
    public static final HashMap<Integer, double[]> taxRates;

    static {
        foodDistributionRates = new HashMap<>();
        foodDistributionRates.put(-2, 0d);
        foodDistributionRates.put(-1, 0.5);
        foodDistributionRates.put(0, 1d);
        foodDistributionRates.put(1, 1.5);
        foodDistributionRates.put(2, 2d);
    }

    static {
        taxRates = new HashMap<>();
        taxRates.put(-3, new double[]{-1, 7});
        taxRates.put(-2, new double[]{-0.8, 5});
        taxRates.put(-1, new double[]{-0.6, 3});
        taxRates.put(0, new double[]{0, 1});
        taxRates.put(1, new double[]{0.6, -2});
        taxRates.put(2, new double[]{0.8, -4});
        taxRates.put(3, new double[]{1, -6});
        taxRates.put(4, new double[]{1.2, -8});
        taxRates.put(5, new double[]{1.4, -12});
        taxRates.put(6, new double[]{1.6, -16});
        taxRates.put(7, new double[]{1.8, -20});
        taxRates.put(8, new double[]{2, -24});
    }
}
