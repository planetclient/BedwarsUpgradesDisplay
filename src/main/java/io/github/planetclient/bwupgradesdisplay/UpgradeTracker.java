package io.github.planetclient.bwupgradesdisplay;

import java.util.HashMap;
import java.util.Map;

public class UpgradeTracker {
    // Store upgrades as a map of upgrade name to tier
    private static final Map<String, Integer> upgrades = new HashMap<>();

    public static void addOrUpdateUpgrade(String upgradeName, int tier) {
        // Handle forge upgrades
        if(upgradeName.contains("Forge")) {
            upgrades.keySet().removeIf(UpgradeTracker::isForgeUpgrade);
        }
        // If the upgrade exists, update the tier only if the new tier is higher
        if (upgrades.containsKey(upgradeName)) {
            int currentTier = upgrades.get(upgradeName);
            if (tier > currentTier) {
                upgrades.put(upgradeName, tier);  // Only update if the new tier is higher
            }
        } else {
            upgrades.put(upgradeName, tier);  // Add new upgrade if it doesn't exist
        }
    }

    private static boolean isForgeUpgrade(String upgradeName) {
        return upgradeName.contains("Forge");
    }

    // Get the upgrades
    public static Map<String, Integer> getUpgrades() {
        return upgrades;
    }
}