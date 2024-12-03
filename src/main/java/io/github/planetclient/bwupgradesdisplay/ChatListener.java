package io.github.planetclient.bwupgradesdisplay;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static io.github.planetclient.bwupgradesdisplay.UpgradeTracker.addOrUpdateUpgrade;

public class ChatListener {
    @SubscribeEvent
    public void onChatReceived(ClientChatReceivedEvent event) {
        String message = event.message.getUnformattedText();

        if (message.contains("Sending you to") || message.contains("You are now connected to") || message.contains("1st Killer") || message.contains("joined the lobby!")) {
            System.out.println("[PlanetClient/Bedwars Upgrades Display] Detected new game or lobby. Resetting upgrades.");
            UpgradeTracker.getUpgrades().clear();
            TrapTracker.getTraps().clear();
        }

        processUpgrade(message);
    }

    private void processUpgrade(String message) {
        // Process Upgrades
        if (message.contains("Sharpened Swords")) {
            UpgradeTracker.addOrUpdateUpgrade("Sharpened Swords", 0);
        } else if (message.contains("Reinforced Armor") && message.endsWith(" I")) {
            UpgradeTracker.addOrUpdateUpgrade("Reinforced Armor", 1);
        } else if (message.contains("Reinforced Armor") && message.endsWith(" II")) {
            UpgradeTracker.addOrUpdateUpgrade("Reinforced Armor", 2);
        } else if (message.contains("Reinforced Armor") && message.endsWith(" III")) {
            UpgradeTracker.addOrUpdateUpgrade("Reinforced Armor", 3);
        } else if (message.contains("Reinforced Armor") && message.endsWith(" IV")) {
            UpgradeTracker.addOrUpdateUpgrade("Reinforced Armor", 4);
        } else if (message.contains("Maniac Miner") && message.endsWith(" I")) {
            UpgradeTracker.addOrUpdateUpgrade("Maniac Miner", 1);
        } else if (message.contains("Maniac Miner") && message.endsWith(" II")) {
            UpgradeTracker.addOrUpdateUpgrade("Maniac Miner", 2);
        } else if (message.contains("Iron Forge")) {
            UpgradeTracker.addOrUpdateUpgrade("Iron Forge", 0);
        } else if (message.contains("Golden Forge")) {
            UpgradeTracker.addOrUpdateUpgrade("Golden Forge", 0);
        } else if (message.contains("Emerald Forge")) {
            UpgradeTracker.addOrUpdateUpgrade("Emerald Forge", 0);
        } else if (message.contains("Molten Forge")) {
            UpgradeTracker.addOrUpdateUpgrade("Molten Forge", 0);
        } else if (message.contains("Heal Pool")) {
            UpgradeTracker.addOrUpdateUpgrade("Heal Pool", 0);
        } else if (message.contains("Heal Pool")) {
            UpgradeTracker.addOrUpdateUpgrade("Heal Pool", 0);
        }

        // Process Traps
        if (message.contains("It's a trap!")) {
            TrapTracker.addOrUpdateTrap("It's a trap!");
        } else if (message.contains("Alarm Trap")) {
            TrapTracker.addOrUpdateTrap("Alarm Trap");
        } else if (message.contains("Counter-Offensive Trap")) {
            TrapTracker.addOrUpdateTrap("Counter-Offensive Trap");
        } else if (message.contains("Miner Fatigue Trap")) {
            TrapTracker.addOrUpdateTrap("Miner Fatigue Trap");
        }

        // Remove Traps
        if (message.contains("Miner Fatigue Trap was set off!")) {
            TrapTracker.removeTrap("Miner Fatigue Trap");
        } else if (message.contains("It's a trap! was set off!")) {
            TrapTracker.removeTrap("It's a trap!");
        } else if (message.contains("Alarm Trap was set off!")) {
            TrapTracker.removeTrap("Alarm Trap");
        } else if (message.contains("Counter-Offensive Trap was set off!")) {
            TrapTracker.removeTrap("Counter-Offensive Trap");
        }
    }
}
