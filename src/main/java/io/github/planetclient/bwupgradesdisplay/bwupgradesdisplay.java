package io.github.planetclient.bwupgradesdisplay;

import io.github.planetclient.bwupgradesdisplay.gui.handlers.PauseMenuHandler;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = bwupgradesdisplay.MODID, version = bwupgradesdisplay.VERSION, useMetadata = true)
public class bwupgradesdisplay
{
    public static final String MODID = "Bedwars Upgrades Display 1.8.9";
    public static final String VERSION = "1.0";
    public static boolean displayEnabled;
    public static boolean showTitles;
    public static boolean darkMode;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigManager.loadConfig();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        System.out.println("[PlanetClient/Bedwars Upgrades Display] Initialized.");
        MinecraftForge.EVENT_BUS.register(new ChatListener());
        MinecraftForge.EVENT_BUS.register(new HUDRenderer());
        new PauseMenuHandler();
    }
}
