package io.github.planetclient.bwupgradesdisplay.gui;

import io.github.planetclient.bwupgradesdisplay.ConfigManager;
import io.github.planetclient.bwupgradesdisplay.bwupgradesdisplay;
import io.github.planetclient.bwupgradesdisplay.gui.buttons.SettingsButton;
import io.github.planetclient.bwupgradesdisplay.utils.MouseUtils;
import io.github.planetclient.bwupgradesdisplay.utils.RoundedUtils;
import io.github.planetclient.bwupgradesdisplay.utils.UrlUtils;
import io.github.planetclient.bwupgradesdisplay.utils.cursors.SystemCursors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.io.IOException;

public class SettingsGui extends GuiScreen {
    private GuiScreen parentScreen;
    boolean setPointerOnHover = false;
    boolean setNormalOnUnHover = false;

    public SettingsGui(GuiScreen parentScreen) {
        this.parentScreen = parentScreen;
    }

    @Override
    public void initGui() {
        // Add buttons or sliders for settings here
        this.buttonList.add(new SettingsButton(0, this.width / 2 - 150, this.height / 2 - 70, 302, 20, "Upgrades Display"));
        this.buttonList.add(new SettingsButton(1, this.width / 2 - 150, this.height / 2 - 40, 302, 20, "Show Titles"));
    }

    @Override
    public void onGuiClosed() {
        ConfigManager.config.displayEnabled = bwupgradesdisplay.displayEnabled;
        ConfigManager.config.showTitles = bwupgradesdisplay.showTitles;
        ConfigManager.saveConfig();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if(MouseUtils.isInside(mouseX, mouseY, this.width / 2 - 160, this.height / 2 + 45, 320, 92)) UrlUtils.openUrl("https://planetclient.github.io/");
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:
                bwupgradesdisplay.displayEnabled = !bwupgradesdisplay.displayEnabled;
                System.out.println("[PlanetClient/Bedwars Upgrades Display] Upgrades Display Toggled!");
                break;
            case 1:
                bwupgradesdisplay.showTitles = !bwupgradesdisplay.showTitles;
                System.out.println("[PlanetClient/Bedwars Upgrades Display] Show Titles Toggled!");
                break;
            case 2:
                this.mc.displayGuiScreen(this.parentScreen);
                break;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        RoundedUtils.drawRound(0,0,this.width,this.height,0,new Color(0,0,0,48));
        RoundedUtils.drawRound(this.width / 2 - 160, this.height / 2 - 140, 320, 180,10,new Color(255,255,255));
        RoundedUtils.drawRound(this.width / 2 - 2, this.height / 2 - 120, 2, 23,2,new Color(1,1,1));
        this.fontRendererObj.drawString("§lBedwars", this.width / 2 + 12, this.height / 2 - 127, new Color(0,0,0).getRGB());
        this.fontRendererObj.drawString("§lUpgrades", this.width / 2 + 12, this.height / 2 - 113, new Color(0,0,0).getRGB());
        this.fontRendererObj.drawString("§lDisplay", this.width / 2 + 12, this.height / 2 - 99, new Color(0,0,0).getRGB());
        mc.getTextureManager().bindTexture(new ResourceLocation("bwupgrades","images/banner_light.png"));
        RoundedUtils.drawRoundTextured(this.width / 2 - 160, this.height / 2 + 45, 320, 92,10,1);

        if(MouseUtils.isInside(mouseX, mouseY, this.width / 2 - 160, this.height / 2 + 45, 320, 92)) {
            if(!setPointerOnHover) {
                SystemCursors.setCursor(SystemCursors.POINTING_HAND);
                setPointerOnHover = true;
            }
            setNormalOnUnHover = false;
        } else {
            if(!setNormalOnUnHover) {
                SystemCursors.setCursor(SystemCursors.ARROW);
                setNormalOnUnHover = true;
            }
            setPointerOnHover = false;
        }

        ResourceLocation resource = new ResourceLocation("bwupgrades", "images/planet-mods_light.png");
        Minecraft.getMinecraft().getTextureManager().bindTexture(resource);
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        Gui.drawModalRectWithCustomSizedTexture(this.width / 2 - 156, this.height / 2 - 151, 0.0f, 0.0f, 153, 86, (float)153, (float)86);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true; // Keep the game paused while in the settings screen
    }
}