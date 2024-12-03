package io.github.planetclient.bwupgradesdisplay.utils;

import net.minecraft.util.Util;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.IOException;

public class UrlUtils {
    /**
     * Open the specified URL in the user's browser if possible, otherwise copy it to the clipboard
     * and send it to chat.
     * @param url The url to open
     */
    public static void openUrl(String url) {
        try {
            if (Util.getOSType() == Util.EnumOS.WINDOWS) {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else if (Util.getOSType() == Util.EnumOS.OSX) {
                Runtime.getRuntime().exec("open " + url);
                // Keys can get "stuck" in LWJGL on macOS when the Minecraft window loses focus.
                // Reset keyboard to solve this.
                Keyboard.destroy();
                Keyboard.create();
            } else {
                Runtime.getRuntime().exec("xdg-open " + url);
            }
            return;
        } catch (IOException | LWJGLException e) {
            e.printStackTrace();
        }
    }
}