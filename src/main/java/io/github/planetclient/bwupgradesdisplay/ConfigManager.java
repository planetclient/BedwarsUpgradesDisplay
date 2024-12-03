package io.github.planetclient.bwupgradesdisplay;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = new File("Planet Mods Settings/bwupgradesdisplay.json");

    public static Config config = new Config();

    // Load the config from file
    public static void loadConfig() {
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                config = GSON.fromJson(reader, Config.class);
                bwupgradesdisplay.displayEnabled = config.displayEnabled;
                bwupgradesdisplay.showTitles = config.showTitles;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            saveConfig(); // Save default config if no file exists
        }
    }

    // Save the config to file
    public static void saveConfig() {
        try {
            if (!CONFIG_FILE.getParentFile().exists()) {
                CONFIG_FILE.getParentFile().mkdirs();
            }
            try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
                GSON.toJson(config, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Configuration class to hold options
    public static class Config {
        public boolean displayEnabled = true; // Default value
        public boolean showTitles = true; // Default value
    }
}
