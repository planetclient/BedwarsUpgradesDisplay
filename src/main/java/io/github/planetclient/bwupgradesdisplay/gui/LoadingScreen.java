package io.github.planetclient.bwupgradesdisplay.gui;

import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;

public class LoadingScreen extends GuiScreen {
    private final GuiScreen nextScreen; // The screen to display after loading
    private int timer = 0; // Timer for the loading screen

    public LoadingScreen(GuiScreen nextScreen) {
        this.nextScreen = nextScreen;
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();

        // Draw loading message or animation
        String loadingText = "Loading settings...";
        drawCenteredString(this.fontRendererObj, loadingText, this.width / 2, this.height / 2 - 10, 0xFFFFFF);

        // Call the parent class drawScreen to handle additional rendering
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        timer++;

        // After a few ticks, transition to the next screen
        if (timer >= 15) {
            this.mc.displayGuiScreen(new SettingsGui(new GuiIngameMenu()));
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        // Prevent any key interactions during loading
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        // Prevent any mouse interactions during loading
    }
}