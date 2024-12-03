package io.github.planetclient.bwupgradesdisplay.gui.buttons;

import io.github.planetclient.bwupgradesdisplay.bwupgradesdisplay;
import io.github.planetclient.bwupgradesdisplay.utils.RoundedUtils;
import io.github.planetclient.bwupgradesdisplay.utils.animations.GlUtil;
import io.github.planetclient.bwupgradesdisplay.utils.animations.SimpleAnimation;
import io.github.planetclient.bwupgradesdisplay.utils.cursors.SystemCursors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import java.awt.*;

public class SettingsButton extends GuiButton {

    private SimpleAnimation animation;
    private SimpleAnimation animation2;
    boolean setPointerOnHover = false;
    boolean setNormalOnUnHover = false;

    public SettingsButton(int buttonId, int x, int y, int width, int height, String buttonText) {
        super(buttonId, x, y, width, height, buttonText);
        this.animation = new SimpleAnimation(0.0f);
        this.animation2 = new SimpleAnimation(0.0f);
    }

    public static void drawImage(final int x, final int y, final int w, final int h, final String resourceDomain, final String resourcePath) {
        final ResourceLocation resource = new ResourceLocation(resourceDomain, resourcePath);
        Minecraft.getMinecraft().getTextureManager().bindTexture(resource);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0f, 0.0f, w, h, (float)w, (float)h);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            boolean settingsEnabled = (this.displayString == "Upgrades Display" && bwupgradesdisplay.displayEnabled) || (this.displayString == "Show Titles" && bwupgradesdisplay.showTitles);

            // Draw the button background
            RoundedUtils.drawRound(this.xPosition, this.yPosition, this.width, this.height, 7, new Color(231, 231, 231));

            if(settingsEnabled) {
                this.animation.setAnimation(1.0f, 10.0);
                this.animation2.setAnimation(0.8f, 10.0);
                GlStateManager.pushMatrix();
                RoundedUtils.drawRound((float)(this.xPosition + this.width - 31), (float)(this.yPosition + 3.55f), (float)(this.width - 276), this.height - 7.0f, 5.0f, new Color(123, 63, 252));
                GlUtil.startScale((float)(this.xPosition + this.width + 278 - 326), (float)(this.yPosition + 5.5f), this.animation.getValue());
                RoundedUtils.drawRound((float)(this.xPosition + this.width + 278 - 298), this.yPosition + 5.5f, (float)(this.width - 290), this.height - 11.0f, 4.0f, new Color(70, 15, 180));
                GlStateManager.popMatrix();
                GlUtil.stopScale();
            } else {
                this.animation2.setAnimation(1.0f, 10.0);
                this.animation.setAnimation(0.8f, 10.0);
                GlStateManager.pushMatrix();
                RoundedUtils.drawRound((float)(this.xPosition + this.width - 31), (float)(this.yPosition + 3.5f), (float)(this.width - 276), this.height - 7.0f, 5.0f, new Color(221, 53, 53));
                GlUtil.startScale((float)(this.xPosition + this.width + 278 - 282), (float)(this.yPosition + 5.5f), this.animation2.getValue());
                RoundedUtils.drawRound((float)(this.xPosition + this.width + 260 - 289), this.yPosition + 5.5f, (float)(this.width - 290), this.height - 11.0f, 4.0f, new Color(77, 18, 18, 255));
                GlStateManager.popMatrix();
                GlUtil.stopScale();
            }

            // Check if hovered
            boolean isHovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;

            if(isHovered) {
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
            int textColor = isHovered ? 0xAF6FFF : 0x000000;
            // Draw the button label
            mc.fontRendererObj.drawString(this.displayString, this.xPosition + 5, this.yPosition + (this.height - 8) / 2, textColor);
        }
    }

}
