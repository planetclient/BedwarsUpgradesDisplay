package io.github.planetclient.bwupgradesdisplay.gui.buttons;

import io.github.planetclient.bwupgradesdisplay.utils.RoundedUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class PauseMenuButton extends GuiButton{

    public PauseMenuButton(int buttonId, int x, int y, int width, int height, String buttonText) {
        super(buttonId, x, y, width, height, buttonText);
    }

    public static void drawImage(final int x, final int y, final int w, final int h, final String resourceDomain, final String resourcePath) {
        final ResourceLocation resource = new ResourceLocation(resourceDomain, resourcePath);
        Minecraft.getMinecraft().getTextureManager().bindTexture(resource);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0f, 0.0f, w, h, (float)w, (float)h);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            // Draw the button background
            RoundedUtils.drawRound(this.xPosition, this.yPosition, this.width, this.height, 7, new Color(255,255,255));

            // Check if hovered
            boolean isHovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            int textColor = isHovered ? 0xAF6FFF : 0x000000;

            // Draw the button label
            mc.fontRendererObj.drawString(this.displayString, this.xPosition + 5, this.yPosition + (this.height - 8) / 2, textColor);
        }
    }
}