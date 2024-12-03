package io.github.planetclient.bwupgradesdisplay.utils.animations;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public class GlUtil
{
    private static Minecraft mc;

    static {
        GlUtil.mc = Minecraft.getMinecraft();
    }

    public static void scissor(final float x, final float y, final float x1, final float y1) {
        final int scaleFactor = getScaleFactor();
        GL11.glScissor((int)(x * scaleFactor), (int)(GlUtil.mc.displayHeight - y1 * scaleFactor), (int)((x1 - x) * scaleFactor), (int)((y1 - y) * scaleFactor));
    }

    public void glScissor(final double x, double y, final double width, final double height) {
        y += height;
        final ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        final Minecraft mc = Minecraft.getMinecraft();
        GL11.glScissor((int)(x * mc.displayWidth / scaledResolution.getScaledWidth()), (int)((scaledResolution.getScaledHeight() - y) * mc.displayHeight / scaledResolution.getScaledHeight()), (int)(width * mc.displayWidth / scaledResolution.getScaledWidth()), (int)(height * mc.displayHeight / scaledResolution.getScaledHeight()));
    }

    public static int getScaleFactor() {
        int scaleFactor = 1;
        final boolean isUnicode = GlUtil.mc.isUnicode();
        int guiScale = GlUtil.mc.gameSettings.guiScale;
        if (guiScale == 0) {
            guiScale = 1000;
        }
        while (scaleFactor < guiScale && GlUtil.mc.displayWidth / (scaleFactor + 1) >= 320 && GlUtil.mc.displayHeight / (scaleFactor + 1) >= 240) {
            ++scaleFactor;
        }
        if (isUnicode && scaleFactor % 2 != 0 && scaleFactor != 1) {
            --scaleFactor;
        }
        return scaleFactor;
    }

    public static void startScale(final float x, final float y, final float scale) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, 0.0f);
        GlStateManager.scale(scale, scale, 1.0f);
        GlStateManager.translate(-x, -y, 0.0f);
    }

    public static void startScale(final float x, final float y, final float width, final float height, final float scale) {
        GlStateManager.pushMatrix();
        GlStateManager.translate((x + (x + width)) / 2.0f, (y + (y + height)) / 2.0f, 0.0f);
        GlStateManager.scale(scale, scale, 1.0f);
        GlStateManager.translate(-(x + (x + width)) / 2.0f, -(y + (y + height)) / 2.0f, 0.0f);
    }

    public static void stopScale() {
        GlStateManager.popMatrix();
    }

    public static void startTranslate(final float x, final float y) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, 0.0f);
    }

    public static void stopTranslate() {
        GlStateManager.popMatrix();
    }
}