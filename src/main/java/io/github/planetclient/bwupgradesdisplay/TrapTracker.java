package io.github.planetclient.bwupgradesdisplay;

import java.util.HashMap;
import java.util.Map;

public class TrapTracker {
    // Store taps as a map of trap name to trap count
    private static final Map<String, Integer> traps = new HashMap<>();

    public static void addOrUpdateTrap(String trapName) {
        // Increment the count if the trap already exists
        traps.put(trapName, traps.getOrDefault(trapName, 0) + 1);
    }

    // Removes a trap when it is set off. If multiple traps of the same type exist, decrement the count.
    public static void removeTrap(String trapName) {
        if (traps.containsKey(trapName)) {
            int currentCount = traps.get(trapName);
            if (currentCount > 1) {
                traps.put(trapName, currentCount - 1); // Decrement count
            } else {
                traps.remove(trapName); // Remove trap if it reaches zero
            }
        }
    }

    // Get the traps
    public static Map<String, Integer> getTraps() {
        return traps;
    }
}