package io.github.planetclient.bwupgradesdisplay.gui.handlers;

import io.github.planetclient.bwupgradesdisplay.gui.LoadingScreen;
import io.github.planetclient.bwupgradesdisplay.gui.SettingsGui;
import io.github.planetclient.bwupgradesdisplay.gui.buttons.PauseMenuButton;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PauseMenuHandler {

    public PauseMenuHandler() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onPauseMenuInit(GuiScreenEvent.InitGuiEvent.Post event) {
        GuiScreen gui = event.gui;
        if (gui instanceof GuiIngameMenu) {
            // Add a button to the pause menu
            event.buttonList.add(new PauseMenuButton(101, gui.width - 105, gui.height - 30,96,22, "Upgrades Display"));
        }
    }

    @SubscribeEvent
    public void onButtonPress(GuiScreenEvent.ActionPerformedEvent.Post event) {
        GuiScreen gui = event.gui;
        GuiButton button = event.button;
        if (gui instanceof GuiIngameMenu && button.id == 101) {
            // Open the settings GUI
            gui.mc.displayGuiScreen(new LoadingScreen(gui));
        }
    }
}