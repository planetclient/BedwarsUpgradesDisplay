package io.github.planetclient.bwupgradesdisplay;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Map;

public class HUDRenderer {

    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Text event) {
        if(!ConfigManager.config.displayEnabled) {
            return;
        }
        Minecraft mc = FMLClientHandler.instance().getClient();
        int yOffset = bwupgradesdisplay.showTitles ? 20 : 10;

        if(!UpgradeTracker.getUpgrades().isEmpty() && ConfigManager.config.showTitles) {
            mc.fontRendererObj.drawStringWithShadow("§c§lUpgrades §7(" + UpgradeTracker.getUpgrades().size() + ")", 10, 10, 0xFFFFFF);
        }

        for (Map.Entry<String, Integer> entry : UpgradeTracker.getUpgrades().entrySet()) {
            String upgradeName = entry.getKey();
            int upgradeTier = entry.getValue();
            if(upgradeTier == 0) {
                mc.fontRendererObj.drawStringWithShadow("- " + upgradeName, 10, yOffset, 0xFFFFFF);
            } else {
                mc.fontRendererObj.drawStringWithShadow("- " + upgradeName + " " + upgradeTier, 10, yOffset, 0xFFFFFF);
            }
            yOffset += 10;
        }

        if(!TrapTracker.getTraps().isEmpty() && ConfigManager.config.showTitles) {
            mc.fontRendererObj.drawStringWithShadow("§c§lTraps §7(" + TrapTracker.getTraps().size() + ")", 10, yOffset + 20, 0xFFFFFF);
        }

        int yOffsetTraps = bwupgradesdisplay.showTitles ? yOffset + 30 : yOffset + 20;
        for (Map.Entry<String, Integer> entry : TrapTracker.getTraps().entrySet()) {
            String trapName = entry.getKey();
            int trapCount = entry.getValue();
            if(trapCount == 1) {
                mc.fontRendererObj.drawStringWithShadow("- " + trapName, 10, yOffsetTraps, 0xFFFFFF);
            } else {
                mc.fontRendererObj.drawStringWithShadow("- " + trapName + " " + trapCount, 10, yOffsetTraps, 0xFFFFFF);
            }
            yOffsetTraps += 10; // Increment Y position for the next trap
        }
    }
}